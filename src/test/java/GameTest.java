import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class GameTest {

    private static Game game;

    public static Stream<Arguments> provideScores() {
        return Stream.of(
                Arguments.of(new Pair(0, 0), "Love All"),
                Arguments.of(new Pair(1, 0), "Fifteen-Love"),
                Arguments.of(new Pair(2, 0), "Thirty-Love"),
                Arguments.of(new Pair(3, 0), "Forty-Love"),
                Arguments.of(new Pair(0, 1), "Love-Fifteen"),
                Arguments.of(new Pair(1, 1), "Fifteen All"),
                Arguments.of(new Pair(2, 1), "Thirty-Fifteen"),
                Arguments.of(new Pair(3, 1), "Forty-Fifteen"),
                Arguments.of(new Pair(0, 2), "Love-Thirty"),
                Arguments.of(new Pair(1, 2), "Fifteen-Thirty"),
                Arguments.of(new Pair(2, 2), "Thirty All"),
                Arguments.of(new Pair(3, 2), "Forty-Thirty"),
                Arguments.of(new Pair(0, 3), "Love-Forty"),
                Arguments.of(new Pair(1, 3), "Fifteen-Forty"),
                Arguments.of(new Pair(2, 3), "Thirty-Forty"),
                Arguments.of(new Pair(3, 3), "Deuce"),
                Arguments.of(new Pair(4, 3), "Advantage Player One"),
                Arguments.of(new Pair(3, 4), "Advantage Player Two"),
                Arguments.of(new Pair(4, 4), "Deuce"),
                Arguments.of(new Pair(4, 0), "Game Player One"),
                Arguments.of(new Pair(0, 4), "Game Player Two")
        );
    }

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @ParameterizedTest
    @MethodSource("provideScores")
    public void test(Pair input, String expected) {

        for (int i = 0; i < input.getP1(); i++) {
            game.playerOneHits();
        }

        for (int i = 0; i < input.getP2(); i++) {
            game.playerTwoHits();
        }

        Assertions.assertEquals(expected, game.score());
    }

    @Test
    public void when_player_score_is_higher_than_three_and_difference_higher_than_two_throws_exception() {
        RuntimeException exception = Assertions.assertThrows(GameAlreadyWonException.class, () -> {
            game.playerOneHits();
            game.playerOneHits();
            game.playerOneHits();
            game.playerOneHits();
            game.playerOneHits();
        });

        Assertions.assertEquals("cannot score another point", exception.getMessage());
    }

    private static class Pair {
        private final int p1;
        private final int p2;

        public Pair(int p1, int p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        public int getP1() {
            return p1;
        }

        public int getP2() {
            return p2;
        }

        @Override
        public String toString() {
            return p1 + "-" + p2;
        }
    }
}