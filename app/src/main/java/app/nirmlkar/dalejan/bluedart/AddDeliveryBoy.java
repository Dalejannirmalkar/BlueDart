package app.nirmlkar.dalejan.bluedart;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDeliveryBoy extends AppCompatActivity implements View.OnClickListener {

    EditText eboyname,eboyemail,eboypass;
    Button btsubmit,btcancle;
    BlueDartDatabase blueDartDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_delivery_boy);

        overridePendingTransition(R.anim.left,R.anim.anim2);
        eboyname=findViewById(R.id.eboydname);
        eboyemail=findViewById(R.id.eboyemail);
        eboypass=findViewById(R.id.eboypass);

        btsubmit=findViewById(R.id.btboysubmit);
        btcancle=findViewById(R.id.btboycancle);

        btsubmit.setOnClickListener(this);
        btcancle.setOnClickListener(this);

    }

    public void Alert(){

        eboyname.setHintTextColor(Color.RED);
        eboyemail.setHintTextColor(Color.RED);
        eboypass.setHintTextColor(Color.RED);
    }

    @Override
    public void onClick(View view) {

        Intent i1=new Intent(getApplicationContext(),MainActivity.class);
        switch (view.getId()) {

            case R.id.btboysubmit: {

                String name=eboyname.getText().toString();
                String email=eboyemail.getText().toString();
                String pass=eboypass.getText().toString();
                if (name.equalsIgnoreCase("") && email.equalsIgnoreCase("") && pass.equalsIgnoreCase("")){
                    Alert();
                    Toast.makeText(getApplicationContext(), "Fill all the Fields", Toast.LENGTH_SHORT).show();

                }
                else {
                    DeliveryBoy deliveryBoy = new DeliveryBoy();

                    deliveryBoy.setBoy_name(name);
                    deliveryBoy.setBoy_email(email);
                    deliveryBoy.setBoy_password(pass);

                    blueDartDatabase = new BlueDartDatabase(getApplicationContext());
                    blueDartDatabase.AddDeliveryBoy(deliveryBoy);

                    Toast.makeText(getApplicationContext(), name + " is enter as delivery boy", Toast.LENGTH_SHORT).show();

                    i1.putExtra("who","manager");
                    startActivity(i1);
                    finish();

                }
                break;

            }

            case R.id.btboycancle:{
                
                i1.putExtra("who","manager");
                startActivity(i1);
                finish();

            }
        }
    }
}
