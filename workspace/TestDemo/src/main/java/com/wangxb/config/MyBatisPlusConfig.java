package com.itrus.contract.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MyBatisPlusConfig
 * @Author: Yuliu
 * @Description:
 * @Date: Created in 13:58 2020/10/28
 * @Modified By:
 * @Version 1.0
 **/
@Configuration
public class MyBatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        //分页查询500条限制
        paginationInterceptor.setLimit(-1);
        return paginationInterceptor;
    }

}
