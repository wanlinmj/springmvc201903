spring web 搭建记录

20190424 
	1、基础模块搭建完毕 ，响应json数据转换完成

20190425	
整合 mybaties

	1、添加jar包  mybatis-3.4.5.jar		mybatis-spring-1.3.1.jar
	2、添加配置文件
	<!-- spring已配置数据源  dataSource  -->
	<!-- spring和MyBatis完美整合，不需要mybatis的配置映射文件 -->
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
	    <!-- 数据库连接池 -->
	    <property name="dataSource" ref="dataSource"/>
	    <property name="configLocation" value="classpath:config/config-mybatis.xml"/>
	     <!-- 自动扫描mapping.xml文件 -->  
        <property name="mapperLocations" value="classpath:mapper/*.xml"></property>
	</bean>
  	
  	<!-- DAO接口所在包名，Spring会自动查找其下的类 --> 
	<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
	    <!-- 扫描包路径，如果需要扫描多个包中间用半角逗号隔开 -->
	    <property name="basePackage" value="com.wanlin.dao"></property>
	    <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
	</bean>
	
	3、配置 config-mybatis.xml
	<!-- 配置别名 -->
	 <typeAliases>
   		<typeAlias alias="HashMap" 				type="java.util.HashMap" />
   		<typeAlias alias="Map" 					type="java.util.Map" />
		<typeAlias alias="String" 				type="java.lang.String" />
		<typeAlias alias="Integer" 				type="java.lang.Integer" />
     </typeAliases>
   	 <!-- 配置映射文件  已启用自动扫描，此处不需要配置映射文件路径-->
   	 <mappers>
   		
     </mappers>
==============================================================================================================================     
整合log4j
	1、 web.xml 配置日志监听   配置文件放在spring监听器之前，保证项目启动不会出现配置加载丢失的提示信息   log4j:WARN No appenders could be found for logger
	<!-- log4j context-param -->
	<context-param>
		<param-name>log4jConfigLocation</param-name>
		<param-value>classpath:config/log4j.properties</param-value>
	</context-param>
    <listener>
    	<listener-class>org.springframework.web.util.Log4jConfigListener</listener-class>
    </listener>
	2、配置log4j jar包     log4j-1.2.17.jar
	3、配置log4j.properties文件
	#定义LOG输出级别  
	log4j.rootLogger=ERROR,Console,File,ErrFile
	# 设置mybaties 打印sql   具体目录设置日志级别高于全局配置
	# com.wanlin.dao 代表的是 dao 层接口路径  控制打印 sql语句 参数 返回结果条数  # 打印sql语句:debug; 执行结果:trace
	log4j.logger.com.wanlin.dao=DEBUG
	# 打印  org.springframework.web 日志
	log4j.logger.org.springframework.web=DEBUG	...

===============================================================================================================================

整合切面编程：aspectj
	1、添加jar包	aspectjrt-1.9.1.jar	aspectjweaver-1.9.1.jar
	
	2、applicationContext-spring.xml 中配置 <!-- 启动AspectJ支持   只对扫描过的bean有效--> <aop:aspectj-autoproxy proxy-target-class="true" />

	3、 编写实现类 LogInterceptor （编写日志拦截，打印 请求url 方法   参数   IP  返回值 ）


    
     
	