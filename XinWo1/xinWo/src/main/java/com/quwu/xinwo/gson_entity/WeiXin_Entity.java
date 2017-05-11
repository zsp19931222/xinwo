package com.quwu.xinwo.gson_entity;

public class WeiXin_Entity {
private String access_token,expires_in,refresh_token,openid,scope,unionid;
private String id,user_name;

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getUser_name() {
	return user_name;
}

public void setUser_name(String user_name) {
	this.user_name = user_name;
}

public String getAccess_token() {
	return access_token;
}

public void setAccess_token(String access_token) {
	this.access_token = access_token;
}

public String getExpires_in() {
	return expires_in;
}

public void setExpires_in(String expires_in) {
	this.expires_in = expires_in;
}

public String getRefresh_token() {
	return refresh_token;
}

public void setRefresh_token(String refresh_token) {
	this.refresh_token = refresh_token;
}

public String getOpenid() {
	return openid;
}

public void setOpenid(String openid) {
	this.openid = openid;
}

public String getScope() {
	return scope;
}

public void setScope(String scope) {
	this.scope = scope;
}

public String getUnionid() {
	return unionid;
}

public void setUnionid(String unionid) {
	this.unionid = unionid;
}
}
