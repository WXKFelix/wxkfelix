package com.example.demo.service.impl;

import com.example.demo.mapper.DemoMapper;
import com.example.demo.param.UserParam;
import com.example.demo.service.DemoService;
import com.example.demo.vo.DemoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: DemoServiceImpl
 * Author: wangxiaokang
 * Date: 2/2/2023 6:07 PM
 * History:
 * <author>       <time>       <version>       <desc>
 * wangxiaokang   修改时间       1.0.0          备注信息
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Resource
    private DemoMapper demoMapper;

    @Override
    public List<DemoVo> selectInfo() {
        List<DemoVo> demoVos = demoMapper.selectInfo();
        return demoVos;
    }

    @Override
    public Integer addUser(UserParam userParam) {
        return demoMapper.addUser(userParam);
    }

}
