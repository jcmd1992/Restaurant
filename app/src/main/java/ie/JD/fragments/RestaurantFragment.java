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

import ie.JD.R;
import ie.JD.activities.Base;

import ie.JD.activities.MealHome;
import ie.JD.adapters.RestaurantFilter;
import ie.JD.adapters.RestaurantListAdapter;
import ie.JD.models.Restaurant;

public class RestaurantFragment extends ListFragment implements View.OnClickListener,
        AbsListView.MultiChoiceModeListener
{
    public Base activity;
    public static RestaurantListAdapter listAdapter;
    public ListView listView;
    public RestaurantFilter restaurantFilter;

    public RestaurantFragment() {
        // Required empty public constructor
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Bundle activityInfo = new Bundle(); // Creates a new Bundle object
        activityInfo.putString("restaurantId", (String) v.getTag());
        Intent gomeal = new Intent(getActivity(), MealHome.class); // Creates a new Intent
        /* Add the bundle to the intent here */
        gomeal.putExtras(activityInfo);
        getActivity().startActivity(gomeal); // Launch the Intent
    }

    public static RestaurantFragment newInstance() {
        RestaurantFragment fragment = new RestaurantFragment();
        return fragment;
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
        listAdapter = new RestaurantListAdapter(activity, this, activity.app.restaurantList);
        restaurantFilter = new RestaurantFilter(activity.app.restaurantList,"all",listAdapter);
        setListAdapter (listAdapter);
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
        if (view.getTag() instanceof Restaurant)
        {
            onRestaurantDelete ((Restaurant) view.getTag());
        }
    }

    public void onRestaurantDelete(final Restaurant restaurant)
    {
        String stringName = restaurant.restaurantName;
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Are you sure you want to Delete the \'Restaurant\' " + stringName + "?");
        builder.setCancelable(false);
        DatabaseReference ToDelete = FirebaseDatabase.getInstance().getReference().child("Restaurant");
        DatabaseReference restaurantToDelete = ToDelete.child(restaurant.restaurantId);
        restaurantToDelete.removeValue();

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                activity.app.restaurantList.remove(restaurant); // remove from our list
                listAdapter.restaurantList.remove(restaurant); // update adapters data
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
            case R.id.menu_item_delete_restaurant:
                deleteRestaurants(actionMode);
                return true;
            default:
                return false;
        }
    }

    public void deleteRestaurants(ActionMode actionMode)
    {
        listAdapter.notifyDataSetChanged(); // refresh adapter
        checkEmptyList();

        actionMode.finish();
    }


    public void checkEmptyList()
    {
        TextView recentList = getActivity().findViewById(R.id.emptyList);

        if(activity.app.restaurantList.isEmpty())
            recentList.setText(getString(R.string.emptyMessageLbl));
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
