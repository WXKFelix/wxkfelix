package com.example.demo.service;

import com.example.demo.param.UserParam;
import com.example.demo.vo.DemoVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: DemoService
 * Description: TODO
 * Author: wangxiaokang
 * Date: 2/2/2023 6:06 PM
 * History:
 * <author>       <time>       <version>       <desc>
 * wangxiaokang   修改时间       1.0.0          备注信息
 */

public interface DemoService {
    List<DemoVo> selectInfo();
    Integer addUser(UserParam userParam);
}
