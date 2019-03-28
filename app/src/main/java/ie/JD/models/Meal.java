package ie.JD.models;


import java.io.Serializable;
import java.util.UUID;



public class Meal implements Serializable
{
    public String mealId;
    public String mealName;
    public double rating;
    public double price;
    public boolean favourite;


    public Meal() {}

    public Meal(String mealname, double rating, double price, boolean fav)
    {
        this.mealId = UUID.randomUUID().toString();
        this.mealName = mealname;
        this.rating = rating;
        this.price = price;
        this.favourite = fav;
    }

    @Override
    public String toString() {
        return mealId + " " + mealName + ", " + ", " + rating
                + ", " + price + ", fav =" + favourite;
    }
}