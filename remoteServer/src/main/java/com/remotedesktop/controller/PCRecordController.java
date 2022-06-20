package com.remotedesktop.controller;

import com.remotedesktop.entities.PCRecord;
import com.remotedesktop.entities.Result;
import com.remotedesktop.service.PCRecordService;
import com.remotedesktop.utils.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/pcrecord")
public class PCRecordController {
    @Resource
    private PCRecordService pcRecordService;
    @GetMapping("/getIdentificationCode")
    public Result getIdentificationCodeByMacAddress(String macAddress){
        PCRecord pcRecord = pcRecordService.pcRecordByMacAddress(macAddress);
        System.out.println("------------"+macAddress);
        if(pcRecord==null){
            return ResultUtil.error(500,"mac地址不合法");
        }
        return ResultUtil.success(pcRecord.getIdentificationCode());
    }

}
