package lowleveldesign;

import java.util.Map;

/**
 * @author harish.kumar-mbp
 * createdOn 21/12/23
 * Requirement:
 *
 */
public class InventoryManagement {
    Map<String, Product> productMap;
    Map<Location, Item> locationMap;

    public void getProducts(){

    }
    public void getProductDetail(){

    }
    public void getItems(){

    }

    public void getProductLocation(){

    }
    public void locateItem(){

    }

    public void placeItem(){

    }
    public void updateItemStatus(){

    }
}

class Product{
    String id;
    String name;
    String desc;
    String weight;
    String dimension;
    int price;


}
class User {

    public void addProduct(){

    }

    public void orderProducts(){

    }


}

class Location {
    String id;
    Size size;

}

class Item {
    String productId;
    String locationid;
    Size size;
    String id;

}
enum Status{
    Inventory, Transit, Delivered
}

enum Size {
    Small, Medium, Large
}
