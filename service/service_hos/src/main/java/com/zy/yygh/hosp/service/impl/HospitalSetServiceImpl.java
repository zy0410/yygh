package com.zy.yygh.hosp.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.yygh.exception.YydsException;
import com.zy.yygh.hosp.mapper.HospitalSetMapper;
import com.zy.yygh.hosp.service.HospitalSetService;
import com.zy.yygh.model.hosp.HospitalSet;
import com.zy.yygh.result.ResultCodeEnum;
import com.zy.yygh.vo.hosp.HospitalSetQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Zhanye
 * @since 2024/11/10 19:55
 */
@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {
    @Autowired
    private HospitalSetMapper hospitalSetMapper;

    @Override
    public String getSignKey(String hoscode) {
        HospitalSet hospitalSet = getByHoscode(hoscode);
        if (hospitalSet == null) {
            throw new YydsException(ResultCodeEnum.HOSPITAL_OPEN);
        }
        if (hospitalSet.getStatus() == 0) {
            throw new YydsException(ResultCodeEnum.HOSPITAL_LOCK);
        }
        return hospitalSet.getSignKey();
    }

    /**
     * 根据hoscode获取医院设置
     *
     * @param hoscode
     * @return
     */
    private HospitalSet getByHoscode(String hoscode) {
        return hospitalSetMapper.selectOne(new QueryWrapper<HospitalSet>().eq("hoscode", hoscode));
    }
}
