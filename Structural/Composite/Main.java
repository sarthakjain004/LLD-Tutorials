// package Structural.Composite;
import java.util.*;

//structural desgin pattern.

/*
//[1] without composite pattern, what does it solve? what is the problem?
//what does it solve?
// take example of amazon checout page.
// multiple standalone products and bundles->iphone+charger+airpods.

class Product{ // we have a standalone product.
    private String name;
    private double price;

    public Product(String name, double price){
        this.price = price;
        this.name = name;
    }

    public double getPrice(){
        return price;
    }
    public void display(String indent){
        System.out.println(indent + "Product: "+name+" - Rupees"+price);
    }
}

//also allow product bundles like in e commerce websites,we get discount on bundles.
class ProductBundle{
    private String bundleName;
    private List<Product> products = new ArrayList<>();

    public ProductBundle(String bundleName){
        this.bundleName = bundleName;
    }
    public void addProduct(Product product){
        products.add(product);
    }
    public double getPrice(){
        double total = 0;
        for(Product product: products){
            total += product.getPrice();
        }
        //can put discount logic here.
        return total;
    }

    public void display(String indent){
        System.out.println(indent + "Bundle: "+bundleName);
        for(Product product: products){
            product.display(indent+" ");
        }
    }

}
*/


interface CartItem{
    double getPrice();
    void display(String indent);
}

class Product implements CartItem{
    private String name;
    private double price;

    public Product(String name, double price){
        this.price = price;
        this.name = name;
    }

    @Override
    public double getPrice(){
        return price;
    }

    @Override
    public void display(String indent){
        System.out.println(indent + "Product: "+name+" - Rupees"+price);
    }
}

class ProductBundle implements CartItem{
    private String bundleName;
    private List<CartItem> products = new ArrayList<>(); //instead of storing list of product.

    public ProductBundle(String bundleName){
        this.bundleName = bundleName;
    }
    public void addProduct(CartItem product){ //change to cartItem everywhere.
        products.add(product);
    }

    @Override
    public double getPrice(){
        double total = 0;
        for(CartItem product: products){
            total += product.getPrice();
        }
        //can put discount logic here.
        return total;
    }

    @Override
    public void display(String indent){
        System.out.println(indent + "Bundle: "+bundleName);
        for(CartItem product: products){
            product.display(indent+" ");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        /*
         //[1] without composite pattern, what problem does it solve?
        
        Product book = new Product("Atomic Habits", 499);
        Product earbuds = new Product("Earbuds", 2999);
        Product charger = new Product("Charger", 1499);
        Product phone = new Product("iPhone 15", 79999);

        ProductBundle iphoneCombo = new ProductBundle("iPhone Essentials Combo");
        iphoneCombo.addProduct(phone);
        iphoneCombo.addProduct(earbuds);
        iphoneCombo.addProduct(charger);

        ProductBundle schoolKit = new ProductBundle("School Kit");
        schoolKit.addProduct(new Product("Notebook Pack", 399));
        schoolKit.addProduct(new Product("Pen Set", 199));
        schoolKit.addProduct(new Product("Highlighter", 99));

        List<Object> cart = new ArrayList<>();// not recommended.
        // not good for industry level code.
        // have to use object since i have standalone object and bundle object.
        cart.add(book); //objects are of two different types.
        cart.add(iphoneCombo);
        cart.add(schoolKit);

        System.out.println("Your cart without Composite Pattern");
        //cant reference Object.getPrice()......
        double total = 0;
        for(Object item: cart){
            if(item instanceof Product){ // explicitly check if instance is of which type.
                // need to typecast it to Product type.
                // cant do .getPrice() on Object type.
                // NOT RECOMMENDED.
                Product product = (Product) item;
                product.display(" ");
                total += product.getPrice();
            }
            else if(item instanceof ProductBundle){
                ProductBundle productBundle = (ProductBundle) item;
                productBundle.display(" ");
                total += productBundle.getPrice();
            }
        }
        */

        //formal defn
        // structural design pattern,
        // lets u compose objects into tree structures to represent
        // part whole hierarchies.
        // you can treat a group of objects just like you would treat a single object.
        // when the client should treat both of them equally (group or single product.)
        // like here Product and productBundle both are equal.

        
        CartItem book = new Product("Atomic Habits", 499);
        CartItem earbuds = new Product("Earbuds", 2999);
        CartItem charger = new Product("Charger", 1499);
        CartItem phone = new Product("iPhone 15", 79999);

        ProductBundle iphoneCombo = new ProductBundle("iPhone Essentials Combo");//can also replace ProductBundle with CartItem.
        // but then cant use .addProduct as cartitem doesnt have that method.
        // hence initially have it as Productbundle.
        iphoneCombo.addProduct(phone);
        iphoneCombo.addProduct(earbuds);
        iphoneCombo.addProduct(charger);

        ProductBundle schoolKit = new ProductBundle("School Kit");
        schoolKit.addProduct(new Product("Notebook Pack", 399));
        schoolKit.addProduct(new Product("Pen Set", 199));
        schoolKit.addProduct(new Product("Highlighter", 99));

        List<CartItem> cart = new ArrayList<>();
        cart.add(book); 
        cart.add(iphoneCombo);
        cart.add(schoolKit);

        System.out.println("Your Amazon Cart: ");
        double total = 0;
        for(CartItem item: cart){
            item.display(" ");
            total += item.getPrice();
        }
        System.out.println("Total: "+total);

        //what is a leaf and what is a composite?
        //Product is leaf and prodcutbundle composite.

        //when to use?
        // u have hierarchial structure. say google drive or folder.
        // want to treat individual and groups in the same way.
        // want to avoid client side logic to differentiate leaf and composite.


        //pros
        // uniformity
        // extensible
        //cleaner client code
        // supports OCP.

        //cons
        // violates SRP on scale.
        //overkill for flat and simple structures.
        // in tightly regulated systems,
        // uniform treatment can hide important distinctions between item types.

    }
}
