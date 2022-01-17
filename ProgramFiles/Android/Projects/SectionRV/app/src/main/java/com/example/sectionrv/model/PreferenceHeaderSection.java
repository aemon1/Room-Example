package com.example.sectionrv.model;

import com.intrusoft.sectionedrecyclerview.Section;

import java.util.List;

public class PreferenceHeaderSection implements Section<PreferenceChildSection> {

    private List<PreferenceChildSection> childSectionList;
    private String description;

    public PreferenceHeaderSection(List<PreferenceChildSection> childSectionList, String description){
        this.childSectionList = childSectionList;
        this.description = description;
    }

    @Override
    public List<PreferenceChildSection> getChildItems() {
        return childSectionList;
    }

    public String getSectionText() {
        return description;
    }
}
