/**
 * 代号:隐无为 2017：厚溥
 * 文件名：SortList.java
 * 日期：2017年6月8日
 * 修改人：
 * 描述：
 */
package org.kd.ssh.util;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 用途：List<E>list 泛型集合排序，根据实体类对象的某个属性排序
 */
public class SortList<T> {
	
	   /**
     * @param targetList
     *            要排序的实体类List集合
     * @param sortField
     *            排序字段(实体类属性名)
     * @param sortMode
     *            true正序，false逆序
     */
    @SuppressWarnings("all")
    public static <T> void sort(List<T> targetList, final String sortField,
            final boolean sortMode) {
        if(targetList==null||targetList.size()<2||sortField==null||sortField.length()==0){
            return;
        }
        Collections.sort(targetList, new Comparator() {
            @Override
            public int compare(Object obj1, Object obj2) {
                int retVal = 0;
                try {
                    // 获取getXxx()方法名称
                    String methodStr = "get" + sortField.substring(0, 1).toUpperCase() + sortField.substring(1);
                    Method method1 = ((T) obj1).getClass().getMethod(methodStr,null);
                    Method method2 = ((T) obj2).getClass().getMethod(methodStr,null);
                    if (sortMode) {
                        retVal = method1.invoke(((T) obj1), null).toString().compareTo(method2.invoke(((T) obj2), null).toString());
                    } else {
                        retVal = method2.invoke(((T) obj2), null).toString().compareTo(method1.invoke(((T) obj1), null).toString());
                    }
                } catch (Exception e) {
                    System.out.println("List<"+ ((T) obj1).getClass().getName() + ">排序异常！");
                    e.printStackTrace();
                }
                return retVal;
            }
        });
    }
	
	
	
	
	
}