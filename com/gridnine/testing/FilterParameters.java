import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Predicate;

public class FilterParameters {


    public static Predicate<Flight> beforeSetTime(LocalDateTime time) {
        Predicate<Flight> beforeSetTimePredicate = flight -> flight.getSegments()
                .stream()
                .anyMatch(segment -> segment.getDepartureDate().compareTo(time) < 0);
        return beforeSetTimePredicate;
    }

    public static Predicate<Flight> arrivesBeforeDeparts() {
        Predicate<Flight> arrivesBeforeDepartsPredicate = flight -> flight.getSegments()
                .stream()
                .anyMatch(segment -> segment.getArrivalDate().compareTo(segment.getDepartureDate()) < 0);
        return arrivesBeforeDepartsPredicate;
    }

    public static Predicate<Flight> moreThanSetGroundTime(int hoursOnTheGround) {
        Predicate<Flight> moreThanSetGroungTimePredicate = flight -> differenceBetweenHours(flight.getSegments(), hoursOnTheGround);
        return moreThanSetGroungTimePredicate;
    }


    private static boolean differenceBetweenHours(List<Segment> segments, int hourOnGround) {
        int hoursOnGroundCount = 0;
        if (segments.size() > 1) {
            for (int i = 0; i < segments.size() - 1; i++) {
                hoursOnGroundCount += ChronoUnit.HOURS.between(segments.get(i).getArrivalDate(), segments.get(i + 1).getDepartureDate());
            }
        }
        return hoursOnGroundCount > hourOnGround;
    }
}
