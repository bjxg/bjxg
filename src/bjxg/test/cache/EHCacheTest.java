package bjxg.test.cache;

import java.net.URL;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EHCacheTest {
    public static void main(String[] args) {
        //
        URL url = Thread.currentThread().getContextClassLoader().getResource("ehcache.xml");
        CacheManager cm = CacheManager.create(url);
        System.out.println(cm.getCacheNames().length);
        Cache cache = cm.getCache("userList");
        // cache.initialise();
        System.out.println(cache.getStatus());
        System.out.println(System.getProperty("java.io.tmpdir"));
        System.out.println(cache.getKeys());
        System.out.println("--" + cache.getSize() + "-" + cache.getMemoryStoreSize() + "-" + cache.getDiskStoreSize());
        for (int i = 6000; i < 9000; i++) {
            User u = new User(i, i + "-name");
            cache.put(new Element("" + i, u));
            cache.flush();
            System.out.println(i + "--" + cache.getSize() + "-" + cache.getMemoryStoreSize() + "-"
                    + cache.getDiskStoreSize());

        }
        // System.out.println(cache.get("110").toString());
        cm.shutdown();
    }
}
