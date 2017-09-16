package app.nirmlkar.dalejan.bluedart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Confirm extends AppCompatActivity {

    EditText etitemname, etitemboy, etpickup, etdrop, etflag;
    Button btdeliver, btback,btlocation;
    BlueDartDatabase blueDartDatabase;
    String itemid="";
    double latitude,longitude;
    String who;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        overridePendingTransition(R.anim.left,R.anim.anim2);
        etitemname = findViewById(R.id.ecitemname);
        etitemboy = findViewById(R.id.ecboyid);
        etpickup = findViewById(R.id.ecpickup);
        etdrop = findViewById(R.id.ecdrop);
        etflag = findViewById(R.id.ecflag);

        btdeliver=findViewById(R.id.btcboysubmit);
        btback=findViewById(R.id.btcboycancle);
        btlocation=findViewById(R.id.btcboylocation);
        Intent i1=getIntent();
        String id=i1.getStringExtra("id");
         who=i1.getStringExtra("who");

        blueDartDatabase=new BlueDartDatabase(getApplicationContext());

          if (blueDartDatabase.getperticularitem(id) != null) {
            for (ItemDetails dget : blueDartDatabase.getperticularitem(id)) {
                itemid=dget.getDetails_id();
                etitemname.setText(dget.getItem_name());
                etitemboy.setText(dget.getBoy_id());
                etpickup.setText(dget.getPickup_place());
                etdrop.setText(dget.getDrop_place());

                latitude=dget.getLatitude();
                longitude=dget.getLongitude();

                Log.d("kkkkk000",latitude+"::"+longitude+" confirm");

                if (dget.getFlag().equalsIgnoreCase("0")){
                    etflag.setText(R.string.progress);
                    if (who.equalsIgnoreCase("delivery")){
                        btdeliver.setEnabled(true);
                        btdeliver.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getApplicationContext(),"Task is completed",Toast.LENGTH_SHORT).show();
                                blueDartDatabase.ConfirmItem(itemid);
                                startActivity(new Intent(getApplicationContext(),Login.class));
                                finish();
                            }
                        });
                    }
                }
                else {
                    etflag.setText(R.string.complete);

                }

            }
        }


        btlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(getApplicationContext(),LocationAction.class);
                i1.putExtra("who",who);
                startActivity(i1);

            }
        });

         btback.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if (who.equalsIgnoreCase("delivery")){
                     startActivity(new Intent(getApplicationContext(),DeliveryboyScreen.class));
                 }
                 else {
                     startActivity(new Intent(getApplicationContext(),MainActivity.class));
                 }
             }
         });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
