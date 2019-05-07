package com.fwlbs.mydlinterTest;


import java.util.HashMap;
import java.util.Map;

import com.fwlbs.mydlinter.KeywordOfInter;
import com.jayway.jsonpath.JsonPath;

public class MydlinterKw {

	public static void main(String[] args) {
		KeywordOfInter kw=new KeywordOfInter();
		Map<String,String> map = new HashMap<String,String>();
		map.put("Content-type","multipart/form-data; boundary=----WebKitFormBoundaryBkHmKS8WQLjrArLa");
		map.put("Authorization","Basic YXBwOmFwcA==");
		kw.addHeader1(map);
		String result=kw.testPost("http://c.fwlbs.com/front-api/uaa/oauth/token", "username=15882195751&password=12345678&grant_type=password&auth_type=1&scope=app");
		kw.assertSame("bearer", "$.token_type");
		
		String access_token=JsonPath.read(result,"$.access_token");
		String tokenValue="Bearer "+access_token;
		map.put("Authorization", tokenValue);
		map.put("Content-type", "application/json;charset=UTF-8");
		kw.addHeader1(map);
		
		kw.testGet("http://c.fwlbs.com/front-api/upms/user/current", "access_token"+access_token);
		kw.assertSame( "61", "$.data.id");
		kw.testPostJson("http://c.fwlbs.com/front-api/upms/user/password/modify","{\"userPhone\":\"15882195751\",\"password\":\"12345678\",\"newPassword\":\"12345678\",\"code\":\"123\"}");
		

	}

}
