package OrderBookImplementation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import OrderBookServices.OrderServices;
import OrderBookServices.OrdersObject;

public class NewOrder {
     OrderServices orderService = new OrderServices(); 
     public List<OrdersObject> initParameters (int OrderID,String Transaction,List<OrdersObject> orders) 
         { 
          OrdersObject order=new OrdersObject();
          order.setOrderID(OrderID); 
          order=extractValueForFields(order,Transaction); 
          orders= orderService.insertService(order,orders); 
          return orders; 
         } 
     
     public OrdersObject extractValueForFields(OrdersObject order,String Trans) 
     {   
    	 boolean q=false, p=false, s= false;  
    	 String [] TransactionSplit = Trans.split(" "); 
    	 int lengthofTransaction=TransactionSplit.length; 
    	 if(lengthofTransaction> 0) 
         {
         String FieldValue= TransactionSplit[lengthofTransaction-1]; 
         String[] FieldValueArray =FieldValue.split(","); 
         for (int i=0;i< FieldValueArray.length;i++) 
         { 
            String[] FieldValueMap=FieldValueArray[i].split("="); 
            if (FieldValueMap[0].equalsIgnoreCase("Quantity")) 
              { 
                order.setQuantity (Double.parseDouble(FieldValueMap[1])); 
                q=true ;
              } 
            if (FieldValueMap[0].equalsIgnoreCase("Price")) 
              {
                order.setPrice(Double.parseDouble(FieldValueMap[1])); 
                p=true; 
              }  
            if (FieldValueMap[0].equalsIgnoreCase("Side"))  
            {
            	order.setSide(FieldValueMap[1]); 
            	s=true;
            }
            
         }
         if(q && p && s)
         {
        	 SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        	 Date date = new Date(System.currentTimeMillis());
        	 order.setCreationTime(date);
        	 order.setTransactionTimeStamp(date);
        	 order.setTransactionName("NewOrder");
        	 System.out.println("Order Created Successfully");
         }
         else
         {
        	 System.out.println("Order Created Failed");
         }
       }
     return order;
     }
}