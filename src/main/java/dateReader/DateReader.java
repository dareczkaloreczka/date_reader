package dateReader;
//10. (Poziom 2) ZnajdŸ program (w jednym z projektów z poprzednich zajêæ) wyszukuj¹cy daty i godzinê w napisach.
// Jego metoda getDate i getTime zwraca³a obiekt LocalDate lub LocalTime w przypadku powodzenia oraz null w przypadku niepowodzenia.
// Wykorzystaj go do utworzenia listy Optional<LocalDate> i Optional<LocalTime> na podstawie wczytanego z pliku tekstu zawieraj¹cego
// poprawne daty i czas. Przetwórz listê tak, aby do nienullowych dat dodaæ jeden dzieñ, a do nienullowych czasów dodaæ jedn¹ godzinê.
// Nastêpnie wyœwietl wszytkie daty i czasy z optionali, które nie zawieraj¹ nulla. Do przetwarzania wykorzystaj odpowiedni¹ lambdê.

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateReader {

    private final String PATH = "hiddendatetime.txt";

    private Pattern datePattern = Pattern.compile("\\d{2}-\\d{2}-\\d{4}");
    private Pattern timePattern = Pattern.compile("\\d{2}:\\d{2}");

    public LocalDate getDate(String txt){
        Matcher dateMatcher = datePattern.matcher(txt);
        if (dateMatcher.find()){
            String dateString = dateMatcher.group();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localDate = LocalDate.parse(dateString, dateFormatter);
            return localDate;
        }
        else {
            return null;
        }
    }
    public LocalTime getTime (String txt){
        Matcher timeMatcher = timePattern.matcher(txt);
        if (timeMatcher.find()){
            String timeString = timeMatcher.group();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime localTime = LocalTime.parse(timeString, timeFormatter);
            return localTime;
        }
        else{
            return null;
        }
    }
    public List<Optional<LocalDate>> lookForDate(){
        String line;
        List<Optional<LocalDate>> mightbeDates = new ArrayList<>();
        try (BufferedReader txtReader = new BufferedReader(new FileReader(PATH))) {
            while (!((line = txtReader.readLine()) == null)){
                Optional<LocalDate> mightBeDate = Optional.ofNullable(getDate(line));
                mightbeDates.add(mightBeDate);
            }
            txtReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mightbeDates;
    }
    public List<Optional<LocalTime>> lookForTime(){
        String line;
        List<Optional<LocalTime>> mightBeTimes = new ArrayList<>();
        try {
            BufferedReader txtReader = new BufferedReader(new FileReader(PATH));
            while (!((line = txtReader.readLine()) == null)){
                Optional<LocalTime> mightBeTime = Optional.ofNullable(getTime(line));
                mightBeTimes.add(mightBeTime);
            }
            txtReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mightBeTimes;
    }
}
