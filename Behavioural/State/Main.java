// package Behavioural.State;


// what is state?
// say food delivery app
// when place order, and go to order page -> see order placed
// then moments after -> changes to order preparing
// then delivery partner picking up -> out for delivery -> delivered
// this is called change of state. these are all states.
// behaviour is changing depending on situation.

class Order {
    private String state;

    public Order(){
        this.state = "ORDER_PLACED";
    }
    public void cancelOrder(){
        if(state.equals("ORDER_PLACED") || state.equals("PREPARING")){
            state = "CANCELLED";
            System.out.println("Order has been cancelled");
        }
        else{
            System.out.println("Cannot cancel the order");
        }
    }

    public void nextState(){
        switch (state) {
            case "ORDER_PLACED":
                state = "PREPARING";
                break;
            case "PREPARING":
                state = "OUT_FOR_DELIVERY";
                break;
            case "OUT_FOR_DELIVERY":
                state = "DELIVERED";
                break;
            default:
                System.out.println("No next state from "+state);
        }
        System.out.println("Order moved to: " +state);
    }
    public String getState(){
        return state;
    }
}
//not scalable
//if add business logic like google maps etc-> very bulky.
// if introducing new state-> not good, touching core logic.

//formal defn
// lets an object change its behaviour when tis internal state changes
// helps to encapsulate state-epecific logic into separate classes.
// it also follows the OCP, states can be added without modifying the existing code.

class OrderContext {
    private OrderState currentState;
    public OrderContext(){
        this.currentState = new OrderPlacedState();
    }
    public void setState(OrderState state){
        this.currentState = state;
    }
    public void next(){
        currentState.next(this);
    }
    public void cancel(){
        currentState.cancel(this);
    }
    public String getCurrentState(){
        return currentState.getStateName();
    }
}

interface OrderState {
    void next(OrderContext context);
    void cancel(OrderContext context);
    String getStateName();
}

class OrderPlacedState implements OrderState {
    public void next(OrderContext context) {
        context.setState(new PreparingState());
        System.out.println("Order is now being prepared.");
    }

    public void cancel(OrderContext context) {
        context.setState(new CancelledState());
        System.out.println("Order has been cancelled.");
    }

    public String getStateName() {
        return "ORDER_PLACED";
    }
}

// PreparingState handles the behavior when the order is being prepared
class PreparingState implements OrderState {
    public void next(OrderContext context) {
        context.setState(new OutForDeliveryState());
        System.out.println("Order is out for delivery.");
    }

    public void cancel(OrderContext context) {
        context.setState(new CancelledState());
        System.out.println("Order has been cancelled.");
    }

    public String getStateName() {
        return "PREPARING";
    }
}

// OutForDeliveryState handles the behavior when the order is out for delivery
class OutForDeliveryState implements OrderState {
    public void next(OrderContext context) {
        context.setState(new DeliveredState());
        System.out.println("Order has been delivered.");
    }

    public void cancel(OrderContext context) {
        System.out.println("Cannot cancel. Order is out for delivery.");
    }

    public String getStateName() {
        return "OUT_FOR_DELIVERY";
    }
}

// DeliveredState handles the behavior when the order is delivered
class DeliveredState implements OrderState {
    public void next(OrderContext context) {
        System.out.println("Order is already delivered.");
    }

    public void cancel(OrderContext context) {
        System.out.println("Cannot cancel a delivered order.");
    }

    public String getStateName() {
        return "DELIVERED";
    }
}

// CancelledState handles the behavior when the order is cancelled
class CancelledState implements OrderState {
    public void next(OrderContext context) {
        System.out.println("Cancelled order cannot move to next state.");
    }

    public void cancel(OrderContext context) {
        System.out.println("Order is already cancelled.");
    }

    public String getStateName() {
        return "CANCELLED";
    }
}

public class Main {
    public static void main(String[] args) {
        OrderContext order = new OrderContext();
        System.out.println("current state: "+order.getCurrentState());
        order.next();
        order.next();
        order.cancel();
        order.next();
        order.cancel();

        //need to have a context which will interact with the states.
        //when to use it?
        // when the iobjects behav depends on its internal state.
        // state transition are well defined and finite
        // want to avoid complex if else or switch case statements
        // state transition should be explicit
        // want each state to have its own behaviour and rules.

        //state vs strategy??
        // ride matching service ->nearest car, surge, airport.
        //why not strategy?
        // intent is different. 
        // in state, states can be dependent or you can easily jump from one state to another.
        // in strategy, are completely independed and unaware of each other.
        // in state, it is about doing different things based on the state, hence results may vary

        //pros
        // clear sep of state behaviour
        // easy to add new states
        // OCP
        // avoid huge if else blocks

        //cons
        // adds more classes
        // slightly more complicated in iinitial setup
        // context needs to manage state transitions
        // requires familiarity.

        
    }
}
