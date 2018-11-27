package com.wyc.ccs81.myprovider;
//55

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
//import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.messaging.FirebaseMessaging;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String companyName, imei, password, mobileNo, convertedPassword = "", companyUrl, selectedItem = "", partyCode, userName, password2, email, res, check, startDate, aResponseServer;
    TextView textViewMultipleBranch;
    EditText editTextCompanyName, editTextUserId, editTextPassword, editTextPartyCode, editTextBranch, editTextUserName, editTextPassword2, etFinancialYear, editTextEmail, editTextMobileNo;
    static EditText editTextOtp;
    Spinner spinnerMultipleBranch;
    Button buttonVerifyOtp, buttonSubmit, buttonGoToOtherApps,buttonFCM;
    ImageButton imageButtonCall, imageButtonAgent, imageButtonCustomer;
    ConnectionDetector cd;
    String multipleBranch = "", branch = "",financialYear;
    TextInputLayout tilPartyCode, tilBranch;
    private final String TOPIC = "SendToAll";

    String app_server_url="http://www.winyatra.com/tanuj/fcmtest/fcm_insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cd = new ConnectionDetector(getApplicationContext());
        // Check if Internet present
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            // Setting Dialog Title
            alertDialog.setTitle("Internet Connection Error");
            // Setting Dialog Message
            alertDialog.setMessage("Please connect to working Internet connection");
            // Setting alert dialog icon
            // Setting OK Button
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.this.finish();
                }
            });
            // Showing Alert Message
            alertDialog.show();
            return;
        }

        editTextCompanyName = (EditText) findViewById(R.id.et_companyname);
        editTextUserId = (EditText) findViewById(R.id.et_userid);
        editTextPassword = (EditText) findViewById(R.id.et_password);
        imageButtonAgent = (ImageButton) findViewById(R.id.ibtn_agent);
        imageButtonCustomer = (ImageButton) findViewById(R.id.ibtn_customer);
        spinnerMultipleBranch = (Spinner) findViewById(R.id.spinner_multiplebranch);
        textViewMultipleBranch = (TextView) findViewById(R.id.tv_multiplebranch);
        tilBranch = (TextInputLayout) findViewById(R.id.til_branch);
        editTextBranch = (EditText) findViewById(R.id.et_branch);
        tilPartyCode = (TextInputLayout) findViewById(R.id.til_partycode);
        editTextPartyCode = (EditText) findViewById(R.id.et_partycode);
        editTextUserName = (EditText) findViewById(R.id.et_username);
        editTextPassword2 = (EditText) findViewById(R.id.et_password2);
        etFinancialYear = (EditText) findViewById(R.id.et_financialyear);
        editTextEmail = (EditText) findViewById(R.id.et_email);
        editTextMobileNo = (EditText) findViewById(R.id.et_mobileno);
        editTextOtp = (EditText) findViewById(R.id.et_otp);
        buttonVerifyOtp = (Button) findViewById(R.id.btnOtp);
        buttonSubmit = (Button) findViewById(R.id.btnSubmit);
        buttonGoToOtherApps = (Button) findViewById(R.id.btn_gotootherapps);
        imageButtonCall = (ImageButton) findViewById(R.id.btn_call);

        buttonFCM = (Button) findViewById(R.id.btn_fcm);
        // buttonGoToOtherApps.setVisibility(View.INVISIBLE);

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        imei = telephonyManager.getDeviceId();
        Log.e("imei", imei);
        //editTextDeviceId.setText(imei);
        imeiConversion(imei);

        editTextUserId.setText(imei);
        editTextUserId.setClickable(false);
        editTextUserId.setFocusable(false);
        editTextUserId.setFocusableInTouchMode(false);

        imageButtonAgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItem = "Agent";

                tilPartyCode.setVisibility(View.INVISIBLE);
                editTextPartyCode.setVisibility(View.INVISIBLE);
                textViewMultipleBranch.setVisibility(View.INVISIBLE);
                spinnerMultipleBranch.setVisibility(View.INVISIBLE);
                tilBranch.setVisibility(View.INVISIBLE);
                editTextBranch.setVisibility(View.INVISIBLE);

            }
        });
        imageButtonCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItem = "Customer";
                tilPartyCode.setVisibility(View.VISIBLE);
                editTextPartyCode.setVisibility(View.VISIBLE);
                textViewMultipleBranch.setVisibility(View.VISIBLE);
                spinnerMultipleBranch.setVisibility(View.VISIBLE);
                tilBranch.setVisibility(View.VISIBLE);
                editTextBranch.setVisibility(View.VISIBLE);
            }
        });


        spinnerMultipleBranch.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("Select");
        categories.add("Yes");
        categories.add("No");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMultipleBranch.setAdapter(dataAdapter);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ///START////Subscribe aaplication for particular topic in FCM//////
                FirebaseMessaging.getInstance().subscribeToTopic(TOPIC);
                ///END////Subscribe aaplication for particular topic in FCM//////

                companyName = editTextCompanyName.getText().toString();
                password = editTextPassword.getText().toString();
                if (selectedItem == "Customer") {
                    partyCode = editTextPartyCode.getText().toString();
                } else if (selectedItem == "Agent") {
                    partyCode = "Agent";

                }
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("usertype", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("utype", selectedItem);
                editor.commit();
                branch = editTextBranch.getText().toString();
                userName = editTextUserName.getText().toString();
                password2 = editTextPassword2.getText().toString();
                financialYear = etFinancialYear.getText().toString();
                email = editTextEmail.getText().toString();
                mobileNo = editTextMobileNo.getText().toString();

                String financialYearPattern = "[0-9]{4}";
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String mobilePattern = "[0-9]{10}";//[0-9] shows 0-9 digits can be enter and {10} shows that limit 10 digit can enter
                //"+91[0-9]{10}" it shows tht +91 must be enter before 10 digit mobile no
                if (financialYear.matches(financialYearPattern)){
                    if (email.matches(emailPattern)) {

                        if (mobileNo.matches(mobilePattern)) {
                            new infoAsync().execute();
                        } else {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                            alertDialog.setTitle("Alert");
                            alertDialog.setMessage("Please Enter Valid Mobile Number");
                            alertDialog.setIcon(R.drawable.alert2);
                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            alertDialog.show();
                        }

                    } else {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                        alertDialog.setTitle("Alert");
                        alertDialog.setIcon(R.drawable.alert2);
                        alertDialog.setMessage("Please Enter Valid Email ID");
                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alertDialog.show();
                    }
            }else{
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                    alertDialog.setTitle("Alert");
                    alertDialog.setIcon(R.drawable.alert2);
                    alertDialog.setMessage("Please Enter Valid Financial Year");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.show();
                }


            }
        });

        imageButtonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + "9810307975"));
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);
            }
        });


        buttonGoToOtherApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences1 = getApplicationContext().getSharedPreferences("usertype", MODE_PRIVATE);
                String checkUserType = sharedPreferences1.getString("utype", null);

                if (checkUserType.trim().equalsIgnoreCase("Agent") || checkUserType.trim().equals("Agent") || checkUserType == "Agent") {
                    Intent intent = new Intent(MainActivity.this, AgentAppsActivity.class);
                    startActivity(intent);
                } else if (checkUserType.trim().equalsIgnoreCase("Customer") || checkUserType.trim().equals("Customer") || checkUserType == "Customer") {
                    Intent intent = new Intent(MainActivity.this, CustomerAppsActivity.class);
                    startActivity(intent);
                }
            }
        });

