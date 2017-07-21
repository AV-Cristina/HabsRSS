package com.course.innopolis.habsrss.app;

import com.course.innopolis.habsrss.models.HabrArticle;

/**
 * Created by Cristina on 21.07.2017.
 */

public interface OnListItemCallback {
    void onClick(HabrArticle hab);
}
