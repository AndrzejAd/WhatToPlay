package whattoplay.domain.entities;

import com.fasterxml.jackson.annotation.JsonSetter;
import whattoplay.domain.dto.PegiRating;

import javax.persistence.Embeddable;

@Embeddable
public class Pegi {
    private PegiRating pegiRating;
    private String pegiSynopsis;

    public PegiRating getPegiRating() {
        return pegiRating;
    }

    @JsonSetter("rating")
    public void setPegiRating(int pegiRating) {
        for ( PegiRating e : PegiRating.values()){
            if ( e.getPegiRating() == pegiRating){
                this.pegiRating = e;
                return;
            }
        }
        this.pegiRating = PegiRating.EIGHTEEN;
    }

    public String getPegiSynopsis() {
        return pegiSynopsis;
    }

    @JsonSetter("synopsis")
    public void setPegiSynopsis(String pegiSynopsis) {
        this.pegiSynopsis = pegiSynopsis;
    }

    public void setRating(PegiRating rating) {
        this.pegiRating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pegi)) return false;

        Pegi pegi = (Pegi) o;

        if (pegiRating != pegi.pegiRating) return false;
        return pegiSynopsis != null ? pegiSynopsis.equals(pegi.pegiSynopsis) : pegi.pegiSynopsis == null;
    }

    @Override
    public int hashCode() {
        int result = pegiRating != null ? pegiRating.hashCode() : 0;
        result = 31 * result + (pegiSynopsis != null ? pegiSynopsis.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pegi{" +
                "pegiRating=" + pegiRating +
                ", pegiSynopsis='" + pegiSynopsis + '\'' +
                '}';
    }
}
