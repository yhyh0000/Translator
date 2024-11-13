package com.yinggg.translator.Pojo;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class User {
    private int id;
    private String account;
    private String password;
}
