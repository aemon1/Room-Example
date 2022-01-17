package com.example.sectionrv.dataBindingClasses;



public class MultiLang {
    private String EmpId, Name, Project, Language, Experience;

    public MultiLang(String empId, String name, String project, String language, String experience) {
        EmpId = empId;
        Name = name;
        Project = project;
        Language = language;
        Experience = experience;
    }

    public String getEmpId() {
        return EmpId;
    }

    public void setEmpId(String empId) {
        EmpId = empId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getProject() {
        return Project;
    }

    public void setProject(String project) {
        Project = project;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getExperience() {
        return Experience;
    }

    public void setExperience(String experience) {
        Experience = experience;
    }
}
