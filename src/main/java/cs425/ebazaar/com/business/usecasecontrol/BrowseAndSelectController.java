package cs425.ebazaar.com.business.usecasecontrol;


import java.util.List;

import cs425.ebazaar.com.presentation.data.DataUtil;
import cs425.ebazaar.com.business.RulesQuantity;
import cs425.ebazaar.com.business.exceptions.BackendException;
import cs425.ebazaar.com.business.exceptions.BusinessException;
import cs425.ebazaar.com.business.exceptions.RuleException;
import cs425.ebazaar.com.business.externalinterfaces.CartItem;
import cs425.ebazaar.com.business.externalinterfaces.Catalog;
import cs425.ebazaar.com.business.externalinterfaces.CustomerProfile;
import cs425.ebazaar.com.business.externalinterfaces.CustomerSubsystem;
import cs425.ebazaar.com.business.externalinterfaces.Product;
import cs425.ebazaar.com.business.externalinterfaces.ProductSubsystem;
import cs425.ebazaar.com.business.externalinterfaces.Rules;
import cs425.ebazaar.com.business.externalinterfaces.ShoppingCartSubsystem;
import cs425.ebazaar.com.business.productsubsystem.ProductSubsystemFacade;
import cs425.ebazaar.com.business.shoppingcartsubsystem.ShoppingCartSubsystemFacade;

public enum BrowseAndSelectController {
	INSTANCE;
	
	public void updateShoppingCartItems(List<CartItem> cartitems) {
		ShoppingCartSubsystemFacade.INSTANCE.updateShoppingCartItems(cartitems);
	}
	
	public List<CartItem> getCartItems() {
		return ShoppingCartSubsystemFacade.INSTANCE.getCartItems();
	}
	
	/** Makes saved cart live in subsystem and then returns the new list of cartitems */
	public void retrieveSavedCart() {
		ShoppingCartSubsystem shopCartSS = ShoppingCartSubsystemFacade.INSTANCE;
		
		// Saved cart was retrieved during login
		shopCartSS.makeSavedCartLive();	
	}
	
	public void runQuantityRules(Product product, String quantityRequested)
			throws RuleException, BusinessException {

		ProductSubsystem prodSS = new ProductSubsystemFacade();
		
		//find current quant avail since quantity may have changed
		//since product was first loaded into UI
		int currentQuantityAvail = prodSS.readQuantityAvailable(product);
		Rules transferObject = new RulesQuantity(currentQuantityAvail, quantityRequested);//
		transferObject.runRules();

	}
	
	public List<Catalog> getCatalogs() throws BackendException {
		ProductSubsystem pss = new ProductSubsystemFacade();
		return pss.getCatalogList();
	}
	
	public List<Product> getProducts(Catalog catalog) throws BackendException {
		ProductSubsystem pss = new ProductSubsystemFacade();
		return pss.getProductList(catalog);
	}
	public Product getProductForProductName(String name) throws BackendException {
		ProductSubsystem pss = new ProductSubsystemFacade();
		return pss.getProductFromName(name);
	}
	
	/** Assume customer is logged in */
	public CustomerProfile getCustomerProfile() {
		CustomerSubsystem cust = DataUtil.readCustFromCache();
		return cust.getCustomerProfile();
	}
}
