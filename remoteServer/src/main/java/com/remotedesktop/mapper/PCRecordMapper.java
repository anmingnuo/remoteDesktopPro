package com.remotedesktop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.remotedesktop.entities.PCRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PCRecordMapper extends BaseMapper<PCRecord> {
    PCRecord pcRecordByMacAddress(@Param("macAddress") String macAddress);
}
