package com.example.MyProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Project {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String fullName;
        private String dob;
        private String gender;
        private String education;
        private String mobile;
        private String email;
        private String guardianName;
        private String guardianOccupation;
        private String guardianMobile;
        private String course;
        private String trainingMode;
        private String scopeLocation;
        private String preferredTimings;
        private String address;
        private String country;
        private String state;
        private String city;
        private String pinCode;
        private  String otp;
        private boolean verified;
        private String username;
        private String password;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getGuardianName() {
            return guardianName;
        }

        public void setGuardianName(String guardianName) {
            this.guardianName = guardianName;
        }

        public String getGuardianOccupation() {
            return guardianOccupation;
        }

        public void setGuardianOccupation(String guardianOccupation) {
            this.guardianOccupation = guardianOccupation;
        }

        public String getGuardianMobile() {
            return guardianMobile;
        }

        public void setGuardianMobile(String guardianMobile) {
            this.guardianMobile = guardianMobile;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public String getTrainingMode() {
            return trainingMode;
        }

        public void setTrainingMode(String trainingMode) {
            this.trainingMode = trainingMode;
        }

        public String getScopeLocation() {
            return scopeLocation;
        }

        public void setScopeLocation(String scopeLocation) {
            this.scopeLocation = scopeLocation;
        }

        public String getPreferredTimings() {
            return preferredTimings;
        }

        public void setPreferredTimings(String preferredTimings) {
            this.preferredTimings = preferredTimings;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPinCode() {
            return pinCode;
        }

        public void setPinCode(String pinCode) {
            this.pinCode = pinCode;
        }

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }

        public boolean isVerified() {
            return verified;
        }

        public void setVerified(boolean verified) {
            this.verified = verified;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }


