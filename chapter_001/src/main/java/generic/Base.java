package generic;
/**
 * 5.2.2. Реализовать Store<T extends Base>[#281944]
 * Абстрактная модель данных
 * @author Kirill Asmanov
 * @since 01.05.2020
 */
public abstract class Base {
    private final String id;

    protected Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
