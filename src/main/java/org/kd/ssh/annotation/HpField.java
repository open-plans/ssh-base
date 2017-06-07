/**
 * 代号:隐无为 2017：厚溥
 * 文件名：HpField.java
 * 日期：2017年6月8日
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
 * 用途 注解字段 排序
 */
@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target({ ElementType.FIELD, ElementType.METHOD }) // 定义注解的作用目标**作用范围字段、枚举的常量/方法
@Documented // 说明该注解将被包含在javadoc中
public @interface HpField {

	/**
	 * 排序字段
	 * @return
	 */
	String order() default "desc";

	/**
	 * 排序顺序
	 * @return
	 */
	int orderIndex()  default 0;
}
