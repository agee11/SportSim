package com.eps.sportsim;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SportSim extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	ArrayList<Athlete> Agents = new ArrayList<Athlete>();;
	Table nameTable, statTable, rootTable;
	ScrollPane scrollPane;
	Stage stage;
	Skin skin;
	TextButton[] buttonArray;
	FileHandle handle;
	Label[] statText;
	Label[] statValue;
	int resource = 10000;
	TextButton scoutButton;
	Label scoutResource, nameLabel;
	
	static int currentAgent;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		handle = Gdx.files.internal("names.txt");
		
		String text = handle.readString();
		String nameArray[] = text.split("\\r?\\n");
		
		
		buttonArray = new TextButton[100];
		scrollPane = new ScrollPane(null);
		stage = new Stage(new ScreenViewport());
		
		nameTable = new Table();
		nameTable.setSize(stage.getWidth(), stage.getHeight());
		nameTable.setPosition(0, stage.getHeight());
		nameTable.align(Align.center|Align.top);
		//nameTable.setDebug(true);
		
		scrollPane.setSize(stage.getWidth()/4, stage.getHeight());
		scrollPane.setScrollingDisabled(true, false);
		scrollPane.setPosition(0, 0);
		scrollPane.setWidget(nameTable);
		
		statTable = new Table();
		statTable.setSize((float) (stage.getWidth()*0.75), stage.getHeight());
		statTable.setPosition((float) (stage.getWidth()* 0.25), 0);
		statTable.align(Align.center|Align.top);
		//statTable.setDebug(true);
		
		scoutResource = new Label("Points: " + resource, skin);
		statTable.add(scoutResource).height(50).expandX().align(Align.topRight).colspan(2);
		statTable.row();
		
		nameLabel = new Label("", skin);
		statTable.add(nameLabel).align(Align.center);
		statTable.row();
		statTable.setDebug(true);
		
		statText = new Label[10];
		statValue = new Label[10];
		for(int i = 0; i < statText.length; i++){
			statText[i] = new Label("", skin);
			statTable.add(statText[i]).align(Align.left);
			
			statValue[i] = new Label("", skin);
			statTable.add(statValue[i]).align(Align.left);
			
			statTable.row();
		}
		
		scoutButton = new TextButton("Scout Cost: 500", skin);
		scoutButton.addListener(new ScoutClickListener(statText));
		statTable.add(scoutButton).align(Align.center);
		
		try {
			for(int i = 0; i < 100; i++){
				Agents.add(new Athlete(i%5 + 1, 4));
				Agents.get(i).setName(nameArray[i]);
				buttonArray[i] = new TextButton(Agents.get(i).getName(), skin);
				buttonArray[i].setSize(200, 50);
				buttonArray[i].addListener(new onAgentClicked(i));
				nameTable.add(buttonArray[i]);
				nameTable.row();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		stage.addActor(statTable);
		stage.addActor(scrollPane);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
	
	private class onAgentClicked extends ChangeListener{

		private int iterator;
		
		public onAgentClicked(int i){
			iterator = i;
		}
		
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			// TODO Auto-generated method stub

				currentAgent = iterator;
				nameLabel.setText(Agents.get(iterator).getName());
				statText[0].setText("Strength: ");
				statText[1].setText("Speed: ");
				statText[2].setText("Intelligence: ");
				
				statText[3].setText("Ball Handling: " );
				statText[4].setText("Passing: ");
				statText[5].setText("Shooting: ");
				
				statText[6].setText("Block Shot: ");
				statText[7].setText("Steal Ball: ");
				statText[8].setText("Intercept Pass: ");
				
				statText[9].setText("Instincts: ");
				
				statValue[0].setText(Agents.get(iterator).getStat("strength").getValue());
				statValue[1].setText(Agents.get(iterator).getStat("speed").getValue());
				statValue[2].setText(Agents.get(iterator).getStat("intelligence").getValue());
				
				statValue[3].setText(Agents.get(iterator).getStat("ballHandling").getValue());
				statValue[4].setText(Agents.get(iterator).getStat("passing").getValue());
				statValue[5].setText(Agents.get(iterator).getStat("shooting").getValue());
				
				statValue[6].setText(Agents.get(iterator).getStat("blockShot").getValue());
				statValue[7].setText(Agents.get(iterator).getStat("stealBall").getValue());
				statValue[8].setText(Agents.get(iterator).getStat("interceptPass").getValue());
				
				statValue[9].setText(Agents.get(iterator).getStat("instincts").getValue());
		}
		
	}
	
	private class ScoutClickListener extends ClickListener{
		Label[] stats;

		public ScoutClickListener(Label[] statText){
			stats = statText;
		}
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			super.clicked(event, x, y);
			if(Agents.get(currentAgent).hiddenStatFound()){
				resource -= 500;
				scoutResource.setText("Points: " + resource);
				int statSelector = (int) (Math.random() * 9);
				while(!Agents.get(currentAgent).getStat(statSelector).isHidden()){
					statSelector = (int) (Math.random() * 10);
				}
				Agents.get(currentAgent).getStat(statSelector).setHidden(false);
				statValue[statSelector].setText(Agents.get(currentAgent).getStat(statSelector).getValue());
			}
		}
		
	}
}
