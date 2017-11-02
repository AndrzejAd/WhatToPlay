package whattoplay.domain.dto;

import java.util.Date;

/**
 *
 * @author Andrzej
 */
public class BaseGameDto implements GameDto{
    private long gameId;
    private String gameName;
    private String producer;
    private String publisher;
    private Date datePublished;
    private int price;
    private String genre;
    private String imagePath;

    public BaseGameDto() {
    }

    public BaseGameDto(long gameId, String gameName, String producer, String publisher, Date datePublished, int price, String genre, String imagePath) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.producer = producer;
        this.publisher = publisher;
        this.datePublished = datePublished;
        this.price = price;
        this.genre = genre;
        this.imagePath = imagePath;
    }
    
    public long getGameId() {
        return gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "BaseGameDto{" +
                "gameId=" + gameId +
                ", gameName='" + gameName + '\'' +
                ", producer='" + producer + '\'' +
                ", publisher='" + publisher + '\'' +
                ", datePublished=" + datePublished +
                ", price=" + price +
                ", genre='" + genre + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
