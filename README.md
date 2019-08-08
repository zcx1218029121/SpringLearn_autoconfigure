# 自动装配过程详解 by zcx

首先我们来回顾一下springboot 是怎么进行自动装配的

 ### 1. Spring boot 自动配置详解

 1. SpringBootApplication

    > ```java
    > @SpringBootApplication
    > ```

    我们就从万恶之源💀SpringBootApplication。我们都知道它是由

    ```java
    @Target(ElementType.TYPE) 
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Inherited
    @SpringBootConfiguration
    @EnableAutoConfiguration
    ```

    @Target(ElementType.TYPE) 
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Inherited

    这些注释就不解释了

    我们重点解释 **EnableAutoConfiguration**自动配置类，我们进去查看nableAutoConfiguration

    ```java
    @Target(ElementType.TYPE)  //不解释
    @Retention(RetentionPolicy.RUNTIME) //不解释
    @Documented  //不解释 
    @Inherited  //不解释
    @AutoConfigurationPackage
    @Import(AutoConfigurationImportSelector.class) // 引入了AutoConfigurationImportSelector class
    ```

    在这里主要解释一下

    [@import]: https://blog.csdn.net/pange1991/article/details/81356594

    导入的类AutoConfigurationImportSelector 首先我们可以看见 **selectImports**这个方法

    ```java
@Override
    	public String[] selectImports(AnnotationMetadata annotationMetadata) {
		if (!isEnabled(annotationMetadata)) {
    			return NO_IMPORTS;
		}
    		AutoConfigurationMetadata autoConfigurationMetadata = AutoConfigurationMetadataLoader
    				.loadMetadata(this.beanClassLoader);
    		AutoConfigurationEntry autoConfigurationEntry = getAutoConfigurationEntry(autoConfigurationMetadata,
    				annotationMetadata);
    		return StringUtils.toStringArray(autoConfigurationEntry.getConfigurations());
    	}
    ```
    
    我们看见它返回的是一串string  而这个string 是从ConfigurationEntry.getConfigurations中读取的。所以理所当然去看
    
    ```java
    AutoConfigurationMetadata autoConfigurationEntry =getAutoConfigurationEntry(autoConfigurationMetadata,
    				annotationMetadata);
    ```
    
    **getAutoConfigurationEntry**
    
    ```java
    List<String> configurations = SpringFactoriesLoader.loadFactoryNames(getSpringFactoriesLoaderFactoryClass(),
    				getBeanClassLoader());
    		Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring.factories. If you "
    				+ "are using a custom packaging, make sure that file is correct.");
    		return configurations;
    ```
    
    
    
    ​	这里的代码很清楚 就是从SpringFactoriesLoader从loadFactoryNames的方法获取configurations一串String 如果为空 就返回 No auto configuration classes found in META-INF/spring.factories. If you "are using a custom packaging, make sure that file is correct.
    
    没有自动配置文件 请确认 META-INF 文件夹下有没有spring.factories 
    
    打开spring.boot自动配置包下的
    
    ![spring.factories](https://i.imgur.com/wjnsyiH.png)
    
    可以看见 
    
    ![](https://i.imgur.com/CMs9wIQ.png)
    
    springboot 自动配置就是读取下面的内容进行装配
    

好了百度了半天，在反复看终于理解了什么是自动装配

接下来我们照网上的大佬抄一个自动装配

