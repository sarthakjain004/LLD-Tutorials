// package Behavioural.Strategy;
//defines the rule of conversation between objects.

//strategy pattern
// defines what strategy u r going to use.

//what problem does it solve?
//example->uber app
// match the rider.
/* 
// [1] without strategy pattern
class RideMatchingService{
    public void matchRider(String riderLocation, String matchingType){
        if(matchingType.equals("NEAREST")){
            //find nearest driver strategy
        }
        else if(matchingType.equals("SURGER_PRIORITY")){
            // match rider based on surge logic. strategy
        }
        else if(matchingType.equals("AIRPORT_QUEUE")){
            // use FIFO-based airport queue logic. strategy
        }
        //if tomorrow i have 10 more stragtegies, then 10 more if else, not good ofcourse.
        // this is where strategy pattern helps.
    }
}
*/

//what is strategy pattern?
// it is a behavioural design pattern that defines a family of algorithms,
// puts each of them in a separate class and makes their objects interchangeable.
// it is about how we change the behaviour of an object at runtime without changing its class.

// we have to segregate the algorithms into standalone classes.
interface MatchingStrategy{
    void match(String riderLocation);
}

class NearestDriverStrategy implements MatchingStrategy{
    @Override
    public void match(String riderLocation){
        System.out.println("Matching nearest driver: "+riderLocation);
    }
}

class AirportQueueStrategy implements MatchingStrategy{
    @Override
    public void match(String riderLocation){
        System.out.println("Matching Airport queue driver: "+riderLocation);
    }
}
class SurgePriorityStrategy implements MatchingStrategy{
    @Override
    public void match(String riderLocation){
        System.out.println("Matching surge priority driver: "+riderLocation);
    }
}

class RideMatchingService{
    private MatchingStrategy strategy;
    public RideMatchingService(MatchingStrategy strategy){
        this.strategy = strategy;
    }
    public void setStrategy(MatchingStrategy strategy){
        this.strategy = strategy;
    }
    public void matchRider(String location){
        strategy.match(location);
    }
}

public class Main {
    public static void main(String[] args) {
        RideMatchingService rideMatchingService = new RideMatchingService(new AirportQueueStrategy());
        rideMatchingService.matchRider("Terminal-1");

        //using nearest driver and later switching to surge propotty.
        RideMatchingService rideMatchingService2 = new RideMatchingService(new NearestDriverStrategy());
        rideMatchingService2.matchRider("Downtown");
        rideMatchingService2.setStrategy(new SurgePriorityStrategy());
        rideMatchingService2.matchRider("Downtown");

        //when to use?
        // you have multi-interchangebale algos
        // want to follow OCP.
        // want to avoid if else or switch
        // want to isolate unit testing behaviour wise.
        // want to select behaviour at runtime.

        //pros
        //supports OCP
        // easy to add new behaviours
        // behavoiour changes at runtime
        // encourages compostion over inheritance

        //cons
        //can lead to many small classes.
        // client must know about all available strategies
        // overhead of using interfaces
        // slightly more complex than if-else.
        
    }
   

}
