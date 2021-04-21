package com.example.noname.Model;

public class benhnhan
{
    int ma;
    public  String ID,Docid,PatientName,PatientContno,PatientEmail,PatientGender,PatientAdd,PatientAge,PatientMedhis,CreationDate;
    public benhnhan(){}
    public benhnhan(int ma, String ID, String docid, String patientName, String patientContno, String patientEmail, String patientGender, String patientAdd, String patientAge, String patientMedhis, String creationDate) {
        this.ma = ma;
        this.ID = ID;
        Docid = docid;
        PatientName = patientName;
        PatientContno = patientContno;
        PatientEmail = patientEmail;
        PatientGender = patientGender;
        PatientAdd = patientAdd;
        PatientAge = patientAge;
        PatientMedhis = patientMedhis;
        CreationDate = creationDate;
    }
}
