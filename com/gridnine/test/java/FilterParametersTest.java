import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

class FilterParametersTest {

    private FlightsFilter flightsFilter = new FlightsFilter();
    private List<Segment> beforeSetTimeSeg = new ArrayList<>();
    private List<Segment> arrivesBeforeDepartsSeg = new ArrayList<>();
    private List<Segment> moreThanSetGroundTimeSeg = new ArrayList<>();
    private List<Flight> data = new ArrayList<>();

    {
        beforeSetTimeSeg.add(new Segment(LocalDateTime.now().minusDays(2), LocalDateTime.now().plusDays(1)));
        arrivesBeforeDepartsSeg.add(new Segment(LocalDateTime.now().plusHours(10), LocalDateTime.now().plusHours(7)));
        moreThanSetGroundTimeSeg.add(new Segment(LocalDateTime.now().plusHours(5), LocalDateTime.now().plusHours(7)));
        moreThanSetGroundTimeSeg.add(new Segment(LocalDateTime.now().plusHours(11), LocalDateTime.now().plusHours(15)));
        Flight beforeSetTimeFlight = new Flight(beforeSetTimeSeg);
        Flight arrivesBeforeDepartsFlight = new Flight(arrivesBeforeDepartsSeg);
        Flight moreThanSetGroundTimeFlight = new Flight(moreThanSetGroundTimeSeg);
        data.add(beforeSetTimeFlight);
        data.add(arrivesBeforeDepartsFlight);
        data.add(moreThanSetGroundTimeFlight);
    }

    @Test
    void beforeSetTime() {
        Predicate<Flight> param1 = FilterParameters.beforeSetTime(LocalDateTime.now());
        List<Flight> result =  flightsFilter.filter(data, param1);
        Flight actual = result.get(0);
        Flight expected = data.get(0);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void arrivesBeforeDeparts() {
        Predicate<Flight> param2 = FilterParameters.arrivesBeforeDeparts();
        List<Flight> result =  flightsFilter.filter(data, param2);
        Flight actual = result.get(0);
        Flight expected = data.get(1);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void moreThanSetGroundTime() {
        Predicate<Flight> param3 = FilterParameters.moreThanSetGroundTime(3);
        List<Flight> result =  flightsFilter.filter(data, param3);
        Flight actual = result.get(0);
        Flight expected = data.get(2);
        Assertions.assertEquals(actual, expected);
    }
}