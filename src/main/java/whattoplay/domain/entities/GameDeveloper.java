package whattoplay.domain.entities;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Games_Developers")
public class GameDeveloper {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private long gameId;
    private long developerId;

    public GameDeveloper() {
    }

    public GameDeveloper(long gameId, long developerId) {
        this.gameId = gameId;
        this.developerId = developerId;
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

    public long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(long developerId) {
        this.developerId = developerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameDeveloper)) return false;
        GameDeveloper that = (GameDeveloper) o;
        return id == that.id &&
                gameId == that.gameId &&
                developerId == that.developerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gameId, developerId);
    }

    @Override
    public String toString() {
        return "GameDeveloper{" +
                "id=" + id +
                ", gameId=" + gameId +
                ", developerId=" + developerId +
                '}';
    }
}
