package cs425.ebazaar.com.business.externalinterfaces;

import java.util.List;

import cs425.ebazaar.com.middleware.exceptions.DatabaseException;
import cs425.ebazaar.com.middleware.externalinterfaces.DbClass;

/* Used only for testing DbClassAddress */
public interface DbClassAddressForTest extends DbClass {
	public List<Address> getAddressList();
	public void readAllAddresses(CustomerProfile custProfile) throws DatabaseException;
}
