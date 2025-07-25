
// TaxCalculator interface defines contract for all region-specific tax calculators
// This interface allows us to add new tax calculation strategies without modifying existing code.
// This is the essence of the Open/Closed Principle (OCP): open for extension, closed for modification.
interface TaxCalculator {
    double calculateTax(double amount);
}

// India tax calculator (GST 18%)
// Implements TaxCalculator for India-specific tax logic.
// New calculators can be added by implementing the interface, not by changing existing classes.
class IndiaTaxCalculator implements TaxCalculator {
    @Override
    public double calculateTax(double amount) {
        return amount * 0.18;
    }
}

// US tax calculator (Sales Tax 8%)
// Another extension of TaxCalculator for US-specific tax logic.
class USTaxCalculator implements TaxCalculator {
    @Override
    public double calculateTax(double amount) {
        return amount * 0.08;
    }
}

// UK tax calculator (VAT 12%)
// Yet another extension, showing how new behavior is added without modifying existing code.
class UKTaxCalculator implements TaxCalculator {
    @Override
    public double calculateTax(double amount) {
        return amount * 0.12;
    }
}

// Germany tax calculator (Tax 15%) - easily added without modifying existing code
// Demonstrates OCP: new functionality is added by creating a new class, not by changing Invoice or other calculators.
class GermanyTaxCalculator implements TaxCalculator {
    @Override
    public double calculateTax(double amount) {
        return amount * 0.15;
    }
}

// Invoice class uses dependency injection for tax calculation
// Invoice depends on the abstraction (TaxCalculator), not concrete implementations.
// This allows Invoice to remain unchanged when new tax calculators are introduced.
class Invoice {
    private double amount;
    private TaxCalculator taxCalculator;

    public Invoice(double amount, TaxCalculator taxCalculator) {
        this.amount = amount;
        this.taxCalculator = taxCalculator;
    }

    public double getTotal() {
        return amount + taxCalculator.calculateTax(amount);
    }
}

// Main class to demonstrate OCP in action
public class OCP {
    public static void main(String[] args) {
        double amount = 1000.0;

        // India Invoice
        Invoice indiaInvoice = new Invoice(amount, new IndiaTaxCalculator());
        System.out.println("India Invoice Total: " + indiaInvoice.getTotal());

        // US Invoice
        Invoice usInvoice = new Invoice(amount, new USTaxCalculator());
        System.out.println("US Invoice Total: " + usInvoice.getTotal());

        // UK Invoice
        Invoice ukInvoice = new Invoice(amount, new UKTaxCalculator());
        System.out.println("UK Invoice Total: " + ukInvoice.getTotal());

        // Germany Invoice (added without modifying Invoice or Main logic)
        // Shows OCP: GermanyTaxCalculator is used without changing Invoice or main logic.
        Invoice germanyInvoice = new Invoice(amount, new GermanyTaxCalculator());
        System.out.println("Germany Invoice Total: " + germanyInvoice.getTotal());
    }
}
