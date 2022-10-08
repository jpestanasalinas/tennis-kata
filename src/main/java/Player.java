public class Player {

    private int score;

    public Player() {
        this.score = 0;
    }

    public void hits() {
        score++;
    }

    public boolean draftWith(Player other) {
        return this.score == other.score;
    }

    public boolean deuceWith(Player other) {
        return this.draftWith(other) && this.score() == 3;
    }

    public boolean advantageOf(Player other) {
        if (this.score < 3 || other.score < 3) return false;

        return this.score == other.score + 1;
    }

    public int score() {
        return score;
    }
}
