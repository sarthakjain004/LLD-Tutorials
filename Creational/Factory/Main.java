// package Creational.Factory;

/* [1]
interface Logistics{
    void send();
}

class Road implements Logistics{
    @Override
    public void send(){
        System.out.println("Sending by road logic");
    }
}
class Air implements Logistics{
    @Override
    public void send(){
        System.out.println("Sending by air logic");
    }
}

class LogisticsService{
    public void send(String mode){// mode can be road, train etc.
        // will send that item by that mode.

        //what one does if one doesnt know design patterns.
        if(mode == "Air"){
            Logistics logistics = new Air();
            // this logisticsService is dealing with object creation as well as sendig the items.
            // it should only deal with sending.
            // this object creation logic can be shifted to another class.
            logistics.send();
        }
        else if(mode == "Road"){
            Logistics logistics = new Road();
            logistics.send();
        }
    }
}
*/

/* [2] 
// Factory pattern implementation.

interface Logistics{
    void send();    
}

class Road implements Logistics{
    @Override
    public void send(){
        System.out.println("Sending by road logic");
    }
}
class Air implements Logistics{
    @Override
    public void send(){
        System.out.println("Sending by air logic");
    }
}

class LogisticsFactory {
    public static Logistics getLogistics(String mode){
        if (mode == "Road"){
            return new Road();
        }
        return new Air();
        // easily extensible, follows OCP.
        // say Train
        // else if (mode=="train") return new Train(); easy. (and create class train above)

    }
}

class LogisticsService{
    public void send(String mode){// mode can be road, train etc.
        Logistics logistics = LogisticsFactory.getLogistics(mode);
        logistics.send();
        // object creation mechanism shifted to logisticsfactory.
    }
}
*/

public class Main {
    public static void main(String[] args) {
        /* [1]
        // Factory pattern is a creational design pattern that lets you create objects without
        // telling your code exactly which class to use.
        // right now logisticsService knows what kind of ibjects im using.
        // take out the logic out of it.
        
        //why use it?
        // when the client does not knows what exact class of the object it needs.
        // it shouldnt know about the new Air and new Road.

        //it decouples object creation logic from the client code.
        // when object creation is complex and depends on some conditions.

        //example -> ask factory to create laptops for me.
        // just say macbook air, dont care how they do it.
        */

        //Pros
        // promotes loose coupling
        // enhances extensibility (OCP)
        // centralises object creation (SRP)
        // Increases flexibility
        // improves code reusability

        //cons
        // increased complexity.
        // more code overhead.

    }
}
