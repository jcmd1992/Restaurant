package ie.JD.activities;

import android.os.Bundle;
import android.widget.TextView;
import ie.JD.R;
import ie.JD.fragments.RestaurantFragment;

public class Favourites extends Base {

    private TextView emptyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourites);

        emptyList = findViewById(R.id.emptyList);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(app.restaurantList.isEmpty())
            emptyList.setText(getString(R.string.emptyMessageLbl));
        else
            emptyList.setText("");

        restaurantFragment = RestaurantFragment.newInstance(); //get a new Fragment instance
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, restaurantFragment)
                .commit(); // add it to the current activity
    }
}
