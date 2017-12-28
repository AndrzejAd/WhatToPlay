package whattoplay.domain.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Games_Genres")
public class GameGenres {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private long gameId;
    private short genreId;

    public GameGenres(long gameId, short genreId) {
        this.gameId = gameId;
        this.genreId = genreId;
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

    public short getGenreId() {
        return genreId;
    }

    public void setGenreId(short genreId) {
        this.genreId = genreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameGenres)) return false;
        GameGenres that = (GameGenres) o;
        return id == that.id &&
                gameId == that.gameId &&
                genreId == that.genreId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, gameId, genreId);
    }

    @Override
    public String toString() {
        return "GameGenres{" +
                "id=" + id +
                ", gameId=" + gameId +
                ", genreId=" + genreId +
                '}';
    }
}
