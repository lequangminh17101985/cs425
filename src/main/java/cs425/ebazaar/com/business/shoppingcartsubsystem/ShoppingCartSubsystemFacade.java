package cs425.ebazaar.com.business.shoppingcartsubsystem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import cs425.ebazaar.com.middleware.exceptions.DatabaseException;
import cs425.ebazaar.com.business.customersubsystem.CustomerSubsystemFacade;
import cs425.ebazaar.com.business.exceptions.BackendException;
import cs425.ebazaar.com.business.externalinterfaces.Address;
import cs425.ebazaar.com.business.externalinterfaces.CartItem;
import cs425.ebazaar.com.business.externalinterfaces.CreditCard;
import cs425.ebazaar.com.business.externalinterfaces.CustomerProfile;
import cs425.ebazaar.com.business.externalinterfaces.CustomerSubsystem;
import cs425.ebazaar.com.business.externalinterfaces.DbClassShoppingCartForTest;
import cs425.ebazaar.com.business.externalinterfaces.ShoppingCart;
import cs425.ebazaar.com.business.externalinterfaces.ShoppingCartSubsystem;

public enum ShoppingCartSubsystemFacade implements ShoppingCartSubsystem {
	INSTANCE;
	
	ShoppingCartImpl liveCart = new ShoppingCartImpl(new LinkedList<CartItem>());
	ShoppingCartImpl savedCart;
	Integer shopCartId;
	CustomerProfile customerProfile;
	Logger log = Logger.getLogger(this.getClass().getPackage().getName());

	// interface methods
	public void setCustomerProfile(CustomerProfile customerProfile) {
		this.customerProfile = customerProfile;
	}
	
	public void makeSavedCartLive() {
		liveCart = savedCart;
	}
	
	public ShoppingCart getLiveCart() {
		return liveCart;
	}
	
	

	public void retrieveSavedCart() throws BackendException {
		try {
			DbClassShoppingCart dbClass = new DbClassShoppingCart();
			ShoppingCartImpl cartFound = dbClass.retrieveSavedCart(customerProfile);
			if(cartFound == null) {
				savedCart = new ShoppingCartImpl(new ArrayList<CartItem>());
			} else {
				savedCart = cartFound;
			}
		} catch(DatabaseException e) {
			throw new BackendException(e);
		}

	}
	
	public static CartItem createCartItem(String productName, String quantity,
            String totalprice) {
		try {
			return new CartItemImpl(productName, quantity, totalprice);
		} catch(BackendException e) {
			throw new RuntimeException("Can't create a cartitem because of productid lookup: " + e.getMessage());
		}
	}
	
	public void updateShoppingCartItems(List<CartItem> list) {
		liveCart.setCartItems(list);
	}
	
	public List<CartItem> getCartItems() {
		return liveCart.getCartItems();
	}


	
	public ShoppingCart getEmptyCartForTest() {
		return new ShoppingCartImpl();
	}

	
	public CartItem getEmptyCartItemForTest() {
		return new CartItemImpl();
	}

	@Override
	public void setShippingAddress(Address addr) {
		liveCart.setShipAddress(addr);
		
	}

	@Override
	public void setBillingAddress(Address addr) {
		liveCart.setBillAddress(addr);
		
	}

	@Override
	public void setPaymentInfo(CreditCard cc) {
		liveCart.setPaymentInfo(cc);
		
	}

	@Override
	public List<CartItem> getLiveCartItems() {
		return liveCart.getCartItems();
	}

	@Override
	public void saveLiveCart() throws BackendException {
		// TODO Auto-generated method stub
		try {
            DbClassShoppingCart dbclass = new DbClassShoppingCart();
            CustomerSubsystem custSubsystem = new CustomerSubsystemFacade();
            custSubsystem.setCustomerProfile(customerProfile);
            custSubsystem.loadDefaultCustomerData();
            liveCart.setBillAddress(custSubsystem.getDefaultBillingAddress());
            liveCart.setShipAddress(custSubsystem.getDefaultBillingAddress());
            liveCart.setPaymentInfo(custSubsystem.getDefaultPaymentInfo());
            dbclass.saveCart(customerProfile, liveCart);
        } catch(DatabaseException ex)  {
            throw new BackendException(ex);

        }
	}

	
	@Override
	public ShoppingCart getFullInfoLiveCart() {
        CustomerSubsystem custSubsystem = new CustomerSubsystemFacade();
        custSubsystem.setCustomerProfile(customerProfile);
        custSubsystem.loadDefaultCustomerData();
        liveCart.setBillAddress(custSubsystem.getDefaultBillingAddress());
        liveCart.setShipAddress(custSubsystem.getDefaultBillingAddress());
        liveCart.setPaymentInfo(custSubsystem.getDefaultPaymentInfo());
        return liveCart;
	}
	@Override
	public DbClassShoppingCartForTest getGenericDbClassShoppingCart() {
		return new DbClassShoppingCart();
	}

}
