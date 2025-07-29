// package Structural.Adapter;

/*
//[1] without Adapter pattern 
//structural design pattern
// focus on how objects and classes are structured to form larger components.
// they take small pieces and define relationships so that you can build
// larger systems from those smaller pieces.
// make sure the smaller pieces are decoupled and exist independently.

interface PaymentGateway {
    void pay(String orderId, double amount);
}

class PayUGateway implements PaymentGateway {
    @Override
    public void pay(String orderId, double amount){
        System.out.println("Paid " + amount + " using PayU for order: " + orderId);
    }
}
// on TUF, there will be checkout page which will call the payment gateway.
// write code such that in future if change PayU to someother, then easily.
// create a CheckoutService class.

class CheckoutService {
    private PaymentGateway paymentGateway;

    public CheckoutService(PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }

    public void checkout(String orderId, double amount){
        paymentGateway.pay(orderId, amount);
    }
}

//after seeing the req for razorpay- this is the API, it doesnt implement the PaymentGateway interface.
class RazorpayAPI{
    public void makePayment(String orderId, double amount){
        System.out.println("Paid " + amount + " using Razorpay for order: " + orderId);
    }
}
// issue-> cant use it, sep method, in checkoutService, can use paymentgateway since 3rd party API.
// we have another small piece of code , trying to use it but not able, as doesnt fit.
// we shouldnt be making much changes on the client side or the checkoutService.
// need a design pattern that allows us to adapt the RazorpayAPI to fit the PaymentGateway interface.
// integrate razorpay.


// what is adapter pattern?
//it is structural design pattern that allows two 
// incompatible interfaces to work with each other.
// it acts as a bridge between the interface a client expects (here the checkoutService is expecting a paymentgateway)
// and the actual interface of an existing class.
// in this case, the RazorpayAPI.


//real life analogy- charger used in india, US has different ports.
// buy an travel adapter then adapter plugged in US port. didnt change indian adapter.
*/




/* 
// [2] with Adapter pattern
interface PaymentGateway {
    void pay(String orderId, double amount);
}

class PayUGateway implements PaymentGateway {
    @Override
    public void pay(String orderId, double amount){
        System.out.println("Paid " + amount + " using PayU for order: " + orderId);
    }
}

class RazorpayAPI{
    public void makePayment(String orderId, double amount){
        System.out.println("Paid " + amount + " using Razorpay for order: " + orderId);
    }
}

//simply add this class which implements the PaymentGateway interface.
// this is the adapter class that adapts RazorpayAPI to PaymentGateway interface.
class RazorpayAdapter implements PaymentGateway {
    private RazorpayAPI razorpayAPI;

    public RazorpayAdapter(){
        this.razorpayAPI = new RazorpayAPI();
    }

    @Override
    public void pay(String orderId, double amount){
        razorpayAPI.makePayment(orderId, amount); //adapting paramters.
    }
}

class CheckoutService {
    private PaymentGateway paymentGateway;

    public CheckoutService(PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }

    public void checkout(String orderId, double amount){
        paymentGateway.pay(orderId, amount);
    }
}
*/


public class Main {
    public static void main(String[] args) {
        /*
         [1] without Adapter pattern
         
        //understand the problem it solves.
        // take example of payment gateway. 
        // say PayU gateway
        CheckoutService checkoutService = new CheckoutService(new PayUGateway());
        checkoutService.checkout("12", 250.0);
        // now I decide im no longer using PayU, but RazorPay.
        // I should be able to change it easily.
        */


        /*
        // [2] with Adapter pattern
        CheckoutService checkoutService = new CheckoutService(new RazorpayAdapter());
        checkoutService.checkout("12", 250.0);
        */

        //when to use?
        // when we want to use an existing class but its interface does not enable the one we need.
        // we want to reuse legacy code without modifying it.
        // you are integrating with a 3rd party library or API that does not match your expected interface.

        //pros
        // code reusability
        // code extensibility
        // minimizes changes to client code.
        // helps in integrating with 3rd party libraries or APIs.

        //cons
        // adds extra layer of abstraction.
        // can lead to overuse of adapters, making code harder to understand.

        
    }
}
