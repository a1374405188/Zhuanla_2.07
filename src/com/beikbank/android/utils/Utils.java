package com.beikbank.android.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.beikbank.android.adapter.QuickAdapter;
import com.beikbank.android.data.SupportBank;
import com.beikbank.android.utils.GifHelper.GifFrame;
import com.beikbank.android.widget.NumberProgressBar;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import coma.beikbank.android.R;



public class Utils {


	public static void log(String TAG, String Message) {
		if (Configuration.DEBUG) {
			Log.d(TAG, Message);
		}
	}


	public static String getJsonResult(String jsonstr,int type){
		String str=null;
		try {
			JSONObject jsonobj=new JSONObject(jsonstr);
			switch(type){
			case BeikBankConstant.TYPE_JSONDATA:
				str = jsonobj.getString("data");
				break;
			case BeikBankConstant.TYPE_JSONSTATUS:
				str = jsonobj.getString("result");
				break;
			case BeikBankConstant.TYPE_JSONMESSAGE:
				str = jsonobj.getString("message");
				break;
			}

		} catch (JSONException e) {
			str="error";
		}
		return str;
	}

	public static boolean isPhoneNumberValid(String phoneNumber){  
		boolean isValid = false;  

		String expression = "^1(3[0-9]|5[0-35-9]|8[025-9])\\d{8}$";  

		String expression2 = "^1(34[0-8]|(3[5-9]|5[017-9]|8[278])\\d)\\d{7}$";  

		String expression3 = "^1(3[0-2]|5[256]|8[56])\\d{8}$";  

		String expression4 = "^1((33|53|8[019])[0-9]|349|(70[059])|(77[0-9]))\\d{7}$";  

		CharSequence inputStr = phoneNumber;  
		Pattern pattern = Pattern.compile(expression);  
		Matcher matcher = pattern.matcher(inputStr);  
		Pattern pattern2 = Pattern.compile(expression2);  
		Matcher matcher2 = pattern2.matcher(inputStr);  
		Pattern pattern3 = Pattern.compile(expression3);  
		Matcher matcher3 = pattern3.matcher(inputStr);
		Pattern pattern4 = Pattern.compile(expression4);  
		Matcher matcher4 = pattern4.matcher(inputStr);
		if (matcher.matches()||matcher2.matches()||matcher3.matches()||matcher4.matches()){  
			isValid = true;  
		}  
		return isValid;  
	} 


	public static String getEncryptedPhonenumber(String phonenumber){		
		return phonenumber.substring(0, 3)+"****"+phonenumber.substring(7,phonenumber.length()); 
	}
	public static String getEncryptedPhonenumber2(String phonenumber){		
		return phonenumber.substring(0, 3)+"••••"+phonenumber.substring(7,phonenumber.length()); 
	}
	public static String getEncryptedRealname(String realname){
		return "*"+realname.substring(1, realname.length());
	}

	public static String getEncryptedIcNumber(String icnumber){		
		return icnumber.substring(0, 6)+"*********"+icnumber.substring(14,icnumber.length()); 
	}

	public static String getEncryptedCardNumber(String cardnumber){
		if(cardnumber.length()==16){
			return "**** **** **** "+cardnumber.substring(12,cardnumber.length()); 
		}else{
			return "**** **** **** **"+cardnumber.substring(14,cardnumber.length());
		}
	}

