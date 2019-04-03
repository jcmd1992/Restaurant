package ie.JD.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ie.JD.R;

import ie.JD.fragments.MealFragment;
import ie.JD.models.Meal;


public class MealHome extends Base {
    DatabaseReference myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_meal_home);
        FirebaseApp.initializeApp(this);

        myDatabase = FirebaseDatabase.getInstance().getReference("Meal");

        myDatabase.addValueEventListener(new ValueEventListener() {  @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            app.mealList.clear();
            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                Meal meallist = (ds.getValue(Meal.class));
                //adding restaurant to the list
                Meal.Meal(meallist);
                app.mealList.add(meallist);

            }
        }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void addMeal(View v) { startActivity(new Intent(this, MealAdd.class)); }

    public void search(View v) {
        startActivity(new Intent(this, MealSearch.class));
    }

    public void favourites(View v) { startActivity(new Intent(this, MealFavourites.class)); }

    @Override
    protected void onResume() {
        super.onResume();

        MealFragment myFragment = MealFragment.newInstance(); //get a new Fragment instance
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, myFragment)
                .commit(); // add it to the current activity
    }
}
