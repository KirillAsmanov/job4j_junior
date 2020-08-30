package cache;

import java.io.IOException;
/**
 * Реализации кеша на SoftReference [#282027]
 * @since 30.08.2020
 * @author Kirill Asmanov
 */
public interface Loader<T> {
    T loadData(String name) throws IOException;
}
