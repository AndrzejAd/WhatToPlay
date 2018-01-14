package whattoplay.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name ="Games_Websites")
public class GameWebsites {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NotNull
    private long id;
    private long gameId;

    @Embedded
    private Website website;

    public GameWebsites() {
    }

    public GameWebsites(long gameId, Website website) {
        this.gameId = gameId;
        this.website = website;
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

    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameWebsites)) return false;
        GameWebsites that = (GameWebsites) o;
        return id == that.id &&
                gameId == that.gameId &&
                Objects.equals(website, that.website);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, gameId, website);
    }

    @Override
    public String toString() {
        return "GameWebsites{" +
                "id=" + id +
                ", gameId=" + gameId +
                ", website=" + website +
                '}';
    }
}
