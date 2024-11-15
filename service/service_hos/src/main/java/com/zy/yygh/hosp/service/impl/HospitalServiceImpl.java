package com.zy.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.yygh.exception.YydsException;
import com.zy.yygh.hosp.mapper.HospitalSetMapper;
import com.zy.yygh.hosp.repository.HospitalRepository;
import com.zy.yygh.hosp.service.HospitalService;
import com.zy.yygh.model.hosp.Hospital;
import com.zy.yygh.model.hosp.HospitalSet;
import com.zy.yygh.result.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author Zhanye
 * @since 2024/11/15 14:47
 */
@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;


    @Override
    public void save(Map<String, Object> paramMap) {
        Hospital hospital = JSONObject.parseObject(JSONObject.toJSONString(paramMap), Hospital.class);
        Hospital targetHospital = hospitalRepository.getHospitalByHoscode(hospital.getHoscode());
        if (targetHospital != null) {
            hospital.setStatus(targetHospital.getStatus());
            hospital.setCreateTime(targetHospital.getCreateTime());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        } else {
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }
    }

    @Override
    public Hospital getByHoscode(String hoscode) {
        return hospitalRepository.getHospitalByHoscode(hoscode);
    }

}
