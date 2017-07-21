package com.course.innopolis.habsrss.app;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.course.innopolis.habsrss.R;
import com.course.innopolis.habsrss.adapters.HabrAdapter;
import com.course.innopolis.habsrss.models.HabrArticle;
import com.course.innopolis.habsrss.utils.HabrXmlLoader;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnListItemCallback{
    final String LOG_TAG = "myLogs";
    private static final String URL = "https://habrahabr.ru/rss/hubs/all/";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.news_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        new DownloadXmlTask().execute(URL);
    }


    // Просмотреть cnfnm. подробнее
    @Override
    public void onClick(HabrArticle hab) {
//        Intent intent = new Intent(this, HabDetailsActivity.class);
//        intent.putExtra(HabrArticle.class.getCanonicalName(), hab);
//        startActivity(intent);
    }


    public void onClickRefreshData(View view){
        new DownloadXmlTask().execute(URL);
    }


    private class DownloadXmlTask extends AsyncTask<String, Void, List<HabrArticle>> {
        @Override
        protected List<HabrArticle> doInBackground(String... urls) {
            List<HabrArticle> habList = null;
            HabrXmlLoader xmlLoader = new HabrXmlLoader(URL);
            try {
                 habList = xmlLoader.loadXmlFromNetwork();
            } catch (IOException e) {
                 Log.d(LOG_TAG, "Connection error");
                 e.printStackTrace();
            } catch (XmlPullParserException e) {
                 Log.d(LOG_TAG, "XML error");
                 e.printStackTrace();
            }
            return habList;
        }


        // Вывод загруженного из xml-файла списка
        @Override
        protected void onPostExecute(List<HabrArticle> habList) {
            try{
                mAdapter = new HabrAdapter(MainActivity.this, habList, MainActivity.this);
                mRecyclerView.setAdapter(mAdapter);
            }
            catch (NullPointerException e){
                Toast.makeText(MainActivity.this, "Не удалось загрузить статьи", Toast.LENGTH_LONG).show();
                Log.d(LOG_TAG, "XML error");
            }

        }
    }


}
