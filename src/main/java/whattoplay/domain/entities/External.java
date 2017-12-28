package whattoplay.domain.entities;

import javax.persistence.Embeddable;

@Embeddable
public class External{
    private String steam;

    public String getSteam() {
        return steam;
    }

    public void setSteam(String steam) {
        this.steam = steam;
    }

}