package com.xuhaoming.hamidemo.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Redis缓存对示例象
 *
 * @author xuhaoming
 * @date 2021/10/3
 */
@Data
public class RedisCacheVO implements Serializable {

    private Long id;

    private String name;

    private Date createTime;

}
