package cs425.ebazaar.com.business.customersubsystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import cs425.ebazaar.com.business.externalinterfaces.CustomerProfile;
import cs425.ebazaar.com.business.externalinterfaces.DbClassCustomerProfileForTest;
import cs425.ebazaar.com.business.externalinterfaces.DbClassCustomerProfileTest;
import cs425.ebazaar.com.middleware.DbConfigProperties;
import cs425.ebazaar.com.middleware.dataaccess.DataAccessSubsystemFacade;
import cs425.ebazaar.com.middleware.exceptions.DatabaseException;
import cs425.ebazaar.com.middleware.externalinterfaces.DataAccessSubsystem;
import cs425.ebazaar.com.middleware.externalinterfaces.DbClass;
import cs425.ebazaar.com.middleware.externalinterfaces.DbConfigKey;


class DbClassCustomerProfile implements DbClass,DbClassCustomerProfileForTest {
	private static final Logger LOG = 
		Logger.getLogger(DbClassCustomerProfile.class.getPackage().getName());
	private DataAccessSubsystem dataAccessSS = 
    	new DataAccessSubsystemFacade();
    private final String READ = "Read";
    private Integer custId;
    String query;
    private String queryType;
    private CustomerProfileImpl customerProfile;
    
    CustomerProfileImpl getCustomerProfile(){
        return customerProfile;
    }
    public void readCustomerProfile(Integer custId) throws DatabaseException {
        this.custId = custId;
        queryType=READ;
        dataAccessSS.atomicRead(this);      	
    }
 
    public void buildQuery() {
    	LOG.info("Query for " + queryType + ": " + query);
        if(queryType.equals(READ)){
            buildReadQuery();
        }
    }
    
    void buildReadQuery() {
        query = "SELECT custid,fname,lname "+
                "FROM Customer "+
                "WHERE custid = "+custId;
    }
    
 
    public void populateEntity(ResultSet resultSet) throws DatabaseException {
        try {
        
            //we take the first returned row
            if(resultSet.next()){
                customerProfile = new CustomerProfileImpl(resultSet.getInt("custid"),
                								resultSet.getString("fname"),
                                               resultSet.getString("lname"));
            }
        }
        catch(SQLException e){
            throw new DatabaseException(e);
        }
    }


    public String getDbUrl() {
    	DbConfigProperties props = new DbConfigProperties();	
    	return props.getProperty(DbConfigKey.ACCOUNT_DB_URL.getVal());
 
    }

 
    public String getQuery() {
        return query;
 
    }
	@Override
	public CustomerProfile getCustomerProfileForTest() {
		return customerProfile;
	}

 
}
