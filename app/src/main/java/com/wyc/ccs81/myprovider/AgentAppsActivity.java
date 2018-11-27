package com.wyc.ccs81.myprovider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by ccs81 on 27/06/2017.
 */

public class AgentAppsActivity extends Activity {
    Button buttonAWSales,buttonAirlineSales,buttonAWSalesTKT,buttonAWSalesMIS,buttonAirlineWiseBusEco;

    Button buttonCustomerStatement,buttonLedger,buttonPartyBalance,buttonBusinessReport,buttonStatusCheck,
            buttonMOption,buttonAutoMail,buttonReceiptEntry,buttonReceiptRegister,buttonTrialBalance,buttonAcceptDuty;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agentapps);

        buttonAWSales = (Button) findViewById(R.id.aw_sales);
        buttonAirlineSales=(Button) findViewById(R.id.airline_sales);
        buttonAWSalesMIS=(Button) findViewById(R.id.aw_salesmis);
        buttonAWSalesTKT=(Button) findViewById(R.id.aw_salestkt);
        buttonAirlineWiseBusEco = (Button) findViewById(R.id.aw_alirlinewise_buseco);

        buttonCustomerStatement =(Button) findViewById(R.id.btn_customer_statement);
        buttonLedger = (Button) findViewById(R.id.ledger);
        buttonPartyBalance = (Button) findViewById(R.id.partybalance);
        buttonBusinessReport=(Button) findViewById(R.id.btn_customerbusinessreport);
        // buttonUrlLink=(Button) findViewById(R.id.urllink);
        //  buttonStatusCheck=(Button) findViewById(R.id.statuscheck);
        buttonMOption=(Button) findViewById(R.id.moption);
        buttonAutoMail=(Button) findViewById(R.id.automail);
        buttonReceiptEntry = (Button) findViewById(R.id.btn_receipt_entry);
        buttonReceiptRegister = (Button) findViewById(R.id.btn_receipt_register);
        buttonTrialBalance = (Button) findViewById(R.id.btn_trial_balance);
        buttonAcceptDuty = (Button) findViewById(R.id.btn_accept_duty);
        buttonAWSales.setVisibility(View.INVISIBLE);
        buttonAWSalesTKT.setVisibility(View.INVISIBLE);
//        buttonAWSales.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.ccs.bankebihari.wyaactivitywisesale");
//                if (launchIntent != null) {
//                    startActivity(launchIntent);//null pointer check in case package name was not found
//                }
//
//            }
//        });

        buttonAirlineSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.ccs.bankebihari.wycairlinesales");
                if (launchIntent != null) {
                    startActivity(launchIntent);//null pointer check in case package name was not found
                }
            }
        });


        buttonAWSalesMIS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.ccs.bankebihari.wyc_awsale_mis");
                if (launchIntent!= null) {
                    startActivity(launchIntent);
                }
            }
        });

//        buttonAWSalesTKT.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.ccs.bankebihari.wyc_awsale_tkt");
//                if (launchIntent!=null){
//                    startActivity(launchIntent);
//                }
//            }
//        });


        buttonAirlineWiseBusEco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.ccs.ccs81.wya_arlnwise_bus_eco");
                if (launchIntent!=null){
                    startActivity(launchIntent);
                }
            }
        });





        buttonCustomerStatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.ccs.ccs81.wyc_cus_state");
                if (intent!=null){
                    startActivity(intent);
                }
            }
        });

        buttonLedger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.ccs.ccs81.wyc_aw_ledger");
                if (intent!=null){
                    startActivity(intent);
                }
            }
        });

        buttonPartyBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.ccs.ccs81.wyc_partybalance");
                if (intent!=null){
                    startActivity(intent);
                }
            }
        });

        buttonBusinessReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(AgentAppsActivity.this,"rrbb11111111",Toast.LENGTH_LONG).show();
//                String pckgName = "com.ccs81.bankebihari.wyc_customerbusinessreport";
                Intent intentbr = getPackageManager().getLaunchIntentForPackage("com.ccs.bankebihari.WYC_CustomerBusinessReport");
                if (intentbr!=null){
                    //  Toast.makeText(AgentAppsActivity.this,"rrbb22222",Toast.LENGTH_LONG).show();
                    startActivity(intentbr);
                }
            }
        });


//        buttonStatusCheck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = getPackageManager().getLaunchIntentForPackage("com.ccs.ccs81.wyc_statuscheck");
//                if (intent!=null){
//                    startActivity(intent);
//                }
//            }
//        });

        buttonMOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.ccs.ccs81.wyc_moption");
                if (intent!=null){
                    startActivity(intent);
                }
            }
        });

        buttonAutoMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.ccs.bankebihari.winyatra");
                if (intent!=null){
                    startActivity(intent);
                }
            }
        });

        buttonReceiptEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.ccs.ccs81.wyc_receiptentry");
                if (intent!=null){
                    startActivity(intent);
                }
            }
        });

        buttonReceiptRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.ccs.ccs79.wyc_receiptregister");
                if (intent!=null){
                    startActivity(intent);
                }
            }
        });

        buttonTrialBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.ccs.bankebihari.wyc_trial_balance");
                if (intent!=null){
                    startActivity(intent);
                }
            }
        });

        buttonAcceptDuty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.RRBB.WYTMS_AcceptDuty");
                if (intent!=null){
                    startActivity(intent);
                }
            }
        });

    }

}
