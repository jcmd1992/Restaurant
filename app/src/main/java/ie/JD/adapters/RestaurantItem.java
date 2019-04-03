package ie.JD.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ie.JD.R;
import ie.JD.models.Restaurant;

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


    }
}