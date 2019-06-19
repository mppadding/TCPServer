import java.util.ArrayList;
import java.util.HashMap;

public class Cache {

    private static HashMap<String, ArrayList<NewsItem>> _cache = new HashMap<String, ArrayList<NewsItem>>();

    public static boolean inCache(String location) {
        return _cache.containsKey(location);
    }

    public static void addCache(String location, ArrayList<NewsItem> items) {

    }
}
