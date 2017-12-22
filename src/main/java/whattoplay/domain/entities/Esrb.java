package whattoplay.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Andrzej on 2017-12-11.
 */
@Entity
@Table(name = "Esrb")
public class Esrb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name ="id", nullable = false )
    private long id;

    @Column(name ="rating", nullable = false )
    private EsrbRating rating;

    @Column(name ="synopsis", nullable = true )
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

    public void setRating(EsrbRating rating) {
        this.rating = rating;
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
