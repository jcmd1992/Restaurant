package ie.JD.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import ie.JD.R;
import ie.JD.models.Restaurant;

public class RestaurantListAdapter extends ArrayAdapter<Restaurant>
{
    private Context context;
    private View.OnClickListener deleteListener;
    public List<Restaurant> restaurantList;

    public RestaurantListAdapter(Context context, View.OnClickListener deleteListener, List<Restaurant> restaurantList)
    {
        super(context, R.layout.restaurantrow, restaurantList);

        this.context = context;
        this.deleteListener = deleteListener;
        this.restaurantList = restaurantList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RestaurantItem item = new RestaurantItem(context, parent,
                                         deleteListener, restaurantList.get(position));
        return item.view;
    }

    @Override
    public int getCount() {
        return restaurantList.size();
    }

    @Override
    public Restaurant getItem(int position) {
        return restaurantList.get(position);
    }
}
