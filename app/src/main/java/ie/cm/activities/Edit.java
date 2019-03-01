package ie.cm.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import ie.cm.R;
import ie.cm.models.Restaurant;

public class Edit extends Base {
    public Context context;
    public boolean isFavourite;
    public Restaurant aRestaurant;
    public ImageView editFavourite;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        context = this;
        activityInfo = getIntent().getExtras();
        aRestaurant = getRestaurantObject(activityInfo.getString("restaurantId"));

        ((TextView)findViewById(R.id.editTitleTV)).setText(aRestaurant.restaurantName);

        ((EditText)findViewById(R.id.editNameET)).setText(aRestaurant.restaurantName);
        ((EditText)findViewById(R.id.editCuisineET)).setText(aRestaurant.cuisine);
        ((EditText)findViewById(R.id.editPriceET)).setText(""+aRestaurant.price);
        ((RatingBar) findViewById(R.id.editRatingBar)).setRating((float)aRestaurant.rating);

        editFavourite = findViewById(R.id.editFavourite);

        if (aRestaurant.favourite == true) {
            editFavourite.setImageResource(R.drawable.ic_thumb_up_black_on);
            isFavourite = true;
        } else {
            editFavourite.setImageResource(R.drawable.ic_thumb_up_black_24dp);
            isFavourite = false;
        }
    }

    private Restaurant getRestaurantObject(String id) {

        for (Restaurant c : app.restaurantList)
            if (c.restaurantId.equalsIgnoreCase(id))
                return c;

        return null;
    }

    public void saveRestaurant(View v) {

        String restaurantName = ((EditText) findViewById(R.id.editNameET)).getText().toString();
        String restaurantCuisine = ((EditText) findViewById(R.id.editCuisineET)).getText().toString();
        String restaurantPriceStr = ((EditText) findViewById(R.id.editPriceET)).getText().toString();
        double ratingValue =((RatingBar) findViewById(R.id.editRatingBar)).getRating();
        double restaurantPrice;

        try {
            restaurantPrice = Double.parseDouble(restaurantPriceStr);
        } catch (NumberFormatException e) {
            restaurantPrice = 0.0;
        }

        if ((restaurantName.length() > 0) && (restaurantCuisine.length() > 0) && (restaurantPriceStr.length() > 0)) {
            aRestaurant.restaurantName = restaurantName;
            aRestaurant.cuisine = restaurantCuisine;
            aRestaurant.price = restaurantPrice;
            aRestaurant.rating = ratingValue;

            startActivity(new Intent(this,Home.class));

        } else
            Toast.makeText(this, "You must Enter Something for Name and Cuisine",Toast.LENGTH_SHORT).show();
    }

    public void toggle(View view) {

        if (isFavourite) {
            aRestaurant.favourite = false;
            Toast.makeText(this,"Removed From Favourites",Toast.LENGTH_SHORT).show();
            isFavourite = false;
            editFavourite.setImageResource(R.drawable.ic_thumb_up_black_24dp);
        } else {
            aRestaurant.favourite = true;
            Toast.makeText(this,"Added to Favourites !!",Toast.LENGTH_SHORT).show();
            isFavourite = true;
            editFavourite.setImageResource(R.drawable.ic_thumb_up_black_on);
        }
    }
}
