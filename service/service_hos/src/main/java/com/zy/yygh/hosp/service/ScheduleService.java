package com.zy.yygh.hosp.service;

import com.zy.yygh.model.hosp.Schedule;
import com.zy.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @author Zhanye
 * @since 2024/11/15 16:53
 */
public interface ScheduleService {
    /**
     * 上传排班信息
     * @param paramMap
     */
    void save(Map<String, Object> paramMap);
    /**
     * 分页查询
     * @param page 当前页码
     * @param limit 每页记录数
     * @param scheduleQueryVo 查询条件
     * @return
     */
    Page<Schedule> selectPage(Integer page, Integer limit, ScheduleQueryVo scheduleQueryVo);
    /**
     * 删除科室
     * @param hoscode
     * @param hosScheduleId
     */
    void remove(String hoscode, String hosScheduleId);


}
