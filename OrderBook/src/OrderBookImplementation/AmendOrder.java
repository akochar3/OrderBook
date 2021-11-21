package OrderBookImplementation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import OrderBookServices.OrderServices;
import OrderBookServices.OrdersObject;

//Class implements the Basic Amend Functionality.
public class AmendOrder {
	OrderServices orderService=new OrderServices();
	
	public List<OrdersObject> amendOrdertobook(String Transaction,List<OrdersObject> orders)
	{
		OrdersObject order= new OrdersObject();
		OrdersObject amendOrder =new OrdersObject();
		order=extractValueforFields(order,Transaction);
		int indexofAmend= indexForOrderID(order.getOrderID(),orders);
		amendOrder= orders.get(indexofAmend);
		orders=orderService.cancelService(order.getOrderID(), orders);
		order=copyFields(order,amendOrder);
		if(order.getQuantity() < amendOrder.getQuantity())
			orders.add(indexofAmend, order);
		else
			orders.add(order);
			
	return orders;
	}
	
//Function to Extract fields from AmendOrderTrans.
	public OrdersObject extractValueforFields(OrdersObject order,String Trans)
	{
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    	 Date date = new Date(System.currentTimeMillis());
		String[] TransactionSplit = Trans.split(" ");
		int lengthofTransaction=TransactionSplit.length;
		if(lengthofTransaction > 0)
		{
			String FieldValue= TransactionSplit[lengthofTransaction-1];
			String[] FieldValueArray= FieldValue.split(",");
			for (int i=0;i< FieldValueArray.length;i++) 
	         { 
	            String[] FieldValueMap=FieldValueArray[i].split("="); 
	            if (FieldValueMap[0].equalsIgnoreCase("Quantity")) 
	              { 
	                order.setQuantity(Double.parseDouble(FieldValueMap[1]));
	              } 
	            if (FieldValueMap[0].equalsIgnoreCase("Price")) 
	              {
	                order.setPrice(Double.parseDouble(FieldValueMap[1]));
	              }  
	            if (FieldValueMap[0].equalsIgnoreCase("OrderID"))  
	            {
	            	order.setOrderID(Integer.parseInt(FieldValueMap[1]));
	            }
	        	 order.setTransactionTimeStamp(date);
	        	 order.setTransactionName("AmendOrder");
	         }
		}
	return order;
	}
	
	public int indexForOrderID(int OrderID,List<OrdersObject> orders) {
	    for (int i = 0; i < orders.size(); i++)
	        if (orders.get(i).getOrderID() == OrderID)
	            return i;
	    return -1;
	}
	
	public OrdersObject copyFields(OrdersObject o1,OrdersObject o2)
	{
		if(o1.getSide() == null)
			o1.setSide(o2.getSide());
		
		//Copying the Creation Time.
			o1.setCreationTime(o2.getCreationTime());
		return o1;
		
	}
}
