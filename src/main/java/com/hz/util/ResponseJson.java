package com.hz.util;

import lombok.Data;

import java.util.List;

@Data
public class ResponseJson {

	public String msg;
	public String url;
	public String returnStr;
	public boolean success=true;

	public Object object;
	public List objects;
	private String json;
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}

	public List getObjects() {
		return objects;
	}
	public void setObjects(List objects) {
		this.objects = objects;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getReturnStr() {
		return returnStr;
	}

	public void setReturnStr(String returnStr) {
		this.returnStr = returnStr;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
}
