
package cs425.ebazaar.com.business.usecasecontrol;

import java.util.List;
import java.util.logging.Logger;

import cs425.ebazaar.com.middleware.exceptions.DatabaseException;
import cs425.ebazaar.com.business.exceptions.BackendException;
import cs425.ebazaar.com.business.externalinterfaces.Catalog;
import cs425.ebazaar.com.business.externalinterfaces.Product;
import cs425.ebazaar.com.business.externalinterfaces.ProductSubsystem;
import cs425.ebazaar.com.business.productsubsystem.ProductSubsystemFacade;


public class ManageProductsController   {
    
    private static final Logger LOG = Logger.getLogger(ManageProductsController.class.getName());
    
    public List<Product> getProductsList(Catalog catalog) throws BackendException {
    	ProductSubsystem pss = new ProductSubsystemFacade();    	
    	return pss.getProductList(catalog);
    }
    
    public List<Catalog> getCatalogList() throws BackendException {
    	ProductSubsystem pss = new ProductSubsystemFacade();
    	return pss.getCatalogList();	
    }
    
    
    public void deleteProduct() {
    	//implement
    }
    
    
}
