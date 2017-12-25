package whattoplay.domain.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import whattoplay.domain.dto.Status;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrzej on 2017-12-05.
 */
public class GameJson implements Serializable {
    private long id;
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
    private TimeToBeat timeToBeat;
    private Esrb esrb;
    private Pegi pegi;
    private Status status;
    private External external;
    private ImageInfo cover;
    private List<Long> developersIds;
    private List<Long> playerPerspectivesIds;
    private List<Long> gameModesIds;
    private List<Long> genresIds;
    private List<Website> websites;
    private List<ImageInfo> screenshots;

    public GameJson() {
        developersIds = new ArrayList<>();
        playerPerspectivesIds = new ArrayList<>();
        gameModesIds = new ArrayList<>();
        genresIds = new ArrayList<>();
        websites = new ArrayList<>();
        screenshots = new ArrayList<>();
        status = Status.RELEASED;
    }

    @Override
    public String toString() {
        return "GameJson{" +
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

    @JsonGetter("collection")
    public long getCollectionId() {
        return collectionId;
    }

    @JsonSetter("collection")
    public void setCollectionId(long collectionId) {
        this.collectionId = collectionId;
    }

    @JsonGetter("franchise")
    public long getFranchiseId() {
        return franchiseId;
    }

    @JsonSetter("franchise")
    public void setFranchiseId(long franchiseId) {
        this.franchiseId = franchiseId;
    }

    @JsonGetter("developers")
    public List<Long> getDevelopersIds() {
        return developersIds;
    }

    @JsonSetter("developers")
    public void setDevelopersIds(List<Long> developersIds) {
        this.developersIds = developersIds;
    }

    @JsonGetter("time_to_beat")
    public TimeToBeat getTimeToBeat() {
        return timeToBeat;
    }

    @JsonSetter("time_to_beat")
    public void setTimeToBeat(TimeToBeat timeToBeat) {
        this.timeToBeat = timeToBeat;
    }

    @JsonGetter("player_perspectives")
    public List<Long> getPlayerPerspectivesIds() {
        return playerPerspectivesIds;
    }

    @JsonSetter("player_perspectives")
    public void setPlayerPerspectivesIds(List<Long> playerPerspectivesIds) {
        this.playerPerspectivesIds = playerPerspectivesIds;
    }

    @JsonGetter("game_modes")
    public List<Long> getGameModesIds() {
        return gameModesIds;
    }

    @JsonSetter("game_modes")
    public void setGameModesIds(List<Long> gameModesIds) {
        this.gameModesIds = gameModesIds;
    }

    @JsonGetter("genres")
    public List<Long> getGenresIds() {
        return genresIds;
    }

    @JsonSetter("genres")
    public void setGenresIds(List<Long> genresIds) {
        this.genresIds = genresIds;
    }

    @JsonGetter("status")
    public Status getStatus() {
        return status;
    }

    @JsonSetter("status")
    public void setStatus(int status) {
        for (Status e : Status.values()) {
            if (e.getGameStatus() == status) {
                this.status = e;
                return;
            }
        }
        this.status = Status.RELEASED;
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

    public List<Website> getWebsites() {
        return websites;
    }

    public void setWebsites(List<Website> websites) {
        this.websites = websites;
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

    public List<ImageInfo> getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(List<ImageInfo> screenshots) {
        this.screenshots = screenshots;
    }
}
