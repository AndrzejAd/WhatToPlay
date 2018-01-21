package whattoplay.domain.dto;

public enum EsrbRating {
    RP(1), EC(2), E(3), E10(4), T(5), M(6), AO(7), UNKNOWN(8);

    private final int esrbRating;

    EsrbRating(int gameRating) {
        this.esrbRating = gameRating;
    }

    public int getEsrbRating() {
        return esrbRating;
    }

}