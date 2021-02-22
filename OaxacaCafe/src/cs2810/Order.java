package cs2810;

import java.util.ArrayList;

public class Order {
  private ArrayList<Menu_Item> order;
  private int tableNum;
  private int waiterId;

  Order(ArrayList<Menu_Item> order, int tableNum, int waiterId) {
    this.order = order;
    this.tableNum = tableNum;
    this.waiterId = waiterId;
  }
  
  
  ArrayList<Menu_Item> getOrder(){
    return order;
    
  }
}
