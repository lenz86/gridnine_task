import java.util.List;
import java.util.function.Predicate;

public interface FilterInt<T> {
     List<T> filter(List<T> list, Predicate<T> params);
}
