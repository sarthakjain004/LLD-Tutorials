// package Creational.Builder;
import java.util.List;
//creational design pattern
/* [1] without builder pattern
//say developing food delivery app
// say burger meal to be in the menu. this would have-
//Bun
//Patty
// these are the mandatory things for a burger meal.

//later on more things need to be added-
// sides
//toppings
// cheese etc .
// these are options for the client side.
class BurgerMeal{
    private String bun;
    private String patty;

    public BurgerMeal(String bun, String patty){
        this.bun = bun;
        this.patty = patty;
    }
    
    //  * public BurgerMeal(String bun, String patty, String sides, List<String> toppings){
    //     this.bun = bun;
    //     this.patty = patty;
    //     // if no sides and toppings, then client has to give  null or empty list.
    //     as more options are added, the constructor will become more complex and difficult to manage.
     
}
*/

/*
// [2] with builder pattern
class BurgerMeal{
    //Required
    private final String bunType;
    private final String patty;

    //Optional
    private final boolean hasCheese;
    private final List<String> toppings;
    private final String side;
    private final String drink;

    //Private constructor to enforce use of Builder
    private BurgerMeal(BurgerBuilder builder){ // instead of multiple parameters, we use a builder object.
        this.bunType = builder.bunType;
        this.patty = builder.patty;
        this.hasCheese = builder.hasCheese;
        this.toppings = builder.toppings;
        this.side = builder.side;
        this.drink = builder.drink;
    }
    // Static Builder class
    public static class BurgerBuilder {
        //Required
        private final String bunType;
        private final String patty;

        //Optional
        private boolean hasCheese;
        private List<String> toppings;
        private String side;
        private String drink;

        public BurgerBuilder(String bunType, String patty) {
            // take the mandatory parameters in the constructor.
            this.bunType = bunType;
            this.patty = patty;
        }
        //sepratae setters for optional parameters
        public BurgerBuilder withCheese(boolean hasCheese) {
            this.hasCheese = hasCheese;
            return this; //returns the current instance of BurgerBuilder
        }

        public BurgerBuilder withToppings(List<String> toppings) {
            this.toppings = toppings;
            return this;
        }

        public BurgerBuilder withSide(String side) {
            this.side = side;
            return this;
        }

        public BurgerBuilder withDrink(String drink) {
            this.drink = drink;
            return this;
        }

        public BurgerMeal build() {
            return new BurgerMeal(this); //creating object of outer class.
        }
    }
    @Override
        public String toString() {
            return "BurgerMeal{" +
                    "bunType='" + bunType + '\'' +
                    ", patty='" + patty + '\'' +
                    ", hasCheese=" + hasCheese +
                    ", toppings=" + toppings +
                    ", side='" + side + '\'' +
                    ", drink='" + drink + '\'' +
                    '}';
        }
}
*/


/*[3] telescoping constructor anti-pattern
// telescoping constructor anti-pattern is when you have multiple constructors with different parameters.
// It makes the code hard to read and maintain, especially when there are many optional parameters.
class BurgerMeal{
    private String bunType;
    private String patty;
    private boolean hasCheese;

    //if not shift to builder pattern, then this will become complex.
    public BurgerMeal(String bunType, String patty) {
        this.bunType = bunType;
        this.patty = patty;
        hasCheese = false; // default value
    }
    // if not want null, overloaded constructor
    public BurgerMeal(String bunType, String patty, boolean hasCheese) {
        this.bunType = bunType;
        this.patty = patty;
        this.hasCheese = hasCheese;
    }
}
*/

public class Main {
    public static void main(String[] args) {
        /*[1] without builder pattern
        BurgerMeal burgermeal = new BurgerMeal("wheat", "veg");
        // what is builder pattern
        // it is a creational pattern used to construct complex objects step by step.
        // it separates the construction of a complex object from its representation
        // allowing the same construction process to create different representations.
         
        
        // problem- Burgermeal
        // * choose bun type - compulsory
        //  * add patty -  compulsory
        //  * add sides - optional
        //  * add toppings - optional
        //  * add cheese - optional
        //  * add drinks - optional
        //  * this is a complex object with many optional parameters.
        //  * if we use constructor, it will become complex and difficult to manage.
         */

        /*
        // [2] with builder pattern implementation 
        // can also have BurgerBuilder class outside the BurgerMeal class.
        //but it is better to have it inside the class because it is tightly coupled with the BurgerMeal class.
        BurgerMeal burgerMeal = new BurgerMeal.BurgerBuilder("wheat","veg").build();
        BurgerMeal burgerMeal2WithCheese = new BurgerMeal.BurgerBuilder("wheat", "veg").withCheese(true).build();
        System.out.println(burgerMeal2WithCheese);
        */

        /* [3]
        // telescoping constructor anti-pattern
        BurgerMeal burgerMeal = new BurgerMeal("wheat", "veg");
        BurgerMeal burgerMealWithCheese = new BurgerMeal("wheat", "veg", true);
        // this is not a good way to handle optional parameters.
        // But this creates a cascade of constructors that become:
        // Hard to read and write
        // Error-prone due to confusing parameter order
        // Difficult to maintain when more fields are added
        // Inflexible, as users must use parameters in a specific order
        */

        //when to use
        // Use the Builder pattern when:
        // an object has multiple fields
        // immutability is preferred. once created cannot change. see all variables are final.
        // you want readable, miantainable object creation

        //when to avoid
        // your class with simply 1-2 fields
        // you dont need object customization or immutability.


        // pros
        //avoids contructor telescoping
        // ensures immutability
        // clean, readable code
        // great for complex configurations.

        //cons
        // slightly tough to set up
        // overkill for small classes
        // separate builder class needed.

        //real world example
        // StringBuilder in Java is a great example of the Builder pattern.
        //It allows you to build strings step by step using methods like append, insert, and delete.
    }
}
