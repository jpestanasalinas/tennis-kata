public class Player {

    private static final int INITIAL_SCORE = 0;
    private static final int DIFFERENCE_TO_WIN = 2;
    private static final int WARNING_ZONE_LIMIT = 3;
    private static final int DIFFERENCE_TO_ADVANTAGE = 1;

    private final String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = INITIAL_SCORE;
    }

    public void hits() {
        score++;
    }

    public boolean draftWith(Player other) {
        return this.score == other.score;
    }

    public boolean deuceWith(Player other) {
        return this.draftWith(other) && this.points() >= WARNING_ZONE_LIMIT;
    }

    public boolean advantageOf(Player other) {
        if (this.score < WARNING_ZONE_LIMIT || other.score < WARNING_ZONE_LIMIT) return false;

        return this.score == other.score + DIFFERENCE_TO_ADVANTAGE;
    }

    public boolean winsTo(Player other) {
        if (this.score <= WARNING_ZONE_LIMIT && other.score <= WARNING_ZONE_LIMIT) return false;

        return this.score >= other.score + DIFFERENCE_TO_WIN;
    }

    public String name() {
        return name;
    }

    public int points() {
        return score;
    }
}
