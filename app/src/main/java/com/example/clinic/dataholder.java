package com.example.clinic;

public class dataholder {
    String firstname, lastname, email, mobile, date, zip, username, userpassword, address, bio, gender, language,nationality, city, usertype, specilization;


<<<<<<< HEAD
    public dataholder(String firstname, String lastname, String email, String mobile, String date, String zip, String username, String userpassword, String address, String bio, String gender, String language, String nationality, String city, String usertype, String specilization) {
=======
    public dataholder(String firstname, String lastname, String email, String mobile, String date, String zip, String username, String userpassword, String address, String bio,
                      String gender, String language, String nationality, String city, String usertype, String specilization) {
>>>>>>> origin/master
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.mobile = mobile;
        this.date = date;
        this.zip = zip;
        this.username = username;
        this.userpassword = userpassword;
        this.address = address;
        this.bio = bio;
<<<<<<< HEAD
        this.gender = this.gender;
        this.language = this.language;
        this.nationality = this.nationality;
        this.city = this.city;
        this.usertype = this.usertype;
        this.specilization = this.specilization;

    }

    public dataholder() {
=======
        this.gender = gender;
        this.language = language;
        this.nationality = nationality;
        this.city = city;
        this.usertype = usertype;
        this.specilization = specilization;
>>>>>>> origin/master

    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getSpecilization() {
        return specilization;
    }

    public void setSpecilization(String specilization) {
        this.specilization = specilization;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}