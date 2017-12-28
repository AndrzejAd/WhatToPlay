package whattoplay.domain.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;

@Embeddable
public class ImageInfo {
    private String imageUrl;
    private String cloudinaryId;
    private int width;
    private int height;

    public String getImageUrl() {
        return imageUrl;
    }

    @JsonSetter("url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
                "imageUrl='" + imageUrl + '\'' +
                ", cloudinaryId='" + cloudinaryId + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
