package OrderBookServices;

import java.util.List;

public interface OrderServicesImplementation {
	
	public List<OrdersObject> insertService(OrdersObject order,List<OrdersObject> orders);
	public List<OrdersObject> cancelService(int OrderID,List<OrdersObject> orders);
	void displayOrderBook(List<OrdersObject> orders);

}
