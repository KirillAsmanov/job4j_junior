package generic;
/**
 * 5.2.2. Реализовать Store<T extends Base>[#281944]
 * Модель данных Role
 * @author Kirill Asmanov
 * @since 01.05.2020
 */
public class Role extends Base {
    private String name;

    public Role(String id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return super.getId();
    }
}