package com.csc.azubiblog.model;

/**
 * @author NFriese, FKohlhaas
 *
 * Category enum to list every category
 */
public enum Category {

	/**
	 * enum elements
	 */
	HOT("Hot"), FLOP("Flop"), FOODPORN("Food Porn"), FITNESS("Fitness"), GAMES("Games"), HAXX0R("Haxx0r");
	private String m_name;
	
	/**
	 * Constructor using m_name for ToString methods
	 * @param name - Category name
	 */
	private Category(String name){
		m_name = name;
	}
	
	/**
	 * Getter 
	 * @return
	 */
	public String getName(){
		return m_name;
	}
	
}
