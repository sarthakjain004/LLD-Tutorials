// package Structural.Facade;
import java.util.*;

/*
// [1] without facade pattern
//what problem does it solve?
// say using bookmyshow to book ticket.
// bunch of services

class PaymentService{
    public void makePayment(String accountId, double amount){
        System.out.println("Payment of Rupees" + amount + " successful for acccount "+ accountId);
    }
}

class SeatReservationService{
    public void reserveSeat(String movieId, String seatNumber){
        System.out.println("Seat " + seatNumber + " reserved for movie "+ movieId);
    }
}

class NotificationService {
    public void sendBookingConfirmation(String userEmail){
        System.out.println("Booking confirmation sent to " + userEmail);
    }
}

class LoyaltyPointsService{
    public void addPoints(String accountId, int points){
        System.out.println(points + " loyalty points added to account " + accountId);
    }
}

class TicketService {
    public void generateTicket(String seatNumber, String movieId){
        System.out.println("Ticket generated for movie "+movieId+" Seat: "+ seatNumber);
    }
}
*/



class PaymentService{
    public void makePayment(String accountId, double amount){
        System.out.println("Payment of Rupees" + amount + " successful for acccount "+ accountId);
    }
}

class SeatReservationService{
    public void reserveSeat(String movieId, String seatNumber){
        System.out.println("Seat " + seatNumber + " reserved for movie "+ movieId);
    }
}

class NotificationService {
    public void sendBookingConfirmation(String userEmail){
        System.out.println("Booking confirmation sent to " + userEmail);
    }
}

class LoyaltyPointsService{
    public void addPoints(String accountId, int points){
        System.out.println(points + " loyalty points added to account " + accountId);
    }
}

class TicketService {
    public void generateTicket(String seatNumber, String movieId){
        System.out.println("Ticket generated for movie "+movieId+" Seat: "+ seatNumber);
    }
}

class MovieBookingFacade {
    private PaymentService paymentService;
    private SeatReservationService seatReservationService;
    private NotificationService notificationService;
    private LoyaltyPointsService loyaltyPointsService;
    private TicketService ticketService;

    public MovieBookingFacade(){
        this.paymentService = new PaymentService();
        this.seatReservationService = new SeatReservationService();
        this.loyaltyPointsService = new LoyaltyPointsService();
        this.notificationService = new NotificationService();
        this.ticketService = new TicketService();
    }

    public void bookMovieTicket(String accountId, String movieId, String seatNumber, String userEmail, int points){
        paymentService.makePayment(accountId, points);
        seatReservationService.reserveSeat(movieId, seatNumber);
        ticketService.generateTicket(seatNumber, movieId);
        loyaltyPointsService.addPoints(accountId, points); 
        notificationService.sendBookingConfirmation(userEmail);

        System.out.println("Movie ticket booking completed successfully.");
    }
}

public class Main {
    public static void main(String[] args) {

        /* 
        // [1] without facade pattern
        
        PaymentService paymentService = new PaymentService();
        SeatReservationService seatReservationService = new SeatReservationService();
        NotificationService notificationService = new NotificationService();
        LoyaltyPointsService loyaltyPointsService = new LoyaltyPointsService();
        TicketService ticketService = new TicketService();

        // Sample values
        String accountId = "ACC123";
        double amount = 500.0;
        String movieId = "MOV456";
        String seatNumber = "A10";
        String userEmail = "user@example.com";
        int points = 50;

        // Using the services
        paymentService.makePayment(accountId, amount);
        seatReservationService.reserveSeat(movieId, seatNumber);
        notificationService.sendBookingConfirmation(userEmail);
        loyaltyPointsService.addPoints(accountId, points);
        ticketService.generateTicket(seatNumber, movieId);

        // if doing manually, lot of repitition of code.
        //This leads to:
        //High complexity for the client
        //Duplicate code if you have to do this in multiple places
        //Violation of the Single Responsibility Principle (the Main class knows too much)

        //problem client deals with all service by itself.
        // facade pattern is a structural design pattern that provides a 
        // simplified, unified interface to a set of interfaces in a subsystem.
        */

        MovieBookingFacade movieBookingFacade = new MovieBookingFacade();
        movieBookingFacade.bookMovieTicket("user123", "movie456", "A10", "user@example.com", 500);

        //when to use it?
        // subsystems are complex(too many classs, too many dependencies.
        //you want to provide a simpler API for outer world.
        // reduce coupling between subsystems and client code.
        
        //pros
        //lightweight coupling between subsystems
        //flexibility (just change on facde level)
        // simplifies client design.
        // promotes layered architecture
        // better testability.

        //cons
        //fragile coupling.
        // hidden complexity.
        // runtime errors.
        // difficult to trace.
        // violation of SRP.


    }
}
