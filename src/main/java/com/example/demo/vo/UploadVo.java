package com.example.demo.vo;

import java.io.Serializable;

/**
 * ClassName: UploadVo
 * Description: TODO
 * Author: wangxiaokang
 * Date: 2/7/2023 9:35 AM
 * History:
 * <author>       <time>       <version>       <desc>
 * wangxiaokang   修改时间       1.0.0          备注信息
 */
public class UploadVo implements Serializable {

    private static final long serialVersionUID = 8068557285232122117L;

    private String svg;

    private String name;

    private String type;

    public String getSvg() {
        return svg;
    }

    public void setSvg(String svg) {
        this.svg = svg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
