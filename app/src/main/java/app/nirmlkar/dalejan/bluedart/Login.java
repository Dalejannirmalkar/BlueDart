package app.nirmlkar.dalejan.bluedart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity implements View.OnClickListener {

    Button btLogin;
    EditText etEmail, etPass;
    BlueDartDatabase blueDartDatabase;
    String[] dBoyEmail, dboyid;
    String[] dBoyPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        overridePendingTransition(R.anim.left, R.anim.anim2);
        blueDartDatabase = new BlueDartDatabase(getApplicationContext());


        int i = 0;

        if (blueDartDatabase.getAllDeliveryBoy() != null) {
            dBoyEmail = new String[blueDartDatabase.getAllDeliveryBoy().toArray().length];
            dboyid = new String[blueDartDatabase.getAllDeliveryBoy().toArray().length];
            dBoyPass = new String[blueDartDatabase.getAllDeliveryBoy().toArray().length];
            for (DeliveryBoy dget : blueDartDatabase.getAllDeliveryBoy()) {
                dBoyEmail[i] = dget.getBoy_email();
                dBoyPass[i] = dget.getBoy_password();
                dboyid[i] = dget.getBoy_id();
                i++;
            }
        }

        btLogin = findViewById(R.id.login);
        etEmail = findViewById(R.id.input_email);
        etPass = findViewById(R.id.input_password);


        btLogin.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();

        switch (view.getId()) {

            case R.id.login: {
                if (email.equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), R.string.plsenteremail, Toast.LENGTH_SHORT).show();

                }
                if (pass.equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), R.string.plsenterpass, Toast.LENGTH_SHORT).show();

                } else {
                    if (email.equalsIgnoreCase(getString(R.string.managerda)) || pass.equalsIgnoreCase(getString(R.string.managerda))) {
                        if (pass.equals(getString(R.string.managerda)) && email.equalsIgnoreCase(getString(R.string.managerda))) {
                            Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                            i1.putExtra(getString(R.string.who), getString(R.string.manager));
                            startActivity(i1);
                            Toast.makeText(getApplicationContext(), R.string.sucsses, Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), R.string.invalid, Toast.LENGTH_SHORT).show();

                        }
                    } else {

                        if (dBoyEmail.length != 0) {

                            for (int i = 0; i < dBoyEmail.length; i++) {

                                if (email.equalsIgnoreCase(dBoyEmail[i])) {

                                    if (pass.equals(dBoyPass[i])) {

                                        Intent i2 = new Intent(getApplicationContext(), DeliveryboyScreen.class);
                                        i2.putExtra(getString(R.string.who), dboyid[i]);
                                        startActivity(i2);
                                        Toast.makeText(getApplicationContext(), R.string.dloginsu, Toast.LENGTH_SHORT).show();
                                        finish();

                                    }

                                }

                            }

                        } else {
                            Toast.makeText(getApplicationContext(), R.string.invalid, Toast.LENGTH_SHORT).show();

                        }
                    }

                }


                break;
            }

        }
    }
}
