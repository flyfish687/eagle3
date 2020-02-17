package com.dfsoft.constant;

/**
 * @ClassName SecurityConstant
 * @Description Security配置项目常理
 **/
public interface SecurityConstant {
    /**
     * token参数头
     */
    String HEADER = "Authorization";

    /**
     * token分割
     */
    String TOKEN_SPLIT = "Bearer ";

    /**
     * token中自定义权限标识
     */
    String AUTHORITIES = "authorities";
    /**
     * token失效时间
     */
    Integer tokenExpirationTime = 360;

    /**
     * Token 发行人
     */
    String tokenIssuer = "lhm";

    /**
     * JWT签名加密key
     */
    String tokenSigningKey = "dfsoftJWT";

    /**
     * $ 刷新Token时间
     **/
    Integer refreshTokenExpTime = 720;

}
