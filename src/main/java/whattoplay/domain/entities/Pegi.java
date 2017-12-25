package whattoplay.domain.entities;

import whattoplay.domain.dto.PegiRating;

/**
 * Created by Andrzej on 2017-12-11.
 */
public class Pegi {
    private PegiRating rating;
    private String synopsis;

    public PegiRating getRating() {
        return rating;
    }

    public void setRating(int rating) {
        for ( PegiRating e : PegiRating.values()){
            if ( e.getPegiRating() == rating){
                this.rating = e;
                return;
            }
        }
        this.rating = PegiRating.EIGHTEEN;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setRating(PegiRating rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pegi)) return false;

        Pegi pegi = (Pegi) o;

        if (rating != pegi.rating) return false;
        return synopsis != null ? synopsis.equals(pegi.synopsis) : pegi.synopsis == null;
    }

    @Override
    public int hashCode() {
        int result = rating != null ? rating.hashCode() : 0;
        result = 31 * result + (synopsis != null ? synopsis.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pegi{" +
                "rating=" + rating +
                ", synopsis='" + synopsis + '\'' +
                '}';
    }
}
