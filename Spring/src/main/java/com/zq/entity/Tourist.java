package com.zq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 游客表
 * </p>
 *
 * @author ${author}
 * @since 2021-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Tourist implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 游客昵称
     */
    private String touristName;

    /**
     * 登陆的session_id(ip和session有一个相同认为同一个游客)
     */
    private String sessionId;

    /**
     * 登陆的ip(ip和session有一个相同认为同一个游客)
     */
    private String ip;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createAt;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateAt;

    /**
     * 逻辑删除标记(1删除)
     */
    private Integer deleteFlag;


}
