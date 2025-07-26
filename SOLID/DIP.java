
// Abstraction for recommendation strategy
interface RecommendationStrategy {
    String recommend(String user);
}

// Concrete strategy: Recently Added
class RecentlyAdded implements RecommendationStrategy {
    public String recommend(String user) {
        return "Recommend recently added shows for " + user;
    }
}

// Concrete strategy: Trending Now
class TrendingNow implements RecommendationStrategy {
    public String recommend(String user) {
        return "Recommend trending shows for " + user;
    }
}

// Concrete strategy: Genre Based
class GenreBased implements RecommendationStrategy {
    public String recommend(String user) {
        return "Recommend shows based on genres liked by " + user;
    }
}

// High-level module depends on abstraction, not concrete classes
class RecommendationEngine {
    private RecommendationStrategy strategy;

    // Inject strategy via constructor (Dependency Injection)
    public RecommendationEngine(RecommendationStrategy strategy) {
        this.strategy = strategy;
    }

    // Can switch strategy at runtime
    public void setStrategy(RecommendationStrategy strategy) {
        this.strategy = strategy;
    }

    public String getRecommendation(String user) {
        return strategy.recommend(user);
    }
}

// Usage Example
public class DIP {
    public static void main(String[] args) {
        // Use RecentlyAdded strategy
        RecommendationEngine engine = new RecommendationEngine(new RecentlyAdded());
        System.out.println(engine.getRecommendation("Alice"));

        // Switch to GenreBased strategy at runtime
        engine.setStrategy(new GenreBased());
        System.out.println(engine.getRecommendation("Alice"));

        // Switch to TrendingNow strategy
        engine.setStrategy(new TrendingNow());
        System.out.println(engine.getRecommendation("Alice"));
    }
}
