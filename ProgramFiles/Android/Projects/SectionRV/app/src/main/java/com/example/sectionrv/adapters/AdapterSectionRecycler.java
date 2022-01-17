package com.example.sectionrv.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sectionrv.R;
import com.example.sectionrv.model.PreferenceChildSection;
import com.example.sectionrv.model.PreferenceHeaderSection;
import com.intrusoft.sectionedrecyclerview.SectionRecyclerViewAdapter;

import java.util.List;

public class AdapterSectionRecycler extends SectionRecyclerViewAdapter<PreferenceHeaderSection, PreferenceChildSection, PrefHeaderViewHolder, PrefChildViewHolder> {

    Context context;

    public AdapterSectionRecycler(Context context, List<PreferenceHeaderSection> sectionItemList) {
        super(context, sectionItemList);
        this.context = context;
    }

    @Override
    public PrefHeaderViewHolder onCreateSectionViewHolder(ViewGroup sectionViewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.preferences_section_header, sectionViewGroup,false);
        return new PrefHeaderViewHolder(view);
    }

    @Override
    public PrefChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.preference_section_child,childViewGroup,false);
        return new PrefChildViewHolder(view);
    }

    @Override
    public void onBindSectionViewHolder(PrefHeaderViewHolder prefHeaderViewHolder, int i, PreferenceHeaderSection preferenceHeaderSection) {
        prefHeaderViewHolder.headerName.setText(preferenceHeaderSection.getSectionText());
    }

    @Override
    public void onBindChildViewHolder(PrefChildViewHolder prefChildViewHolder, int i, int i1, PreferenceChildSection preferenceChildSection) {

        prefChildViewHolder.description.setText(preferenceChildSection.getDescription());
    }

}
class PrefHeaderViewHolder extends RecyclerView.ViewHolder {

    TextView headerName;

    public PrefHeaderViewHolder(@NonNull View itemView) {
        super(itemView);
        headerName = itemView.findViewById(R.id.pref_sec_header_tv);
    }
}
class PrefChildViewHolder extends RecyclerView.ViewHolder {
    TextView description;
    public PrefChildViewHolder(@NonNull View itemView) {
        super(itemView);

        description = itemView.findViewById(R.id.pref_child_tv);
    }
}
