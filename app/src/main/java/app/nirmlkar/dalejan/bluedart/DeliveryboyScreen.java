package app.nirmlkar.dalejan.bluedart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class DeliveryboyScreen extends AppCompatActivity {

    BlueDartDatabase blueDartDatabase;
    AdapterboyGet adapterboyGet;
    RecyclerView recycleview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliveryboy_screen);


        overridePendingTransition(R.anim.left,R.anim.anim2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i1 = getIntent();
        String user = i1.getStringExtra("who");
        Toast.makeText(this, user + "", Toast.LENGTH_SHORT).show();
        blueDartDatabase = new BlueDartDatabase(getApplicationContext());
        if (blueDartDatabase.getAllItemProcess().toArray().length != 0) {

                adapterboyGet = new AdapterboyGet(blueDartDatabase.getperticularBoy(user),getApplicationContext());

        }

        recycleview = findViewById(R.id.deliverylist);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycleview.setLayoutManager(layoutManager);
        recycleview.setItemAnimator(new DefaultItemAnimator());
        recycleview.setAdapter(adapterboyGet);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.btlogoutboy) {
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
