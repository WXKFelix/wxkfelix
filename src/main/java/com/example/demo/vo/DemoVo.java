package com.example.demo.vo;

/**
 * ClassName: DemoVo
 * Description: TODO
 * Author: wangxiaokang
 * Date: 2/2/2023 6:10 PM
 * History:
 * <author>       <time>       <version>       <desc>
 * wangxiaokang   修改时间       1.0.0          备注信息
 */
public class DemoVo {
    //用户ID
    private String id;
    //用户名称
    private String name;
    //创建时间
    private String time;
    //用户地址
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "DemoVo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
