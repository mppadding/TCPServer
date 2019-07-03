package com.github.pukkertje.TCPServer.command;

import com.github.pukkertje.TCPServer.cache.Cache;
import com.github.pukkertje.TCPServer.helpers.HTTPSClient;
import com.github.pukkertje.TCPServer.helpers.RSSParser;
import com.github.pukkertje.TCPServer.server.model.NewsItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class News extends Command {

    private final String _url = "https://news.google.com/rss/headlines/section/geo/";

    public News() {

    }

    @Override
    public String execute(String[] arguments) {
        String locationText = Arrays.stream(arguments, 1, arguments.length).collect(Collectors.joining(" "));
        String locationUrl = locationText.replace(" ", "");
        StringBuilder sb = new StringBuilder();

        sb.append("News for ");
        sb.append(locationText);
        sb.append("\n");

        ArrayList<NewsItem> items;

        if(Cache.inCache(locationUrl)) {
            items = Cache.fetch(locationUrl);
        } else {
            items = RSSParser.parse(HTTPSClient.fetchURL(_url + locationUrl));
            Cache.addCache(locationUrl, items);
        }

        if(items == null || items.size() == 0) {
            sb.append("Unknown location or no news found.\n");
        } else {
            for(NewsItem item : items) {
                sb.append("News (");
                sb.append(item.getDate());
                sb.append("): ");
                sb.append(item.getTitle());
                sb.append("\nRead more at: ");
                sb.append(item.getUrl());
                sb.append("\n\n");
            }
        }

        return sb.toString();
    }

    @Override
    public String getHelp() {
        return "news {location}\tGives news about {location}.\n";
    }

    @Override
    public String getCommand() {
        return "news";
    }

    @Override
    public int getArgumentNumber() {
        return 1;
    }

    @Override
    public boolean hasEnoughArguments(int arguments) {
        return arguments > 0;
    }
}
