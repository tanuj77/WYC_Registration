package com.wyc.ccs81.myprovider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by ccs81 on 27/06/2017.
 */

public class CustomerAppsActivity extends Activity {
    Button buttonCustomerStatement,buttonLedger,buttonPartyBalance,buttonBusinessReport,buttonStatusCheck,buttonFeedbackForm,
            buttonMOption,buttonAutoMail,buttonReceiptEntry;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerapps);

        buttonCustomerStatement =(Button) findViewById(R.id.btn_customer_statement);
        buttonLedger = (Button) findViewById(R.id.ledger);
        buttonPartyBalance = (Button) findViewById(R.id.partybalance);
        buttonBusinessReport=(Button) findViewById(R.id.btn_customerbusinessreport);
        // buttonUrlLink=(Button) findViewById(R.id.urllink);
        //  buttonStatusCheck=(Button) findViewById(R.id.statuscheck);
        buttonMOption=(Button) findViewById(R.id.moption);
        buttonAutoMail=(Button) findViewById(R.id.automail);
        buttonReceiptEntry = (Button) findViewById(R.id.btn_receipt_entry);

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
                Toast.makeText(CustomerAppsActivity.this,"rrbb11111111",Toast.LENGTH_LONG).show();
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.ccs.bankebihari.wyc_customerbusinessreport");
                if (intent!=null){
                    Toast.makeText(CustomerAppsActivity.this,"rrbb22222",Toast.LENGTH_LONG).show();
                    startActivity(intent);
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

        buttonFeedbackForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.example.ccs79.wyc_frm_feedbackform");
                if (intent!=null){
                    startActivity(intent);
                }
            }
        });
    }
}
