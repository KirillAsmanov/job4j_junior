package generic;

import java.util.ArrayList;
import java.util.Iterator;
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
        int index = indexById(id);
        if (index == -1) {
            return false;
        }
        mem.set(index, model);
        return true;
    }

    @Override
    public boolean delete(String id) {
        int index = indexById(id);
        if (index == -1) {
            return false;
        }
        mem.remove(index);
        return true;
    }

    @Override
    public T findById(String id) {
        return mem.stream()
                .filter((T e) -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public int indexById(String id) {
        int index = 0;
        for (T t: mem) {
            if (t.getId().equals(id)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public static void main(String[] args) {
        MemStore<User> memStore = new MemStore<>();
        User userFirst = new User("1", "Кирилл");
        User userSecond = new User("2", "Катя");
        User userThird = new User("3", "Иван");
        memStore.add(userFirst);
        memStore.add(userSecond);
        memStore.add(userThird);
        System.out.println(memStore.indexById("1"));
        System.out.println(memStore.indexById("2"));
        System.out.println(memStore.indexById("3"));
        System.out.println(memStore.indexById("4"));

    }
}
