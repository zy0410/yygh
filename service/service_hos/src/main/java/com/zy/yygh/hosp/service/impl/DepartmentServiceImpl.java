package com.zy.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zy.yygh.hosp.repository.DepartmentRepository;
import com.zy.yygh.hosp.service.DepartmentService;
import com.zy.yygh.model.hosp.Department;
import com.zy.yygh.util.BeanUtil;
import com.zy.yygh.vo.hosp.DepartmentQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author Zhanye
 * @since 2024/11/15 15:46
 */
@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void save(Map<String, Object> paramMap) {
        Department department = JSONObject.parseObject(JSONObject.toJSONString(paramMap), Department.class);
        Department targetDepartment = departmentRepository.getDepartmentByHoscodeAndDepcode(department.getHoscode(), department.getDepcode());
        if (targetDepartment != null){
            //已有这个科室进行更新
            BeanUtil.copyNotNull(department, targetDepartment);
            departmentRepository.save(targetDepartment);
        }else {
            //没有这个科室进行插入
            department.setCreateTime(new Date());
            department.setUpdateTime(new Date());
            department.setIsDeleted(0);
            departmentRepository.save(department);
        }
    }

    @Override
    public Page<Department> selectPage(Integer page, Integer limit, DepartmentQueryVo departmentQueryVo) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        //0为第一页
        Pageable pageable = PageRequest.of(page-1, limit, sort);

        Department department = new Department();
        BeanUtils.copyProperties(departmentQueryVo, department);
        department.setIsDeleted(0);

        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true); //改变默认大小写忽略方式：忽略大小写

        //创建实例
        Example<Department> example = Example.of(department, matcher);
        Page<Department> pages = departmentRepository.findAll(example, pageable);
        return pages;
    }

    @Override
    public void remove(String hoscode, String depcode) {
        Department department = departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode, depcode);
        if(null != department) {
            departmentRepository.deleteById(department.getId());
        }
    }

}
