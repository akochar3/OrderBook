package OrderBookImplementation;

import java.util.List;

import OrderBookServices.OrderServices;
import OrderBookServices.OrdersObject;

public class DisplayOrderBook {
	OrderServices orderService = new OrderServices(); 
	
	public void displayBook(List<OrdersObject> orders)
	{
		orderService.displayOrderBook(orders);
	}
}
