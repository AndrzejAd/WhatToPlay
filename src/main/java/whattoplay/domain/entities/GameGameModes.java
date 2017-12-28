package whattoplay.domain.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Games_Game_Modes")
public class GameGameModes {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private long gameId;
    private short gameModeId;

    public GameGameModes(long gameId, short gameModeId) {
        this.gameId = gameId;
        this.gameModeId = gameModeId;
    }

    public long getId() {
        return id;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public short getGameModeId() {
        return gameModeId;
    }

    public void setGameModeId(short gameModeId) {
        this.gameModeId = gameModeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameGameModes)) return false;
        GameGameModes that = (GameGameModes) o;
        return id == that.id &&
                gameId == that.gameId &&
                gameModeId == that.gameModeId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, gameId, gameModeId);
    }

    @Override
    public String toString() {
        return "GameGameModes{" +
                "id=" + id +
                ", gameId=" + gameId +
                ", gameModeId=" + gameModeId +
                '}';
    }
}
