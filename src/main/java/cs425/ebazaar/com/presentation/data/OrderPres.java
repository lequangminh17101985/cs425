package cs425.ebazaar.com.presentation.data;

import java.time.format.DateTimeFormatter;

import cs425.ebazaar.com.presentation.gui.GuiConstants;
import cs425.ebazaar.com.presentation.gui.GuiUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import cs425.ebazaar.com.business.externalinterfaces.Order;
import cs425.ebazaar.com.business.ordersubsystem.OrderImpl;

public class OrderPres {
	private Order order;
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	public SimpleStringProperty orderIdProperty() {
		return new SimpleStringProperty(String.valueOf(order.getOrderId()));
	}
	public SimpleStringProperty dateProperty() {
		return new  SimpleStringProperty(
			order.getOrderDate().format(
				DateTimeFormatter.ofPattern(GuiConstants.DATE_FORMAT)));
		
	}
	public SimpleStringProperty totalPriceProperty() {
		return new SimpleStringProperty(
				String.format("%.2f", order.getTotalPrice()));
	}

	public ObservableList<OrderItemPres> getOrderItemsPres() {
		return GuiUtils.orderItemsToOrderItemsPres(order.getOrderItems());
	}
	
}
