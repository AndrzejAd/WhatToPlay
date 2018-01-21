package whattoplay.domain.entities;

import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Entity
@Table(name="Collections")
public class Collection {
    @Id
    @Column(nullable = false)
    @NotNull
    private long id;

    @Column(nullable = false)
    @NotNull
    private String name;

    private String url;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public Collection() {
    }

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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    @JsonSetter("created_at")
    public void setCreatedAt(long createdAt) {
        this.createdAt = Instant.ofEpochMilli(createdAt).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    @JsonSetter("updated_at")
    public void setUpdatedAt(long createdAt) {
        this.updatedAt = Instant.ofEpochMilli(createdAt).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
