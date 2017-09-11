package app.nirmlkar.dalejan.bluedart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class Confirm extends AppCompatActivity {

    EditText etitemname, etitemboy, etpickup, etdrop, etflag;
    Button btdeliver, btback;
    BlueDartDatabase blueDartDatabase;


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
        blueDartDatabase=new BlueDartDatabase(getApplicationContext());

         if (blueDartDatabase.getperticularitem(id) != null) {
            for (ItemDetails dget : blueDartDatabase.getperticularitem(id)) {
                Log.d("dddd","jjj");
                etitemname.setText(dget.getItem_name());
                Log.d("dddd",dget.getItem_name()+"jjj");
                etitemname.setText(dget.getBoy_id());
                Log.d("dddd","jjj");
                etitemname.setText(dget.getPickup_place());
                Log.d("dddd","jjj");
                etitemname.setText(dget.getDrop_place());
                Log.d("dddd","jjj");
                if (dget.getFlag().equalsIgnoreCase("0")){
                    etflag.setText(R.string.progress);
                    btdeliver.isClickable();
                }

            }
        }

    }
}
