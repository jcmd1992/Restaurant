package ie.JD.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

import ie.JD.R;
import ie.JD.models.Meal;


public class MealItem {
    View view;

    public MealItem(Context context, ViewGroup parent,
                    View.OnClickListener deleteListener, Meal meal) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.mealrow, parent, false);
        view.setTag(meal.mealId);

        updateControls(meal);

        ImageView imgDelete = view.findViewById(R.id.rowDeleteImg);
        imgDelete.setTag(meal);
        imgDelete.setOnClickListener(deleteListener);
    }

    private void updateControls(Meal meal) {
        ((TextView) view.findViewById(R.id.rowMealName)).setText(meal.mealName);
        ((TextView) view.findViewById(R.id.rowRating)).setText(meal.rating + " *");
        ((TextView) view.findViewById(R.id.rowPrice)).setText("€" +
                new DecimalFormat("0.00").format(meal.price));

        ImageView imgIcon = view.findViewById(R.id.rowFavouriteImg);

        if (meal.favourite == true)
            imgIcon.setImageResource(R.drawable.ic_thumb_up_black_on);
        else
            imgIcon.setImageResource(R.drawable.ic_thumb_up_black_24dp);


    }
}