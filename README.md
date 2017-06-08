# ssh-base
ssh-base init

look me ！
:smile:  plase  add  star
:star:  :star:  :star:  :star:  :star: 

# use maven 
```xml  
	<dependency>
	  <groupId>org.kd</groupId>
	  <artifactId>ssh-base</artifactId>
	  <version>1.0.0-M7</version>
	</dependency>
``` 

# how use
<<<<<<< HEAD
```xml 
 	<!-- base 系列 要注入的 -->
    <context:component-scan base-package="org.kd.ssh.base" />
 ``` 

```xml  
	<aop:config>
		<aop:pointcut id="transactionPointcut"
			expression="execution(* org.kd.service.impl.*.*(..))" />
		<aop:advisor pointcut-ref="transactionPointcut"
			advice-ref="transactionAdvice" />
	<!-- base aop -->
	<aop:advisor pointcut="execution(* org.kd.ssh.base.service..*Impl.*(..))" advice-ref="transactionAdvice" />
	</aop:config>
``` 

 action      exetend BaseAction or BaseActionPlus
 
 
 dao         exetend  BaseDao
 
 
 daoImpl     exetend  BaseDaoImpl
 
 
 service     exetend  BaseService
 
 
 serviceImpl exetend  BaseServiceImpl
 