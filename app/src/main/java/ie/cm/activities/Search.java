package ie.cm.activities;

import android.os.Bundle;

import ie.cm.R;
import ie.cm.fragments.RestaurantFragment;
import ie.cm.fragments.SearchFragment;

public class Search extends Base  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
    }

    @Override
    protected void onResume() {
        super.onResume();

        restaurantFragment = SearchFragment.newInstance(); //get a new Fragment instance
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, restaurantFragment)
                .commit(); // add it to the current activity
    }
}
