package com.eps.sportsim;

public class BruteForce {

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

	public int getStrength() {
		return strength;
	}

	public int getSpeed() {
		return speed;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public int getBallHandling() {
		return ballHandling;
	}

	public int getPassing() {
		return passing;
	}

	public int getShooting() {
		return shooting;
	}

	public int getBlockShot() {
		return blockShot;
	}

	public int getStealBall() {
		return stealBall;
	}

	public int getInterceptPass() {
		return interceptPass;
	}

	public int getInstincts() {
		return instincts;
	}
	//Player details
	private int age;
	private int height;
	private int weight;
	private int position;
	private int tier;
	
	//General stats
	private int strength;
	private int speed;
	private int intelligence;
	
	//Offensive stats
	private int ballHandling;
	private int passing;
	private int shooting;
	
	//Defensive stats
	private int blockShot;
	private int stealBall;
	private int interceptPass;
	
	//Wildcard
	private int instincts;
	
	//Formula variables
	private int minRange = 5;
	private int maxRange = 20;
	private int tierMultiplier = 4;
	
	public BruteForce(int tier, int position) throws Exception{
		if((1 <= tier && tier <= 5) && (1 <= position && position <= 5)){
			this.tier = tier;
			this.position = position;
			
			strength = randomInRange(minRange, maxRange);
			speed = randomInRange(minRange, maxRange);
			intelligence = randomInRange(minRange, maxRange);
			
			ballHandling = randomInRange(minRange, maxRange);
			passing = randomInRange(minRange, maxRange);
			shooting = randomInRange(minRange, maxRange);
			
			blockShot = randomInRange(minRange, maxRange);
			stealBall = randomInRange(minRange, maxRange);
			interceptPass = randomInRange(minRange, maxRange);
			
			instincts = randomInRange(minRange, maxRange);
		} else {
			throw new Exception("Player Tier or Position was out of bounds");
		}

		
		
	}
	
	private int randomInRange(int min, int max){
		int range = (max - min) + 1;
		return (int) ((Math.random() * range) + min) + tier * tierMultiplier;
		
	}
}


