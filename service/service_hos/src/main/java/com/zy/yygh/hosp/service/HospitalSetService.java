package com.zy.yygh.hosp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.yygh.model.hosp.HospitalSet;
import com.zy.yygh.vo.hosp.HospitalSetQueryVo;

import java.util.List;

/**
 * @author Zhanye
 * @since 2024/11/10 21:35
 */
public interface HospitalSetService extends IService<HospitalSet> {

    /**
     * 获取签名key
     * @param hoscode
     * @return
     */
    String getSignKey(String hoscode);
}

