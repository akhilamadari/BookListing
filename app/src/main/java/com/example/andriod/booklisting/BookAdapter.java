package com.example.andriod.booklisting;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import javax.sql.StatementEvent;

/**
 * Created by akhilamadari on 3/4/18.
 */

public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(Context context, ArrayList<Book> book) {
        super(context, 0, book);
    }

    private String arrayString(String[] Author) {
        String s = "";
        for (int i = 0; i < Author.length; i++) {
             s = s + Author[i] + " ";
        }
        return s;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Book pos = getItem(position);
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView n = (TextView) listItemView.findViewById(R.id.title);
        n.setText((pos.getTitle()));
        String[] str;
        TextView n1 = (TextView) listItemView.findViewById(R.id.author);
        str = pos.getAuthor();

        n1.setText(arrayString(str));

        return listItemView;
    }
}
