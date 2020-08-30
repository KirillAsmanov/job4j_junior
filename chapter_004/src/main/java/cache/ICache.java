package cache;
/**
 * Реализации кеша на SoftReference [#282027]
 * @since 30.08.2020
 * @author Kirill Asmanov
 */
public interface ICache<T> {
    void saveToCache(String name, T data);
    T getFromCache(String name);
}
