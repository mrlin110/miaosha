package com.mrlin.miaosha.common;

public interface Constant {

    //默认盐
    String SALT_CODE = "swqfq1f0";

    String COOKI_NAME_TOKEN = "token";
    /**
     * reidis
     */
    interface RedisKey {
         String TOKEN_KEY = "Token:";

         int  TOKEN_EXPIRE = 3600*24*2;

         String GOODS_LIST_KEY ="html:goods_list:";

        String GOODS_DETAIL_KEY ="html:goods_list:";
         int   HTML_EXPIRE = 60;
    }

}
