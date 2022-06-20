package com.remotedesktop.service.impl;


import cn.hutool.core.util.RandomUtil;
import com.remotedesktop.entities.PCRecord;
import com.remotedesktop.mapper.PCRecordMapper;
import com.remotedesktop.service.PCRecordService;
import com.remotedesktop.utils.MyNetUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PCRecordServiceImpl implements PCRecordService {

    @Resource
    private PCRecordMapper pcRecordMapper;

    @Override
    public PCRecord pcRecordByMacAddress(String macAddress) {
        if (!MyNetUtil.isValidMac(macAddress)) {
            return null;
        }
        PCRecord pcRecord = pcRecordMapper.pcRecordByMacAddress(macAddress);
        if (pcRecord == null) {
            String identificationCode = RandomUtil.randomNumbers(6);
            pcRecord = new PCRecord(macAddress, identificationCode);
            pcRecordMapper.insert(pcRecord);
        }
        return pcRecord;
    }
}
