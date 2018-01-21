package whattoplay.domain.dto;

public enum Status{
    RELEASED (0), ALPHA (2), BETA(3), EARLY_ACCESS(4), OFFLINE(5), CANCELLED(6), UNKNOWN(7);

    private final int gameStatus;

    Status(int levelCode) {
        this.gameStatus = levelCode;
    }

    public int getGameStatus() {
        return gameStatus;
    }
}