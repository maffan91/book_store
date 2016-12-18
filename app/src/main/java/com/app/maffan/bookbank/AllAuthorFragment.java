package com.app.maffan.bookbank;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.List;


public class AllAuthorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View fragmentView;
    private DaoSession daoSession;
    private AuthorDao authorDao;
    private List<Author> authors;

    public AllAuthorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllAuthorFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        getAllAuthores();

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
   }

    private void getAllAuthores(){

        authors = authorDao.loadAll();
    }

}
