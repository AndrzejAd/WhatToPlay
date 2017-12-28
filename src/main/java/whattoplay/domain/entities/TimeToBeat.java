package whattoplay.domain.entities;

import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.Embeddable;
import java.time.Duration;
import java.util.Optional;

@Embeddable
public class TimeToBeat {
    private Duration hastly;
    private Duration normally;
    private Duration completely;

    public TimeToBeat() {
    }

    public TimeToBeat(Duration hastly, Duration normally, Duration completely) {
        this.hastly = hastly;
        this.normally = normally;
        this.completely = completely;
    }

    public Duration getHastly() {
        return hastly;
    }

    @JsonSetter("hastly")
    public void setHastly(long hastly) {
        this.hastly = Duration.ofSeconds(hastly);
    }

    public Duration getNormally() {
        return normally;
    }

    @JsonSetter("normally")
    public void setNormally(long normally) {
        this.normally = Duration.ofSeconds(normally);
    }

    public Duration getCompletely() {
        return completely;
    }

    @JsonSetter("completely")
    public void setCompletely(long completely) {
        this.completely = Duration.ofSeconds(completely);
    }

    @Override
    public String toString() {
        return "TimeToBeat{" +
                "hastly=" + Optional.ofNullable( hastly ).map( Duration::toMinutes )+
                ", normally=" + Optional.ofNullable( normally ).map( Duration::toMinutes ) +
                ", completely=" + Optional.ofNullable( completely ).map( Duration::toMinutes ) +
                '}';
    }
}
