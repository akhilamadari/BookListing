package com.example.andriod.booklisting;

import android.content.AsyncTaskLoader;
import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;
/**
 * Created by akhilamadari on 3/4/18.
 */

public  class BookLoader extends AsyncTaskLoader<List<Book>> {

    private String mUrl;

    public BookLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        List<Book> books = BookUtils.fetchEarthquakeData(mUrl);
        return books;
    }

}
