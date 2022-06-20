package com.remotedesktop.service;


import com.remotedesktop.entities.PCRecord;

public interface PCRecordService{
    PCRecord pcRecordByMacAddress(String macAddress);
}
