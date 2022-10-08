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

    boolean deuceWith(Player other) {
        return draftWith(other) && score() == 3;
    }

    public int score() {
        return score;
    }
}
