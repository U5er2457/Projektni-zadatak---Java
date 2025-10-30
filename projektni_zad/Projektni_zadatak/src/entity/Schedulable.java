package entity;

import java.time.LocalDateTime;

public interface Schedulable {
    LocalDateTime dateTime();
    String location();
    Integer duration();
}
