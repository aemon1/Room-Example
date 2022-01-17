package com.example.sectionrv.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.sectionrv.R;
import com.example.sectionrv.adapters.AdapterSectionRecycler;
import com.example.sectionrv.model.PreferenceHeaderSection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterSectionRecycler adapterSectionRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.main_rv);
        PreferenceResponse preferenceResponse = new PreferenceResponse();
        List<PreferenceHeaderSection> prefHeaderList = preferenceResponse.JSONParsing(this) ;
       setRecyclerView(prefHeaderList);

    }

    private void setRecyclerView(List<PreferenceHeaderSection> sectionList){
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setHasFixedSize(true);
        adapterSectionRecycler = new AdapterSectionRecycler(this, sectionList);
        recyclerView.setAdapter(adapterSectionRecycler);
    }
}