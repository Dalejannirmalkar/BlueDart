package app.nirmlkar.dalejan.bluedart;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class PrograssTask extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText etPickupPlace, etDropPlace;
    Spinner spinner, etitemName;
    BlueDartDatabase blueDartDatabase;
    Button btitemsubmit, btitemcancle;
    String boyid,itemname;
    String[] boys = {};
    String[] itemDetails = {};
    double latitude;
    String[] item_n = {"Car", "Bus", "Hat", "Bag", "Bat", "Shoe", "Laptop", "Bench", "Bottle", "Shampoo", "Soap", "Guitar", "Earphone", "Ac", "Fan"};
    double longitude;
    private ArrayList<String> permissionsRejected = new ArrayList<>();
    private ArrayList<String> permissions = new ArrayList<>();

    private final static int ALL_PERMISSIONS_RESULT = 101;
    LocationTrack locationTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prograss_task);

        overridePendingTransition(R.anim.left,R.anim.anim2);
        blueDartDatabase = new BlueDartDatabase(getApplicationContext());

        etPickupPlace = findViewById(R.id.eitempick);
        etDropPlace = findViewById(R.id.eitemdrop);
        btitemsubmit = findViewById(R.id.btitemsubmit);
        btitemcancle = findViewById(R.id.btitemcancle);


        permissions.add(ACCESS_FINE_LOCATION);
        permissions.add(ACCESS_COARSE_LOCATION);

        ArrayList<Object> permissionsToRequest;
        permissionsToRequest = findUnAskedPermissions(permissions);
        //get the permissions we have asked for before but are not granted..
        //we will store this in a global list to access later.


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


            if (permissionsToRequest.size() > 0)
                requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
        }


        locationTrack = new LocationTrack(PrograssTask.this);


        if (locationTrack.canGetLocation()) {


            longitude = locationTrack.getLongitude();
            latitude = locationTrack.getLatitude();

            Toast.makeText(getApplicationContext(), "Longitude:" + Double.toString(longitude) + "\nLatitude:" + Double.toString(latitude), Toast.LENGTH_SHORT).show();
        } else {

            locationTrack.showSettingsAlert();
        }

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
            for (String itemDetail : itemDetails) {
                if (itemDetail != null) {
                    for (int i = 0; i < boys.length; i++) {
                        if (itemDetail.equalsIgnoreCase(boys[i])) {
                            boys[i] = "notAvailable";
                        }
                    }

                }

            }
        }

        etitemName = findViewById(R.id.eitemdname);
        spinner = findViewById(R.id.sboyid);

        ArrayAdapter<String> aa1 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, item_n);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etitemName.setAdapter(aa1);

        btitemcancle.setOnClickListener(this);
        btitemsubmit.setOnClickListener(this);


        if (boys != null) {
            ArrayAdapter<String> aa = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, boys);
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(aa);

        }
        spinner.setOnItemSelectedListener(PrograssTask.this);
        etitemName.setOnItemSelectedListener(PrograssTask.this);




    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btitemsubmit: {

                String itempick = etPickupPlace.getText().toString();
                String itemdrop = etDropPlace.getText().toString();

                if (itempick.equalsIgnoreCase("") && itemdrop.equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), "Fill all the Details", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("yyyyy","hg");
                    if (boyid != null) {
                        Log.d("yyyyy","hg");
                        if (boyid.equalsIgnoreCase("notAvailable")) {
                            Toast.makeText(getApplicationContext(), "Choose boy id for delivery", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("yyyyy","hg");

                            ItemDetails itemDetails = new ItemDetails();
                            itemDetails.setItem_name(itemname);
                            itemDetails.setPickup_place(itempick);
                            itemDetails.setDrop_place(itemdrop);
                            itemDetails.setBoy_id(boyid);

                            Log.d("kkkkk000",latitude+" progress");

                            Log.d("kkkkk000",longitude+" progress");
                            itemDetails.setLatitude(latitude);
                            itemDetails.setLongitude(longitude);
                            itemDetails.setFlag("0");
                            blueDartDatabase.AddItem(itemDetails);
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();

                        }
                    }

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
      boyid= (String) adapterView.getItemAtPosition(i);
      itemname= (String) adapterView.getItemAtPosition(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    private ArrayList<Object> findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList<Object> result = new ArrayList<>();

        for (Object perm : wanted) {
            if (!hasPermission(perm.toString())) {
                result.add(perm);
            }
        }

        return result;
    }

    private boolean hasPermission(String permission) {
        if (canMakeSmores()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {

            case ALL_PERMISSIONS_RESULT:
                for (String perms : permissions) {
                    if (!hasPermission(perms)) {
                        permissionsRejected.add(perms);
                    }
                }

                if (permissionsRejected.size() > 0) {


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                            showMessageOKCancel(
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(permissionsRejected.toArray(new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                            }
                                        }
                                    });
                            return;
                        }
                    }

                }

                break;
        }

    }

    private void showMessageOKCancel(DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(PrograssTask.this)
                .setMessage("These permissions are mandatory for the application. Please allow access.")
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationTrack.stopListener();
    }
}
