package cs425.ebazaar.com.business.externalinterfaces;

import java.util.List;

import cs425.ebazaar.com.middleware.exceptions.DatabaseException;
import cs425.ebazaar.com.middleware.externalinterfaces.DbClass;

/* Used only for testing DbClassProducts */
public interface DbClassCatalogForTest extends DbClass {
	public List<Catalog> getCatalogList();
	//public void readAllProducts() throws DatabaseException;
}