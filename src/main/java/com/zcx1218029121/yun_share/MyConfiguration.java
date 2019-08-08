package com.zcx1218029121.yun_share;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ZcxProperties.class)
@ConditionalOnClass(ZcxBean.class) // 判断当前目录下是否 有该class文件
@ConditionalOnProperty(prefix="zcx",value="enabled",matchIfMissing=true)//在前缀为author的配置为enabled的情况下，即author=enabled,没有配置默认为enabled
public class MyConfiguration {

    private ZcxProperties zcxProperties;

    @org.springframework.context.annotation.Bean
    public ZcxBean getBean(){
        ZcxBean bean = new ZcxBean();
        bean.setAge(zcxProperties.getAge());
        bean.setName(zcxProperties.getName());
        return bean;
    }

    @Autowired
    public void setZcxProperties(ZcxProperties zcxProperties) {
        this.zcxProperties = zcxProperties;
    }
}
