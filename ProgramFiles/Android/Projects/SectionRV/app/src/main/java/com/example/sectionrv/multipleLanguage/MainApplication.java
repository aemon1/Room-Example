package com.example.sectionrv.multipleLanguage;

import android.app.Application;
import android.content.Context;

import com.example.sectionrv.multipleLanguage.Helper.LocaleHelper;

import static com.example.sectionrv.constants.KeyConstants.ENG_LOCALE;

public class MainApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base,ENG_LOCALE));

    }
}
