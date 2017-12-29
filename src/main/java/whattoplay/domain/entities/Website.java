package whattoplay.domain.entities;

import com.fasterxml.jackson.annotation.JsonSetter;
import whattoplay.domain.dto.WebsiteCategory;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Website {

    @Enumerated(EnumType.STRING)
    private WebsiteCategory websiteCategory;
    private String url;

    public Website(WebsiteCategory websiteCategory, String url) {
        this.websiteCategory = websiteCategory;
        this.url = url;
    }

    public Website(int websiteCategory, String url) {
        setWebsiteCategory(websiteCategory);
        this.url = url;
    }

    public Website() {
    }

    public WebsiteCategory getWebsiteCategory() {
        return websiteCategory;
    }

    @JsonSetter("category")
    public void setWebsiteCategory(int websiteCategory) {
        for ( WebsiteCategory e : WebsiteCategory.values()){
            if ( e.getCategory() == websiteCategory){
                this.websiteCategory = e;
                return;
            }
        }
        this.websiteCategory = WebsiteCategory.OFFICIAL;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Website{" +
                "websiteCategory=" + websiteCategory +
                ", url='" + url + '\'' +
                '}';
    }
}
