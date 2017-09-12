package app.nirmlkar.dalejan.bluedart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class PrograssTask extends AppCompatActivity implements  View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText etitemName, etPickupPlace, etDropPlace;
    Spinner spinner;
    BlueDartDatabase blueDartDatabase;
    Button btitemsubmit, btitemcancle;
    String boyid;
    String[] boys = {};
    String[] itemDetails = {};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prograss_task);

        blueDartDatabase = new BlueDartDatabase(getApplicationContext());

        etitemName = findViewById(R.id.eitemdname);
        etPickupPlace = findViewById(R.id.eitempick);
        etDropPlace = findViewById(R.id.eitemdrop);
        btitemsubmit = findViewById(R.id.btitemsubmit);
        btitemcancle = findViewById(R.id.btitemcancle);


        if (blueDartDatabase.getAllDeliveryBoy() != null) {

            boys = new String[blueDartDatabase.getAllDeliveryBoy().toArray().length];

            int i = 0;

            for (DeliveryBoy i1 : blueDartDatabase.getAllDeliveryBoy()) {
                boys[i] = i1.getBoy_id();
                i++;
            }

        }

        if (blueDartDatabase.getAllItemProcess() != null) {
            itemDetails = new String[blueDartDatabase.getAllItemProcess().toArray().length];

            List<ItemDetails> allitem = blueDartDatabase.getAllItemProcess();

            for (int k = 0; k < allitem.toArray().length; k++) {
                if (Integer.parseInt(allitem.get(k).getFlag()) == 0) {
                    itemDetails[k] = allitem.get(k).getBoy_id();
                }
            }

        }
        if (boys != null) {
            for (int j = 0; j < itemDetails.length; j++) {
                if (itemDetails[j] != null) {
                    if (itemDetails[j].equalsIgnoreCase(boys[j])) {
                        boys[j] = "notAvailable";
                    }
                }

            }
        }

        spinner = findViewById(R.id.sboyid);

        btitemcancle.setOnClickListener(this);
        btitemsubmit.setOnClickListener(this);


        if (boys != null) {
           ArrayAdapter<String> aa = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, boys);
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(aa);

        }//wkjdh
        spinner.setOnItemSelectedListener(this);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btitemsubmit: {

                String itemname = etitemName.getText().toString();
                String itempick = etPickupPlace.getText().toString();
                String itemdrop = etDropPlace.getText().toString();

                if (itemname.equalsIgnoreCase("") && itempick.equalsIgnoreCase("") && itemdrop.equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), "Fill all the Details", Toast.LENGTH_SHORT).show();
                } else {
                    if (boyid != null) {
                        if (boyid.equalsIgnoreCase("notAvailable")) {
                            Toast.makeText(getApplicationContext(), "Choose boy id for delivery", Toast.LENGTH_SHORT).show();
                        }else{
                        ItemDetails itemDetails = new ItemDetails();
                        itemDetails.setItem_name(itemname);
                        itemDetails.setPickup_place(itempick);
                        itemDetails.setDrop_place(itemdrop);
                        itemDetails.setBoy_id(boyid);
                        itemDetails.setFlag("0");
                        blueDartDatabase.AddItem(itemDetails);
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();

                    }}

                }
                break;
            }

            case R.id.btitemcancle: {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
            }
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i){

                default:
                    boyid=boys[i];

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
