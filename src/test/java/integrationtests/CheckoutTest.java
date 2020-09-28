package integrationtests;

import java.util.logging.Logger;

import dbsetup.DbQueries;
import cs425.ebazaar.com.business.BusinessConstants;
import cs425.ebazaar.com.business.SessionCache;
import cs425.ebazaar.com.business.customersubsystem.AddressImpl;
import cs425.ebazaar.com.business.externalinterfaces.Address;
import cs425.ebazaar.com.business.externalinterfaces.CustomerProfile;
import cs425.ebazaar.com.business.externalinterfaces.CustomerSubsystem;
import cs425.ebazaar.com.business.usecasecontrol.LoginControl;
import alltests.AllTests;
import junit.framework.TestCase;

public class CheckoutTest extends TestCase {
	static String name = "Checkout test";
	static Logger logger = Logger.getLogger(CheckoutTest.class.getName());
	
	static{
		AllTests.initializeProperties();
	}
	
	public void testDefaultShippingBilling(){
		Address[] expectedAddresses = DbQueries.readDefaultShippingBilling();
		
	
	}
}
