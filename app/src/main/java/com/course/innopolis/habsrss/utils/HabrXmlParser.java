package com.course.innopolis.habsrss.utils;

import android.util.Log;
import android.util.Xml;

import com.course.innopolis.habsrss.models.HabrArticle;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cristina on 21.07.2017.
 */

public class HabrXmlParser {

    final static String LOG_TAG = "HabrParserLogs ";

    public List<HabrArticle> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            return readHabrArticleList(parser);
        } finally {
            in.close();
        }
    }

    // Парсим xml файл в список объектов HabrArticle
    private static List<HabrArticle> readHabrArticleList(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<HabrArticle> habList = new ArrayList();
        HabrArticle hab = null;

        while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {

            if (parser.getEventType() == XmlPullParser.START_TAG) {

                if (parser.getName().equals("item")) {
                    hab = new HabrArticle();
                    Log.d(LOG_TAG, "get item");
                }

                else if (hab != null){
                    switch (parser.getName()) {
                        case "title":
                            hab.setTitle(parser.nextText());
                            Log.d(LOG_TAG, "title = " + hab.getTitle());
//                        case "description":
//                            hab.setDescription(parser.nextText());
//                            Log.d(LOG_TAG, "description = " + hab.getDescription());
//                            break;
                            case "description":
                            int token = parser.nextToken();
                            while(token!=XmlPullParser.CDSECT){
                                token = parser.nextToken();
                            }
                            String cdata = parser.getText();
                            hab.setDescription(cdata);
                            Log.d(LOG_TAG,"cdata = " + cdata);
                            break;
                        case "pubDate":
                            String pubDate = parser.nextText();
                            //hab.setPubDate(pubDate.substring(0, pubDate.length() - 6));
                            hab.setPubDate(pubDate.substring(0, pubDate.length()));
                            Log.d(LOG_TAG, "pubDate = " + hab.getPubDate());
                            break;
                        case "dc:creator":
                            hab.setCreator(parser.nextText());
                            Log.d(LOG_TAG, "creator = " + hab.getCreator());
                            habList.add(hab); ///
                            break;
//                        case "category":
//                            String category  = parser.nextText();
//                            habList.add(hab);
//                            Log.d(LOG_TAG, "category = " + category);
//                            break;
                    }
                }
            }
            parser.next();
        }
        return habList;
    }
}
