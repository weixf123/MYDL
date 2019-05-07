package com.fwlbs.mydlinterTest;

import com.fwlbs.mydlinter.KeywordOfInter;

public class MydlParamTest {

	public static void main(String[] args) {
		//登录接口
		KeywordOfInter kw=new KeywordOfInter();
//		String headlist= kw.toParam("{\"Content-type\":\"multipart/form-data; boundary=----WebKitFormBoundaryBkHmKS8WQLjrArLa\",\"Authorization\":\"Basic YXBwOmFwcA==\"}");
		kw.addHeader("{\"Content-type\":\"multipart/form-data; boundary=----WebKitFormBoundaryBkHmKS8WQLjrArLa\",\"Authorization\":\"Basic YXBwOmFwcA==\"}");
		kw.testPost("http://c.fwlbs.com/front-api/uaa/oauth/token", "username=15882195751&password=12345678&grant_type=password&auth_type=1&scope=app");
		
		//获取当前用户信息
		kw.saveParam("access_token", "$.access_token");
		kw.testGet("http://c.fwlbs.com/front-api/upms/user/current", "access_token={access_token}");
		
		//修改密码
		kw.addHeader("{\"Authorization\": \"Bearer {access_token}\"}");
		kw.testPostJson("http://c.fwlbs.com/front-api/upms/user/password/modify","{\"userPhone\":\"15882195751\",\"password\":\"12345678\",\"newPassword\":\"12345678\",\"code\":\"123\"}");

	}

}
