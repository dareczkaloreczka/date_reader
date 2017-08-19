package dateReader;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//11. (Poziom 2) Wykonaj zadanie 10, zamiast optionali zastosuj stream - wynik powinien byæ taki sam.

public class Main_DateReader {

    public static void main(String[] args) {

        DateReader dateReader = new DateReader();

        List<Optional<LocalDate>> optionalDates = dateReader.lookForDate();
        List<Optional<LocalTime>> optionalTimes = dateReader.lookForTime();

        List<LocalDate> actualDates = new ArrayList<>();
        List<LocalTime> actualTimes = new ArrayList<>();

        for (Optional<LocalDate> optionalDate : optionalDates) {
            optionalDate.map(localDate -> localDate.plusDays(1)).ifPresent(System.out::println);

        }
        for (Optional<LocalTime> optionalTime : optionalTimes) {
            optionalTime.map(localTime -> localTime.plusMinutes(1)).ifPresent(System.out::println);
        }
        optionalDates.stream().filter(localDate -> localDate.isPresent()).forEach(localDate -> actualDates.add(localDate.orElse(null)));
        optionalTimes.stream().filter(localTime -> localTime.isPresent()).forEach(localTime -> actualTimes.add(localTime.orElse(null)));

        actualDates.stream().map(localDate -> localDate.plusDays(2)).forEach(System.out::println);
        actualTimes.stream().map(localTime -> localTime.plusMinutes(2)).forEach(System.out::println);
    }
}
