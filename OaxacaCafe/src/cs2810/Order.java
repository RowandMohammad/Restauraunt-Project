package cs2810;

import java.util.ArrayList;

public class Order {
  private ArrayList<Menu_Item> order;
  boolean payed;
  String status;
  String orderID;


  Order(ArrayList<Menu_Item> order, int tableNum, int waiterId, boolean payed, String status, String orderID) {
    this.order = order;
    this.payed = payed;
    this.status = status;
    this.orderID = orderID;

  }
  
  
  ArrayList<Menu_Item> getOrder(){
    return order;
    
  }
  

  
}
