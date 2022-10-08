public class GameAlreadyWonException extends RuntimeException {
    public GameAlreadyWonException() {
        super("cannot score another point");
    }
}
