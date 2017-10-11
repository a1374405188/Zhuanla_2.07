package com.beikbank.android.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
 /**
 *copyright yu guo he
 *email: 1374405188@qq.com
 *2014-12-16
 **/
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamAnnotation {
  int index();
}
