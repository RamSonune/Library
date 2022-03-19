package com.example.android.mylibaray;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<BookCustom> {
    public BookAdapter(@NonNull Context context, int resource, ArrayList<BookCustom> bookCustomArrayList) {
        super(context, resource ,bookCustomArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View inflateView = convertView;
        // this  is supposed to be null because when we open the app for the first time , at that time it is null only , so thus we have to provide it as null.
        if (inflateView==null){
            inflateView = LayoutInflater.from(getContext()).inflate(R.layout.list_child,parent,false);

        }

        BookCustom bookCustom = getItem(position);

        TextView bookNameAdapter = inflateView.findViewById(R.id.bookNamechild);
        bookNameAdapter.setText(bookCustom.getBookNameCustom());

        TextView authorAdapter = inflateView.findViewById(R.id.authorchild);
        authorAdapter.setText(bookCustom.getAuthorCustom());

        ImageView bookimageAdapter = inflateView.findViewById(R.id.imagebookchild);
        bookimageAdapter.setImageResource(bookCustom.getImageResCustom());

        return inflateView;



//        return super.getView(position, convertView, parent);
    }
}
