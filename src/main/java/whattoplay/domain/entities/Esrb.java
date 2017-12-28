package whattoplay.domain.entities;

import com.fasterxml.jackson.annotation.JsonSetter;
import whattoplay.domain.dto.EsrbRating;

import javax.persistence.*;

@Embeddable
public class Esrb {
    private EsrbRating esrbRating;
    private String esrbSynopsis;

    public EsrbRating getEsrbRating() {
        return esrbRating;
    }

    @JsonSetter("rating")
    public void setEsrbRating(int esrbRating) {
        for ( EsrbRating e : EsrbRating.values()){
            if ( e.getEsrbRating() == esrbRating){
                this.esrbRating = e;
                return;
            }
        }
        this.esrbRating = EsrbRating.UNKNOWN;
    }

    public void setRating(EsrbRating rating) {
        this.esrbRating = rating;
    }

    public String getEsrbSynopsis() {
        return esrbSynopsis;
    }

    @JsonSetter("synopsis")
    public void setEsrbSynopsis(String esrbSynopsis) {
        this.esrbSynopsis = esrbSynopsis;
    }

    @Override
    public String toString() {
        return "Esrb{" +
                "esrbRating=" + esrbRating +
                ", esrbSynopsis='" + esrbSynopsis + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Esrb)) return false;

        Esrb esrb = (Esrb) o;

        if (esrbRating != esrb.esrbRating) return false;
        return esrbSynopsis != null ? esrbSynopsis.equals(esrb.esrbSynopsis) : esrb.esrbSynopsis == null;
    }

    @Override
    public int hashCode() {
        int result = esrbRating != null ? esrbRating.hashCode() : 0;
        result = 31 * result + (esrbSynopsis != null ? esrbSynopsis.hashCode() : 0);
        return result;
    }
}
