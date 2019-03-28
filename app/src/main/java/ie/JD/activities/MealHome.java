package ie.JD.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ie.JD.R;

import ie.JD.fragments.MealFragment;
import ie.JD.models.Meal;


public class MealHome extends Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_meal_home);
        if(app.mealList.isEmpty()) setupMeals();
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

    public void setupMeals(){
        app.mealList.add(new Meal("Kungpo",2.5,1.99,false));
        app.mealList.add(new Meal("Beef in blackbean sauce",3.5,2.99,true));
        app.mealList.add(new Meal("Dumplings", 4.5,1.49,true));
    }
}
