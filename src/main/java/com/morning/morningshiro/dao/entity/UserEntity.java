package com.morning.morningshiro.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("hr")
public class UserEntity {
    @TableId("id")
    private Integer id;

    @TableField("username")
    private String userName;

    @TableField("password")
    private String passWord;
}
