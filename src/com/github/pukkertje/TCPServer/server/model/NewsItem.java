package com.github.pukkertje.TCPServer.server.model;

public class NewsItem {

    private String _title;
    private String _date;
    private String _url;

    public NewsItem(String title, String url, String date) {
        _title = title;
        _url = url;
        _date = date;
    }

    public String getUrl() {
        return _url;
    }

    public String getTitle() {
        return _title;
    }

    public String getDate() {
        return _date;
    }

    @Override
    public String toString() {
        return "NewsItem{" +
                ", _title = " + _title +
                ", _url = " + _url +
                ", _date = " + _date +
                "}";
    }
}
