package ie.JD.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ie.JD.R;
import ie.JD.models.Restaurant;

public class Add extends Base {

    private String 		restaurantName, Cuisine;
    private double 		ratingValue;
    private EditText name, cuisine;
    private RatingBar ratingBar;
    //variables
    DatabaseReference myDatabase;
    Restaurant restaurant;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        name = findViewById(R.id.addNameET);
        cuisine =  findViewById(R.id.addCuisineET);
        ratingBar =  findViewById(R.id.addRatingBar);

        FirebaseApp.initializeApp(this);

        //getting the reference of issue node
        myDatabase = FirebaseDatabase.getInstance().getReference("Restaurant");

    }


    public void addRestaurant(View v) {

        restaurantName = name.getText().toString();
        Cuisine = cuisine.getText().toString();
        ratingValue = ratingBar.getRating();

        if ((restaurantName.length() > 0) && (Cuisine.length() > 0))
                {

            Restaurant c = new Restaurant(restaurantName, Cuisine, ratingValue,
                     false);

                    myDatabase.child(c.restaurantId).setValue(c);
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
