package com.example.demo.param;

import java.io.Serializable;

/**
 * ClassName: userParam
 * Description: TODO
 * Author: wangxiaokang
 * Date: 2/10/2023 2:47 PM
 * History:
 * <author>       <time>       <version>       <desc>
 * wangxiaokang   修改时间       1.0.0          备注信息
 */
public class UserParam implements Serializable {
    private static final long serialVersionUID = 4091222488246593183L;
    //主键id
    private String id;
    //员工姓名
    private String name;
    //创建时间
    //private String time;
    //员工地址
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

/*    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }*/

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