	public static String getFromAssets(Context context,String fileName){ 
		try { 
			InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName)); 
			BufferedReader bufReader = new BufferedReader(inputReader);
			String line="";
			String Result="";
			while((line = bufReader.readLine()) != null)
				Result += line;
			return Result;
		} catch (Exception e) { 
			e.printStackTrace(); 
			return "";
		}
	} 

	public static void loadHtml(WebView mWebView ,String assetPath ) { 
		try {
			mWebView.loadUrl(assetPath);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static boolean isNetworkConnected(Context context) {  
		if (context != null) {  
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context  
					.getSystemService(Context.CONNECTIVITY_SERVICE);  
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();  
			if (mNetworkInfo != null) {  
				return mNetworkInfo.isAvailable();  
			}  
		}  
		return false;  
	}

	public static void performAnimateForToast(View target,AnimatorSet toastAnimSet) {
		if(!toastAnimSet.isStarted()){			
			ValueAnimator transAnim1=ObjectAnimator.ofFloat(target, "translationY", target.getHeight());
			transAnim1.setDuration(300);
			transAnim1.setInterpolator(new AccelerateDecelerateInterpolator());
			ValueAnimator transAnim2=ObjectAnimator.ofFloat(target, "translationY", -target.getHeight());
			transAnim2.setStartDelay(1200);
			transAnim2.setDuration(300);
			transAnim2.setInterpolator(new AccelerateDecelerateInterpolator());
			toastAnimSet.play(transAnim1).before(transAnim2);
			toastAnimSet.play(transAnim2);
			toastAnimSet.start();
		}

	}

	public static void performAnimateForDown(View target,View target2) {  
		AnimatorSet animatorSet = new AnimatorSet();
		ValueAnimator transAnim1=ObjectAnimator.ofFloat(target, 
				"translationY", target2.getHeight());
		transAnim1.setDuration(300);
		transAnim1.setInterpolator(new AccelerateDecelerateInterpolator());
		animatorSet.play(transAnim1);
		animatorSet.start();
	}

	public static void performAnimateForUp(View target) {  
		AnimatorSet animatorSet = new AnimatorSet();
		ValueAnimator transAnim1=ObjectAnimator.ofFloat(target, 
				"translationY", 0);
		transAnim1.setDuration(300);
		transAnim1.setInterpolator(new AccelerateDecelerateInterpolator());
		animatorSet.play(transAnim1);
		animatorSet.start();
	}

	public static Dialog createPorgressDialog(Context context,OnCancelListener cancelListener) {
		Dialog dialog = null;
		dialog = new Dialog(context, R.style.mxx_theme_dialog);
		View view = LayoutInflater.from(context).inflate(
				R.layout.dialog_progress_layout, null);
		dialog.setContentView(view);

		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(true);
		dialog.setOnCancelListener(cancelListener);
		return dialog;
	}
	public static Dialog createTiShiDialog(Context context,OnCancelListener cancelListener) {
		Dialog dialog = null;
		dialog = new Dialog(context, R.style.mxx_theme_dialog);
		View view = LayoutInflater.from(context).inflate(
				R.layout.dialog_tishi_layout, null);
		dialog.setContentView(view);

		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(true);
		dialog.setOnCancelListener(cancelListener);
		return dialog;
	}
	public static Dialog createPorgressDialogNoCancel(Context context,OnCancelListener cancelListener) {
		Dialog dialog = null;
		dialog = new Dialog(context, R.style.mxx_theme_dialog);
		View view = LayoutInflater.from(context).inflate(
				R.layout.dialog_progress_layout, null);
		dialog.setContentView(view);

		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(false);
		dialog.setOnCancelListener(cancelListener);
		return dialog;
	}

	public static boolean isRealname(String strRealname) {
		String strPattern = "^[\u4E00-\u9FA5]{2,5}(?:·[\u4E00-\u9FA5]{2,10})*$";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(strRealname);
		return m.matches();
	}
	/**
	 * 创建提示对话框
	 * @param context
	 * @param title
	 * @param text
	 * @return
	 */
    public static Dialog creadialog1(Context context,String title,String text)
    {
    	final Dialog dialog = new Dialog(context, R.style.mxx_theme_dialog);
    	View view = LayoutInflater.from(context).inflate(
				R.layout.redeem_popupwindow, null);
		//TextView tvt = (TextView) view
		//		.findViewById(R.id.dialog_confirm_title);
		//tvt.setText(title);
		
		TextView cancle=(TextView) view
				.findViewById(R.id.tv_tv4);
		cancle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			
				
			}
		});
    	dialog.setCanceledOnTouchOutside(false);
		dialog.setContentView(view);

    	
    	
    	return  dialog;
    }
	public static Dialog createConfirmDialog(Context context,final BeikBankDialogListener beikBankDialogListener) {
		final Dialog dialog = new Dialog(context, R.style.mxx_theme_dialog);
		View view = LayoutInflater.from(context).inflate(
				R.layout.dialog_confirm_layout, null);
		TextView btn1 = (TextView) view
				.findViewById(R.id.dialog_confirm_btn1);
		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				if (beikBankDialogListener != null) {
					beikBankDialogListener.onRightBtnClick();
				}
			}
		});
		dialog.setCanceledOnTouchOutside(false);
		dialog.setContentView(view);
		dialog.setCancelable(false);
		dialog.setOnCancelListener(new OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				// TODO Auto-generated method stub
				if (beikBankDialogListener != null) {
					beikBankDialogListener.onCancel();
				}
			}
		});
		return dialog;
	}

	public static Dialog createRemindDialog(Context context,String content,final BeikBankDialogListener beikBankDialogListener) {
		final Dialog dialog = new Dialog(context, R.style.mxx_theme_dialog);
		View view = LayoutInflater.from(context).inflate(
				R.layout.dialog_remind_layout, null);
		TextView textview_content = (TextView) view
				.findViewById(R.id.dialog_confirm_title);
		textview_content.setText(content);
		TextView btn1 = (TextView) view
				.findViewById(R.id.dialog_confirm_btn1);
		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				if (beikBankDialogListener != null) {
					beikBankDialogListener.onRightBtnClick();
				}
			}
		});
		dialog.setCanceledOnTouchOutside(true);
		dialog.setContentView(view);
		dialog.setCancelable(true);
		dialog.setOnCancelListener(new OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				// TODO Auto-generated method stub
				if (beikBankDialogListener != null) {
					beikBankDialogListener.onCancel();
				}
			}
		});
		return dialog;
	}

	public static Dialog createUpdateDialog(Context context,final BeikBankDialogListener beikBankDialogListener) {
		final Dialog dialog = new Dialog(context, R.style.mxx_theme_dialog);
		View view = LayoutInflater.from(context).inflate(
				R.layout.dialog_update_layout, null);

		NumberProgressBar numberprogress_rate=(NumberProgressBar)view.findViewById(R.id.numberprogress_rate);
		TextView textview_percent=(TextView)view.findViewById(R.id.textview_percent);
		TextView textview_total=(TextView)view.findViewById(R.id.textview_total);
		dialog.setCanceledOnTouchOutside(false);
		dialog.setContentView(view);
		dialog.setCancelable(false);

		return dialog;
	}

	public static Dialog createSimpleDialog2(Context context,String content,String leftButton,String buttontext,final BeikBankDialogListener beikBankDialogListener) {
		final Dialog dialog = new Dialog(context, R.style.mxx_theme_dialog);
		View view = LayoutInflater.from(context).inflate(
				R.layout.dialog_simple_layout, null);
		TextView dialog_confirm_title = (TextView) view.findViewById(R.id.dialog_confirm_title);
		dialog_confirm_title.setText(content);

		TextView btn1 = (TextView) view
				.findViewById(R.id.dialog_confirm_btn1);
		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				if (beikBankDialogListener != null) {
					beikBankDialogListener.onRightBtnClick();
				}
			}
		});
		btn1.setText(buttontext);
		TextView btn2 = (TextView) view
				.findViewById(R.id.dialog_confirm_btn2);
		btn2.setText(leftButton);
		btn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				if (beikBankDialogListener != null) {
					beikBankDialogListener.onLeftBtnClick();
				}
			}
		});
		dialog.setCanceledOnTouchOutside(true);
		dialog.setContentView(view);
		dialog.setCancelable(false);
		dialog.setOnCancelListener(new OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				// TODO Auto-generated method stub
				if (beikBankDialogListener != null) {
					beikBankDialogListener.onCancel();
				}
			}
		});
		return dialog;
	}
	
	
	public static Dialog createSimpleDialog(Context context,String content,String buttontext,final BeikBankDialogListener beikBankDialogListener) {
		final Dialog dialog = new Dialog(context, R.style.mxx_theme_dialog);
		View view = LayoutInflater.from(context).inflate(
				R.layout.dialog_simple_layout, null);
		TextView dialog_confirm_title = (TextView) view.findViewById(R.id.dialog_confirm_title);
		dialog_confirm_title.setText(content);

		TextView btn1 = (TextView) view
				.findViewById(R.id.dialog_confirm_btn1);
		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				if (beikBankDialogListener != null) {
					beikBankDialogListener.onRightBtnClick();
				}
			}
		});
		btn1.setText(buttontext);
		TextView btn2 = (TextView) view
				.findViewById(R.id.dialog_confirm_btn2);
		btn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				if (beikBankDialogListener != null) {
					beikBankDialogListener.onLeftBtnClick();
				}
			}
		});
		dialog.setCanceledOnTouchOutside(true);
		dialog.setContentView(view);
		dialog.setCancelable(false);
		dialog.setOnCancelListener(new OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				// TODO Auto-generated method stub
				if (beikBankDialogListener != null) {
					beikBankDialogListener.onCancel();
				}
			}
		});
		return dialog;
	}

	public static boolean isNumeric(String str){ 
		Pattern pattern = Pattern.compile("[0-9]*"); 
		return pattern.matcher(str).matches();    
	} 

	public static int isPositiveInteger(String str,int partInvestAmount){

		if(isNumeric(str)){
			long i=Long.parseLong(str);
			if(i<partInvestAmount){
				return 1;
			}else if(i%partInvestAmount!=0){
				return 2;
			}else{
				return 0;
			}
		}
		return 1;
	}


	public static ArrayList<SupportBank> getSupportBankList(Context context){
		ArrayList<SupportBank> list=new ArrayList<SupportBank>();
		InputStream is=null;
		is=context.getResources().openRawResource(R.raw.bankcard);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
		}
		String tmp;
		try {
			while((tmp=br.readLine())!=null){
				String[] array=tmp.split(",");
				SupportBank sb=new SupportBank();
				sb.setName(array[0]);
				sb.setNumber(array[1]);
				list.add(sb);
			}
			br.close();
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		return list;
	}


	public static Dialog createListViewDialog(final Context context,
			final CharSequence title, final ArrayList<String> items ,
			final BeikBankDialogListener mxxDialogListener) {
		final Dialog dlg = new Dialog(context, R.style.mxx_theme_dialog);
		LayoutInflater inflater =LayoutInflater.from(context);
		LinearLayout layout = (LinearLayout) inflater.inflate(
				R.layout.dialog_listview, null);
		final ListView list = (ListView) layout
				.findViewById(R.id.dialog_listview);
		QuickAdapter qa=new QuickAdapter(context, items);
		list.setAdapter(qa);
		TextView titlte = (TextView) layout.findViewById(R.id.dialog_title);
		titlte.setText(title);
		list.setOnItemClickListener(new ListView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				dlg.dismiss();
				if(mxxDialogListener!=null){
					mxxDialogListener.onListItemClick(position, items.get(position));
				}
			}
		});
		dlg.setCanceledOnTouchOutside(true);
		if (mxxDialogListener != null) {
			dlg.setOnCancelListener(new DialogInterface.OnCancelListener() {

				@Override
				public void onCancel(DialogInterface dialog) {
					// TODO Auto-generated method stub
					mxxDialogListener.onCancel();
				}
			});
		}
		((TextView)layout.findViewById(R.id.dialog_cancle_btn)).setText(R.string.cancel);
		layout.findViewById(R.id.dialog_cancle_btn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dlg.dismiss();
			}
		});

		dlg.setContentView(layout);
		return dlg;
	}

	public static synchronized String getVersion(Context context) {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			String version = info.versionName;
			return version;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static GifFrame[] getGif(InputStream is) {
		GifHelper gifHelper = new GifHelper();
		if (GifHelper.STATUS_OK == gifHelper.read(is)) {
			return gifHelper.getFrames();
		}
		return null;
	}

	public static String getTime(String user_time) { 
		String re_time = null; 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒"); 
		Date d; 
		try { 
			d = sdf.parse(user_time); 
			long l = d.getTime(); 
			String str = String.valueOf(l); 
			re_time = str.substring(0, 10); 
		} catch (ParseException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		} 
		return re_time; 
	} 

	// 将时间戳转为字符串 
	public static String getStrTime(String cc_time) { 
		String re_StrTime = null; 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒"); 
		// 例如：cc_time=1291778220 
		long lcc_time = Long.valueOf(cc_time); 
		re_StrTime = sdf.format(new Date(lcc_time * 1000L)); 
		return re_StrTime; 
	} 

	public static String getSign(TreeMap<String,String>map){
		Set<String> keySet = map.keySet();
		Iterator<String> iter = keySet.iterator();
		String str = "";
		while (iter.hasNext()) {
			String key = iter.next();
			if(!TextUtils.isEmpty(map.get(key))){
				try {
					str = str + key +"="+URLEncoder.encode(map.get(key), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(iter.hasNext()){
					str = str + "&";
				}
			}
		}
		return MD5.md5s32(str + MD5.md5s32("beikbank"));
	}
	
	public static boolean isPointLast(String str){
		if(str.lastIndexOf(".")==str.length()-1){
			return false;
		}else{
			return true;
		}
	}

}



