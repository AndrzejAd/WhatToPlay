package whattoplay.domain.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Created by Andrzej on 2017-12-05.
 */
public class GameJsonDto implements Serializable {
    private long id;
    private String name;
    private String slug;
    private String url;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate firstReleaseDate;
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
    //private Series collection;
    //private Franchise franchise;
    //private Developer developers;
    //private TimeToBeat timeToBeat;
    //private PlayerPerspectives playerPerspectives;
    //private GameModes gameModes;
    //private Genres genres;
    //private GameStatus status;
    //private Image cover;
    //private ESRB esrb;
    //private PEGI pegi;
    //private Websites websites;
    //private External external;


    @Override
    public String toString() {
        return "GameJsonDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", url='" + url + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", firstReleaseDate=" + firstReleaseDate +
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
                '}';
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

    @JsonGetter("first_release_date")
    public LocalDate getFirstReleaseDate() {
        return firstReleaseDate;
    }

    @JsonSetter("first_release_date")
    public void setFirstReleaseDate(long firstReleaseDate) {
        this.firstReleaseDate = Instant.ofEpochMilli(firstReleaseDate).atZone(ZoneId.systemDefault()).toLocalDate();
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

    @JsonGetter("rating_count")
    public int getRatingCount() {
        return ratingCount;
    }

    @JsonSetter("rating_count")
    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    @JsonGetter("aggregated_rating")
    public double getAggregatedRating() {
        return aggregatedRating;
    }

    @JsonSetter("aggregated_rating")
    public void setAggregatedRating(double aggregatedRating) {
        this.aggregatedRating = aggregatedRating;
    }

    @JsonGetter("aggregated_rating_count")
    public int getAggregatedRatingCount() {
        return aggregatedRatingCount;
    }

    @JsonSetter("aggregated_rating_count")
    public void setAggregatedRatingCount(int aggregatedRatingCount) {
        this.aggregatedRatingCount = aggregatedRatingCount;
    }

    @JsonGetter("total_rating")
    public double getTotalRating() {
        return totalRating;
    }

    @JsonSetter("total_rating")
    public void setTotalRating(double totalRating) {
        this.totalRating = totalRating;
    }

    @JsonGetter("total_rating_count")
    public int getTotalRatingCount() {
        return totalRatingCount;
    }

    @JsonSetter("total_rating_count")
    public void setTotalRatingCount(int totalRatingCount) {
        this.totalRatingCount = totalRatingCount;
    }

}
