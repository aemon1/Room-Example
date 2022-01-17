package com.example.sectionrv.activities;

import android.content.Context;

import com.example.sectionrv.JSON.JSONHelper;
import com.example.sectionrv.constants.KeyConstants;
import com.example.sectionrv.model.PreferenceChildSection;
import com.example.sectionrv.model.PreferenceHeaderSection;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PreferenceResponse {

    private List<PreferenceHeaderSection> prefHeaderList =new ArrayList<>();

    public List<PreferenceHeaderSection> JSONParsing(Context context){


        try {
            // get JSONObject from JSON file
            JSONObject obj = new JSONObject(JSONHelper.loadJSONFromAsset(context, KeyConstants.JSON_FILE));

            JSONObject preferenceObject = obj.getJSONObject(KeyConstants.PREFERENCE_OBJECT);
            JSONArray prefArray = preferenceObject.getJSONArray(KeyConstants.PREFERENCE_ARRAY);

            for(int i = 0 ;i< prefArray.length(); i++){
                //JSONObject prefDetail = preferenceObject.getJSONObject("preferences");
                JSONObject prefDetail = prefArray.getJSONObject(i);
                String preferenceName = prefDetail.getString(KeyConstants.PREFERENCE_NAME);
                String preferenceType = prefDetail.getString(KeyConstants.PREFERENCE_TYPE);

                PreferenceHeaderSection section = getPreferenceHeaderSection(preferenceType);

                if(section == null){
                    section = new PreferenceHeaderSection(new ArrayList<>(), preferenceType);
                    prefHeaderList.add(section);
                }
                section.getChildItems().add(new PreferenceChildSection(preferenceName));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return prefHeaderList;
    }

    private PreferenceHeaderSection getPreferenceHeaderSection(String type){
        if(prefHeaderList != null){
            for (int i = 0; i < prefHeaderList.size(); i++) {
                String prefText = prefHeaderList.get(i).getSectionText();
                if (type.equals(prefText)){
                    return prefHeaderList.get(i);
                }
            }
        }
        return null;
    }

}
