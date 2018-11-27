package com.wyc.ccs81.myprovider;

/**
 * Created by Banke Bihari on 9/14/2016.
 */

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

public class AgentWebservice {

    /**
     * Variable Decleration................
     */
    String namespace = "http://tempuri.org/";
    // private String url = "http://103.16.142.124/WYAactivityWiseSaleMIS_V1/WSMP_GetValidUser.asmx";
    private String url = null;
    String SOAP_ACTION;
    SoapObject request = null, objMessages = null;
    SoapSerializationEnvelope envelope;
    AndroidHttpTransport androidHttpTransport;
    String name, check, startDate;

    AgentWebservice() {
    }

    /**
     * Set Envelope
     */
    protected void SetEnvelope(String appUrl) {
        url = appUrl + "WSMP_GetValidUser.asmx";
        try {

            // Creating SOAP envelope
            envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            //You can comment that line if your web service is not .NET one.
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            androidHttpTransport = new AndroidHttpTransport(url);
            androidHttpTransport.debug = true;

        } catch (Exception e) {
            System.out.println("Soap Exception---->>>" + e.toString());
        }
    }

    // MethodName variable is define for which webservice function  will call
    public String getConvertedWeight(String webUrl, String MethodName, String compID, String userID, String password) {

        try {
            SOAP_ACTION = namespace + MethodName;

            //Adding values to request object
            request = new SoapObject(namespace, MethodName);

            //Adding Double value to request object
//            PropertyInfo weightProp =new PropertyInfo();
//            weightProp.setName("CompId");
//            weightProp.setValue(compID);
//            weightProp.setType(double.class);
//            request.addProperty(weightProp);

            //Adding String value to request object
            request.addProperty("CompId", compID.trim());
            request.addProperty("UID", userID.trim());
            request.addProperty("PWD", password.trim());
//            request.addProperty("CompId", "GTT");
//            request.addProperty("UID","SUP");
//            request.addProperty("PWD","123456");

            SetEnvelope(webUrl);

            try {

                //SOAP calling webservice
                androidHttpTransport.call(SOAP_ACTION, envelope);

                //Got Webservice response
                String result = envelope.getResponse().toString();
                if (result.equals("Invalid User") || result.equalsIgnoreCase("Invalid User") || result == "Invalid User") {

                    return result;
                } else {

                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.optJSONArray("Table");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                        check = jsonObject1.getString("StrValidData");
                        startDate = jsonObject1.getString("StrFinYr");
                    }

                    return check + "%" + startDate;
                }
            } catch (Exception e) {
                // TODO: handle exception
                return e.toString();
            }
        } catch (Exception e) {
            // TODO: handle exception
            return e.toString();
        }

    }
//anyType{PartyName=HEMLATA SINGH; Result=Valid User; }

    /************************************/
}