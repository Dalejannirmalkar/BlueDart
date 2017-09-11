package app.nirmlkar.dalejan.bluedart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import java.util.Timer;
import java.util.TimerTask;

public class Spalsh extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        ImageView im=findViewById(R.id.logo);
        im.startAnimation(anim);
        Timer time = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        };
        time.schedule(task, 2800);
    }
}
