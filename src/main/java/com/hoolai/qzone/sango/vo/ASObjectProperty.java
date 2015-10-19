package com.hoolai.qzone.sango.vo;

import java.math.BigDecimal;

import flex.messaging.io.amf.ASObject;

public class ASObjectProperty {

	private final ASObject aso;
	
	public ASObjectProperty(ASObject aso) {
		this.aso = aso;
	}
	
	public int getInt(String key){
		return new BigDecimal(getStr(key)).intValue();
	}
	
	public long getLong(String key){
		return new BigDecimal(getStr(key)).longValue();
	}

	public String getStr(String key){
		return get(key).toString();
	}
	
	public ASObjectProperty getAsop(String key){
		return new ASObjectProperty(getAso(key));
	}
	
	public ASObject getAso(String key){
		return (ASObject)get(key);
	}
	
	public Object get(String key){
		return aso.get(key);
	}
	
	public boolean contains(String key){
		return aso.containsKey(key);
	}
	
}
