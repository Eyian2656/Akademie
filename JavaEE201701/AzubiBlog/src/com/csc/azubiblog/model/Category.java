package com.csc.azubiblog.model;

public enum Category {

	HOT("Hot"), FLOP("Flop"), FOODPORN("Food Porn"), FITNESS("Fitness"), GAMES("Games"), HAXX0R("Haxx0r");
	
	private String m_name;
	
	private Category(String name){
		m_name = name;
	}
	
	public String getName(){
		return m_name;
	}
	
}
