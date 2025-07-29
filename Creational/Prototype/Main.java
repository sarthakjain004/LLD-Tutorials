// package Creational.Prototype;
import java.util.*;
// creational design pattern

import javax.management.RuntimeErrorException;

/* 
// [1] without prototype pattern.
interface EmailTemplate{
    void setContent(String content);
    void send(String to);
}

class WelcomeEmail implements EmailTemplate{
    private String content;
    private String subject;
    public WelcomeEmail(){
        this.subject = "Welcome to TUF+";
        this.content = "Hi there! Thanks for joining us.";
    }
    @Override
    public void setContent(String content){
        this.content = content;
    }
    @Override
    public void send(String to){
        System.out.println("Sending to " + to + ": [" + subject + "] " + content);
    }
}
*/

interface EmailTemplate extends Cloneable{
    EmailTemplate clone(); //deep copy recommended. dont go for shallow copy.
    void setContent(String content);
    void send(String to);
    String getContent(); // to get the content of the email.
}

class WelcomeEmail implements EmailTemplate{
    private String content;
    private String subject;
    public WelcomeEmail(){
        this.subject  = "Welcome to TUF+";
        this.content = "Hi there! Thanks for joining us.";
    }

    @Override
    public EmailTemplate clone(){
        try{
            return (WelcomeEmail) super.clone(); // deep copy
        }
        catch (CloneNotSupportedException e){
            throw new RuntimeException("Clone failed", e);
        }
    }

    @Override
    public String getContent(){
        return content;
    }

    @Override
    public void setContent(String content){
        this.content = content;
    }
    @Override
    public void send(String to){
        System.out.println("Sending to " + to + ": [" + subject + "] " + content);
    }
}

//can be overkill
// in case you have multiple patterns this will help.
class EmailTemplateRegistry{
    private static final Map<String, EmailTemplate> templates = new HashMap<>();
    static {
        templates.put("welcome", new WelcomeEmail());
        // first time we create the object, it will cost us.
        // add more templates like discount, feature-update etc
    }

    public static EmailTemplate getTemplate(String type){
        return templates.get(type).clone();
        // get that initial object from templates.get(type
        // then for that object call clone method, retunr the cloned stuff. 
        // make sure deep cloning.

    }
}

public class Main {
    public static void main(String[] args) {
        /*
        // [1] without prototype pattern.
        WelcomeEmail welcomeEmailTuf = new WelcomeEmail();
        welcomeEmailTuf.setContent("Hello! Welcome to TUF+.");;
        // if using for TUF+ again have to create it,
        // if using somewhere else, have to create it again.
        // overdoing it, even when using same template
        // 
        // instead, we can use prototype pattern.
        // there is a catch over using singleton pattern
        // singleton pattern is a global resource, it is the same thing.
        WelcomeEmail welcomeEmailTufPlus = new WelcomeEmail();
        welcomeEmailTufPlus.setContent("Hello! Welcome to TUF+ Plus."); 
        // if using singleton and set the content for tuf plus, then it will override the previous one.
        // if 2 concurrent users, one for TUf and one for TUF+, then tuf will be overridden by tuf plus.
        // hence singleton cannot be used here.
        // at the same time i dont want to create another object as it will take time.


        // what is it?
        // it is used when creation of an object is costly or time consuming, and
        // we want to clone objects instead of creating new ones form scratch.

        //when to use it?
        // when object creation is expensive. (db calls, expensive computation.
        // when a system should be independ on how its products are created.
        // when u need to create a large number of similar objects with slight modifications.
        */

        //[2] woith prototype pattern.
        EmailTemplate welcomeEmail = EmailTemplateRegistry.getTemplate("welcome");
        welcomeEmail.setContent("type-1");
        System.out.println(welcomeEmail.getContent());
        EmailTemplate welcomeEmail2 = EmailTemplateRegistry.getTemplate("welcome");
        welcomeEmail2.setContent("type-2");
        System.out.println(welcomeEmail.getContent());
        System.out.println(welcomeEmail2.getContent());
        // why clone method doesnt cost us much?
        // because we are not creating the object from scratch.
        // we are just cloning the existing object.
        // how does clone method work?
        // clone method creates a new object with the same state as the original object.
        // it is a shallow copy by default, but we can make it deep copy by overriding the clone method.

        //dont have to use email template registry.
        EmailTemplate welcomeEmail3 = new WelcomeEmail();
        EmailTemplate welcomeEmail4 = welcomeEmail3.clone();

        //pros
        //faster object creation
        // reduces subclassing
        // runtime object configuration like the .get in registry.
        // great for ui ux cloning.

        //cons
        // deep cloning can be hard.
        // difficult with circular references.
        // might introduce bugs.

        //follow prototype pattern, this is cloning is simple.

    }
}
