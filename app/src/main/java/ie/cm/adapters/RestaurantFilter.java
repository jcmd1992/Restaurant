package ie.cm.adapters;

import android.util.Log;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

import ie.cm.models.Restaurant;

public class RestaurantFilter extends Filter {
	public List<Restaurant> originalRestaurantList;
	public String filterText;
	public RestaurantListAdapter adapter;

	public RestaurantFilter(List<Restaurant> originalRestaurantList, String filterText,
							RestaurantListAdapter adapter) {
		super();
		this.originalRestaurantList = originalRestaurantList;
		this.filterText = filterText;
		this.adapter = adapter;
	}

	public void setFilter(String filterText) {
		this.filterText = filterText;
	}

	@Override
	protected FilterResults performFiltering(CharSequence prefix) {
		FilterResults results = new FilterResults();

		List<Restaurant> newRestaurants;
		String coffeeName;

		if (prefix == null || prefix.length() == 0) {
			newRestaurants = new ArrayList<>();
			if (filterText.equals("all")) {
				results.values = originalRestaurantList;
				results.count = originalRestaurantList.size();
			} else {
				if (filterText.equals("favourites")) {
					for (Restaurant c : originalRestaurantList)
						if (c.favourite)
							newRestaurants.add(c);
					}
					results.values = newRestaurants;
					results.count = newRestaurants.size();
			}
		} else {
			String prefixString = prefix.toString().toLowerCase();
			newRestaurants = new ArrayList<>();

			for (Restaurant c : originalRestaurantList) {
				coffeeName = c.restaurantName.toLowerCase();
				if (coffeeName.contains(prefixString)) {
					if (filterText.equals("all")) {
						newRestaurants.add(c);
					} else if (c.favourite) {
						newRestaurants.add(c);
					}}}
			results.values = newRestaurants;
			results.count = newRestaurants.size();
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void publishResults(CharSequence prefix, FilterResults results) {

		adapter.restaurantList = (ArrayList<Restaurant>) results.values;

		if (results.count >= 0)
			adapter.notifyDataSetChanged();
		else {
			adapter.notifyDataSetInvalidated();
			adapter.restaurantList = originalRestaurantList;
		}
		Log.v("Restaurant", "publishResults : " + adapter.restaurantList);
	}
}
