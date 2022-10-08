import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {

    public static final Map<Integer, String> translations = Stream.of(
                    new Object[][]{
                            {0, "Love"},
                            {1, "Fifteen"},
                            {2, "Thirty"},
                            {3, "Forty"}})
            .collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1]));

    private final Player playerOne;
    private final Player playerTwo;

    public Game() {
        playerOne = new Player();
        playerTwo = new Player();
    }

    public String score() {
        if(playerOne.deuceWith(playerTwo)) return "Deuce";
        if(playerOne.draftWith(playerTwo)) return buildPlayerOneScore() + " All";

        return buildPlayerOneScore() + "-" + buildPlayerTwoScore();
    }

    private String buildPlayerOneScore() {
        return translations.get(playerOne.score());
    }

    private String buildPlayerTwoScore() {
        return translations.get(playerTwo.score());
    }

    public void playerOneHits() {
        playerOne.hits();
    }

    public void playerTwoHits() {
        playerTwo.hits();
    }
}