package OrderBookServices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderServices implements OrderServicesImplementation { 
	  
	public List <OrdersObject> insertService(OrdersObject Order, List<OrdersObject> Orders) 
	{ 
		System.out.println("Implementing the Order Insert Service") ;
		Orders=checkedforFullyMatchedOrders(Order,Orders);	
		return Orders; 
	} 
	
	//Implementating the cancel Service.
	public List<OrdersObject> cancelService(int OrderID,List<OrdersObject>orders)
	{ 
		for (int i=0; i<orders.size();i++) 
		{ 
		    if(orders.get(i).getOrderID()  == OrderID) 
		    	orders.remove(i); 
		} 
	return orders;
	}
	
	//Displaying the OrderBook at a given time
	public void displayOrderBook(List<OrdersObject> orders) 
	{ 
		 List<OrdersObject> BidOrders = new ArrayList<OrdersObject>(); 
		 List<OrdersObject> AskOrders = new ArrayList<OrdersObject>(); 
		 BidOrders=getBidOrders(BidOrders,orders);
		 AskOrders=getAskOrders(AskOrders,orders);
		 displayBidOrders(BidOrders); 
		 displayAskOrders(AskOrders); 
		} 
		public void displayBidOrders(List<OrdersObject> bidorders) 
		{ 
			System.out.println ("--------------BID ORDERS ------------"+" \t"); 
			System.out.println("PRICE"+"\t"+"	QUANTITY "+"\t"+"  ORDERID  "+"\t"+ "   Creation  Time  " +"\t"+ 
			"  Transaction TimeStamp  "+ "\t" + "TransactionName"); 
			Collections.sort(bidorders,Collections.reverseOrder( new OrderPriceComparator())); 
		    for(OrdersObject j:bidorders)
		    { 
		    System.out.println(j.getPrice()+"\t"+"\t"+j.getQuantity()+"\t"+"\t"+j.getOrderID()+"\t"+j.getCreationTime()+
		    "\t"+j.getTransactionTimeStamp()+"\t"+j.getTransactionName());
		    } 
		}
        public void displayAskOrders(List<OrdersObject> askOrders) 
        { 
        	System.out.println("--------------ASK ORDERS---------"+"\t"); 
        	System.out.println("PRICE"+"\t"+"	QUANTITY "+"\t"+"  ORDERID  "+"\t"+ "   Creation  Time  " +"\t"+ 
        	"  Transaction TimeStamp  "+ "\t" + "TransactionName"); 
        	Collections.sort(askOrders, new OrderPriceComparator());
        	for (OrdersObject k:askOrders) 
        	{ 
        	System.out.println(k.getPrice()+"\t"+"\t"+k.getQuantity()+"\t"+"\t"+k.getOrderID()+"\t"+k.getCreationTime()+
        				    "\t"+k.getTransactionTimeStamp()+"\t"+k.getTransactionName());
        	}
        }
        
        public List<OrdersObject> getBidOrders(List<OrdersObject> BidOrders,List<OrdersObject> orders)
        {
         for(OrdersObject i:orders) 
   		  { 
   		    if(i.getSide().equalsIgnoreCase("BUY"))
   		           BidOrders .add(i);  	
          }
         return BidOrders;
        }
        
        public List<OrdersObject> getAskOrders(List<OrdersObject> AskOrders,List<OrdersObject> orders)
        {
         for(OrdersObject i:orders) 
   		  { 
   		    if(i.getSide().equalsIgnoreCase("SELL"))
   		    	AskOrders.add(i);  	
          }
         return AskOrders;
        }
        
        //Logic for Checking Matched Orders.
        public List <OrdersObject> checkedforFullyMatchedOrders(OrdersObject Order, List<OrdersObject> Orders) 
        {
          if(Order.getSide().equalsIgnoreCase("SELL"))
  		  {
        	List<OrdersObject> BidOrders = new ArrayList<OrdersObject>(); 
    		BidOrders=getBidOrders(BidOrders,Orders);
    		Collections.sort(BidOrders,Collections.reverseOrder( new OrderPriceComparator())); 
    		for(OrdersObject z:BidOrders) 
     		  { 
     		    if(z.getPrice() >= Order.getPrice())
     		    {
     		       if(z.getQuantity() <= Order.getQuantity())
     		       {
     		    	   Order.setQuantity(Order.getQuantity() - z.getQuantity());
     		    	   Orders.remove(z); 
     		       }
     		       else
     		       {
     		    	  z.setQuantity(z.getQuantity()- Order.getQuantity());
     		    	  Order.setQuantity(0);
     		       }
     		    }           	
     		  }  	
  		  }
          else
          {
        	List<OrdersObject> AskOrders = new ArrayList<OrdersObject>(); 
        	AskOrders=getAskOrders(AskOrders,Orders);
      		Collections.sort(AskOrders, new OrderPriceComparator()); 
      		for(OrdersObject y:AskOrders) 
       		  { 
       		    if(y.getPrice() <= Order.getPrice())
       		    {
       		       if(y.getQuantity() <= Order.getQuantity())
       		       {
       		    	   Order.setQuantity(Order.getQuantity() - y.getQuantity());
       		    	   Orders.remove(y); 
       		       }
       		       else
       		       {
       		    	  y.setQuantity(y.getQuantity()- Order.getQuantity());
       		    	  Order.setQuantity(0);
       		       }
       		    }           	
       		  }
          }
    	
         if(Order.getQuantity() != 0)
    	     Orders.add(Order); 
		return Orders;
        	
        }    
    
        public double GetMaxBidPrice(List<OrdersObject> orders)
        {
        	 List<OrdersObject> BidOrders = new ArrayList<OrdersObject>(); 
    		 BidOrders=getBidOrders(BidOrders,orders);
    		 OrdersObject maxSal = Collections.max(BidOrders, new OrderPriceComparator());
        	 return maxSal.getPrice();
        }
        public double GetMinAskPrice(List<OrdersObject> orders)
        {
        	 List<OrdersObject> AskOrders = new ArrayList<OrdersObject>(); 
        	 AskOrders=getAskOrders(AskOrders,orders);
    		 OrdersObject minSal = Collections.min(AskOrders, new OrderPriceComparator());
        	 return minSal.getPrice();
        }
        public void GetDistinctBidPrices(List<OrdersObject> orders)
        {
        	 List<OrdersObject> BidOrders = new ArrayList<OrdersObject>(); 
   		     BidOrders=getBidOrders(BidOrders,orders);
   		     Set<Double> distinctorders = new HashSet<>();
   	         for( OrdersObject o1: BidOrders) {
   	        	distinctorders.add(o1.getPrice());
   	             }
   	          distinctorders.forEach(System.out::println);
   		     
        }
        public void GetDistinctAskPrices(List<OrdersObject> orders)
        {
        	 List<OrdersObject> AskOrders = new ArrayList<OrdersObject>(); 
        	 AskOrders=getAskOrders(AskOrders,orders);
   		     Set<Double> distinctorders = new HashSet<>();
   	         for( OrdersObject o1: AskOrders) {
   	        	distinctorders.add(o1.getPrice());
   	             }
   	          distinctorders.forEach(System.out::println);
        }
		
}
//Class Comparator Function ..
class OrderPriceComparator implements Comparator<OrdersObject>
{
	@Override
	public int compare(OrdersObject o1, OrdersObject o2) {
		int result = o1.getPrice() < o2.getPrice() ? -1 : o1.getPrice() == o2.getPrice() ? 0 : 1;
		return result;
	}
	
}



	



