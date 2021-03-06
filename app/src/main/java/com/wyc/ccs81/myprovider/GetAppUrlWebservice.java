package com.wyc.ccs81.myprovider;

/**
 * Created by Banke Bihari on 9/14/2016.
 */

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

/**
 * @author AndroidExample DotNetWebService Class
 */
public class GetAppUrlWebservice {

    /**
     * Variable Decleration................
     */
    String namespace = "http://tempuri.org/";
    private String url = "http://43.240.65.164/MOBWEBSERVICE/geturl.asmx";


    String SOAP_ACTION;
    SoapObject request = null, objMessages = null;
    SoapSerializationEnvelope envelope;
    AndroidHttpTransport androidHttpTransport;
    String name, check, startDate;

    GetAppUrlWebservice() {
    }
    /**
     * Set Envelope
     */
    protected void SetEnvelope() {

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
    public String getConvertedWeight(String MethodName, String compID) {

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
            request.addProperty("Compid", compID);
            request.addProperty("BranchName", "");


            SetEnvelope();

            try {

                //SOAP calling webservice
                androidHttpTransport.call(SOAP_ACTION, envelope);

                //Got Webservice response
                String result = envelope.getResponse().toString();

//                JSONObject jsonRootObject = new JSONObject(result);
//
//                //Get the instance of JSONArray that contains JSONObjects
//                JSONArray jsonArray = jsonRootObject.optJSONArray("Table");
//
//                //Iterate the jsonArray and print the info of JSONObjects
//                for(int i=0; i < jsonArray.length(); i++){
//                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                  startDate =  jsonObject.getString("StrFinYr");
//                  check = jsonObject.getString("StrValidData");
//
//                }
                return result;

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