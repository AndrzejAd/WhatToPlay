package whattoplay.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

/**
 *
 * @author Andrzej
 */
@JsonDeserialize( as = BaseGameDto.class)
public interface GameDto {
    long getGameId();
    String getGameName();
    void setGameName(String gameName);
    String getProducer();
    void setProducer(String producer);
    String getPublisher();
    void setPublisher(String publisher);
    Date getDatePublished();
    void setDatePublished(Date datePublished);
    int getPrice();
    void setPrice(int price);
    String getGenre();
    void setGenre(String genre);
    String getImagePath();
    void setImagePath(String imagePath);
}
