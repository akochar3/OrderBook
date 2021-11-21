package OrderBookImplementation;

import java.util.List;

import OrderBookServices.OrderServices;
import OrderBookServices.OrdersObject;

public class CancelOrder {

	OrderServices orderService = new OrderServices(); 
	 
	public List<OrdersObject> cancelOrderfromBook(String Transaction,List<OrdersObject> orders)
	{
		int OrderId=extractOrderID(Transaction);
		orders=orderService.cancelService(OrderId, orders);
		return orders;
	}
	
	public int extractOrderID(String Trans)
	{
		String[] TransactionSplit = Trans.split(" ");
		int lengthofTransaction = TransactionSplit.length;
		String FieldValue= TransactionSplit[lengthofTransaction - 1];
		
		String[] FieldValueMap= FieldValue.split("=");
		  if(FieldValueMap[0].equals("OrderID"))
			  return Integer.parseInt(FieldValueMap[1]);
		
		return 0;
	}
}
