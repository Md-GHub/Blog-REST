package com.mdghu.blog.model;

public class ConfirmationMail {
    private String mail;
    private String otp;
    public ConfirmationMail(String mail, String otp) {
        this.mail = mail;
        this.otp = otp;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}

