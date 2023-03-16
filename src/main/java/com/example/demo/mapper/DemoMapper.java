package com.example.demo.mapper;

import com.example.demo.param.UserParam;
import com.example.demo.vo.DemoVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName: DemoMapper
 * Author: wangxiaokang
 * Date: 2/2/2023 6:18 PM
 * History:
 * <author>       <time>       <version>       <desc>
 * wangxiaokang   修改时间       1.0.0          备注信息
 */
@Mapper
public  interface DemoMapper {

    List<DemoVo> selectInfo();

    Integer addUser(UserParam userParam);
}
