package com.huang.Test;

import net.sf.json.JSONObject;

public class Test {

	public static void main(String[] args) {
		String requestStr = "{\"access_token\":\"1\"}";
		//		JSONObject jsonObj = new JSONObject();
		//		jsonObj.put("name0", "zhangsan");
		//		jsonObj.put("sex1", "female");
		//		System.out.println(jsonObj);
		JSONObject jsonObject = JSONObject.fromObject(requestStr);
		String string;
		try {

			string = jsonObject.getString("access");
		}
		catch (Exception e) {
			string = null;
		}
		System.out.println(jsonObject.getString("access_token"));
		System.out.println(string);

	}

}
