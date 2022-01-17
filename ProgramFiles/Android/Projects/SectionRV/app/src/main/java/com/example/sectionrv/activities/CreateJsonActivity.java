package com.example.sectionrv.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.sectionrv.R;
import com.example.sectionrv.constants.FileConstants;
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
import java.util.ArrayList;

import static com.example.sectionrv.constants.FileConstants.bufferedReader;
import static com.example.sectionrv.constants.FileConstants.bufferedWriter;
import static com.example.sectionrv.constants.FileConstants.file;
import static com.example.sectionrv.constants.FileConstants.fileReader;
import static com.example.sectionrv.constants.FileConstants.fileWriter;
import static com.example.sectionrv.constants.FileConstants.response;
import static com.example.sectionrv.constants.KeyConstants.channel;
import static com.example.sectionrv.files.FileCreator.JsonFileCreator;
import static com.example.sectionrv.files.FileCreator.JsonFileReader;
import static com.example.sectionrv.files.FileCreator.JsonFileWriter;

public class CreateJsonActivity extends AppCompatActivity {

    JSONArray newPrefArray = new JSONArray();
    JSONObject newPrefObject = new JSONObject();
    JSONArray channelsArray = new JSONArray();
    JSONObject channelObject = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_json);
        file = new File(this.getFilesDir(), KeyConstants.FILE_NAME);

        //JsonFile(file);
        check(file);

    }

    void check(File file){
        String jsonResponse = JsonFileCreator(file);
        JSONObject messageDetails = null;
        try {
            messageDetails = new JSONObject(jsonResponse);

            Boolean isPreferenceExisting = messageDetails.has("preferences");
            //if preference not exists create
            if (!isPreferenceExisting) {
                for (int i = 0; i < KeyConstants.keys.length; i++) {
                    newPrefObject.put(KeyConstants.keys[i], KeyConstants.values[i]);
                    for (int j = 0; j < channel.length; j++) {
                    channelObject.put(channel[j], KeyConstants.channelVal[j]);
                }

            }
            channelsArray.put(channelObject);
            newPrefObject.put("channels", channelsArray);
            newPrefArray.put(newPrefObject);
            messageDetails.put("preferences", newPrefArray);
            }

            else {
                JSONArray userMessage = (JSONArray) messageDetails.get("preferences");
                for (int i = 0; i < KeyConstants.keys2.length; i++) {
                    newPrefObject.put(KeyConstants.keys2[i], KeyConstants.values2[i]);

                    for (int j = 0; j < KeyConstants.channel2.length; j++) {
                        channelObject.put(KeyConstants.channel2[j], KeyConstants.channelVal2[j]);
                    }


                }
                channelsArray.put(channelObject);
                newPrefObject.put("channels", channelsArray);

                userMessage.put(newPrefObject);

                messageDetails.put("preferences", userMessage);

            }
            JsonFileWriter(file,messageDetails);


        } catch (JSONException e) {
            e.printStackTrace();
        }

      String result = JsonFileReader(file);
        System.out.println(result);


    }

    private void JsonFile(File file) {

        String jsonResponse = JsonFileCreator(file);

        try {
            JSONObject messageDetails = new JSONObject(jsonResponse);
            Boolean isPreferenceExisting = messageDetails.has("preferences");
            //if preference not exists create
            if (!isPreferenceExisting) {

                newPrefObject.put("preferenceName","Bill Generated");
                newPrefObject.put("preferenceType","Billing Notification");
                newPrefObject.put("notificationFactor","");

                channelObject.put("type","SMS");
                channelObject.put("value","false");

                channelsArray.put(channelObject);

                channelObject.put("type","Email");
                channelObject.put("value","false");

                channelsArray.put(channelObject);
                newPrefObject.put("channels", channelsArray);

                newPrefArray.put(newPrefObject);

                messageDetails.put("preferences",newPrefArray);
            }
            else {
                JSONArray userMessage = (JSONArray) messageDetails.get("preferences");
                newPrefObject.put("preferenceName","Bill Generated");
                newPrefObject.put("preferenceType","Billing Notification");
                newPrefObject.put("notificationFactor","");

                channelObject.put("type","SMS");
                channelObject.put("value","false");

                channelsArray.put(channelObject);

                channelObject.put("type","Email");
                channelObject.put("value","false");

                channelsArray.put(channelObject);

                newPrefObject.put("channels", channelsArray);

                userMessage.put(newPrefObject);

                messageDetails.put("preferences",userMessage);
            }

            JsonFileWriter(file,messageDetails);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String result = JsonFileReader(file);
        System.out.println(result);

    }


}