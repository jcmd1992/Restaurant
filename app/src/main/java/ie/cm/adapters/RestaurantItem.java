package ie.cm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

import ie.cm.R;
import ie.cm.models.Restaurant;

public class RestaurantItem {
    View view;

    public RestaurantItem(Context context, ViewGroup parent,
                          View.OnClickListener deleteListener, Restaurant restaurant)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.restaurantrow, parent, false);
        view.setTag(restaurant.restaurantId);

        updateControls(restaurant);

        ImageView imgDelete = view.findViewById(R.id.rowDeleteImg);
        imgDelete.setTag(restaurant);
        imgDelete.setOnClickListener(deleteListener);
    }

    private void updateControls(Restaurant restaurant) {
        ((TextView) view.findViewById(R.id.rowRestaurantName)).setText(restaurant.restaurantName);

        ((TextView) view.findViewById(R.id.rowRestaurantCuisine)).setText(restaurant.cuisine);
        ((TextView) view.findViewById(R.id.rowRating)).setText(restaurant.rating + " *");
        ((TextView) view.findViewById(R.id.rowPrice)).setText("€" +
                new DecimalFormat("0.00").format(restaurant.price));

        ImageView imgIcon = view.findViewById(R.id.rowFavouriteImg);

        if (restaurant.favourite == true)
            imgIcon.setImageResource(R.drawable.ic_thumb_up_black_on);
        else
            imgIcon.setImageResource(R.drawable.ic_thumb_up_black_24dp);


    }
}