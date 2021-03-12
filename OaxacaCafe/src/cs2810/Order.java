package cs2810;

import java.util.ArrayList;

public class Order {
  private ArrayList<Menu_Item> order;
  private int tableNum;
  private int waiterId;
  boolean payed;


  Order(ArrayList<Menu_Item> order, int tableNum, int waiterId, boolean payed) {
    this.order = order;
    this.tableNum = tableNum;
    this.waiterId = waiterId;
    this.payed = payed;

  }
  
  
  ArrayList<Menu_Item> getOrder(){
    return order;
    
  }
  

  
}
