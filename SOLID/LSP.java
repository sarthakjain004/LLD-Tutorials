
// Liskov Substitution Principle (LSP) Example

// Base class
class Bird {
    public void fly() {
        System.out.println("Bird is flying");
    }
}

// Subclass that follows LSP
class Sparrow extends Bird {
    @Override
    public void fly() {
        System.out.println("Sparrow is flying");
    }
}

// Subclass that violates LSP
class Ostrich extends Bird {
    // Ostrich can't fly, so this violates LSP if used as a Bird
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Ostrich can't fly!");
    }
}

public class LSP {
    // Method that expects any Bird to fly
    public static void makeBirdFly(Bird bird) {
        bird.fly();
    }

    public static void main(String[] args) {
        Bird sparrow = new Sparrow();
        Bird ostrich = new Ostrich();

        System.out.println("Sparrow:");
        makeBirdFly(sparrow); // OK

        System.out.println("Ostrich:");
        try {
            makeBirdFly(ostrich); // Throws exception, violates LSP
        } catch (UnsupportedOperationException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}

/*
Output in terminal:
Sparrow:
Sparrow is flying
Ostrich:
Exception: Ostrich can't fly!
*/

// Explanation:
// LSP states that subclasses should be substitutable for their base classes without breaking the program.
// Here, Ostrich is a Bird but can't fly, so substituting it breaks the code.
// To follow LSP, design hierarchies so that subclasses don't remove base class behavior.
