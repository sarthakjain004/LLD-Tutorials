// package Behavioural.Visitor;
import java.util.*;
//example of amazon e commerce checkout
// 3 products ->physical, digital and gift card
// all with diff functionalities

/*
// [1] without visitor pattern
class PhysicalProduct {
    void printInvoice(){
        return;
    }
    double calculateShippingCost() {
        return 0.0;
    }
}

class DigitalProduct {
    void printInvoice(){
        return;
    }
    //no shipping needed.
}

class GiftCard {
    void printInvoice(){
        return;
    }
    double calculateDiscount(){ //or shippping cost.
        return 0.0;
    }
}
// later on, i might want to add more functionalities like
// warehouse cost, surge cost, multiple funcitonalities.

// bloating the class, violates SRP.
// a class shd have 1 resp.
// class will also get very huge.
// also if they all implement Item type, the have to check which instance it belongs to
// like if later on combo etc.
*/


//[2] with visitor pattern
// physicalproduct, digital product and gift card are what we call elements.

interface Item{
    void accept(ItemVisitor visitor);
}

//elements
class DigitalProduct implements Item {
    String name;
    int downloadSizeInMB;

    public DigitalProduct(String name, int downloadSizeInMB) {
        this.name = name;
        this.downloadSizeInMB = downloadSizeInMB;
    }
    @Override
    public void accept(ItemVisitor visitor) {// the element class defines an accept method.
        visitor.visit(this);
    }

}

class GiftCard implements Item {
    String code;
    double amount;
    public GiftCard(String code, double amount) {
        this.code = code;
        this.amount = amount;
    }

    @Override
    public void accept(ItemVisitor visitor) {
        visitor.visit(this);
    }

}
class PhysicalProduct implements Item {
    String name;
    double weight;
    public PhysicalProduct(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }
    @Override
    public void accept(ItemVisitor visitor) {//gets an interface
        visitor.visit(this);// which itemVisitor type? second dispatch.
    }
}
//visitor interface
interface ItemVisitor {
    void visit(DigitalProduct item);// move the logic to a class.
    void visit(GiftCard item);
    void visit(PhysicalProduct item);
    // because there are 3 elements, we have 3 visit methods.
    // if there was a combo product, then
    // void visit(ComboProduct item);
    // if there was a warehouse product, then
    // void visit(WarehouseProduct item);
}

class InvoiceVisitor implements ItemVisitor { //standalone class which takes care of invoice generation
    @Override
    public void visit(DigitalProduct item) {
        System.out.println("Generating invoice for digital product: " + item.name);
    }

    @Override
    public void visit(GiftCard item) {
        System.out.println("Generating invoice for gift card: " + item.code);
    }

    @Override
    public void visit(PhysicalProduct item) {
        System.out.println("Generating invoice for physical product: " + item.name);
    }
}

class WarehouseCostVisitor implements ItemVisitor { //standalone class which takes care of warehouse cost
    @Override
    public void visit(DigitalProduct item) {
        System.out.println("No warehouse cost for digital product: " + item.name);
    }
    @Override
    public void visit(GiftCard item) {
        System.out.println("No warehouse cost for gift card: " + item.code);
    }
    @Override
    public void visit(PhysicalProduct item) {
        System.out.println("Calculating warehouse cost for physical product: " + item.name);
    }
}

class ShippingCostVisitor implements ItemVisitor { //standalone class which takes
    @Override
    public void visit(DigitalProduct item) {
        System.out.println("No shipping cost for digital product: " + item.name);
    }

    @Override
    public void visit(GiftCard item) {
        System.out.println("No shipping cost for gift card: " + item.code);
    }

    @Override
    public void visit(PhysicalProduct item) {
        System.out.println("Calculating shipping cost for physical product: " + item.name);
    }
}

public class Main {
    public static void main(String[] args) {
        /*
        //[1] problem without visitor
        for(Item item: cart){
            if(item instanceof PhysicalProduct){
        }
        else if(item instanceof DigitalProduct){
        }
    }
        */
        // formal defn of visitor pattern
        // BDP, Behavioral Design Pattern, that lets u add new operations to existing class
        //hierarchies without modifying the classes themselves.
        // it achieves this by moving the logic of the operation into a separate class visitor.

        // the element classes define an accept/Visitor method and the visitor class deines a visit(element) method
        // for each element type.

        // key idea -> decouples operations from the object on which they operate.

        // ocncept of double dispatch is used. during runtime, it resolves the item type
        // and the itemVisitor type.
        // element and visitor.

        //[2] with visitor pattern
        List<Item> cart = new ArrayList<>();
        cart.add(new PhysicalProduct("Laptop", 2.5));
        cart.add(new DigitalProduct("E-book", 5));
        cart.add(new GiftCard("GC123", 100));

        //invoice generation
        ItemVisitor invoiceVisitor = new InvoiceVisitor();
        ItemVisitor shippingCalculator = new ShippingCostVisitor();

        for(Item item : cart){
            item.accept(invoiceVisitor);// not aware of which item. determined at runtime which to call.
            // determinig the item type during runtime
            // this was first dispatch.
            item.accept(shippingCalculator);
        }
        // when to use?
        // u have a complex object structure and want to perform unrelated operations on the elements.
        // want to add operations without modifying the element classes.
        // have to distinct types of elements and each requires different logic.
        // avoid if object structure changes frequently.

        //pros
        //follows OCP
        // clean separation of logic
        // easyto add new operations
        // helps centralize operations

        //cons
        // adding new element requires modifying the visitor interface.
        // can be an overkill for simple structures.
        // double dispatch() might be unintuitive for some
        // element and vissitor are tightly coupled.

    }    
}
