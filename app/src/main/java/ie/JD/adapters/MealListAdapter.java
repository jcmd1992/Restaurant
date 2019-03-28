package ie.JD.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import ie.JD.R;
import ie.JD.models.Meal;


public class MealListAdapter extends ArrayAdapter<Meal>
{
    private Context context;
    private View.OnClickListener deleteListener;
    public List<Meal> mealList;

    public MealListAdapter(Context context, View.OnClickListener deleteListener, List<Meal> mealList)
    {
        super(context, R.layout.mealrow, mealList);

        this.context = context;
        this.deleteListener = deleteListener;
        this.mealList = mealList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MealItem item = new MealItem(context, parent,
                deleteListener, mealList.get(position));
        return item.view;
    }

    @Override
    public int getCount() {
        return mealList.size();
    }

    @Override
    public Meal getItem(int position) {
        return mealList.get(position);
    }
}
