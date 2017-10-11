package com.beikbank.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beikbank.android.utils2.StateBarColor;

import comc.beikbank.android.R;

/**活期订单详情
 * Created by Administrator on 2015/3/16.
 */
public class DingdanDingqiActivity extends BaseActivity1 implements View.OnClickListener
{
    TextView title;
    /**
     * return
     */
    LinearLayout ll1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdan_dingqi);
        StateBarColor.init(this,0xffffffff);
        init();
    }
    private void init()
    {
        ll1=(LinearLayout)findViewById(R.id.linear_left);
        title=(TextView)findViewById(R.id.titleTv);
        title.setText(getString(R.string.dingdan_huoqi8));
    }
    @Override
      public void onClick(View view)
      {
          switch (view.getId())
          {
              case R.id.linear_left:
                  finish();
                  break;
          }
      }
}
