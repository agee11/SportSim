package com.eps.sportsim;

public class Stat {

	private int value;
	private boolean isHidden;
	
	public Stat(int v, boolean h){
		value = v;
		isHidden = h;
	}
	
	public String getValue() {
		if(isHidden){
			return "??";
		}else{
			return Integer.toString(value);
		}
	}
	public void setValue(int value) {
		this.value = value;
	}
	public boolean isHidden() {
		return isHidden;
	}
	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}
	
}
