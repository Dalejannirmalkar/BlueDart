package app.nirmlkar.dalejan.bluedart;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton fab, fab1, fab2;
    Animation show_fab_1, show_fab_2, hide_fab_1, hide_fab_2;
    AdapterItemDetails adapterItemDetails;
    BlueDartDatabase blueDartDatabase;
    RecyclerView recycleview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        blueDartDatabase = new BlueDartDatabase(getApplicationContext());

        if (blueDartDatabase.getAllItemProcess() != null) {

            adapterItemDetails = new AdapterItemDetails(blueDartDatabase.getAllItemProcess());

        }

        recycleview = findViewById(R.id.listview);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recycleview.setLayoutManager(layoutManager);
        recycleview.setItemAnimator(new DefaultItemAnimator());
        recycleview.setAdapter(adapterItemDetails);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);
        fab1 = findViewById(R.id.fab_1);
        fab2 = findViewById(R.id.fab_2);

        fab.setVisibility(View.VISIBLE);
        Anim();


        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);


    }


    public void Anim() {
        show_fab_1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_show);
        show_fab_2 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab2_show);

        hide_fab_1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_hide);
        hide_fab_2 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab2_hide);

    }

    public void UpdateShowButton() {
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) fab1.getLayoutParams();
        CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams) fab2.getLayoutParams();

        layoutParams.rightMargin += (int) (fab1.getWidth() * 1.7);
        layoutParams.bottomMargin += (int) (fab1.getHeight() * 0.25);

        layoutParams2.rightMargin += (int) (fab2.getWidth() * 1.2);
        layoutParams2.bottomMargin += (int) (fab2.getHeight() * 1.8);

        fab1.setLayoutParams(layoutParams);
        fab1.setVisibility(View.VISIBLE);
        fab1.startAnimation(show_fab_1);
        fab1.setClickable(true);

        fab2.setLayoutParams(layoutParams2);
        fab2.setVisibility(View.VISIBLE);
        fab2.startAnimation(show_fab_2);
        fab2.setClickable(true);
    }


    public void UpdateHideButton() {
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) fab1.getLayoutParams();
        CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams) fab2.getLayoutParams();

        layoutParams.rightMargin -= (int) (fab1.getWidth() * 1.7);
        layoutParams.bottomMargin -= (int) (fab1.getHeight() * 0.25);

        layoutParams2.rightMargin -= (int) (fab2.getWidth() * 1.2);
        layoutParams2.bottomMargin -= (int) (fab2.getHeight() * 1.8);

        fab1.setLayoutParams(layoutParams);
        fab1.setVisibility(View.INVISIBLE);
        fab1.startAnimation(hide_fab_1);
        fab1.setClickable(false);

        fab2.setLayoutParams(layoutParams2);
        fab2.setVisibility(View.INVISIBLE);
        fab2.startAnimation(hide_fab_2);
        fab2.setClickable(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.btlogout) {
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.fab: {

                if (fab1.getVisibility() == View.INVISIBLE) {

                    UpdateShowButton();

                } else {

                    UpdateHideButton();

                }

                break;
            }

            case R.id.fab_1: {

                startActivity(new Intent(getApplicationContext(), PrograssTask.class));

                finish();

                break;

            }

            case R.id.fab_2: {

                startActivity(new Intent(getApplicationContext(), AddDeliveryBoy.class));

                finish();

                break;

            }
        }
    }
}
