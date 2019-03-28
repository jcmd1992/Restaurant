package ie.JD.activities;

import android.os.Bundle;

import ie.JD.R;
import ie.JD.fragments.mealSearchFragment;

public class MealSearch extends Base  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mealsearch);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mealFragment = mealSearchFragment.newInstance(); //get a new Fragment instance
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, mealFragment)
                .commit(); // add it to the current activity
    }
}
