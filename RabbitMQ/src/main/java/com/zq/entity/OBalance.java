package com.zq.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 余额表
 * </p>
 *
 * @author ${author}
 * @since 2021-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OBalance implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 余额
     */
    private BigDecimal balance;


}
