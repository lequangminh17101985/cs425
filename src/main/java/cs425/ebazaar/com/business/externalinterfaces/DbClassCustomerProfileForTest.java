
package cs425.ebazaar.com.business.externalinterfaces;

import cs425.ebazaar.com.middleware.exceptions.DatabaseException;
import cs425.ebazaar.com.middleware.externalinterfaces.DbClass;

public interface DbClassCustomerProfileForTest extends DbClass {
    
    public CustomerProfile getCustomerProfileForTest();
    public void readCustomerProfile(Integer custId) throws DatabaseException;
    
}
