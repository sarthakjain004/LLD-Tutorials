// package Creational.AbstractFactory;


/*
 * The Abstract Factory Pattern is a creational design pattern that provides 
 * an interface for creating families of 
 * related or dependent objects without specifying their concrete classes.

In simpler terms:
You use it when you have multiple factories, each responsible for producing objects 
that are meant to work together.
*/

/* [1] without abstract factory pattern
// creational design pattern
// we have TUF+, and will have checkoutService.
// in this Pg(payment gateway) and invoice.
// only operate in india for now, so create for that only.

interface PaymentGateway { //abstract layer.
    void processPayment(double amount); 
}

class RazorpayGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing INR payment via Razorpay: Rupees " + amount);
    }
}

class PayUGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing INR payment via PayU: Rupees " + amount);
    }
}

//now we also need to generate invoice for the user
interface Invoice{
    void generateInvoice();
}

class GSTInvoice implements Invoice {
    public void generateInvoice(){
        System.out.println("Generating GST Invoice for India");
    }
}

class CheckoutService{
    private String gatewayType;
    public CheckoutService(String gatewayType) {
        this.gatewayType = gatewayType;
    }

    public void checkOut(double amount){
        if(gatewayType =="razorpay"){
            PaymentGateway paymentGateway = new RazorpayGateway();
            // this checkoutService is dealing with object creation as well as payment processing.
            // it should only deal with checkout.
            // this object creation logic can be shifted to another class.
            // this is a violation of the single responsibility principle.
            // we can use abstract factory pattern to shift this logic.
            paymentGateway.processPayment(amount);
        }
        else{
            PaymentGateway paymentGateway = new PayUGateway();
            paymentGateway.processPayment(amount);
        }
        Invoice invoice = new GSTInvoice();
        invoice.generateInvoice();
    }
}
*/

/*
// [3] only for India with abstract factory pattern
interface PaymentGateway { //abstract layer.
    void processPayment(double amount); 
}

class RazorpayGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing INR payment via Razorpay: Rupees " + amount);
    }
}

class PayUGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing INR payment via PayU: Rupees " + amount);
    }
}

//now we also need to generate invoice for the user
interface Invoice{
    void generateInvoice();
}

class GSTInvoice implements Invoice {
    public void generateInvoice(){
        System.out.println("Generating GST Invoice for India");
    }
}

class IndiaFactory{
    public static PaymentGateway createPaymentGateway(String gatewayType){
        switch (gatewayType) {
            case "razorpay":
                return new RazorpayGateway();
            case "payu":
                return new PayUGateway();
            default:
                throw new IllegalArgumentException("Unknown payment gateway type: " + gatewayType);
        }
    }
    public static Invoice createInvoice() {
        return new GSTInvoice();
    }
}

class CheckoutService{
    private String gatewayType;
    public CheckoutService(String gatewayType) {
        this.gatewayType = gatewayType;
    }

    public void checkOut(double amount){
        PaymentGateway paymentGateway = IndiaFactory.createPaymentGateway(gatewayType);
        paymentGateway.processPayment(amount);
        Invoice invoice  = IndiaFactory.createInvoice();
        invoice.generateInvoice();
    }
}
*/


/*
// [3] With US and India, without abstract factory pattern.
//say now US comes in with Paypal and stripe as preferred methods.
// invoicing also different for US and India.
// brute force without abstract factory pattern would be to create a new class for US and implement the same logic.
interface PaymentGateway { //abstract layer.
    void processPayment(double amount); 
}

class RazorpayGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing INR payment via Razorpay: Rupees " + amount);
    }
}

class PayUGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing INR payment via PayU: Rupees " + amount);
    }
}
class PaypalGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing USD payment via PayPal: $" + amount);
    }
}
class StripeGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing USD payment via Stripe: $" + amount);
    }
}

//now we also need to generate invoice for the user
interface Invoice{
    void generateInvoice();
}

class GSTInvoice implements Invoice {
    public void generateInvoice(){
        System.out.println("Generating GST Invoice for India");
    }
}

class USInvoice implements Invoice {
    public void generateInvoice(){
        System.out.println("Generating Invoice for US");
    }
}

class IndiaFactory{
    public static PaymentGateway createPaymentGateway(String gatewayType){
        switch (gatewayType) {
            case "razorpay":
                return new RazorpayGateway();
            case "payu":
                return new PayUGateway();
            default:
                throw new IllegalArgumentException("Unknown payment gateway type: " + gatewayType);
        }
    }
    public static Invoice createInvoice() {
        return new GSTInvoice();
    }
}

class USFactory{
    public static PaymentGateway createPaymentGateway(String gatewayType){
        switch (gatewayType) {
            case "paypal":
                return new PaypalGateway(); // Assume PaypalGateway is defined similarly to RazorpayGateway
            case "stripe":
                return new StripeGateway(); // Assume StripeGateway is defined similarly to PayUGateway
            default:
                throw new IllegalArgumentException("Unknown payment gateway type: " + gatewayType);
        }
    }
    public static Invoice createInvoice() {
        return new USInvoice(); // Assume USInvoice is defined similarly to GSTInvoice
    }
}

class CheckoutService{
    private String gatewayType;
    // to handle US, another variable say country code or sth.
    private String countryCode;
    public CheckoutService(String gatewayType, String countryCode) {
        this.gatewayType = gatewayType;
        this.countryCode = countryCode;
    }

    public void checkOut(double amount){
        if(countryCode.equals("IN")){
            PaymentGateway paymentGateway = IndiaFactory.createPaymentGateway(gatewayType);
        paymentGateway.processPayment(amount);
        Invoice invoice  = IndiaFactory.createInvoice();
        invoice.generateInvoice();
        }
        else{
            // logic for US goes here.
            PaymentGateway paymentGateway = USFactory.createPaymentGateway(gatewayType);
            paymentGateway.processPayment(amount);
            Invoice invoice  = USFactory.createInvoice();
            invoice.generateInvoice();
            // now we have to create a new factory class for US.
            // similar for US factory create USfactory class.
            // again we are violating the single responsibility principle.
            // this checkoutService is dealing with object creation as well as payment processing.
        }
        
    }
}
*/

