package whattoplay.domain.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Created by Andrzej on 2017-12-12.
 */
@Entity
@Table(name = "[Game Modes]")
public class GameMode {
    @Id
    @Column(name ="[Game Mode Id]", nullable = false)
    private long id;

    @Column(name ="[Game Mode Name]", nullable = false)
    private String name;

    @Column(name ="url", nullable = true)
    private String url;

    @Column(name ="created_At", nullable = true)
    private LocalDate createdAt;

    @Column(name ="updated_At", nullable = true)
    private LocalDate updatedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonGetter("created_at")
    public LocalDate getCreatedAt() {
        return createdAt;
    }

    @JsonSetter("created_at")
    public void setCreatedAt(long createdAt) {
        this.createdAt = Instant.ofEpochMilli(createdAt).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @JsonGetter("updated_at")
    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    @JsonSetter("updated_at")
    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = Instant.ofEpochMilli(updatedAt).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "GameMode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
