package com.example.sectionrv.JSON;

import android.content.Context;

import com.example.sectionrv.constants.KeyConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONFile {

    private void ResponseParser(Context context){
        String response = JSONFileWriter(context);
        try {
            JSONObject messageDetails = new JSONObject(response);
            Boolean isPreferenceExisting = messageDetails.has("preferences");
            //if preference not exists create
            if (!isPreferenceExisting) {
                JSONArray newPrefArray = new JSONArray();

                JSONObject newPrefObject = new JSONObject();
                newPrefObject.put("preferenceName","Bill Generated");
                newPrefObject.put("preferenceType","Billing Notification");
                newPrefObject.put("notificationFactor","");

                JSONArray channelsArray = new JSONArray();

                JSONObject channelObject = new JSONObject();
                channelObject.put("type","SMS");
                channelObject.put("value","false");

                channelsArray.put(channelObject);

                newPrefObject.put("channels", channelsArray);

                newPrefArray.put(newPrefObject);

                messageDetails.put("preferences",newPrefArray);
            }
            else {
                JSONArray userMessage = (JSONArray) messageDetails.get("preferences");
                JSONObject newPrefObject = new JSONObject();
                newPrefObject.put("preferenceName","Bill Generated");
                newPrefObject.put("preferenceType","Billing Notification");
                newPrefObject.put("notificationFactor","");

                JSONArray channelsArray = new JSONArray();

                JSONObject channelObject = new JSONObject();
                channelObject.put("type","SMS");
                channelObject.put("value","false");

                channelsArray.put(channelObject);

                newPrefObject.put("channels", channelsArray);

                userMessage.put(newPrefObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String JSONFileWriter(Context context){
        File file = new File(context.getFilesDir(), KeyConstants.FILE_NAME);
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        String response = null;

        //if file not exists create a new file

        if (!file.exists()){
            try {
                file.createNewFile();
                fileWriter = new FileWriter(file.getAbsoluteFile());
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("{}");
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //open the file in the read mode using the bufferReader object
        //and read the complete file line by line as a string
        StringBuffer output = new StringBuffer();
        try {
            fileReader = new FileReader(file.getAbsoluteFile());
            bufferedReader = new BufferedReader(fileReader);

            String line ="";
            while ((line = bufferedReader.readLine()) != null){
                output.append(line + "\n");
            }
            response = output.toString();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


}
