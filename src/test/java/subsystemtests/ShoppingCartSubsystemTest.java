package subsystemtests;

import java.util.logging.Logger;

import dbsetup.DbQueries;
import cs425.ebazaar.com.business.customersubsystem.CustomerSubsystemFacade;
import cs425.ebazaar.com.business.exceptions.BackendException;
import cs425.ebazaar.com.business.externalinterfaces.CustomerProfile;
import cs425.ebazaar.com.business.externalinterfaces.CustomerSubsystem;
import cs425.ebazaar.com.business.externalinterfaces.ShoppingCart;
import cs425.ebazaar.com.business.externalinterfaces.ShoppingCartSubsystem;
import cs425.ebazaar.com.business.shoppingcartsubsystem.ShoppingCartSubsystemFacade;
import alltests.AllTests;
import junit.framework.TestCase;

public class ShoppingCartSubsystemTest extends TestCase {
	static String name = "ShoppingCart Subsystem Test";
	static Logger log = Logger.getLogger(ShoppingCartSubsystem.class.getName());
	
	static{
		AllTests.initializeProperties();
	}
	
	public void testMakeSavedCartLive(){
		ShoppingCart expected = DbQueries.readSavedCart();
		
		ShoppingCartSubsystem scs = ShoppingCartSubsystemFacade.INSTANCE;
		
		CustomerSubsystem css = new CustomerSubsystemFacade();
		CustomerProfile custProfile = css.getGenericCustomerProfile();
		scs.setCustomerProfile(custProfile);
		try {
			scs.retrieveSavedCart();
		} catch (BackendException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scs.makeSavedCartLive();
		ShoppingCart found = scs.getLiveCart();
		
		assertEquals(expected.getCartItems().size(), found.getCartItems().size());
	}

}
