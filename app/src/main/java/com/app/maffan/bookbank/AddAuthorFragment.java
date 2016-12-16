package com.app.maffan.bookbank;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddAuthorFragment.OnAddAuthorFragmentInteractoin} interface
 * to handle interaction events.
 * Use the {@link AddAuthorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddAuthorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View fragmentView;
    private DaoSession daoSession;
    private AuthorDao authorDao;
    private OnAddAuthorFragmentInteractoin mListener;

    public AddAuthorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddAuthorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddAuthorFragment newInstance(String param1, String param2) {
        AddAuthorFragment fragment = new AddAuthorFragment();
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

        getActivity().setTitle("Add Author");
        setupDao();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView =  inflater.inflate(R.layout.fragment_add_author, container, false);

        Button addAuthor = (Button) fragmentView.findViewById(R.id.add_author_button);

        addAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText authorName = (EditText) fragmentView.findViewById(R.id.et_author_name);
                authorDao.insert(new Author(null,authorName.getText().toString()));
                Toast.makeText(getActivity().getApplicationContext(),"Author Added!",Toast.LENGTH_SHORT).show();
                authorName.setText("");
            }
        });

        return  fragmentView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onAddAuthorFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddAuthorFragmentInteractoin) {
            mListener = (OnAddAuthorFragmentInteractoin) context;
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
    public interface OnAddAuthorFragmentInteractoin {
        // TODO: Update argument type and name
        void onAddAuthorFragmentInteraction(Uri uri);
    }

    private void setupDao(){


        daoSession = ((App) getActivity().getApplication()).getDaoSession();
        authorDao = daoSession.getAuthorDao();

    }
}
