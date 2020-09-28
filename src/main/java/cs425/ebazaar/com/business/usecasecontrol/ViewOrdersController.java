
package cs425.ebazaar.com.business.usecasecontrol;

import java.util.List;

import cs425.ebazaar.com.presentation.data.OrderPres;
import cs425.ebazaar.com.presentation.data.ViewOrdersData;



/**
 * @author pcorazza
 */
public enum ViewOrdersController   {
	INSTANCE;
	public List<OrderPres> getOrders() {
		return ViewOrdersData.INSTANCE.getOrders();
	}
}
