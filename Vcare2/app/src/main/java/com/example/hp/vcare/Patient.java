package com.example.hp.vcare;

/**
 * Created by HP on 10-07-2017.
 */

public class Patient {
    private String id;
    private String name;
    private String phone;
    private String blood;
    private String disease;
    private String age;
    private String gender;

    private String medicine;
    private String doctor;
    private String time;
    private String dodont;
 public Patient(){

    }
   public Patient(String id,String name,String phone,String blood,String disease,String age,String gender) {
       this.id=id;
       this.name=name;
       this.phone=phone;
       this.blood=blood;
       this.disease=disease;
       this.age=age;
       this.gender=gender;
   }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDodont() {
        return dodont;
    }

    public void setDodont(String dodont) {
        this.dodont = dodont;
    }
}
