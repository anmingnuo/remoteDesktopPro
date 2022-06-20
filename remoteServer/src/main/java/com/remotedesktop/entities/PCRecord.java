package com.remotedesktop.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


@TableName(value = "pc_record")
public class PCRecord {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String macAddress;
    private String identificationCode;

    public PCRecord() {
    }

    public PCRecord(Integer id, String macAddress, String identificationCode) {
        this.id = id;
        this.macAddress = macAddress;
        this.identificationCode = identificationCode;
    }

    public PCRecord(String macAddress, String identificationCode) {
        this.macAddress = macAddress;
        this.identificationCode = identificationCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getIdentificationCode() {
        return identificationCode;
    }

    public void setIdentificationCode(String identificationCode) {
        this.identificationCode = identificationCode;
    }

    @Override
    public String toString() {
        return "PCRecord{" +
                "id=" + id +
                ", macAddress='" + macAddress + '\'' +
                ", identificationCode='" + identificationCode + '\'' +
                '}';
    }
}
