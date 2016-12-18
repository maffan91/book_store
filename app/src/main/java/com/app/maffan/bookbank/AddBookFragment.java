package com.app.maffan.bookbank;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddBookFragment.OnBookFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddBookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddBookFragment extends Fragment  {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View fragmentView;
    private OnBookFragmentInteractionListener mListener;
    private List<Author> authors;
    private DaoSession daoSession;
    private AuthorDao authorDao;
    private ArrayList<Author> selectedAuthorsList = new ArrayList<>();
    private JoinBookWithAuthorDao bookWithAuthorDao;
    private BookDao bookDao;
    private long insertedBookId;
    public AddBookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddBookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddBookFragment newInstance(String param1, String param2) {
        AddBookFragment fragment = new AddBookFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setTitle("Add Book");
        setupDao();
        authors = authorDao.loadAll();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_add_book, container, false);

        final TextView selectAuthor = (TextView) fragmentView.findViewById(R.id.select_author);
        selectAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Open a multi selection dialog box
                showDialog();

            }
        });

        final Button addBook = (Button) fragmentView.findViewById(R.id.add_book_button);

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText title = (EditText) fragmentView.findViewById(R.id.et_book_name);
                EditText price = (EditText) fragmentView.findViewById(R.id.et_price);

                if(selectedAuthorsList.size() == 0){
                    Toast.makeText(getActivity().getApplicationContext(),"No Author selected",Toast.LENGTH_SHORT).show();
                }else {

                    insertedBookId = addBook(new Book(null,title.getText().toString(),price.getText().toString()));

                    //insert authors with book association
                    for (int i = 0;i< selectedAuthorsList.size();i++){

                        addAuthorToBook(selectedAuthorsList.get(i),insertedBookId);

                    }

                }








            }
        });


        return fragmentView;
    }


    public void showDialog(){


        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Select Author");
        AuthorAdapter adapter = new AuthorAdapter(getActivity().getApplicationContext(),authors);
        dialog.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity().getApplicationContext(),authors.get(i).getName(),Toast.LENGTH_SHORT).show();
                TextView selectedAuthors = (TextView) fragmentView.findViewById(R.id.selected_authors);
                selectedAuthorsList.add(authors.get(i));

                if(selectedAuthors.getText().toString().length() == 0){

                    selectedAuthors.setText(authors.get(i).getName());

                }else {

                    selectedAuthors.append("," + authors.get(i).getName());
                }

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


    private long addBook(Book book){

        return bookDao.insert(book);


    }

    private void addAuthorToBook(Author author,long insertedBookId){

        bookWithAuthorDao.insert(new JoinBookWithAuthor(null,author.getId(),insertedBookId));



    }


    //setup database
    private void setupDao(){

        daoSession = ((App) getActivity().getApplication()).getDaoSession();
        authorDao = daoSession.getAuthorDao();
        bookDao = daoSession.getBookDao();
        bookWithAuthorDao = daoSession.getJoinBookWithAuthorDao();
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onBookFragmentInteractionListener(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBookFragmentInteractionListener) {
            mListener = (OnBookFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnBookFragmentInteractionListener {
        // TODO: Update argument type and name
        void onBookFragmentInteractionListener(Uri uri);
    }
}
