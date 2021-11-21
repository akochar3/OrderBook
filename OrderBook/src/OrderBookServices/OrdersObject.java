package OrderBookServices;

import java.util.Date;

//Order Book Object Class.
public class OrdersObject {
	
	public int OrderID;
	public String Side;
	public double Quantity;
	public double Price;
	public Date CreationTime;
	public Date TransactionTimeStamp;
	public String TransactionName;
	
	public String getTransactionName() {
		return TransactionName;
	}
	public void setTransactionName(String transactionName) {
		TransactionName = transactionName;
	}
	public int getOrderID() {
		return OrderID;
	}
	public void setOrderID(int orderID) {
		OrderID = orderID;
	}
	public String getSide() {
		return Side;
	}
	public void setSide(String side) {
		Side = side;
	}
	public double getQuantity() {
		return Quantity;
	}
	public void setQuantity(double quantity) {
		Quantity = quantity;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public Date getCreationTime() {
		return CreationTime;
	}
	public void setCreationTime(Date creationTime) {
		CreationTime = creationTime;
	}
	public Date getTransactionTimeStamp() {
		return TransactionTimeStamp;
	}
	public void setTransactionTimeStamp(Date transactionTimeStamp) {
		TransactionTimeStamp = transactionTimeStamp;
	}
}
