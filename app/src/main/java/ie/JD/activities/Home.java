package ie.JD.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import ie.JD.R;
import ie.JD.fragments.RestaurantFragment;
import ie.JD.models.Restaurant;

public class Home extends Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Information", Snackbar.LENGTH_LONG)
                        .setAction("More Info...", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        }).show();
            }
        });
        if (app.restaurantList.isEmpty()) setupRestaurants();
    }

    public void add(View v) {
        startActivity(new Intent(this, Add.class));
    }

    public void search(View v) {
        startActivity(new Intent(this, Search.class));
    }

    public void favourites(View v) {
        startActivity(new Intent(this, Favourites.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        restaurantFragment = RestaurantFragment.newInstance(); //get a new Fragment instance
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, restaurantFragment)
                .commit(); // add it to the current activity
    }

    public void setupRestaurants() {
        app.restaurantList.add(new Restaurant("Imperial Gardens", "Chinese", 2.5, false));
        app.restaurantList.add(new Restaurant("Emilianos", "Italian", 3.5, true));
        app.restaurantList.add(new Restaurant("La Boheme", "French", 4.5, true));
    }
}