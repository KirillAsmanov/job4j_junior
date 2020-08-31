package cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * Реализации кеша на SoftReference [#282027]
 * @since 30.08.2020
 * @author Kirill Asmanov
 */
public class TextCache implements ICache<String> {
    private final HashMap<String, SoftReference<String>> cacheStore;

    public TextCache() {
        this.cacheStore = new HashMap<>();
    }

    /**
     * Puts on cache entry of loaded file
     * @param name name of file
     * @param text entry of file
     */
    @Override
    public void saveToCache(String name, String text) {
        cacheStore.put(name, new SoftReference<String>(text));
    }

    /**
     * Gets entry of file from cache
     * @param name name of file
     * @return entry of txt file
     */
    @Override
    public String getFromCache(String name) {
        return cacheStore.get(name).get();
    }

    @Override
    public boolean checkExist(String name) {
        return cacheStore.containsKey(name);
    }
}
