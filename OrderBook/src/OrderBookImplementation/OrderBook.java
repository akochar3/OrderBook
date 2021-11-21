package OrderBookImplementation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import OrderBookServices.OrderServices;
import OrderBookServices.OrdersObject;

public class OrderBook {
	
// Main Function ---
public static void main (String[] args) { 
	
   List<OrdersObject> orders = new ArrayList<OrdersObject>();
   OrderServices orderService = new OrderServices();
   System.out.println("---@@@ Welcome To OrderBook Application @@@---");
   while (true) 
    { 
	System.out.println(""); 
	System.out.println("------1.Execute Transactions 2.DisplayOrderBook 3.QueryOrderBook 4.Exit ------ "); 
	Scanner sc = new Scanner(System.in); 
	int input = sc.nextInt(); 
	switch (input) 
	  { 
	   case 1 :
		    File file=new File("src\\OrderBookImplementation\\Input.txt");  //Creates a new file instance 
		    FileReader fr=null;
		    try {
			fr = new FileReader(file);
		    } catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		    }     
		    BufferedReader br=new BufferedReader(fr);             //creates a buffering character input stream
		    String Transaction;  
		    int i=0;
		    try {
			while((Transaction=br.readLine())!=null)  
		    {  
				String [] TransactionSplit = Transaction.split (" "); 
				String IDFTransaction = TransactionSplit [1]; 
				switch (IDFTransaction)
				{ 
				case "NewOrderTrans": 
					System .out.println("Executing NewOrderTrans"); 
					NewOrder no= new NewOrder(); 
					int orderId=i+1;
					no.initParameters(orderId,Transaction,orders); 
					i++;
					break; 
				case "AmendOrderTrans" : 
					System.out.println("Executing AmendOrderTrans"); 
					AmendOrder ao = new AmendOrder(); 
					ao.amendOrdertobook(Transaction, orders); 
					break; 
				case "CancelOrderTrans" : 
					System.out.println("Executing CancelOrderTrans");
					CancelOrder co= new CancelOrder(); 
					co.cancelOrderfromBook(Transaction,orders); 
					break;
			   default:
				   System.out.println("No input matched");
				}
		    }
		    }
		    catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }  
		try {
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 break;
	   case 2: 
		   System.out.println("Display Output");
		   DisplayOrderBook dob=new DisplayOrderBook();
           dob.displayBook(orders);
           break;
	   case 3: 
		   System.out.println("Query Order Book");
		   System.out.println("------1.GetMaxBidPrice 2.GetMinAskPrice 3.DistinctBidPrices 4.DistinctAskPrices ------ "); 
		   Scanner sc1 = new Scanner(System.in); 
			int qinput = sc1.nextInt(); 
		   switch (qinput)
			{ 
			case 1:
				System.out.println("GetMaxBidPrice");
				double maxBidPrice=orderService.GetMaxBidPrice(orders);
				System.out.println("MaxBidPrice in OrderBook :- "+maxBidPrice);
				break; 
			case 2 :
				System.out.println("GetMinAskPrice");
				double minAskPrice=orderService.GetMinAskPrice(orders);
				System.out.println("MinAskPrice in OrderBook :-"+minAskPrice);
				break; 
			case 3 :  
				System.out.println("DistinctBidPrices");
				orderService.GetDistinctBidPrices(orders);
				break;
			case 4 :  
				System.out.println("DistinctAskPrices");
				orderService.GetDistinctAskPrices(orders);
				break;
		   default:
			   System.out.println("No input matched");
			}
           break;
	   case 4:
		   System.out.println("Terminating the Order Book App");
		   System.exit(0);
		   break;
	  }
    }
 }
}

	
		   
           