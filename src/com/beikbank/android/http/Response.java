package com.beikbank.android.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.util.EntityUtils;

public class Response{


	private static final String TAG = "Response";
	private StatusLine mStatusLine = null;
	private String responseBody = null;
	private Map<String, String> headerMap = new HashMap<String, String>();

	public Response( HttpResponse response) throws IOException{
		
		mStatusLine = response.getStatusLine();
		setResponseString(response);
		setHeaders(response.getAllHeaders());

	}

	public StatusLine getStatusLine(){
		return mStatusLine;
	}


	public String getResponseString(){
		return responseBody;
	}

	public Map<String, String> getHeaderMap(){
		return headerMap;
	}


	private void setResponseString(HttpResponse response) throws IOException{
		//---------------------add end
		HttpEntity entity = null;
		HttpEntity temp = response.getEntity();
		if(temp != null) {
			entity = new BufferedHttpEntity(temp);
			responseBody = EntityUtils.toString(entity, "UTF-8");
		}
//		Utils.log(TAG, "--------------解密�? + responseBody);
		//得到字符串先用BASE64 DECODE
//		byte[] src = Base64.decode(responseBody);
//		try {
//			responseBody = Aes.Decrypt(src , Aes.getRealKey());
//			if(responseBody == null){
//				responseBody = "";
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}


	private void setHeaders( Header[] headers) {
		for( int i = 0 ; i < headers.length ; i++ ){
			headerMap.put(headers [i].getName() , headers[i].getValue() );
		}

	}



}
