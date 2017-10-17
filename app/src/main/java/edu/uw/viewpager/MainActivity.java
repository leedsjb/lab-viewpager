package edu.uw.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements MovieListFragment.OnMovieSelectedListener {

    private FragmentStatePagerAdapter adapter;

    private SearchFragment sf;
    private MovieListFragment mf;
    private DetailFragment df;

    private static final String TAG = "MainActivity";
    public static final String MOVIE_LIST_FRAGMENT_TAG = "MoviesListFragment";
    public static final String MOVIE_DETAIL_FRAGMENT_TAG = "DetailFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiate adapter
        MoviePagerAdapter mpa = new MoviePagerAdapter(getSupportFragmentManager()); // *** is this the correct way to obtain S.F.Manager?

        ViewPager vp = (ViewPager)findViewById(R.id.container); // obtain reference to view
        vp.setAdapter(mpa); // set view's adapter

        sf = SearchFragment.newInstance();

    }

    //respond to search button clicking
    public void handleSearchClick(View v){
        EditText text = (EditText)findViewById(R.id.txt_search);
        String searchTerm = text.getText().toString();

        MovieListFragment fragment = MovieListFragment.newInstance(searchTerm);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment, MOVIE_LIST_FRAGMENT_TAG);
        ft.addToBackStack(null);
        ft.commit();
    }



    @Override
    public void onMovieSelected(Movie movie) {
        DetailFragment fragment = DetailFragment.newInstance(movie);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment, MOVIE_DETAIL_FRAGMENT_TAG);
        ft.addToBackStack(null); //remember for the back button
        ft.commit();
    }

    private class MoviePagerAdapter extends FragmentStatePagerAdapter{

        public MoviePagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position){

            if(position == 0){
                return sf;
            } else if(position == 1){
                return  mf;
            } else { // TODO error handling if position is not bounded by 0 and 2 inclusive
                return df;
            }
        }

        /**
         * Returns how many pages the Pager currently supports (a run-time dynamic value)
         * @return
         */
        @Override
        public int getCount(){
            return 1;
        }

        /**
         *
         * @param object
         * @return
         */
        @Override
        public int getItemPosition(Object object){
            return POSITION_NONE;
        }

    }
}
