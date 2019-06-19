import java.time.Instant;
import java.util.ArrayList;

public class CacheItem {

    private String _location;
    private ArrayList<NewsItem> _items;
    private Instant _updated;

    public CacheItem(String location, ArrayList<NewsItem> items) {
        _location = location;
        _items = items;
        _updated = Instant.now();
    }

    public Instant getTime() {
        return _updated;
    }

    public String getLocation() {
        return _location;
    }

    public ArrayList<NewsItem> getItems() {
        return _items;
    }
}
