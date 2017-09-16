package com.eps.sportsim;

import java.util.Hashtable;

public class Athlete {

	public int getAge() {
		return age;
	}

	public int getHeight() {
		return height;
	}

	public int getWeight() {
		return weight;
	}

	public int getPosition() {
		return position;
	}

	public int getTier() {
		return tier;
	}

	public Stat getStat(String s) {
		return h.get(s);
	}
	
	public Stat getStat(int s) {
		return h.get(statNames[s]);
	}
	
	public boolean hiddenStatFound(){
		for(int i = 0; i < h.size(); i++){
			if (this.getStat(i).isHidden()){
				return true;
			}
		}
		return false;
	}
	
	Hashtable<String, Stat> h = new Hashtable<String, Stat>();
	
	//Player details
	private int age;
	private int height;
	private int weight;
	private int position;
	private int tier;
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//Formula variables
	private int minRange = 5;
	private int maxRange = 30;
	private int tierMultiplier = 4;
	
	String[] statNames = {  "strength",
							"speed",
							"intelligence",
							"ballHandling",
							"passing",
							"shooting",
							"blockShot",
							"stealBall",
							"interceptPass",
							"instincts",
	};
	
	public Athlete(int tier, int position) throws Exception{
		if((1 <= tier && tier <= 5) && (1 <= position && position <= 5)){
			this.tier = tier;
			this.position = position;
			
			
			for(int i = 0; i < statNames.length; i++){
				h.put(statNames[i], new Stat(randomInRange(minRange, maxRange), true));
			}
			
			
		} else {
			throw new Exception("Player Tier or Position was out of bounds");
		}

		
		
	}
	
	private int randomInRange(int min, int max){
		int range = (max - min) + 1;
		return (int) ((Math.random() * range) + min) + tier * tierMultiplier;
		
	}
}
