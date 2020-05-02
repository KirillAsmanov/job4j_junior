package generic;
/**
 * 5.2.2. Реализовать Store<T extends Base>[#281944]
 * Хранилище для объектов Role
 * @author Kirill Asmanov
 * @since 01.05.2020
 */
public class RoleStore implements Store<Role> {

    private final Store<Role> store = new MemStore<>();

    @Override
    public void add(Role model) {
        store.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        return store.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return store.delete(id);
    }

    @Override
    public Role findById(String id) {
        return store.findById(id);
    }
}
