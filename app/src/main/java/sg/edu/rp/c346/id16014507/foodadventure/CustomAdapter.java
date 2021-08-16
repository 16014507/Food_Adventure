package sg.edu.rp.c346.id16014507.foodadventure;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Food> foodList;

    public CustomAdapter(Context context, int resource, ArrayList<Food> object) {
        super(context, resource, object);

        parent_context = context;
        layout_id = resource;
        foodList = object;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvFood = rowView.findViewById(R.id.textViewFood);
        TextView tvLocation = rowView.findViewById(R.id.textViewLocation);
        TextView tvSellinpt = rowView.findViewById(R.id.textViewSellingPoint);
        RatingBar rb = rowView.findViewById(R.id.ratingBar2);

        Food currentFood = foodList.get(position);

        tvFood.setText(currentFood.getFood());
        tvLocation.setText(currentFood.getLocation());
        tvSellinpt.setText(currentFood.getSellingPoint());
        rb.setRating(currentFood.getStars());

        return rowView;
    }

}
