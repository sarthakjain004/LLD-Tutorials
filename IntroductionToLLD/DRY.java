
import java.util.*;
/**
 * The AreaCalculator class demonstrates the DRY (Don't Repeat Yourself) principle.
 * DRY principle states that "Every piece of knowledge must have a single, unambiguous, authoritative representation within a system."
 * 
 * In this class:
 * - The method calculateArea(int length, int width) encapsulates the logic for calculating the area of a rectangle.
 *   This prevents duplication of area calculation logic elsewhere in the codebase.
 * - The method printArea(int area) encapsulates the logic for printing the area, so the print statement is not repeated.
 * 
 * By centralizing these functionalities, the code avoids repetition and is easier to maintain and update.
 */
class AreaCalculator {
    public static int calculateArea(int length, int width) {
        return length * width;
    }
    public static void printArea(int area) {
        System.out.println("Area: " + area);
    }
}
public class DRY {
    public static void main(String[] args) {
        int length1 = 10, width1 = 5;
        int area1 = length1 * width1;
        System.out.println("Area1: " + area1);

        int length2 = 8, width2 = 4;
        int area2 = length2 * width2;
        System.out.println("Area2: " + area2);

        int length3 = 6, width3 = 3;
        int area3 = AreaCalculator.calculateArea(length3, width3);
        AreaCalculator.printArea(area3);
}
}