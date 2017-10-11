package com.beikbank.android.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
 /**
 *copyright yu guo he
 *email: 1374405188@qq.com
 *2014-12-17
 *该注解用于解析json标识一级数据
 *<p>
 *  base =1 简单string 
 *</p>
 *<p>
 *  base =2 数据主体 
 *</p>
 **/
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseParamAnnotation {
  int base();
}
