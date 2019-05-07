package com.fwlbs.mydlinterTest;


import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.fwlbs.mydlinter.HttpClientKw;
import com.jayway.jsonpath.JsonPath;
public class MydlTest {

	public static void main(String[] args) {
		HttpClientKw http=new HttpClientKw();
		try {
			
			Map<String,String> map1 = new HashMap<String,String>();
			map1.put("Content-type", "multipart/form-data; boundary=----WebKitFormBoundaryBkHmKS8WQLjrArLa");
			map1.put("Authorization","Basic YXBwOmFwcA==");
	 		//传递map的token添加到请求头中
			http.addHeader(map1);
			String result=http.doPost("http://c.fwlbs.com/front-api/uaa/oauth/token","username=15882195751&password=12345678&grant_type=password&auth_type=1&scope=app");
			JSONObject json=new JSONObject(result);		
			String tokenValue="Bearer "+json.get("access_token").toString();
			System.out.println(json.get("access_token").toString());
			//将token的值，存到header键值对的map中
			
			
			Map<String,String> map = new HashMap<String,String>();
			map.put("Authorization", tokenValue);
//			map.put("Content-type", "mapplication/json;charset=UTF-8");
	 		//传递map的token添加到请求头中
			http.addHeader(map);
			System.out.println(map);
			String access_token=JsonPath.read(result, "$.access_token");
			http.doGet("http://c.fwlbs.com/front-api/upms/user/current", "access_token"+access_token);
			http.doPostJson("http://c.fwlbs.com/front-api/upms/user/password/modify","{\"userPhone\":\"15882195751\",\"password\":\"12345678\",\"newPassword\":\"12345678\",\"code\":\"123\"}");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 

	}

}
