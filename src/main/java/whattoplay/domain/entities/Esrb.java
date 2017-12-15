package whattoplay.domain.entities;

/**
 * Created by Andrzej on 2017-12-11.
 */
public class Esrb {
    private EsrbRating rating;
    private String synopsis;

    public EsrbRating getRating() {
        return rating;
    }

    public void setRating(int rating) {
        for ( EsrbRating e : EsrbRating.values()){
            if ( e.getEsrbRating() == rating){
                this.rating = e;
                return;
            }
        }
        this.rating = EsrbRating.UNKNOWN;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }


    @Override
    public String toString() {
        return "Esrb{" +
                "rating=" + rating +
                ", synopsis='" + synopsis + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Esrb)) return false;

        Esrb esrb = (Esrb) o;

        if (rating != esrb.rating) return false;
        return synopsis != null ? synopsis.equals(esrb.synopsis) : esrb.synopsis == null;
    }

    @Override
    public int hashCode() {
        int result = rating != null ? rating.hashCode() : 0;
        result = 31 * result + (synopsis != null ? synopsis.hashCode() : 0);
        return result;
    }
}
