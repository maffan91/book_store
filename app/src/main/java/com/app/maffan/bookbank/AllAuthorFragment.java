package com.app.maffan.bookbank;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.List;


public class AllAuthorFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View fragmentView;
    private DaoSession daoSession;
    private AuthorDao authorDao;
    private BookDao bookDao;
    private List<Book> books;
    private List<Author> authors;

    public AllAuthorFragment() {
        // Required empty public constructor
    }

    public static AllAuthorFragment newInstance(String param1, String param2) {
        AllAuthorFragment fragment = new AllAuthorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("All Authors");
        setupDao();
        getAllAuthors();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView =  inflater.inflate(R.layout.fragment_all_author, container, false);


        //set the list of authors in it
        ListView authorList = new ListView(getActivity().getApplicationContext());
        AuthorAdapter adapter =new AuthorAdapter(getActivity().getApplicationContext(),authors);
        authorList.setAdapter(adapter);
        authorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                getBooks((Long) view.findViewById(R.id.author_name).getTag());

            }
        });
        RelativeLayout relativeLayout = (RelativeLayout) fragmentView.findViewById(R.id.all_author_fragment_view);
        relativeLayout.addView(authorList);
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
       authorDao = daoSession.getAuthorDao();
       bookDao = daoSession.getBookDao();
   }

    private void getAllAuthors(){

        authors = authorDao.loadAll();
    }

    private void getBooks(Long authorId){


        Author author = authorDao.load(authorId);
        books = author.getBooks();
        AllBookAdapter bookAdapter = new AllBookAdapter(getActivity().getApplicationContext(),books);

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Books");
        dialog.setAdapter(bookAdapter,null);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK, so save the mSelectedItems results somewhere
                // or return them to the component that opened the dialog
                dialog.dismiss();
            }
        });

        dialog.create();
        dialog.show();


    }
}
