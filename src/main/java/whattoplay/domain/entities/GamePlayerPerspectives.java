package whattoplay.domain.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name ="Games_Player_Perspectives")
public class GamePlayerPerspectives {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private long gameId;
    private short player_perspective_id;

    public GamePlayerPerspectives() {
    }

    public GamePlayerPerspectives(long gameId, short player_perspective_id) {
        this.gameId = gameId;
        this.player_perspective_id = player_perspective_id;
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

    public short getPlayer_perspective_id() {
        return player_perspective_id;
    }

    public void setPlayer_perspective_id(short player_perspective_id) {
        this.player_perspective_id = player_perspective_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GamePlayerPerspectives)) return false;
        GamePlayerPerspectives that = (GamePlayerPerspectives) o;
        return id == that.id &&
                gameId == that.gameId &&
                player_perspective_id == that.player_perspective_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gameId, player_perspective_id);
    }

    @Override
    public String toString() {
        return "GamePlayerPerspectives{" +
                "id=" + id +
                ", gameId=" + gameId +
                ", player_perspective_id=" + player_perspective_id +
                '}';
    }
}
