/**
 * 代号:隐无为 2017：厚溥
 * 文件名：HpIsDelete.java
 * 日期：2017年6月10日
 * 修改人：
 * 描述：
 */
package org.kd.ssh.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用途：注解isdelete字段
 */
@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target({ ElementType.FIELD }) // 定义注解的作用目标**作用范围字段、枚举的常量/方法
@Documented // 说明该注解将被包含在javadoc中
public @interface HpIsDeleteField {
	String isDeleteField() default "";

	int isDeleteValue() default 1; // 未删除

	int alreadyDeleteValue() default 0; // 删除
}
