package edu.uw.viewpager;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

//import edu.uw.fragmentdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private OnSearchListener callback;

    public SearchFragment() {
        // Required empty public constructor
    }

    //an interface for those who can respond to interactions with this Fragment
    interface OnSearchListener {
        void onSearchSubmitted(String searchTerm);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        Button button = (Button)rootView.findViewById(R.id.btn_search); // obtain reference to the button

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText searchEditText = (EditText)rootView.findViewById(R.id.txt_search); // obtain reference to EditText
                String searchText = searchEditText.getText().toString(); // retrieve text from EditText
                callback.onSearchSubmitted(searchText); // invoke callback function
            }
        });

        return rootView;
    }

    /**
     *
     * @return
     */
    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = (OnSearchListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnSearchListener");
        }
    }

}
