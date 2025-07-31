// package Behavioural.Template;


//what problem does it solve
//say email -> common template
// when there is common stuff.


// first we need to enforce that they do rate limit check ,validate, and analytics
// lot of code that can be reused. by putting in some class
// but how do i enforce it? that that flow is being followed?
// problem is that we want to enforce a flow.
// first rate, validate, trigger sms/email then do analytics by any notification we want to implement.
// this is where template helps

/*
// [1] without template
class EmailNotification {

    public void send(String to, String message) {
        System.out.println("Checking rate limits for: " + to);
        System.out.println("Validating email recipient: " + to);
        String formatted = message.trim();
        System.out.println("Logging before send: " + formatted + " to " + to);

        // Compose Email
        String composedMessage = "<html><body><p>" + formatted + "</p></body></html>";

        // Send Email
        System.out.println("Sending EMAIL to " + to + " with content:\n" + composedMessage);

        // Analytics
        System.out.println("Analytics updated for: " + to);
    }
}

class SMSNotification {

    public void send(String to, String message) {
        System.out.println("Checking rate limits for: " + to);
        System.out.println("Validating phone number: " + to);
        String formatted = message.trim();
        System.out.println("Logging before send: " + formatted + " to " + to);

        String composedMessage = "[SMS] " + formatted;

        // Send SMS
        System.out.println("Sending SMS to " + to + " with message: " + composedMessage);

        // Analytics (custom)
        System.out.println("Custom SMS analytics for: " + to);
    }
}
*/


//formal defn of template design pattern
// it defines the skeleton of an algorithm in the superclass but lets 
// subclass override specific steps of the algorithm without changing its structure.

//key steps
// template method (final method in base class)
// primitive operations (abstract methods)
// concrete operations (private methods)
// hooks (optional methods with default behaviour)




abstract class NotificationSender {

    // Template method
    public final void send(String to, String rawMessage) {// final so that none of the subclasses can tamper with it.
        // Common Logic
        rateLimitCheck(to);
        validateRecipient(to);
        String formatted = formatMessage(rawMessage);
        preSendAuditLog(to, formatted);
        
        // Specific Logic: defined by subclassese
        String composedMessage = composeMessage(formatted);
        sendMessage(to, composedMessage);
        
        // Optional Hook
        postSendAnalytics(to);
    }

    // Common step 1: Check rate limits
    private void rateLimitCheck(String to) {
        System.out.println("Checking rate limits for: " + to);
    }

    // Common step 2: Validate recipient
    private void validateRecipient(String to) {
        System.out.println("Validating recipient: " + to);
    }

    // Common step 3: Format the message (can be customized)
    private String formatMessage(String message) {
        return message.trim(); // could include HTML escaping, emoji processing, etc.
    }

    // Common step 4: Pre-send audit log
    private void preSendAuditLog(String to, String formatted) {
        System.out.println("Logging before send: " + formatted + " to " + to);
    }

    // Hook for subclasses to implement custom message composition
    // this is not common part, hence make it abstract so that subclasses would have to implement it.
    // make the common part as normal methods.
    protected abstract String composeMessage(String formattedMessage);

    // Hook for subclasses to implement custom message sending
    protected abstract void sendMessage(String to, String message);

    // Optional hook for analytics (can be overridden)
    protected void postSendAnalytics(String to) {
        System.out.println("Analytics updated for: " + to);
    }
}

class EmailNotification extends NotificationSender {

    // Implement message composition for email
    @Override
    protected String composeMessage(String formattedMessage) {
        return "<html><body><p>" + formattedMessage + "</p></body></html>";
    }

    // Implement email sending logic
    @Override
    protected void sendMessage(String to, String message) {
        System.out.println("Sending EMAIL to " + to + " with content:\n" + message);
    }
}

// Concrete class for SMS notifications
class SMSNotification extends NotificationSender {

    // Implement message composition for SMS
    @Override
    protected String composeMessage(String formattedMessage) {
        return "[SMS] " + formattedMessage;
    }

    // Implement SMS sending logic
    @Override
    protected void sendMessage(String to, String message) {
        System.out.println("Sending SMS to " + to + " with message: " + message);
    }

    // Override optional hook for custom SMS analytics
    @Override
    protected void postSendAnalytics(String to) {
        System.out.println("Custom SMS analytics for: " + to);
    }
}
public class Main {
    public static void main(String[] args) {
        /*
        //[1] without template.
        EmailNotification emailNotification = new EmailNotification();
        SMSNotification smsNotification = new SMSNotification();

        // Sending email notification
        emailNotification.send("example@example.com", "Your order has been placed!");
        
        System.out.println(" ");
        // Sending SMS notification
        smsNotification.send("1234567890", "Your OTP is 1234.");
        */


        NotificationSender emailSender = new EmailNotification();
        emailSender.send("john@example.com", "Welcome to TUF+!");

        System.out.println(" ");

        NotificationSender smsSender = new SMSNotification();
        smsSender.send("9876543210", "Your OTP is 4567.");

        //when to us it?
        // have multiple classes that follow the same overall algo but differ in few steps
        // want to avoid code duplication of common steps.
        // want to enforce a fixed  order of steps.
        // dont call us, we will call u. base class will call everything
        

        //pros
        // promotes code reusability by sharing same stesp
        // promotes OCP
        // enforces a consistent flow
        // allows optional customization via hook methods

        //cons
        // inheritance based, limits flex
        // subclasses are tightly coupled with the base class
        // not ideal if algo varies, switch to strategy
        // may result in too many subclasses.

    }
}
