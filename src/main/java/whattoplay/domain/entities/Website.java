package whattoplay.domain.entities;

/**
 * Created by Andrzej on 2017-12-11.
 */
public class Website {
    private WebsiteCategory category;
    private String url;

    public WebsiteCategory getCategory() {
        return category;
    }

    public void setCategory(int category) {
        for ( WebsiteCategory e : WebsiteCategory.values()){
            if ( e.getCategory() == category){
                this.category = e;
                return;
            }
        }
        this.category = WebsiteCategory.OFFICIAL;
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
                "category=" + category +
                ", url='" + url + '\'' +
                '}';
    }
}
