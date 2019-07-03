package com.github.pukkertje.TCPServer.helpers;

import com.github.pukkertje.TCPServer.server.model.NewsItem;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class RSSParser {

    public static ArrayList<NewsItem> parse(InputStream stream) {
        ArrayList<NewsItem> items = new ArrayList<NewsItem>();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(stream);

            doc.getDocumentElement().normalize();

            NodeList nodes = doc.getElementsByTagName("item");

            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String title =  element.getElementsByTagName("title").item(0).getTextContent();

                    if(title.equals("This feed is not available.")) {
                        break;
                    }

                    String url = element.getElementsByTagName("link").item(0).getTextContent();
                    String date = element.getElementsByTagName("pubDate").item(0).getTextContent();

                    items.add(new NewsItem(title, url, date));
                }
            }
        } catch(ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return items;
    }
}
