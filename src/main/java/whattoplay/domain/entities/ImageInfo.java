package whattoplay.domain.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

//
public class ImageInfo {
    private long id;
    private long gameId;
    private String url;
    private String cloudinaryId;
    private int width;
    private int height;

    public long getId() {
        return id;
    }

    public long getGameId() {
        return gameId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonGetter("cloudinary_id")
    public String getCloudinaryId() {
        return cloudinaryId;
    }

    @JsonSetter("cloudinary_id")
    public void setCloudinaryId(String cloudinaryId) {
        this.cloudinaryId = cloudinaryId;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "ImageInfo{" +
                "url='" + url + '\'' +
                ", cloudinaryId='" + cloudinaryId + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
