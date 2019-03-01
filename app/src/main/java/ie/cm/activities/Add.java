package ie.cm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import ie.cm.R;
import ie.cm.main.RestaurantApp;
import ie.cm.models.Restaurant;

public class Add extends Base {

    private String 		restaurantName, Cuisine;
    private double 		Price, ratingValue;
    private EditText name, cuisine, price;
    private RatingBar ratingBar;
    //variables


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        name = findViewById(R.id.addNameET);
        cuisine =  findViewById(R.id.addCuisineET);
        price =  findViewById(R.id.editPriceET);
        ratingBar =  findViewById(R.id.addRatingBar);

    }


    public void addRestaurant(View v) {

        restaurantName = name.getText().toString();
        Cuisine = cuisine.getText().toString();
        try {
            Price = Double.parseDouble(price.getText().toString());
        } catch (NumberFormatException e) {
            Price = 0.0;
        }
        ratingValue = ratingBar.getRating();

        if ((restaurantName.length() > 0) && (Cuisine.length() > 0)
                && (price.length() > 0)) {
            Restaurant c = new Restaurant(restaurantName, Cuisine, ratingValue,
                    Price, false);


            app.restaurantList.add(c);
            startActivity(new Intent(this, Home.class));
        } else
            Toast.makeText(
                    this,
                    "You must Enter Something for "
                            + "\'Name\', \'Shop\' and \'Price\'",
                    Toast.LENGTH_SHORT).show();
    }
}
