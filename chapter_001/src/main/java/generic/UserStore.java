package generic;
/**
 * 5.2.2. Реализовать Store<T extends Base>[#281944]
 * Хранилище для объектов User
 * @author Kirill Asmanov
 * @since 01.05.2020
 */
public class UserStore implements Store<User> {

    private final Store<User> store = new MemStore<>();

    @Override
    public void add(User model) {
        store.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        return store.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return store.delete(id);
    }

    @Override
    public User findById(String id) {
        return store.findById(id);
    }
}
