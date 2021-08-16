package sg.edu.rp.c346.id16014507.foodadventure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Food> foodList;
    Button btn5Stars;
    CustomAdapter adapter;

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(this);
        foodList.clear();
        foodList.addAll(dbh.getAllFoods());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lv = (ListView) this.findViewById(R.id.lv);
        btn5Stars = (Button) this.findViewById(R.id.btnShow5Stars);

        DBHelper dbh = new DBHelper(this);
        foodList = dbh.getAllFoods();
        dbh.close();

        adapter = new CustomAdapter(this, R.layout.row, foodList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ListActivity.this, EditActivity.class);
                i.putExtra("food", foodList.get(position));
                startActivity(i);
            }
        });

        btn5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ListActivity.this);
                foodList.clear();
                foodList.addAll(dbh.getAllFoodsByStars(5));
                adapter.notifyDataSetChanged();
            }
        });

    }
}