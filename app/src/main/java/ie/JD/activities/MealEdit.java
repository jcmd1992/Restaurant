package ie.JD.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import ie.JD.R;
import ie.JD.models.Meal;

public class MealEdit extends Base {
    public Context context;
    public boolean isFavourite;
    public Meal aMeal;
    public ImageView editFavourite;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mealedit);
        context = this;
        activityInfo = getIntent().getExtras();
        aMeal = getMealObject(activityInfo.getString("mealId"));

        ((TextView)findViewById(R.id.editTitleTV)).setText(aMeal.mealName);

        ((EditText)findViewById(R.id.editNameET)).setText(aMeal.mealName);
        ((EditText)findViewById(R.id.editPriceET)).setText(""+aMeal.price);
        ((RatingBar) findViewById(R.id.editRatingBar)).setRating((float)aMeal.rating);

        editFavourite = findViewById(R.id.editFavourite);

        if (aMeal.favourite == true) {
            editFavourite.setImageResource(R.drawable.ic_thumb_up_black_on);
            isFavourite = true;
        } else {
            editFavourite.setImageResource(R.drawable.ic_thumb_up_black_24dp);
            isFavourite = false;
        }
    }

    private Meal getMealObject(String id) {

        for (Meal c : app.mealList)
            if (c.mealId.equalsIgnoreCase(id))
                return c;

        return null;
    }

    public void saveMeal(View v) {

        String mealName = ((EditText) findViewById(R.id.editNameET)).getText().toString();
        String mealPriceStr = ((EditText) findViewById(R.id.editPriceET)).getText().toString();
        double ratingValue =((RatingBar) findViewById(R.id.editRatingBar)).getRating();
        double mealPrice;

        try {
            mealPrice = Double.parseDouble(mealPriceStr);
        } catch (NumberFormatException e) {
            mealPrice = 0.0;
        }

        if ((mealName.length() > 0) && (mealPriceStr.length() > 0)) {
            aMeal.mealName = mealName;
            aMeal.price = mealPrice;
            aMeal.rating = ratingValue;

            startActivity(new Intent(this, MealHome.class));

        } else
            Toast.makeText(this, "You must Enter Something for Name and Price",Toast.LENGTH_SHORT).show();
    }

    public void toggle(View view) {

        if (isFavourite) {
            aMeal.favourite = false;
            Toast.makeText(this,"Removed From Favourites",Toast.LENGTH_SHORT).show();
            isFavourite = false;
            editFavourite.setImageResource(R.drawable.ic_thumb_up_black_24dp);
        } else {
            aMeal.favourite = true;
            Toast.makeText(this,"Added to Favourites !!",Toast.LENGTH_SHORT).show();
            isFavourite = true;
            editFavourite.setImageResource(R.drawable.ic_thumb_up_black_on);
        }
    }
}