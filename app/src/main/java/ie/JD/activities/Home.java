package ie.JD.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;


import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ie.JD.R;
import ie.JD.adapters.RestaurantFilter;
import ie.JD.adapters.RestaurantItem;
import ie.JD.adapters.RestaurantListAdapter;
import ie.JD.fragments.RestaurantFragment;
import ie.JD.models.Restaurant;

import static ie.JD.fragments.RestaurantFragment.listAdapter;

public class Home extends Base {

    DatabaseReference myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FirebaseApp.initializeApp(this);

        myDatabase = FirebaseDatabase.getInstance().getReference("Restaurant");

        myDatabase.addValueEventListener(new ValueEventListener() {  @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            app.restaurantList.clear();
            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                Restaurant restaurantlist = (ds.getValue(Restaurant.class));
                //adding restaurant to the list
                Restaurant.Restaurant(restaurantlist);
                app.restaurantList.add(restaurantlist);
              //  listAdapter.restaurantList.add(restaurantlist);
            }
        }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void add(View v) {
        startActivity(new Intent(this, Add.class));
    }

    public void search(View v) {
        startActivity(new Intent(this, Search.class));
    }


    @Override
    protected void onResume() {
        super.onResume();

        restaurantFragment = RestaurantFragment.newInstance(); //get a new Fragment instance
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, restaurantFragment)
                .commit(); // add it to the current activity
    }
}