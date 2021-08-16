package sg.edu.rp.c346.id16014507.foodadventure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    EditText etFood, etSellingPoint, etLocation;
    Button btnInsert, btnShowList;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        etFood = (EditText) findViewById(R.id.etFood);
        etSellingPoint = (EditText) findViewById(R.id.etSellingPoint);
        etLocation = (EditText) findViewById(R.id.etLocation);
        btnInsert = (Button) findViewById(R.id.btnInsertFood);
        btnShowList = (Button) findViewById(R.id.btnShowList);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar3);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String food = etFood.getText().toString().trim();
                String sellingpt = etSellingPoint.getText().toString().trim();
                String location = etLocation.getText().toString().trim();
                if (food.length() == 0 || sellingpt.length() == 0 || location.length() == 0){
                    Toast.makeText(InsertActivity.this, "Incomplete data", Toast.LENGTH_SHORT).show();
                    return;
                }

                DBHelper dbh = new DBHelper(InsertActivity.this);

                int stars = getStars();
                dbh.insertFood(food, sellingpt, location, stars);
                dbh.close();
                Toast.makeText(InsertActivity.this, "Inserted", Toast.LENGTH_LONG).show();

                etFood.setText("");
                etSellingPoint.setText("");
                etLocation.setText("");

            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(InsertActivity.this, ListActivity.class);
                startActivity(i);
            }
        });

    }

    private int getStars() {
        int stars = 1;
        stars = (int) ratingBar.getRating();
        return stars;
    }

}
