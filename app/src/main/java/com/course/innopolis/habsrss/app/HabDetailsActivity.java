package com.course.innopolis.habsrss.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.course.innopolis.habsrss.R;

/**
 * Created by Cristina on 21.07.2017.
 *
 * Activity, позволяющая просмотреть выбранную статью подробнее
 */

public class HabDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hab_details);
    }
}
