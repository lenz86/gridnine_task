import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FlightsFilter implements FilterInt<Flight> {

    @Override
    public List<Flight> filter(List<Flight> flights, Predicate<Flight> params) {
        List<Flight> result = flights.stream()
                .filter(params)
                .collect(Collectors.toList());
        return result;
    }
}
