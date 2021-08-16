package sg.edu.rp.c346.id16014507.foodadventure;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    EditText etID, etFood, etSellingpt, etLocation;

    Button btnCancel, btnUpdate, btnDelete;

    RatingBar rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        etID = (EditText) findViewById(R.id.etID);
        etFood = (EditText) findViewById(R.id.etFood);
        etSellingpt = (EditText) findViewById(R.id.etSellingPoint);
        etLocation = (EditText) findViewById(R.id.etLocation);
        rb = (RatingBar) findViewById(R.id.ratingBar3);

        Intent i = getIntent();
        final Food currentFood = (Food) i.getSerializableExtra("food");

        etID.setText(currentFood.getId()+"");
        etFood.setText(currentFood.getFood());
        etSellingpt.setText(currentFood.getSellingPoint());
        etLocation.setText(currentFood.getLocation());
        rb.setRating(currentFood.getStars());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                currentFood.setFood(etFood.getText().toString().trim());
                currentFood.setSellingPoint(etSellingpt.getText().toString().trim());
                currentFood.setLocation(etLocation.getText().toString().trim());
                currentFood.setStars((int)rb.getRating());

                int result = dbh.updateFood(currentFood);
                if (result>0){
                    Toast.makeText(EditActivity.this, "Food updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the food \n" + etFood.getText().toString());

                myBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(EditActivity.this);
                        int result = dbh.deleteFood(currentFood.getId());
                        if (result>0){
                            Toast.makeText(EditActivity.this, "Food deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(EditActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                myBuilder.setPositiveButton("Cancel", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes");

                myBuilder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

                myBuilder.setPositiveButton("Do not discard", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });

    }
}