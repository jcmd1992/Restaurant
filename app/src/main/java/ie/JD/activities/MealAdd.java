package ie.JD.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import ie.JD.R;
import ie.JD.models.Meal;


public class MealAdd extends Base {

    private String 		mealName;
    private double 		Price, ratingValue;
    private EditText name, price;
    private RatingBar ratingBar;
    //variables


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mealadd);
        name = findViewById(R.id.addNameET);
        price =  findViewById(R.id.editPriceET);
        ratingBar =  findViewById(R.id.addRatingBar);

    }


    public void addMeal(View v) {

        mealName = name.getText().toString();
        try {
            Price = Double.parseDouble(price.getText().toString());
        } catch (NumberFormatException e) {
            Price = 0.0;
        }
        ratingValue = ratingBar.getRating();

        if ((mealName.length() > 0)
                && (price.length() > 0)) {
            Meal c = new Meal(mealName, ratingValue,
                    Price, false);


            app.mealList.add(c);
            startActivity(new Intent(this, MealHome.class));
        } else
            Toast.makeText(
                    this,
                    "You must Enter Something for "
                            + "\'Name\' and \'Price\'",
                    Toast.LENGTH_SHORT).show();
    }
}
