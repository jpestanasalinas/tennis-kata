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
        if(isADraft()) return handleDraft();
        if(isAdvantage()) return handleAdvantage();
        if(isAWin()) return handleWin();


        return buildPlayerOneScore() + "-" + buildPlayerTwoScore();
    }

    private boolean isAWin() {
        return playerOne.winsTo(playerTwo) || playerTwo.winsTo(playerOne);
    }

    private String handleWin() {
        if(playerOne.winsTo(playerTwo)) return "Game Player One";
        return "Game Player Two";
    }

    private boolean isADraft() {
        return playerOne.draftWith(playerTwo);
    }

    private String handleDraft() {
        if(playerOne.deuceWith(playerTwo)) return "Deuce";

        return buildPlayerOneScore() + " All";
    }

    private boolean isAdvantage() {
        return playerOne.advantageOf(playerTwo) || playerTwo.advantageOf(playerOne);
    }

    private String handleAdvantage() {
        if(playerOne.advantageOf(playerTwo)) return "Advantage Player One";
        return "Advantage Player Two";
    }

    private String buildPlayerOneScore() {
        return translations.get(playerOne.points());
    }

    private String buildPlayerTwoScore() {
        return translations.get(playerTwo.points());
    }

    public void playerOneHits() {
        playerOne.hits();
    }

    public void playerTwoHits() {
        playerTwo.hits();
    }

}