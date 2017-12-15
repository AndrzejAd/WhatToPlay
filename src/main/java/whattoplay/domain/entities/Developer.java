package whattoplay.domain.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.time.LocalDate;

/**
 * Created by Andrzej on 2017-12-12.
 */
public class Developer {
    private long id;
    private String name;
    private String url;
    private GameCover logo;
    private String description;
    private String website;
    private LocalDate startDate;

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

    public GameCover getLogo() {
        return logo;
    }

    public void setLogo(GameCover logo) {
        this.logo = logo;
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
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Developer)) return false;

        Developer developer = (Developer) o;

        if (id != developer.id) return false;
        if (!name.equals(developer.name)) return false;
        if (url != null ? !url.equals(developer.url) : developer.url != null) return false;
        if (logo != null ? !logo.equals(developer.logo) : developer.logo != null) return false;
        if (description != null ? !description.equals(developer.description) : developer.description != null)
            return false;
        if (website != null ? !website.equals(developer.website) : developer.website != null) return false;
        return startDate != null ? startDate.equals(developer.startDate) : developer.startDate == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (logo != null ? logo.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", logo=" + logo +
                ", description='" + description + '\'' +
                ", website='" + website + '\'' +
                ", startDate=" + startDate +
                '}';
    }
}
