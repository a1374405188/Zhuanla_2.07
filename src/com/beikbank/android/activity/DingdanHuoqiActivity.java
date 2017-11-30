package com.beikbank.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beikbank.android.utils2.StateBarColor;
import coma.beikbank.android.R;



/**活期订单详情
 * Created by Administrator on 2015/3/16.
 */
public class DingdanHuoqiActivity extends BaseActivity1 implements View.OnClickListener
{
    /**
     *
     */
    ImageView iv1;
    /**
     * 标题
     */
    TextView title;
    /**
     * 活期产品名
     */
    TextView tv1;
    /**
     * 购买成功
     */
    TextView tv2;
    /**
     * 购买金额
     */
    TextView tv3;
    /**
     * 银行卡
     */
    TextView tv4;
    /**
     * 订单号
     */
    TextView tv5;
    /**
     * 交易时间
     */
    TextView tv6;
    /**
     * return
     */
    LinearLayout ll1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdan_huoqi);
        StateBarColor.init(this,0xffffffff);
        init();
    }
    private void init()
    {
        ll1=(LinearLayout)findViewById(R.id.linear_left);
        title=(TextView)findViewById(R.id.title);
        title.setText(getString(R.string.dingdan_huoqi8));

        tv1=(TextView)findViewById(R.id.tv1);
        tv2=(TextView)findViewById(R.id.tv2);
        tv3=(TextView)findViewById(R.id.tv3);
        tv4=(TextView)findViewById(R.id.tv4);
        tv5=(TextView)findViewById(R.id.tv5);
        tv6=(TextView)findViewById(R.id.tv6);

        iv1=(ImageView)findViewById(R.id.iv1);
    }
    private void initData()
    {

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
