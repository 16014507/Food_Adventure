package sg.edu.rp.c346.id16014507.foodadventure;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Food implements Serializable {

    private int id;
    private String food;
    private String sellingPoint;
    private String location;
    private int stars;

    public Food(int id, String food, String sellingPoint, String location, int stars) {
        this.id = id;
        this.food = food;
        this.sellingPoint = sellingPoint;
        this.location = location;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public Food setId(int id) {
        this.id = id;
        return this;
    }

    public String getFood() {
        return food;
    }

    public Food setFood(String food) {
        this.food = food;
        return this;
    }

    public String getSellingPoint() {
        return sellingPoint;
    }

    public Food setSellingPoint(String sellingPoint) {
        this.sellingPoint = sellingPoint;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Food setLocation(String location) {
        this.location = location;
        return this;
    }

    public int getStars() {
        return stars;
    }

    public Food setStars(int stars) {
        this.stars = stars;
        return this;
    }

    @NonNull
    @Override
    public String toString() {
        String starsString = "";
        for(int i = 0; i < stars; i++){
            starsString += " *";
        }
        return starsString;
    }
}
