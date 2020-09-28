package cs425.ebazaar.com.presentation.data;


import cs425.ebazaar.com.business.BusinessConstants;
import cs425.ebazaar.com.business.SessionCache;

import cs425.ebazaar.com.business.externalinterfaces.CustomerSubsystem;

public class DataUtil {
	
	public static boolean custIsAdmin() {
		CustomerSubsystem cust = readCustFromCache();
        return (cust != null && cust.isAdmin());
	}
	
	public static boolean isLoggedIn() {
		return (Boolean)SessionCache.getInstance().get(BusinessConstants.LOGGED_IN);
	}
	
	public static CustomerSubsystem readCustFromCache() {
		return (CustomerSubsystem)SessionCache.getInstance().get(BusinessConstants.CUSTOMER);
	}
}
