package com.example.umbrella.dto;

public class CenterDto {

    String id;
    String pw;
    String name;
    String phone;
    int rentalUmbCnt;
    int level;
    String centercode;
    String centerAddr;
    String regId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
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

    public int getRentalUmbCnt() {
        return rentalUmbCnt;
    }

    public void setRentalUmbCnt(int rentalUmbCnt) {
        this.rentalUmbCnt = rentalUmbCnt;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCentercode() {
        return centercode;
    }

    public void setCentercode(String centercode) {
        this.centercode = centercode;
    }

    public String getCenterAddr() {
        return centerAddr;
    }

    public void setCenterAddr(String centerAddr) {
        this.centerAddr = centerAddr;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    @Override
    public String toString() {
        return "CenterDto{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", rentalUmbCnt=" + rentalUmbCnt +
                ", level=" + level +
                ", centercode='" + centercode + '\'' +
                ", centerAddr='" + centerAddr + '\'' +
                ", regId='" + regId + '\'' +
                '}';
    }
}
