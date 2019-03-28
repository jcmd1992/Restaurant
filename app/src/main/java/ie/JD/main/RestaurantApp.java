package ie.JD.main;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;
import android.util.Log;

import ie.JD.models.Meal;
import ie.JD.models.Restaurant;

public class RestaurantApp extends Application
{
    public List<Restaurant> restaurantList = new ArrayList<>();
    public List<Meal> mealList = new ArrayList<>();

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.v("Restaurant", "Restaurant App Started");
    }
}