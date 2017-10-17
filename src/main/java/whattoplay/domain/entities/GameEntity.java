package whattoplay.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 *
 * @author Andrzej
 */
@Entity
@Table(name = "Games")
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name ="[Game Id]", nullable = false )
    private long gameId;

    @NotNull
    @Column(name ="[Game Name]", nullable = false)
    private String gameName;

    @NotNull
    @Column(name ="Producer", nullable = false)
    private String producer;

    @NotNull
    @Column(name ="Publisher", nullable = false)
    private String publisher;
    
    @Column(name ="[Date Published]", nullable = true)
    private Date datePublished;

    @Column(name ="Price", nullable = true)
    private int price;

    @Column(name ="Genre", nullable = true)
    private String genre;

    @Column(name ="[Image Path]", nullable = true)
    private String imagePath;
    
    public GameEntity() { }

    public GameEntity(String gameName, String producer, String publisher, Date datePublished, int price) {
        this.gameName = gameName;
        this.producer = producer;
        this.publisher = publisher;
        this.datePublished = datePublished;
        this.price = price;
    }

    public GameEntity(String gameName, String producer, String publisher, Date datePublished, int price, String genre) {
        this.gameName = gameName;
        this.producer = producer;
        this.publisher = publisher;
        this.datePublished = datePublished;
        this.price = price;
        this.genre = genre;
    }

    public GameEntity(String gameName, String producer, String publisher, Date datePublished, int price, String genre, String imagePath) {
        this.gameName = gameName;
        this.producer = producer;
        this.publisher = publisher;
        this.datePublished = datePublished;
        this.price = price;
        this.genre = genre;
        this.imagePath = imagePath;
    }
    

    @Override
    public String toString() {
        return "Game{" + "gameId=" + gameId + ", gameName=" + gameName + ", "
                + "producer=" + producer + ", publisher=" + publisher + ", datePublished=" + datePublished + ", "
                + "price=" + price + ", genre=" + genre + ", imagePath=" + imagePath + "}";
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
  
    
  

}