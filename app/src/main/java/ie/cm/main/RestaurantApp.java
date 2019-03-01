package ie.cm.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.app.Application;
import android.util.Log;

import ie.cm.models.Restaurant;

public class RestaurantApp extends Application
{
    public List<Restaurant> restaurantList = new ArrayList<>();

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.v("Restaurant", "Restaurant App Started");
    }
}