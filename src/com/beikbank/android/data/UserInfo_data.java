package com.beikbank.android.data;

import java.util.ArrayList;

import android.database.Cursor;

import com.beikbank.android.annotation.BaseParamAnnotation;
import com.beikbank.android.db.UserTableMetaData;

public class UserInfo_data {
	/**
	 * 是否成功
	 */
  @BaseParamAnnotation(base=1)
  public String result;
  /**
   * 返回的信息
   */
  @BaseParamAnnotation(base=1)
  public String message;
  /**
   * 数据主体
   */
 @BaseParamAnnotation(base=2)
  public UserInfo data;
}
