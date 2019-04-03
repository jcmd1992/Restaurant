package ie.JD.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;

import ie.JD.R;
import ie.JD.activities.Base;
import ie.JD.activities.MealEdit;

import ie.JD.activities.MealFavourites;
import ie.JD.adapters.MealFilter;
import ie.JD.adapters.MealListAdapter;
import ie.JD.models.Meal;


public class MealFragment extends ListFragment implements View.OnClickListener,
        AbsListView.MultiChoiceModeListener
{
    public Base activity;
    public static MealListAdapter listAdapter;
    public ListView listView;
    public MealFilter mealFilter;

    public MealFragment() {
        // Required empty public constructor
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Bundle activityInfo = new Bundle(); // Creates a new Bundle object
        activityInfo.putString("mealId", (String) v.getTag());
        Intent goEdit = new Intent(getActivity(), MealEdit.class); // Creates a new Intent
        /* Add the bundle to the intent here */
        goEdit.putExtras(activityInfo);
        getActivity().startActivity(goEdit); // Launch the Intent
    }

    public static MealFragment newInstance() {
        MealFragment myfragment = new MealFragment();
        return myfragment;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        this.activity = (Base) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        listAdapter = new MealListAdapter(activity, this, activity.app.mealList);
        mealFilter = new MealFilter(activity.app.mealList,"all",listAdapter);

        if (getActivity() instanceof MealFavourites) {
            mealFilter.setFilter("favourites"); // Set the filter text field from 'all' to 'favourites'
            mealFilter.filter(null); // Filter the data, but don't use any prefix
            listAdapter.notifyDataSetChanged(); // Update the adapter
        }
        setListAdapter (listAdapter);
        setRandomMeal();
        checkEmptyList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, parent, savedInstanceState);

        listView = v.findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(this);

        return v;
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onClick(View view)
    {
        if (view.getTag() instanceof Meal)
        {
            onMealDelete ((Meal) view.getTag());
        }
    }

    public void onMealDelete(final Meal meal)
    {
        String stringName = meal.mealName;
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Are you sure you want to Delete the \'meal\' " + stringName + "?");
        builder.setCancelable(false);
        DatabaseReference ToDelete = FirebaseDatabase.getInstance().getReference().child("Meal");
        DatabaseReference mealToDelete = ToDelete.child(meal.mealId);
        mealToDelete.removeValue();
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                activity.app.mealList.remove(meal); // remove from our list
                listAdapter.mealList.remove(meal); // update adapters data
                setRandomMeal();
                listAdapter.notifyDataSetChanged(); // refresh adapter
                checkEmptyList();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /* ************ MultiChoiceModeListener methods (begin) *********** */
    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu)
    {
        MenuInflater inflater = actionMode.getMenuInflater();
        inflater.inflate(R.menu.delete_list_context, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem)
    {
        switch (menuItem.getItemId())
        {
            case R.id.menu_item_delete_meal:
                deleteMeals(actionMode);
                return true;
            default:
                return false;
        }
    }

    public void deleteMeals(ActionMode actionMode)
    {
        for (int i = listAdapter.getCount() -1 ; i >= 0; i--)
        {
            if (listView.isItemChecked(i))
            {
                activity.app.mealList.remove(listAdapter.getItem(i));
                if (activity instanceof MealFavourites)
                    listAdapter.mealList.remove(listAdapter.getItem(i));
            }
        }
        setRandomMeal();
        listAdapter.notifyDataSetChanged(); // refresh adapter
        checkEmptyList();

        actionMode.finish();
    }

    public void setRandomMeal() {

        ArrayList<Meal> mealList = new ArrayList<>();

        for(Meal c : activity.app.mealList)
            if (c.favourite)
                mealList.add(c);

        if (activity instanceof MealFavourites)
            if( !mealList.isEmpty()) {
                Meal randomMeal = mealList.get(new Random()
                        .nextInt(mealList.size()));

                ((TextView) getActivity().findViewById(R.id.favouriteMealName)).setText(randomMeal.mealName);
                ((TextView) getActivity().findViewById(R.id.favouriteMealPrice)).setText("€ " + randomMeal.price);
                ((TextView) getActivity().findViewById(R.id.favouriteMealRating)).setText(randomMeal.rating + " *");
            }
            else {
                ((TextView) getActivity().findViewById(R.id.favouriteMealName)).setText("N/A");
                ((TextView) getActivity().findViewById(R.id.favouriteMealPrice)).setText("N/A");
                ((TextView) getActivity().findViewById(R.id.favouriteMealRating)).setText("N/A");
            }
    }

    public void checkEmptyList()
    {
        TextView recentList = getActivity().findViewById(R.id.emptyList);

        if(activity.app.mealList.isEmpty())
            recentList.setText(getString(R.string.emptyMessageLbl2));
        else
            recentList.setText("");
    }
    @Override
    public void onDestroyActionMode(ActionMode actionMode)
    {}

    @Override
    public void onItemCheckedStateChanged(ActionMode actionMode, int position, long id, boolean checked)
    {}
    /* ************ MultiChoiceModeListener methods (end) *********** */
}