// here abstract factory comes in. when multiple factories are needed to create families of related or dependent objects.
// a group of multiple factories wrapped inside a single interface
// it provides an interface for creating families of dependent objects and without specifying their concrete classes.
interface PaymentGateway { //abstract layer.
    void processPayment(double amount); 
}

class RazorpayGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing INR payment via Razorpay: Rupees " + amount);
    }
}

class PayUGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing INR payment via PayU: Rupees " + amount);
    }
}
class PaypalGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing USD payment via PayPal: $" + amount);
    }
}
class StripeGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing USD payment via Stripe: $" + amount);
    }
}

interface Invoice{
    void generateInvoice();
}

class GSTInvoice implements Invoice {
    public void generateInvoice(){
        System.out.println("Generating GST Invoice for India");
    }
}

class USInvoice implements Invoice {
    public void generateInvoice(){
        System.out.println("Generating Invoice for US");
    }
}
interface RegionFactory{
    PaymentGateway createPaymentGateway(String gatewayType);
    Invoice createInvoice();
}

class IndiaFactory implements RegionFactory {
    public PaymentGateway createPaymentGateway(String gatewayType) {
        switch (gatewayType) {
            case "razorpay":
                return new RazorpayGateway();
            case "payu":
                return new PayUGateway();
            default:
                throw new IllegalArgumentException("Unknown payment gateway type: " + gatewayType);
        }
    }
    
    public Invoice createInvoice() {
        return new GSTInvoice();
    }
}
class USFactory implements RegionFactory {
    public PaymentGateway createPaymentGateway(String gatewayType) {
        switch (gatewayType) {
            case "paypal":
                return new PaypalGateway();
            case "stripe":
                return new StripeGateway();
            default:
                throw new IllegalArgumentException("Unknown payment gateway type: " + gatewayType);
        }
    }
    
    public Invoice createInvoice() {
        return new USInvoice();
    }
}

class CheckoutService {
    private PaymentGateway paymentGateway;
    private Invoice invoice;

    public CheckoutService(RegionFactory factory, String gatewayType) {
        this.paymentGateway = factory.createPaymentGateway(gatewayType);
        this.invoice = factory.createInvoice();
    }
    public void completeOrder(double amount) {
        paymentGateway.processPayment(amount);
        invoice.generateInvoice();
    }
}


public class Main {
    public static void main(String[] args) {
        // [1] without abstract factory pattern
        // CheckoutService checkoutService = new CheckoutService("razorpay");
        // checkoutService.checkOut(1000.0);
        /*
        // [2] with abstract factory pattern
        CheckoutService checkoutService = new CheckoutService("payu");
        checkoutService.checkOut(2000.0);
        CheckoutService checkoutService2 = new CheckoutService("razorpay");
        checkoutService2.checkOut(1000.0);
        */

        /*
        // [3] without abstract factory pattern for US
        // because we have to create a new class for US and implement the same logic.  
        // the checkoutService is dealing with which object creation? Ans: PaymentGateway and Invoice for India as well as US.
        CheckoutService checkoutService = new CheckoutService("paypal", "US");
        checkoutService.checkOut(1500.0);
        */
        // [4] with abstract factory pattern for US and India
        CheckoutService checkoutService = new CheckoutService(new IndiaFactory(), "razorpay");
        checkoutService.completeOrder(1000.0);


        //pros
        // consistency (Stripen+ USinvoice)
        // decouples client from concrete implementations
        // promotes OCP (Open/Closed Principle) - new factories can be added without modifying existing code.
        // scalable and flexible (add phonePe)
        // centralized object creation logic

        // cons
        // complexity - more classes and interfaces to manage.
        // can lead to over-engineering for simple scenarios.
        // addition of more code.
    }
} 
