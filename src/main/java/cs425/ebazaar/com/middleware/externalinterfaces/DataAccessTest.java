package cs425.ebazaar.com.middleware.externalinterfaces;

import java.sql.ResultSet;

import cs425.ebazaar.com.middleware.exceptions.DatabaseException;

public interface DataAccessTest {
	public ResultSet[] multipleInstanceQueries(String[] queries, String[] dburls) throws DatabaseException;
}
