package com.app.maffan.bookbank;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class AllBookFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View fragmentView;
    private RelativeLayout relativeLayout;
    private DaoSession daoSession;
    private BookDao bookDao;
    private AuthorDao authorDao;
    private Long bookId;
    private List<Author> authors;
    public AllBookFragment() {
        // Required empty public constructor
    }

    public static AllBookFragment newInstance(String param1, String param2) {
        AllBookFragment fragment = new AllBookFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        setupDao();




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView =  inflater.inflate(R.layout.fragment_all_book, container, false);

        AllBookAdapter adapter = new AllBookAdapter(getActivity().getApplicationContext(),bookDao.loadAll());
        final ListView bookList = new ListView(getActivity().getApplicationContext());
        bookList.setAdapter(adapter);

        bookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                showAuthors((Long) view.findViewById(R.id.title).getTag());
            }
        });

        relativeLayout = (RelativeLayout) fragmentView.findViewById(R.id.all_book_fragment);
        relativeLayout.addView(bookList);
        return fragmentView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    private void setupDao(){

        daoSession = ((App) getActivity().getApplication()).getDaoSession();
        bookDao = daoSession.getBookDao();
        authorDao = daoSession.getAuthorDao();
    }


    private void showAuthors(Long bookId){

        Book book = bookDao.load(bookId);
        authors = book.getAuthors();
        AuthorAdapter authorAdapter = new AuthorAdapter(getActivity().getApplicationContext(),authors);
        //show the dialog box

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Authors");
        dialog.setAdapter(authorAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK, so save the mSelectedItems results somewhere
                // or return them to the component that opened the dialog

                dialog.dismiss();

            }
        });


        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });




        dialog.create();
        dialog.show();
    }


}
