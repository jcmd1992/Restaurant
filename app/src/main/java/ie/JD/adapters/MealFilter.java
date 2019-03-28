package ie.JD.adapters;

import android.util.Log;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

import ie.JD.models.Meal;


public class MealFilter extends Filter {
    public List<Meal> originalMealList;
    public String filterText;
    public MealListAdapter adapter;

    public MealFilter(List<Meal> originalMealList, String filterText,
                      MealListAdapter adapter) {
        super();
        this.originalMealList = originalMealList;
        this.filterText = filterText;
        this.adapter = adapter;
    }

    public void setFilter(String filterText) {
        this.filterText = filterText;
    }

    @Override
    protected FilterResults performFiltering(CharSequence prefix) {
        FilterResults results = new FilterResults();

        List<Meal> newMeals;
        String mealName;

        if (prefix == null || prefix.length() == 0) {
            newMeals = new ArrayList<>();
            if (filterText.equals("all")) {
                results.values = originalMealList;
                results.count = originalMealList.size();
            } else {
                if (filterText.equals("favourites")) {
                    for (Meal c : originalMealList)
                        if (c.favourite)
                            newMeals.add(c);
                }
                results.values = newMeals;
                results.count = newMeals.size();
            }
        } else {
            String prefixString = prefix.toString().toLowerCase();
            newMeals = new ArrayList<>();

            for (Meal c : originalMealList) {
                mealName = c.mealName.toLowerCase();
                if (mealName.contains(prefixString)) {
                    if (filterText.equals("all")) {
                        newMeals.add(c);
                    } else if (c.favourite) {
                        newMeals.add(c);
                    }}}
            results.values = newMeals;
            results.count = newMeals.size();
        }
        return results;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void publishResults(CharSequence prefix, FilterResults results) {

        adapter.mealList = (ArrayList<Meal>) results.values;

        if (results.count >= 0)
            adapter.notifyDataSetChanged();
        else {
            adapter.notifyDataSetInvalidated();
            adapter.mealList = originalMealList;
        }
        Log.v("Restaurant", "publishResults : " + adapter.mealList);
    }
}
