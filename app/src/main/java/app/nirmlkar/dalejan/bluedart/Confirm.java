package app.nirmlkar.dalejan.bluedart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Confirm extends AppCompatActivity {

    EditText etitemname, etitemboy, etpickup, etdrop, etflag;
    Button btdeliver, btback;
    BlueDartDatabase blueDartDatabase;
    String itemid="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        etitemname = findViewById(R.id.ecitemname);
        etitemboy = findViewById(R.id.ecboyid);
        etpickup = findViewById(R.id.ecpickup);
        etdrop = findViewById(R.id.ecdrop);
        etflag = findViewById(R.id.ecflag);

        btdeliver=findViewById(R.id.btcboysubmit);
        btback=findViewById(R.id.btcboycancle);
        Intent i1=getIntent();
        String id=i1.getStringExtra("id");
        String who=i1.getStringExtra("who");

        blueDartDatabase=new BlueDartDatabase(getApplicationContext());

          if (blueDartDatabase.getperticularitem(id) != null) {
            for (ItemDetails dget : blueDartDatabase.getperticularitem(id)) {
                itemid=dget.getDetails_id();
                etitemname.setText(dget.getItem_name());
                etitemboy.setText(dget.getBoy_id());
                etpickup.setText(dget.getPickup_place());
                etdrop.setText(dget.getDrop_place());

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



         btback.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(getApplicationContext(),Login.class));
                 finish();
             }
         });

    }
}