//this code is to send token on php server and token will be saved in table.....when we need to send notification to one person or device whose token is available in table////////
//        buttonFCM.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);
//                final String token = sharedPreferences.getString(getString(R.string.FCM_TOKEN),"");
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, app_server_url, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }){
//
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//                        Map<String,String> params = new HashMap<String, String>();
//                        params.put("fcm_token",token);
//                        return params;
//                    }
//                };
//
//                MySingleton.getmInstance(MainActivity.this).addToRequestque(stringRequest);
//            }
//        });


    }


    private void getOverflowMenu() {

        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.facebook:
                Toast.makeText(getBaseContext(), "Under  Maintenance", Toast.LENGTH_SHORT).show();
                break;

            case R.id.maps:
                Toast.makeText(getBaseContext(), "Way to office", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
                break;

            case R.id.bank:
                Toast.makeText(getBaseContext(), "Under  Maintenance", Toast.LENGTH_SHORT).show();
                break;

            case R.id.contact:
                // Toast.makeText(getBaseContext(), "You selected Camera", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(MainActivity.this, ContactUsActivity.class);
                startActivity(intent2);
                break;

        }
        return true;

    }


    public void recivedSms(String message) {
        try {
            editTextOtp.setText(message);
        } catch (Exception e) {
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        multipleBranch = parent.getItemAtPosition(position).toString();
        multipleBranch = String.valueOf(multipleBranch);

        if (multipleBranch.equalsIgnoreCase("No") || multipleBranch.equals("No") || multipleBranch == "No") {
            editTextBranch.setText("");
            editTextBranch.setVisibility(View.INVISIBLE);
        } else {
            editTextBranch.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    class infoAsync extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            //Create Webservice class object
            GetAppUrlWebservice startAppWebservice = new GetAppUrlWebservice();

            //Call Webservice class method and pass values and get response
            companyUrl = startAppWebservice.getConvertedWeight("wyGetServiceURL", companyName);

            Log.i("BBRRaResponse", companyUrl);


            return null;
        }

        protected void onPostExecute(Void result) {
            if (partyCode == "Agent" || partyCode.equalsIgnoreCase("Agent") || partyCode.equals("Agent")) {
                new agentAsync().execute();
            } else {
                new customerAsync().execute();
            }

        }
    }

    class agentAsync extends AsyncTask<Void, Void, Void> {
        String aResponse;

        @Override
        protected Void doInBackground(Void... voids) {
            //Create Webservice class object
            AgentWebservice mainWebservice = new AgentWebservice();

            //Call Webservice class method and pass values and get response
            aResponse = mainWebservice.getConvertedWeight(companyUrl, "GetValidUser", companyName, userName, password2.trim());
            res = aResponse;
            Log.i("BBRRaResponse", aResponse);
            if (res == "Invalid User" || res.equalsIgnoreCase("Invalid User") || res.equals("Invalid User")) {
                check = "";
                startDate = "";
            } else {

                String[] strArray = res.split("%");
                check = strArray[0];
                startDate = strArray[1];
            }

            return null;
        }

        protected void onPostExecute(Void result) {
            //		pbCircular.setVisibility(View.INVISIBLE);
            if (res == "Invalid User" || res.equalsIgnoreCase("Invalid User") || res.equals("Invalid User")) {
                Toast.makeText(MainActivity.this, "Invalid User Name or Password", Toast.LENGTH_LONG).show();
            } else if (check.equals("Valid User")) {
                //buttonGoToOtherApps.setVisibility(View.VISIBLE);
                onClickAddName();
                Toast.makeText(getApplicationContext(), check, Toast.LENGTH_LONG).show();
                new sendDataToServerAsync().execute();

            }
        }
    }

    class customerAsync extends AsyncTask<Void, Void, Void> {
        String bResponse;

        @Override
        protected Void doInBackground(Void... voids) {
            //Create Webservice class object
            CustomerWebservice com = new CustomerWebservice();

            //Call Webservice class method and pass values and get response
            bResponse = com.checkValidUsernamePassword(companyUrl, "wyCheckPartyLogin", companyName, partyCode, userName, password2.trim());

            Log.i("AndroidExampleOutput", "----" + bResponse);

            return null;
        }

        protected void onPostExecute(Void result) {
            Toast.makeText(getApplicationContext(), bResponse, Toast.LENGTH_LONG).show();

            if (bResponse.equals("Successfully Login")) {
                // buttonGoToOtherApps.setVisibility(View.VISIBLE);
                onClickAddName();
                new sendDataToServerAsync().execute();
            } else {
                Toast.makeText(MainActivity.this, "Invalid User Name or Password", Toast.LENGTH_LONG).show();
            }
        }
    }

    ////////// on Click of submit button this method vl call cz its define in xml file(onClick)//////
    public void onClickAddName() {
        String alphabaticPassword = convertedPassword.trim();
        if (password.trim().equalsIgnoreCase(alphabaticPassword) || password.trim().equals(alphabaticPassword) || password.trim() == alphabaticPassword) {
            //  Toast.makeText(MainActivity.this, "Correct Password", Toast.LENGTH_SHORT).show();

            ContentValues values = new ContentValues();
            values.put(MyProvider.id, imei);
            values.put(MyProvider.name, companyName);
            values.put(MyProvider.companyUrl, companyUrl);
            values.put(MyProvider.partyCode, partyCode);
            values.put(MyProvider.userName, userName);
            values.put(MyProvider.password, password2.trim());
            values.put(MyProvider.financialYear, financialYear);
            values.put(MyProvider.email, email);
            values.put(MyProvider.mobileNo, mobileNo);
            values.put(MyProvider.multipleBranch, multipleBranch);
            values.put(MyProvider.branch, branch);
            Uri uri = getContentResolver().insert(MyProvider.CONTENT_URI, values);
            //   Toast.makeText(getBaseContext(), "New record inserted", Toast.LENGTH_LONG).show();
            //Toast.makeText(MainActivity.this,partyCode,Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "InCorrect Password", Toast.LENGTH_LONG).show();
        }
    }
    //////////////END///////////

    class sendDataToServerAsync extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            DataSendServerWebservice dataSendServerWebservice = new DataSendServerWebservice();
            aResponseServer = dataSendServerWebservice.getConvertedWeight("Registration", companyName, imei, password, selectedItem, partyCode, userName, password2.trim(), email, mobileNo);
            Log.d("RRBBSERVER", "" + aResponseServer);
            return null;
        }

        protected void onPostExecute(Void result) {

        }
    }


    public void imeiConversion(String imeino) {
        int i;
        char[] convertedImeiNo = imeino.toCharArray();

        for (i = 0; i < convertedImeiNo.length; i++) {

            char convtvalue = convertedImeiNo[i];
            switch (convtvalue) {
                case '1':
                    convertedPassword += "a";
                    break;
                case '2':
                    convertedPassword += "b";
                    break;
                case '3':
                    convertedPassword += "c";
                    break;
                case '4':
                    convertedPassword += "d";
                    break;
                case '5':
                    convertedPassword += "e";
                    break;
                case '6':
                    convertedPassword += "f";
                    break;
                case '7':
                    convertedPassword += "g";
                    break;
                case '8':
                    convertedPassword += "h";
                    break;
                case '9':
                    convertedPassword += "i";
                    break;
                case '0':
                    convertedPassword += "j";
                    break;
            }
        }

    }
}
//////hhvfdfxdxsssdsszdsdugyuuyuu