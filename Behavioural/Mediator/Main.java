// package Behavioural.Mediator;

// when u talk via someone
import java.util.*;
// when an object interactes via a mediator.
// document example
// user gives access of doc to other users

/*
//[1] without mediator pattern
class User{ //mutiple instances of user
    private String name;
    private List<User> others;

    public User(String name) {
        this.name = name;
        this.others = new ArrayList<>();
    }
    public void addCollaborator(User user) {
        others.add(user);
    }
    public void makeChange(String change) {
        System.out.println(name + " made a change: " + change);
        for(User user : others) {
            user.receiveChange(change, this);
        }
    }
    public void receiveChange(String change, User from) {
        System.out.println(name + " received change: " + change + " from " + from.name);
    }
}

// each user has reference to every other user
// adding/removing a user breaks the structure.
// hard to orchestrate roles(editor,viewer,admin)
*/


//[2] with mediator pattern
interface DocumentSessionMediator {
    void broadcastChange(String change, User user);
    void join(User user);
}

class CollaborativeDocument implements DocumentSessionMediator {
    private List<User> users = new ArrayList<>();

    @Override
    public void broadcastChange(String change, User user) {
        for (User u : users) {
            if (u != user) {
                u.receiveChange(change, user);
            }
        }
    }

    @Override
    public void join(User user) {
        users.add(user);
    }
}
class User {
    private String name;
    private DocumentSessionMediator mediator;
    public User(String name, DocumentSessionMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public void makeChange(String change) {
        System.out.println(name + " made a change: " + change);
        mediator.broadcastChange(change, this);
    }
    public void receiveChange(String change, User from) {
        System.out.println(name + " received change: " + change + " from " + from.name);
    }
}
public class Main {
    public static void main(String[] args) {
        // formal defn
        //BDP, that centralizes complex communication between objects into a single mediator object.
        // it promotes loose coupling and organizes the interaction between components.

        //[2] with mediator pattern
        CollaborativeDocument doc = new CollaborativeDocument();
        User alice = new User("Alice", doc);
        User bob = new User("Bob", doc);
        doc.join(alice);
        doc.join(bob);
        alice.makeChange("Added a new section");
        bob.makeChange("Fixed a typo in the section");

        //when to use?
        // multiple users or services interact but should remain decoupled
        // want to manage rules or permission centrally
        // need flexible broadcasting, filtering, or transformation of messages.

        // pros
        // users dont need to know about each other
        // easy to manage roles and access centrally
        // easier to test and extend
        // clean separation of business logic and interaction
        
        //cons
        // mediator can become a bottleneck if it handles too many interactions
        // mediator can become complex if it handles too many users or rules
        // one point of failure if mediator goes down
        // adds an extra layer of abstraction which can be overkill for simple interactions

    }
}
