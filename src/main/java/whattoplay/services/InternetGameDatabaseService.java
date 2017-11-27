package whattoplay.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by Andrzej on 2017-11-21.
 */
@Service
public class InternetGameDatabaseService {
    private final String token = "Bearer T1RLAQKE/yKcuq/qI1iolCdA5nAnYH5nbBBqI+rL1LuPptZ7Dxa5aqj4AADAlVylMTrrj1bK7yTJEbBTBeAhfk1vxt1XEA+2OP5xuYFQ+creg+xXjCbqh7EmIhkA4H/JRqoWcg9BoiQ3zVGEK9dNQeijoDsqBh6wC6X+Kd62AFTpSRecYptdeiISmVWfbOsrhCaUtAs1/tEf7k7JXsSU2xB+ItjVlp7pwc8qW4BI+DpTFR8q8ykG0BznQTIaZITECDk9B2DCY/0/rTaSbvaUGAonoYv1GDpYBkdnQqllX9N2j3MfQfmjfd1Jh0X5";
    private final String requestUrl = "https://api.test.sabre.com/v1/lists/utilities/geocode/locations";

    public void test(){
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(requestUrl);
        HttpResponse response;

    }

}
