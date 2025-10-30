package entity;

import java.time.LocalDateTime;

public record Subject(
        String name,
        Integer ects,
        LocalDateTime dateTime,
        String location,
        Integer duration
) implements Schedulable{

    public Subject{

        if(ects < 0){
            throw new IllegalArgumentException("Broj ECTS bodova mora biti veći od 0!");
        }
        if(duration < 0){
            throw new IllegalArgumentException("Broj minuta mora biti veći od 0!");
        }

    }

}
