package com.zy.yygh.hosp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.yygh.model.hosp.Hospital;

import java.util.Map;

/**
 * @author Zhanye
 * @since 2024/11/15 14:46
 */
public interface HospitalService {
    /**
     * 上传医院信息
     * @param paramMap
     */
    void save(Map<String, Object> paramMap);

    /**
     * 查询医院
     * @param hoscode
     * @return
     */
    Hospital getByHoscode(String hoscode);
}
