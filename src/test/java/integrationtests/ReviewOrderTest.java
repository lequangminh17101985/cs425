package integrationtests;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import junit.framework.TestCase;
import cs425.ebazaar.com.middleware.exceptions.DatabaseException;
import alltests.AllTests;
import cs425.ebazaar.com.business.BusinessConstants;
import cs425.ebazaar.com.business.SessionCache;
import cs425.ebazaar.com.business.customersubsystem.CustomerSubsystemFacade;
import cs425.ebazaar.com.business.exceptions.BackendException;
import cs425.ebazaar.com.business.externalinterfaces.CustomerProfile;
import cs425.ebazaar.com.business.externalinterfaces.CustomerSubsystem;
import cs425.ebazaar.com.business.externalinterfaces.Order;
import cs425.ebazaar.com.business.externalinterfaces.OrderSubsystem;
import cs425.ebazaar.com.business.externalinterfaces.ProductSubsystem;
import cs425.ebazaar.com.business.ordersubsystem.OrderSubsystemFacade;
import cs425.ebazaar.com.business.productsubsystem.ProductSubsystemFacade;
import dbsetup.DbQueries;

public class ReviewOrderTest extends TestCase {
	
	static String name = "Review Submit Test";
	static Logger log = Logger.getLogger(ReviewOrderTest.class.getName());
	
	static {
		AllTests.initializeProperties();
	}
	
	//DatTX
	public void testSubmitOrder() {
		String[] vals = DbQueries.insertOrderRow();
		int expectedId = Integer.parseInt(vals[1]);
		List<Integer> orderIds=null;

        
		try {
			// Perform test
			CustomerSubsystem customer = new CustomerSubsystemFacade();
	        //finish initialization
	        OrderSubsystem oss = new OrderSubsystemFacade(customer.getGenericCustomerProfile());
			orderIds = oss.getOrderHistory()
					      .stream()
					      .map(cat -> cat.getOrderId())
					      .collect(Collectors.toList());
                
		}
		catch(BackendException ex){
			fail("DatabaseException: " + ex.getMessage());
		}
		finally {
			assertTrue(orderIds != null);
			boolean idFound = false;
			if(orderIds != null){
				for (int next : orderIds) {
					if(next == expectedId) {
						idFound=true;
						System.out.println(next);
						break;
					}
				}
			}
			assertTrue(idFound);
			// Clean up table
			DbQueries.deleteOrderRow(expectedId);
			
		}
	}
	public void testReviewOrder() {
		List<Order> expected = DbQueries.readOrders();
		
		//test real dbclass order
		CustomerSubsystem customer = new CustomerSubsystemFacade();
        //finish initialization
        OrderSubsystem oss = new OrderSubsystemFacade(customer.getGenericCustomerProfile());
		try {
			List<Order> found = oss.getOrderHistory();
			assertTrue(expected.size() == found.size());
		} catch(Exception e) {
			fail("Order Lists don't match");
		}
		
	}
}