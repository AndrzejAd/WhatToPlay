package whattoplay.domain.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Created by Andrzej on 2017-12-11.
 */
public class GameCover {
    private String url;
    private String cloudinaryId;
    private int width;
    private int height;


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
        return "GameCover{" +
                "url='" + url + '\'' +
                ", cloudinaryId='" + cloudinaryId + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
