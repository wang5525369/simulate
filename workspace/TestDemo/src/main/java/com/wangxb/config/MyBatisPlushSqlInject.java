package com.itrus.contract.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import org.apache.ibatis.builder.MapperBuilderAssistant;

public class MyBatisPlushSqlInject implements ISqlInjector {
    MapperBuilderAssistant builderAssistant;
    Class<?> mapperClass;
    @Override
    public void inspectInject(MapperBuilderAssistant builderAssistant, Class<?> mapperClass) {
        this.builderAssistant = builderAssistant;
        this.mapperClass = mapperClass;
    }
}
