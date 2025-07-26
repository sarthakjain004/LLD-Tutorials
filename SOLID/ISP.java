
// ISP: Interface Segregation Principle
// Clients should not be forced to depend on interfaces they do not use.

// BAD EXAMPLE: One big interface forces all implementers to implement all methods
interface Worker {
    void work();
    void eat();
}

// Robot does not eat, but is forced to implement eat()
class Robot implements Worker {
    public void work() {
        System.out.println("Robot is working");
    }
    public void eat() {
        // Not applicable
        System.out.println("Robot does not eat");
    }
}

// Human does both
class Human implements Worker {
    public void work() {
        System.out.println("Human is working");
    }
    public void eat() {
        System.out.println("Human is eating");
    }
}

// GOOD EXAMPLE: Split interfaces
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class NewRobot implements Workable {
    public void work() {
        System.out.println("NewRobot is working");
    }
}

class NewHuman implements Workable, Eatable {
    public void work() {
        System.out.println("NewHuman is working");
    }
    public void eat() {
        System.out.println("NewHuman is eating");
    }
}

public class ISP {
    public static void main(String[] args) {
        // Bad example
        Worker robot = new Robot();
        Worker human = new Human();
        robot.work();
        robot.eat();
        human.work();
        human.eat();

        System.out.println("---");

        // Good example
        Workable newRobot = new NewRobot();
        Workable newHuman = new NewHuman();
        Eatable eatHuman = new NewHuman();

        newRobot.work();
        newHuman.work();
        eatHuman.eat();
    }
}

/*
Output in terminal:
Robot is working
Robot does not eat
Human is working
Human is eating
---
NewRobot is working
NewHuman is working
NewHuman is eating
*/
