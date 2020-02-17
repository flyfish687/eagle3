package com.dfsoft.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description: PasswordEncoder 加密实现
 * @author: 史卫鹏
 * @date: 2019年12月31日 下午1:46:15
 * @Copyright:大方软件
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}