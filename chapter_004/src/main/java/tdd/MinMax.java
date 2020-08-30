package tdd;

import java.util.Comparator;
import java.util.List;

public class MinMax {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return getComparedValue(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return getComparedValue(value, comparator);
    }
    // Назначение двух методов выше не очень понятно в случае, если мы, согласно условию,
    // передаем компаратор извне - в таком случае достаточно одного метода ниже.

    public <T> T getComparedValue(List<T> value, Comparator<T> comparator) {
        T rsl = value.get(0);
        for (int i = 1; i < value.size(); i++) {
            T element = value.get(i);
            if (comparator.compare(rsl, element) > 0) {
                rsl = element;
            }
        }
        return rsl;
    }
}
