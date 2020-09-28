
package cs425.ebazaar.com.business.usecasecontrol;


import cs425.ebazaar.com.business.BusinessConstants;
import cs425.ebazaar.com.business.DbClassLogin;
import cs425.ebazaar.com.business.Login;
import cs425.ebazaar.com.business.SessionCache;
import cs425.ebazaar.com.business.customersubsystem.CustomerSubsystemFacade;
import cs425.ebazaar.com.business.exceptions.BackendException;
import cs425.ebazaar.com.business.exceptions.UserException;
import cs425.ebazaar.com.business.externalinterfaces.CustomerSubsystem;


public class LoginControl {
	
	//returns authorization level if authenticated
	public int authenticate(Login login) throws UserException, BackendException {
		
		DbClassLogin dbClass = new DbClassLogin(login);
        if(!dbClass.authenticate()) {
        	throw new UserException("Authentication failed for ID: " + login.getCustId());
        }
        return dbClass.getAuthorizationLevel();
        
	}
	
	public CustomerSubsystem prepareAndStoreCustomerObject(Login login, int authorizationLevel) throws BackendException {
		CustomerSubsystem customer = new CustomerSubsystemFacade();
		//need to place into SessionContext immediately since the facade will be used during
		//initialization; alternative: createAddress, createCreditCard methods
		//made to be static
		SessionCache cache = SessionCache.getInstance();
		cache.add(BusinessConstants.LOGGED_IN, Boolean.TRUE);
		cache.add(BusinessConstants.CUSTOMER, customer);
        
        //finish initialization
        customer.initializeCustomer(login.getCustId(), authorizationLevel);
        return customer;
	}
    
}
