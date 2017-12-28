package whattoplay.domain.entities;

import whattoplay.domain.dto.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Games")
public class Game implements Serializable {
    @Id
    @Column( name ="id", nullable = false)
    private long id;

    @Column( name="name", nullable = false)
    private String name;

    @Column( name="slug", nullable = true)
    private String slug;

    @Column( name="url", nullable = true)
    private String url;

    @Column( name="summary", nullable = true)
    private String summary;

    @Column( name="storyline", nullable = true)
    private String storyline;

    @Column( name="hypes", nullable = true)
    private int hypes;

    @Column( name="popularity", nullable = true)
    private double popularity;

    @Column( name="rating", nullable = true)
    private double rating;

    @Column( name="rating_count", nullable = true)
    private int ratingCount;

    @Column( name="aggregated_rating", nullable = true)
    private double aggregatedRating;

    @Column( name="aggregated_rating_count", nullable = true)
    private int aggregatedRatingCount;

    @Column( name="total_rating", nullable = true)
    private double totalRating;

    @Column( name="total_rating_count", nullable = true)
    private int totalRatingCount;

    @Column( name="collection_id", nullable = true)
    private long collectionId;

    @Column( name="franchise_id", nullable = true)
    private long franchiseId;

    @Column( name="created_at", nullable = true)
    private LocalDate createdAt;

    @Column( name="updated_at", nullable = true)
    private LocalDate updatedAt;

    @Column( name="first_release_date", nullable = true)
    private LocalDate firstReleaseDate;

    @Column( name="status", nullable = true)
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

    public Game() {
    }

    public Game(long id, String name, String slug, String url, String summary, String storyline, int hypes,
                double popularity, double rating, int ratingCount, double aggregatedRating, int aggregatedRatingCount,
                double totalRating, int totalRatingCount, long collectionId, long franchiseId, LocalDate createdAt,
                LocalDate updatedAt, LocalDate firstReleaseDate, Status status, TimeToBeat timeToBeat, Esrb esrb,
                Pegi pegi, External external, ImageInfo cover) {
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
}