
// The KISS Principle: "Keep It Simple, Stupid"
// This principle suggests that most systems work best if they are kept simple rather than made complicated.
// Simplicity should be a key goal in design, and unnecessary complexity should be avoided.

// Example: Checking if a number is even

// Bad Code (Too Complex)
class NumberUtilsBad {
    public static boolean isEven(int number) {
        // Using unnecessary logic to determine evenness
        boolean isEven = false;

        if (number % 2 == 0) {
            isEven = true;
        } else {
            isEven = false;
        }

        return isEven;
    }
}

// Good Code (Simple and Clear)
class NumberUtilsGood {
    public static boolean isEven(int number) {
        // Simple, one-liner solution
        return number % 2 == 0;
    }
}

public class KISS {
    public static void main(String[] args) {
        int num = 4;

        // Using bad example
        System.out.println("Bad Example: Is " + num + " even? " + NumberUtilsBad.isEven(num));

        // Using good example
        System.out.println("Good Example: Is " + num + " even? " + NumberUtilsGood.isEven(num));
    }
}

/*
Explanation:
- The bad example uses extra variables and unnecessary if-else logic, making the code longer and harder to follow.
- The good example is a simple, one-liner solution that is easy to read and understand.
- The good example follows the KISS principle by avoiding overengineering.

Terminal Output:
Bad Example: Is 4 even? true
Good Example: Is 4 even? true
*/
