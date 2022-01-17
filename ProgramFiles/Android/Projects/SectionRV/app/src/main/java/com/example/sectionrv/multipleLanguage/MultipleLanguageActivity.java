package com.example.sectionrv.multipleLanguage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sectionrv.R;
import com.example.sectionrv.dataBindingClasses.MultiLang;
import com.example.sectionrv.databinding.ActivityMultipleLanguageBinding;
import com.example.sectionrv.multipleLanguage.Helper.LocaleHelper;
import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

import io.paperdb.Paper;

import static com.example.sectionrv.constants.KeyConstants.ENG_LOCALE;
import static com.example.sectionrv.constants.KeyConstants.LANGUAGE_KEY;
import static com.example.sectionrv.constants.KeyConstants.URDU_LOCALE;

public class MultipleLanguageActivity extends AppCompatActivity {

    String empID, name, project, language, experience;
    MultiLang multiLang;
    ActivityMultipleLanguageBinding languageBinding;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase,ENG_LOCALE));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //databinding
        languageBinding = DataBindingUtil.setContentView(this, R.layout.activity_multiple_language);

        multiLang = new MultiLang(getString(R.string.employeeID),getString(R.string.name),getString(R.string.project),getString(R.string.languages),getString(R.string.experience));
        languageBinding.setMultiLang(multiLang);

        Paper.init(this);

        setOrientation(this);

        languageBinding.switchLang.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // The toggle is enabled
                paperBook(LANGUAGE_KEY,URDU_LOCALE);
            } else {
                // The toggle is disabled
                paperBook(LANGUAGE_KEY,ENG_LOCALE);
            }
        });
    }

    public void paperBook(String key, String locale){
        Paper.book().write(key,locale);
        updateView((String)Paper.book().read(key));
        snackBar(locale);
    }

    public void setOrientation(Context context){
        OrientationEventListener orientationEventListener = new OrientationEventListener(context) {
            @Override
            public void onOrientationChanged(int orientation) {
                int epsilon = 10;
                int leftLandscape = 90;
                int rightLandscape = 270;
                if(epsilonCheck(orientation, leftLandscape, epsilon) ||
                        epsilonCheck(orientation, rightLandscape, epsilon)){
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                }
            }

            private boolean epsilonCheck(int a, int b, int epsilon) {
                return a > b - epsilon && a < b + epsilon;
            }
        };
        orientationEventListener.enable();
    }

    private void updateView(String lang) {
        Context context = LocaleHelper.setLocale(this,lang);
        Resources resources = context.getResources();

        empID = resources.getString(R.string.employeeID);
        name = resources.getString(R.string.name);
        project = resources.getString(R.string.project);
        language = resources.getString(R.string.languages);
        experience = resources.getString(R.string.experience);
        multiLang = new MultiLang(empID,name,project,language,experience);
        languageBinding.setMultiLang(multiLang);

    }

    public void snackBar(String lang){
        Snackbar snackbar = Snackbar.make(languageBinding.constraint, lang, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

}