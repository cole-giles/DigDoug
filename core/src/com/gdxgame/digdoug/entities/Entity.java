package com.gdxgame.digdoug.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.gdxgame.digdoug.world.GameMap;


public abstract class Entity {
	
	protected Vector2 pos;
	protected com.gdxgame.digdoug.entities.EntityType type;
	protected float velocityY = 0;
	protected GameMap map;
	protected boolean grounded = false;


	public Entity   (float x, float y, EntityType type, GameMap map) {
		this.pos = new Vector2(x, y);
		this.type = type;
		this.map = map;
	}
	
	public void update (float deltaTime, float gravity) {
		float newY = pos.y;
		
		this.velocityY += gravity * deltaTime * getWeight();
		newY += this.velocityY * deltaTime;
		
		if (map.doesRectCollideWithMap(pos.x, newY, getWidth(), getHeight())) {
			if (velocityY < 0) {
				this.pos.y = (float) Math.floor(pos.y);
				grounded = true;
			}
			this.velocityY = 0;
		} else {
			this.pos.y = newY;
			grounded = false;
		}
	}
	
	public abstract void render (SpriteBatch batch);
	
	public Vector2 getPos() {
		return pos;
	}
	
	public float getX () {
		return pos.x;
	}
	
	public float getY () {
		return pos.y;
	}

	public EntityType getType() {
		return type;
	}

	public boolean isGrounded() {
		return grounded;
	}
	
	public int getWidth() {
		return type.getWidth();
	}
	
	public int getHeight() {
		return type.getHeight();
	}

	public float getWeight() {
		return type.getWeight();
	}
}
