import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightsFilter flightsFilter = new FlightsFilter();
        System.out.println("===========================ALL FLIGHTS===============================");
        System.out.println(flights);
        System.out.println("=====================BEFORE CURRENT TIME FLIGHTS=====================");
        System.out.println(flightsFilter.filter(flights, FilterParameters.beforeSetTime(LocalDateTime.now())));
        System.out.println("=====================ARRIVES BEFORE IT DEPARTS=======================");
        System.out.println(flightsFilter.filter(flights, FilterParameters.arrivesBeforeDeparts()));
        System.out.println("=====================MORE THEN 2 HOURS GROUND TIME===================");
        System.out.println(flightsFilter.filter(flights, FilterParameters.moreThanSetGroundTime(2)));
    }
}
