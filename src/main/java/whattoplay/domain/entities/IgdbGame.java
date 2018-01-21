package whattoplay.domain.entities;

import whattoplay.domain.dto.Status;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Games")
public class IgdbGame implements Serializable {
    @Id
    @Column(nullable = false)
    @NotNull
    private long id;

    @Column(nullable = false)
    @NotNull
    private String name;
    private String slug;
    private String url;
    private String summary;
    private String storyline;
    private int hypes;
    private double popularity;
    private double rating;
    private int ratingCount;
    private double aggregatedRating;
    private int aggregatedRatingCount;
    private double totalRating;
    private int totalRatingCount;
    private long collectionId;
    private long franchiseId;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate firstReleaseDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Embedded
    private TimeToBeat timeToBeat;

    @Embedded
    private Esrb esrb;

    @Embedded
    private Pegi pegi;

    @Embedded
    private External external;

    @Embedded
    private ImageInfo cover;

    public IgdbGame() {
    }

    public IgdbGame(long id, String name, String slug, String url, String summary, String storyline, int hypes,
                    double popularity, double rating, int ratingCount, double aggregatedRating, int aggregatedRatingCount,
                    double totalRating, int totalRatingCount, long collectionId, long franchiseId, LocalDate createdAt,
                    LocalDate updatedAt, LocalDate firstReleaseDate, Status status,
                    TimeToBeat timeToBeat, Esrb esrb, Pegi pegi, External external, ImageInfo cover) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.url = url;
        this.summary = summary;
        this.storyline = storyline;
        this.hypes = hypes;
        this.popularity = popularity;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.aggregatedRating = aggregatedRating;
        this.aggregatedRatingCount = aggregatedRatingCount;
        this.totalRating = totalRating;
        this.totalRatingCount = totalRatingCount;
        this.collectionId = collectionId;
        this.franchiseId = franchiseId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.firstReleaseDate = firstReleaseDate;
        this.status = status;
        this.timeToBeat = timeToBeat;
        this.esrb = esrb;
        this.pegi = pegi;
        this.external = external;
        this.cover = cover;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStoryline() {
        return storyline;
    }

    public void setStoryline(String storyline) {
        this.storyline = storyline;
    }

    public int getHypes() {
        return hypes;
    }

    public void setHypes(int hypes) {
        this.hypes = hypes;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public double getAggregatedRating() {
        return aggregatedRating;
    }

    public void setAggregatedRating(double aggregatedRating) {
        this.aggregatedRating = aggregatedRating;
    }

    public int getAggregatedRatingCount() {
        return aggregatedRatingCount;
    }

    public void setAggregatedRatingCount(int aggregatedRatingCount) {
        this.aggregatedRatingCount = aggregatedRatingCount;
    }

    public double getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(double totalRating) {
        this.totalRating = totalRating;
    }

    public int getTotalRatingCount() {
        return totalRatingCount;
    }

    public void setTotalRatingCount(int totalRatingCount) {
        this.totalRatingCount = totalRatingCount;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDate getFirstReleaseDate() {
        return firstReleaseDate;
    }

    public void setFirstReleaseDate(LocalDate firstReleaseDate) {
        this.firstReleaseDate = firstReleaseDate;
    }

    public TimeToBeat getTimeToBeat() {
        return timeToBeat;
    }

    public void setTimeToBeat(TimeToBeat timeToBeat) {
        this.timeToBeat = timeToBeat;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Esrb getEsrb() {
        return esrb;
    }

    public void setEsrb(Esrb esrb) {
        this.esrb = esrb;
    }

    public Pegi getPegi() {
        return pegi;
    }

    public void setPegi(Pegi pegi) {
        this.pegi = pegi;
    }

    public External getExternal() {
        return external;
    }

    public void setExternal(External external) {
        this.external = external;
    }

    public ImageInfo getCover() {
        return cover;
    }

    public void setCover(ImageInfo cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "IgdbGame{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", url='" + url + '\'' +
                ", summary='" + summary + '\'' +
                ", storyline='" + storyline + '\'' +
                ", hypes=" + hypes +
                ", popularity=" + popularity +
                ", rating=" + rating +
                ", ratingCount=" + ratingCount +
                ", aggregatedRating=" + aggregatedRating +
                ", aggregatedRatingCount=" + aggregatedRatingCount +
                ", totalRating=" + totalRating +
                ", totalRatingCount=" + totalRatingCount +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", firstReleaseDate=" + firstReleaseDate +
                ", status=" + status +
                ", timeToBeat=" + timeToBeat +
                ", esrb=" + esrb +
                ", pegi=" + pegi +
                ", external=" + external +
                ", cover=" + cover +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IgdbGame)) return false;
        IgdbGame igdbGame = (IgdbGame) o;
        return id == igdbGame.id &&
                hypes == igdbGame.hypes &&
                Double.compare(igdbGame.popularity, popularity) == 0 &&
                Double.compare(igdbGame.rating, rating) == 0 &&
                ratingCount == igdbGame.ratingCount &&
                Double.compare(igdbGame.aggregatedRating, aggregatedRating) == 0 &&
                aggregatedRatingCount == igdbGame.aggregatedRatingCount &&
                Double.compare(igdbGame.totalRating, totalRating) == 0 &&
                totalRatingCount == igdbGame.totalRatingCount &&
                collectionId == igdbGame.collectionId &&
                franchiseId == igdbGame.franchiseId &&
                Objects.equals(name, igdbGame.name) &&
                Objects.equals(slug, igdbGame.slug) &&
                Objects.equals(url, igdbGame.url) &&
                Objects.equals(summary, igdbGame.summary) &&
                Objects.equals(storyline, igdbGame.storyline) &&
                Objects.equals(createdAt, igdbGame.createdAt) &&
                Objects.equals(updatedAt, igdbGame.updatedAt) &&
                Objects.equals(firstReleaseDate, igdbGame.firstReleaseDate) &&
                status == igdbGame.status &&
                Objects.equals(timeToBeat, igdbGame.timeToBeat) &&
                Objects.equals(esrb, igdbGame.esrb) &&
                Objects.equals(pegi, igdbGame.pegi) &&
                Objects.equals(external, igdbGame.external) &&
                Objects.equals(cover, igdbGame.cover);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, slug, url, summary, storyline, hypes, popularity, rating, ratingCount,
                aggregatedRating, aggregatedRatingCount, totalRating, totalRatingCount, collectionId,
                franchiseId, createdAt, updatedAt, firstReleaseDate, status,
                timeToBeat, esrb, pegi, external, cover);
    }

    public long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(long collectionId) {
        this.collectionId = collectionId;
    }

    public long getFranchiseId() {
        return franchiseId;
    }

    public void setFranchiseId(long franchiseId) {
        this.franchiseId = franchiseId;
    }
}