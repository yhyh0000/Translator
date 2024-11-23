package com.yinggg.translator.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * (TUser)实体类
 *
 * @author makejava
 * @since 2024-11-13 13:34:20
 */
@Data
public class TUser implements Serializable {
    private static final long serialVersionUID = -35513291461012880L;

    private Integer id;
    /**
     * 用户账户
     */

    private String username;
    /**
     * 用户密码
     */
    private String password;
    private String email;
    private String created_at;


}

