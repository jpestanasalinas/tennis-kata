public class Player {

    public static final int INITIAL_SCORE = 0;
    public static final int DIFFERENCE_TO_WIN = 2;
    public static final int WARNING_ZONE_LIMIT = 3;
    public static final int DIFFERENCE_TO_ADVANTAGE = 1;
    private int score;

    public Player() {
        this.score = INITIAL_SCORE;
    }

    public void hits() {
        score++;
    }

    public boolean draftWith(Player other) {
        return this.score == other.score;
    }

    public boolean deuceWith(Player other) {
        return this.draftWith(other) && this.score() >= WARNING_ZONE_LIMIT;
    }

    public boolean advantageOf(Player other) {
        if (this.score < WARNING_ZONE_LIMIT || other.score < WARNING_ZONE_LIMIT) return false;

        return this.score == other.score + DIFFERENCE_TO_ADVANTAGE;
    }

    public boolean winsTo(Player other) {
        if (this.score <= WARNING_ZONE_LIMIT && other.score <= WARNING_ZONE_LIMIT) return false;

        return this.score >= other.score + DIFFERENCE_TO_WIN;
    }

    public int score() {
        return score;
    }
}
