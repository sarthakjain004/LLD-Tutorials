// package Behavioural.ChainOfResponsibility;


// different classes with different responsibilities are chained together
// customer
// encounter AI -> customer support -> admin support -> trigger refund/replacement et request.
// chain of responsibility that passes through.

/*
// [1] without chain of resp pattern
//naive implementation.
//In a customer sypport system for an e-commerce platofmr like amazon, users raise tickets.
// these tickets could be:
// General inquiries
// Refund requests
// Technical issues
// Complaints about delivery.
class SupportService{
    public void handleRequest(String type){
        if(type.equals("general")){
            System.out.println("Handled by General Inquiries");
        }
        else if(type.equals("refund")){
            System.out.println("Handled by Billing Team");
        }
        else if(type.equals("technical")){
            System.out.println("Handled by Technical Support");
        }
        else if(type.equals("delivery")){
            System.out.println("Handled by Delivery team");
        }
        else {
            System.out.println("No handler available");
        }
    }
}
//violates OCP: Everytime a new type is added, the method must be edited
// monolithic code: all logic is in a single class
// not flexible or scalable: cannot change the order of processing without modifying core logic.
*/

//formal defn
//BDP, that transforms particular behaviours into stand-alone objects
// called handlers

//it allows a request to be passed along a chain of handlers. each handler decides either 
// to process the request or to pass it to the next handler in the chain
// this pattern decouples the sender of a request from the receiver, giving multiple
// object a chance to handle the object.

//we will let the client assign the chain. thats the whole purpose of this design

abstract class SupportHandler{
    protected SupportHandler nextHandler;
    public void setNextHandler(SupportHandler nextHandler){
        this.nextHandler = nextHandler;
    }
    public abstract void handleRequest(String requestType);
}

class GeneralSupport extends SupportHandler{
    public void handleRequest(String requestType) {
        if (requestType.equalsIgnoreCase("general")) {
            System.out.println("GeneralSupport: Handling general query");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(requestType);
        }
    }
}

// Concrete Handler for Billing Support
class BillingSupport extends SupportHandler {
    public void handleRequest(String requestType) {
        if (requestType.equalsIgnoreCase("refund")) {
            System.out.println("BillingSupport: Handling refund request");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(requestType);
        }
    }
}

// Concrete Handler for Technical Support
class TechnicalSupport extends SupportHandler {
    public void handleRequest(String requestType) {
        if (requestType.equalsIgnoreCase("technical")) {
            System.out.println("TechnicalSupport: Handling technical issue");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(requestType);
        }
    }
}

// Concrete Handler for Delivery Support
class DeliverySupport extends SupportHandler {
    public void handleRequest(String requestType) {
        if (requestType.equalsIgnoreCase("delivery")) {
            System.out.println("DeliverySupport: Handling delivery issue");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(requestType);
        } else {
            System.out.println("DeliverySupport: No handler found for request");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SupportHandler general = new GeneralSupport();
        SupportHandler billing = new BillingSupport();
        SupportHandler technical = new TechnicalSupport();
        SupportHandler delivery = new DeliverySupport();

        general.setNextHandler(billing);
        billing.setNextHandler(technical);
        technical.setNextHandler(delivery);

        general.handleRequest("refund");
        general.handleRequest("delivery");
        general.handleRequest("unknown");

        //when to use
        //When multiple objects can handle a request, but the handler is not known beforehand
        //when you want to decouple requests, sender and receiver
        //When you want to dynamically specify the chain of processing

        //pros
        // reduces coupling between sender and receiver
        // easy to add or remove handlers without change
        // follows srp and ocp
        // dynamic control over handler execution sequence

        // cons
        // may lead to performance issue if chain is too long
        // debugging can be harder due to flow being dynamic
        // risk of request not being handled at all
        // sequence is imporant, wrong order can break logic.

        
    }
}
