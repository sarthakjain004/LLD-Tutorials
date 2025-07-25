
// Main class to demonstrate SRP (Single Responsibility Principle)
public class SRP {
    public static void main(String[] args) {
        // Create TUFCompiler instance and run the compiler workflow
        TUFCompiler tufCompiler = new TUFCompiler();
        tufCompiler.runCompiler("cpp", "code snippet", "P_001", "S_123");
    }
}

// Class responsible only for generating driver code
class DriverCodeGenerator {
    public void generateDriverCode(String language) {
        System.out.println("[DriverCodeGenerator] Driver code generated for the language: " + language);
    }
}

// Class responsible only for checking syntax
class SyntaxChecker {
    public void checkLanguageSyntax(String language, String code) {
        System.out.println("[SyntaxChecker] Code syntax check successful for language: " + language);
    }
}

// Class responsible only for running tests
class TestRunner {
    public void runTests(String language, String code, String problemID) {
        System.out.println("[TestRunner] Running tests successful for problem: " + problemID);
    }
}

// Class responsible only for database operations
class DatabaseManager {
    public void connectToDB() {
        System.out.println("[DatabaseManager] Connection to database successful");
    }

    public void executeQuery(String query) {
        System.out.println("[DatabaseManager] Executed given query: " + query);
    }
}

// Class responsible only for handling user output
class UserOutputHandler {
    public void generateStdOutput(String submissionID) {
        System.out.println("[UserOutputHandler] User output generated for the submission: " + submissionID);
    }
}

// Facade class that uses all the above classes to perform the compilation workflow
// This class coordinates the workflow but delegates each responsibility to a separate class
class TUFCompiler {
    DriverCodeGenerator driverCodeGenerator;
    SyntaxChecker syntaxChecker;
    TestRunner testRunner;
    DatabaseManager databaseManager;
    UserOutputHandler userOutputHandler;

    public TUFCompiler() {
        this.driverCodeGenerator = new DriverCodeGenerator();
        this.syntaxChecker = new SyntaxChecker();
        this.testRunner = new TestRunner();
        this.databaseManager = new DatabaseManager();
        this.userOutputHandler = new UserOutputHandler();
    }

    // This method demonstrates how each class has a single responsibility
    // and how TUFCompiler coordinates them
    public void runCompiler(String language, String code, String problemID, String submissionID) {
        System.out.println("=== TUF Compiler Workflow Started ===");
        this.driverCodeGenerator.generateDriverCode(language);
        this.syntaxChecker.checkLanguageSyntax(language, code);
        this.testRunner.runTests(language, code, problemID);
        this.databaseManager.connectToDB();
        this.userOutputHandler.generateStdOutput(submissionID);
        System.out.println("=== TUF Compiler Workflow Finished ===");
    }
}

/*
    SRP (Single Responsibility Principle) Explanation:
    -------------------------------------------------
    Each class in this example has only one reason to change, i.e., one responsibility:
    - DriverCodeGenerator: Only generates driver code.
    - SyntaxChecker: Only checks code syntax.
    - TestRunner: Only runs tests.
    - DatabaseManager: Only manages database operations.
    - UserOutputHandler: Only handles user output.
    This makes the code easier to maintain, test, and extend.
*/
