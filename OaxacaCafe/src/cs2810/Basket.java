package cs2810;

import java.util.ArrayList;
public class Basket {
	  MenuMain main = new MenuMain();
	  ArrayList<Menu_Item> basketItems ;

   public Basket() {
       basketItems = new  ArrayList<Menu_Item>();

   }

   public ArrayList<Menu_Item> getList() {
       return basketItems;
   }
}