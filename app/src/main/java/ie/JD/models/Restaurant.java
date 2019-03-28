package ie.JD.models;


import java.io.Serializable;
import java.util.UUID;



public class Restaurant implements Serializable
{
	public String restaurantId;
	public String restaurantName;
	public String cuisine;
	public double rating;
	public boolean favourite;


	public Restaurant() {}

	public Restaurant(String name, String cuisine, double rating, boolean fav)
	{
		this.restaurantId = UUID.randomUUID().toString();
		this.restaurantName = name;
		this.cuisine = cuisine;
		this.rating = rating;
		this.favourite = fav;
	}

	@Override
	public String toString() {
		return restaurantId + " " + restaurantName + ", " + cuisine + ", " + rating
				+ ", " + ", fav =" + favourite;
	}
}
