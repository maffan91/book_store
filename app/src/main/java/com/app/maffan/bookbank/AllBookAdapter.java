package com.app.maffan.bookbank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by maffan on 12/19/2016.
 */

public class AllBookAdapter extends BaseAdapter {

    private Context context;
    private List<Book> books;

    public AllBookAdapter(Context context,List<Book> books){

        this.context = context;
        this.books = books;
    }
    @Override
    public int getCount() {
        return books.size();
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

        Book book = books.get(position);
        if(inflatedView == null){

            inflatedView = LayoutInflater.from(context).inflate(R.layout.book_item,null);
        }


        TextView title = (TextView) inflatedView.findViewById(R.id.title);
        TextView price = (TextView) inflatedView.findViewById(R.id.price);

        title.setText(book.getTitle());
        price.setText(book.getPrice());
        title.setTag(book.getId());

        return inflatedView;    }
}
