package com.beikbank.android.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
 /**
 *copyright yu guo he
 *email: 1374405188@qq.com
 *2014-12-18
 *privaryKey=1表示该字段是主键
 **/
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DBPrimaryKey {
  int privaryKey();
}
