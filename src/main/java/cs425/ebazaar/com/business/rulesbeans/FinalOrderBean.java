package cs425.ebazaar.com.business.rulesbeans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import cs425.ebazaar.com.business.Util;
import cs425.ebazaar.com.business.exceptions.BackendException;
import cs425.ebazaar.com.business.externalinterfaces.Address;
import cs425.ebazaar.com.business.externalinterfaces.CartItem;
import cs425.ebazaar.com.business.externalinterfaces.CreditCard;
import cs425.ebazaar.com.business.externalinterfaces.DynamicBean;
import cs425.ebazaar.com.business.externalinterfaces.Product;
import cs425.ebazaar.com.business.externalinterfaces.ShoppingCart;
import cs425.ebazaar.com.business.productsubsystem.ProductSubsystemFacade;
import cs425.ebazaar.com.business.util.Pair;
import cs425.ebazaar.com.business.util.TwoKeyHashMap;



public class FinalOrderBean implements DynamicBean {
	private static final Logger LOG = Logger.getLogger(FinalOrderBean.class
			.getPackage().getName());
    
	private ShoppingCart shopCart;
	public FinalOrderBean(ShoppingCart sc){		
		shopCart = sc;
	}
	
	
	///////bean interface for shopping cart
	
	public Address getShippingAddress() {
		return shopCart.getShippingAddress();
	}
    public Address getBillingAddress(){
		return shopCart.getBillingAddress();
	}
    public CreditCard getPaymentInfo(){
		return shopCart.getPaymentInfo();
	}
    public List<CartItem> getCartItems(){
		return shopCart.getCartItems();
	}
    /** 
     * This is a collection of pairs indicating
     * the quantity requested vs quantity avail
     * for each line item in the shopping cart
     * 
     * If return value is empty, this indicates an error condition
     * @return
     */
    
    public List<Pair> getRequestedAvailableList() throws BackendException { 	
    	List<Pair> retVal = computeRequestedAvailableList(shopCart);
    	if(!shopCart.isEmpty() && retVal.isEmpty()) {
    		LOG.severe("FinalOrderBean.getRequestedAvailableList() returned an empty list");
    		throw new BackendException("Quantity pairs not found");
    		
    	}
    	return retVal;
    }
    
    /** Find a way to implement this -- either implement getProductTable or do a different way */
    public static List<Pair> computeRequestedAvailableList(ShoppingCart shoppingCart) throws BackendException {
//    	//stub
//    	Pair p1 = new Pair(2,1);
//    	Pair p2 = new Pair(3,2);
//    	List<Pair> list = Arrays.asList(p1,p2);
//    	return list;
    	
    	
    	if(shoppingCart == null) return null;
    	List<CartItem> cartItems = shoppingCart.getCartItems();
    	//HashMap<Integer,Integer> reqAvailMap = new HashMap<Integer,Integer>();
    	List<Pair> reqAvailList = new ArrayList<Pair>();
    	TwoKeyHashMap<Integer,String,Product> prodTable = (new ProductSubsystemFacade()).getProductTable();
    	CartItem nextItem = null;
    	String nextName = null;
    	String nextQuantRequested = null;
    	Integer nextQuantAvail = null;
    	Product nextProduct = null;
    	Iterator<CartItem> it = cartItems.iterator();
    	while(it.hasNext()){
    		nextItem = (CartItem)it.next();
    		nextQuantRequested = nextItem.getQuantity();
    		nextName = nextItem.getProductName();
    		nextProduct = (Product)prodTable.getValWithSecondKey(nextName);
    		nextQuantAvail = nextProduct.getQuantityAvail();
    		reqAvailList.add(new Pair(Integer.parseInt(nextQuantRequested), 
    					   nextQuantAvail));
    		
    	}
    	System.out.println(reqAvailList);
    	return reqAvailList;
    }
    
	///////////property change listener code
    private PropertyChangeSupport pcs = 
    	new PropertyChangeSupport(this);
    public void addPropertyChangeListener(PropertyChangeListener pcl){
	 	pcs.addPropertyChangeListener(pcl);
	}
	public void removePropertyChangeListener(PropertyChangeListener pcl){	
    	pcs.removePropertyChangeListener(pcl);
    }
}
