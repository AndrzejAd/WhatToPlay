package whattoplay.domain.entities;

/**
 * Created by Andrzej on 2017-12-11.
 */
public enum PegiRating {
    THREE(1), SEVEN(2), TWELVE(3), SIXTEEN(4), EIGHTEEN(5);

    private final int pegiRating;

    PegiRating(int pegiRating) {
        this.pegiRating = pegiRating;
    }

    public int getPegiRating() {
        return pegiRating;
    }
}
