package com.zy.yygh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.yygh.model.cmn.Dict;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Zhanye
 * @since 2024/11/13 21:34
 */
public interface DictService extends IService<Dict> {
    //根据数据id查询子数据列表
    List<Dict> findChlidData(Long id);

    /**
     * 导出
     * @param response
     */
    void exportData(HttpServletResponse response);

    void importDictData(MultipartFile file);
}
