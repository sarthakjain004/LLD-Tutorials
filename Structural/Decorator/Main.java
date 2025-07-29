// package Structural.Decorator;
import java.util.*;
// structural design pattern
//what problem does it solve?
// take example of a food delivery app.
// could be uberEats, zomato etc.
// ordering a pizza, multiple options for it, cheese,olives etc.


/* [1]
//without decorator pattern
//this is how a dev would do it:
class PlainPizza{}
class CheesePizza extends PlainPizza{}
class OlivesPizza extends PlainPizza{}
class StuffedPizza extends PlainPizza{}
class CheeseStuffedPizza extends CheesePizza{}
class CheeseOlivesPizza extends CheesePizza{}
class CheeseOlivesJalapenoPizza extends CheeseOlivesPizza{}
// explosion of classes.
// N, then 2 ^ N classes.
// similar pattern like builder pattern
// similar problem can be solved using decorator pattern.

//what is it?
// structural design pattern
// its gonna take small pieces like cheese, olives, stuffed and help us make larger things without coupling them.
// it allows us to add new behaviour to objects dynamically at runtime (not compile time) wihtout modifying their original structure.
*/

//[2] with decorator pattern

interface Pizza{ // kept minimal.
    String getDescription();
    double getCost();
}

//concrete component. we later on keep on adding decorators to this.
class MargheritaPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Margherita Pizza";
    }

    @Override
    public double getCost() {
        return 200.0; // base cost of Margherita pizza
    }
}

class PlainPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Plain Pizza";
    }

    @Override
    public double getCost() {
        return 100.0; // base cost of Plain pizza
    }
}


//cannot create an object of this class.
// abstract class which stores the base every now and then.
// every decorator class will have a base.
// the stack end must be a concrete component.
abstract class PizzaDecorator implements Pizza {
    protected Pizza pizza; // has-a relationship.
    public PizzaDecorator(Pizza pizza){
        this.pizza = pizza;
    }
    // not implementing getDescription and getCost here, as we want to add behaviour dynamically.
    // it will let the subclasses implement it.
}

class ExtraCheese extends PizzaDecorator {
    public ExtraCheese(Pizza pizza){//base pizza.
        super(pizza); //pass to pizza decorator and assign it.
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Extra Cheese"; // adding behaviour dynamically.
    }


    @Override
    public double getCost() {
        return pizza.getCost() + 40.0; // adding cost of Extra Cheese
    }
}

class Olives extends PizzaDecorator {
    public Olives(Pizza pizza){
        super(pizza);
    }

    @Override
    public String getDescription(){
        return pizza.getDescription() + ", Olives"; // adding behaviour dynamically.
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 30.0; // adding cost of Olives
    }
}

class StuffedCrust extends PizzaDecorator {
    public StuffedCrust(Pizza pizza){
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Stuffed Crust"; // adding behaviour dynamically.
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 50.0; // adding cost of Stuffed Crust
    }
}

public class Main {
    public static void main(String[] args) {
        Pizza pizza = new ExtraCheese(new MargheritaPizza());
        System.out.println(pizza.getDescription());
        System.out.println("Total Cost: " + pizza.getCost());

        Pizza plainPizza = new ExtraCheese(new PlainPizza()); //executed at runtime.
        System.out.println(plainPizza.getDescription());
        System.out.println("Total Cost: " + plainPizza.getCost());

        Pizza olivesPizza = new Olives(pizza);
        System.out.println(olivesPizza.getDescription());
        System.out.println("Total Cost: " + olivesPizza.getCost());
        // oliveCheeseMargherita
        // new olive(new ExtraCheese(new MargheritaPizza()));

        Pizza stuffedPizza = new StuffedCrust(new ExtraCheese(new MargheritaPizza()));
        System.out.println(stuffedPizza.getDescription());
        System.out.println("Total Cost: " + stuffedPizza.getCost());
        // its all about stacking decorators dynamically at runtime.
        //key takeaways:
        // abstract classes do have contructors and get executed when the subclass is instantiated.
        // each deocorator is a layer, like wrapping gift boxed, and each one just adds behavior to the one it wraps.
        // the decorator pattern works like a call stack where behaviour is accumulated as calls.

        // u can also opt for builder pattern, depends on dev.

        //when to use it?
        // u need to add responsibilities to objects dynamically and you want to add responsibilities without modifying existing code.
        // avoid explosion of subclasses.
        // want to follow OCP.
        // want reusable and composable behaviour.
        // need layered step by step enhancements.

        //pros
        // follows OCp.
        // runtime flexibility to add or remove features.
        // avoids class explosion.
        // promotes single responsibility principle(for each add on), as each decorator has a single responsibility.

        //cons
        //can result in small classes.
        // stack trace can get complicated with many decorators.
        // overhead of multiple wrapping class
        // developers must understand decorator flow.

    }
}
