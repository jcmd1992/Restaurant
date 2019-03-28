package ie.JD.activities;

import android.os.Bundle;
import android.widget.TextView;
import ie.JD.R;
import ie.JD.fragments.MealFragment;


public class MealFavourites extends Base {

    private TextView emptyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mealfavourites);

        emptyList = findViewById(R.id.emptyList);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(app.mealList.isEmpty())
            emptyList.setText(getString(R.string.emptyMessageLbl2));
        else
            emptyList.setText("");

        MealFragment myFragment = MealFragment.newInstance(); //get a new Fragment instance
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, myFragment)
                .commit(); // add it to the current activity
    }
}
