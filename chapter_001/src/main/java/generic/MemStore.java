package generic;

import java.util.ArrayList;
import java.util.List;
/**
 * 5.2.2. Реализовать Store<T extends Base>[#281944]
 * Логика хранилища для любых объектов, расширяющих Base
 * @author Kirill Asmanov
 * @since 01.05.2020
 */
public final class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        T found = findById(id);
        if (found == null) {
            return false;
        }
        mem.set(mem.indexOf(found), model);
        return true;
    }

    @Override
    public boolean delete(String id) {
        T found = findById(id);
        if (found == null) {
            return false;
        }
        return mem.remove(found);
    }

    @Override
    public T findById(String id) {
        return mem.stream()
                .filter((T e) -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
