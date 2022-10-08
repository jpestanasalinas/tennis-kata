import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {

    public static final Map<Integer, String> scoreTranslations = Stream.of(
                    new Object[][]{
                            {0, "Love"},
                            {1, "Fifteen"},
                            {2, "Thirty"},
                            {3, "Forty"}})
            .collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1]));

    private final Player playerOne;
    private final Player playerTwo;

    public Game() {
        playerOne = new Player("Player One");
        playerTwo = new Player("Player Two");
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
        String result = "Game ";
        if(playerOne.winsTo(playerTwo)) return result + playerOne.name();
        return result + playerTwo.name();
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
        if(playerOne.advantageOf(playerTwo)) return "Advantage " + playerOne.name();
        return "Advantage " + playerTwo.name();
    }

    private String buildPlayerOneScore() {
        return scoreTranslations.get(playerOne.points());
    }

    private String buildPlayerTwoScore() {
        return scoreTranslations.get(playerTwo.points());
    }

    public void playerOneHits() {
        if(playerOne.winsTo(playerTwo)) throw new GameAlreadyWonException("cannot score another point");

        playerOne.hits();
    }

    public void playerTwoHits() {
        if(playerTwo.winsTo(playerOne)) throw new GameAlreadyWonException("cannot score another point");

        playerTwo.hits();
    }

}