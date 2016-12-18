package com.app.maffan.bookbank;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by affan on 12/18/16.
 */

public class AuthorAdapter extends BaseAdapter {

    private Context context;
    private List<Author> authors;



    public AuthorAdapter(Context context,List<Author> authors){

        this.context = context;
        this.authors = authors;
    }



    @Override
    public int getCount() {
        return authors.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View inflatedView, ViewGroup parent) {

        Author author = authors.get(position);

        if(inflatedView == null){

            inflatedView = LayoutInflater.from(context).inflate(R.layout.author_item,null);
        }

        TextView authorName = (TextView) inflatedView.findViewById(R.id.author_name);
        authorName.setText(author.getName());
        authorName.setTag(author.getId());

        return inflatedView;
    }
}
