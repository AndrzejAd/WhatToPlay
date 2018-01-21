package whattoplay.domain.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
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
@Table(name = "Developers")
public class Developer {
    @Id
    @Column(name ="id", nullable = false)
    @NotNull
    private long id;

    @Column(name ="name", nullable = false)
    @NotNull
    private String name;

    @Column(name ="url", nullable = true )
    private String url;

    @Column(name ="description", nullable = true )
    private String description;

    @Column(name ="website", nullable = true )
    private String website;

    @Column(name ="start_date", nullable = true )
    private LocalDate startDate;

    @Column(name ="image_url", nullable = true )
    private String developerImageUrl;

    @Column(name ="image_cloudinary_id", nullable = true )
    private String developerImageCloudinaryId;

    @Column(name ="image_width", nullable = true )
    private int developerImageWidth;

    @Column(name ="image_height", nullable = true )
    private int developerImageHeight;


    public Developer() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @JsonGetter("start_date")
    public LocalDate getStartDate() {
        return startDate;
    }

    @JsonSetter("start_date")
    public void setStartDate(long startDate) {
        this.startDate = Instant.ofEpochMilli(startDate).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public String getDeveloperImageUrl() {
        return developerImageUrl;
    }

    @JsonSetter("logo")
    public void setDeveloperImageUrl(ImageInfo developerImageUrl) {
        this.developerImageUrl = developerImageUrl.getImageUrl();
        this.developerImageCloudinaryId = developerImageUrl.getCloudinaryId();
        this.developerImageWidth = developerImageUrl.getWidth();
        this.developerImageHeight = developerImageUrl.getHeight();
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setDeveloperImageUrl(String developerImageUrl) {
        this.developerImageUrl = developerImageUrl;
    }

    public String getDeveloperImageCloudinaryId() {
        return developerImageCloudinaryId;
    }

    public void setDeveloperImageCloudinaryId(String developerImageCloudinaryId) {
        this.developerImageCloudinaryId = developerImageCloudinaryId;
    }

    public int getDeveloperImageWidth() {
        return developerImageWidth;
    }

    public void setDeveloperImageWidth(int developerImageWidth) {
        this.developerImageWidth = developerImageWidth;
    }

    public int getDeveloperImageHeight() {
        return developerImageHeight;
    }

    public void setDeveloperImageHeight(int developerImageHeight) {
        this.developerImageHeight = developerImageHeight;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", website='" + website + '\'' +
                ", startDate=" + startDate +
                ", developerImageUrl='" + developerImageUrl + '\'' +
                ", developerImageCloudinaryId='" + developerImageCloudinaryId + '\'' +
                ", developerImageWidth=" + developerImageWidth +
                ", developerImageHeight=" + developerImageHeight +
                '}';
    }
}
