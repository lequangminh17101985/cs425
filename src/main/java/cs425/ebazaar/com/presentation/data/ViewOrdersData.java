package cs425.ebazaar.com.presentation.data;

import java.util.ArrayList;
import java.util.List;

import cs425.ebazaar.com.business.BusinessConstants;
import cs425.ebazaar.com.business.SessionCache;
import cs425.ebazaar.com.business.Util;
import cs425.ebazaar.com.business.exceptions.BackendException;
import cs425.ebazaar.com.business.externalinterfaces.CustomerSubsystem;
import cs425.ebazaar.com.business.externalinterfaces.Order;
import cs425.ebazaar.com.business.externalinterfaces.OrderSubsystem;
import cs425.ebazaar.com.business.ordersubsystem.OrderImpl;
import cs425.ebazaar.com.business.ordersubsystem.OrderSubsystemFacade;

public enum ViewOrdersData {
	INSTANCE;
	private OrderPres selectedOrder;
	public OrderPres getSelectedOrder() {
		return selectedOrder;
	}
	public void setSelectedOrder(OrderPres so) {
		selectedOrder = so;
	}
	
	public List<OrderPres> getOrders() {
		try {
			SessionCache cache = SessionCache.getInstance();
			CustomerSubsystem customerSub = (CustomerSubsystem) cache.get(BusinessConstants.CUSTOMER);
			OrderSubsystem orderSub = new OrderSubsystemFacade(customerSub.getCustomerProfile());
//			List<Order> orders = customerSub.getOrderHistory();
			List<Order> orders = orderSub.getOrderHistory();
			/*List<OrderPres> orderPresList = new ArrayList<OrderPres>();
			for (int i = 0 ; i <orders.size(); i++) {
				OrderPres orderPres = new OrderPres();
				orderPres.setOrder((OrderImpl)orders.get(i));
				orderPresList.add(orderPres);
			}*/
			return Util.orderListToOrderPresList(orders);
		} catch (BackendException e) {
			e.printStackTrace();
			return null;
		}
	}
}
