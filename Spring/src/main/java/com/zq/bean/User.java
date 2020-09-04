package com.zq.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class User implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private String email;
    @Version
    private Integer version;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createAt;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateAt;

    @TableField(select = false)
    private Integer deleted;
}
