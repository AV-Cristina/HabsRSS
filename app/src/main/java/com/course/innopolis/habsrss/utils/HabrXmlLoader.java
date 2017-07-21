package com.course.innopolis.habsrss.utils;

import com.course.innopolis.habsrss.models.HabrArticle;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Cristina on 21.07.2017.
 *
 * Класс для загрузки xml-файла и передачи его парсеру
 */

public class HabrXmlLoader {

    private String urlString;

    public HabrXmlLoader(String urlString) {
        this.urlString = urlString;
    }

    public List<HabrArticle> loadXmlFromNetwork() throws XmlPullParserException, IOException {
        InputStream stream = null;
        List<HabrArticle> habList  = null;
        HabrXmlParser habXmlParser = new HabrXmlParser();
        try {
            stream = downloadUrl();
            habList = habXmlParser.parse(stream);
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
        return habList;
    }

    private InputStream downloadUrl() throws IOException {
        URL url = new URL(this.urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 );
        conn.setConnectTimeout(15000 );
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        return conn.getInputStream();
    }
}
