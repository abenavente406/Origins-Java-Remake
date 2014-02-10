/*
 * Copyright (c) 2014 Anthony Benavente
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ambenavente.origins.gameplay.entities;

import com.ambenavente.origins.gameplay.entities.interfaces.Collidable;
import com.ambenavente.origins.gameplay.entities.interfaces.Renderable;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/9/14
 */
public abstract class Entity implements Renderable {

    protected static Image NO_TEX;
    static {
        try {
            NO_TEX = new Image("no_tex.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private Vector2f pos;
    private int width;
    private int height;
    private Direction direction;
    private float walkingSpeed;
    private boolean isMoving;

    public Entity(float x, float y) {
        this(new Vector2f(x, y));
    }

    public Entity(Vector2f pos) {
        this.pos            = pos;
        this.width          = 0;
        this.height         = 0;
        this.direction      = Direction.SOUTH;
        this.walkingSpeed   = 0.0f;
        this.isMoving       = false;

        init();
    }

    /**
     * Called when an entity is created
     */
    public abstract void init();

    /**
     * Called on each update in the game loop
     *
     * @param container The container that is holding the game
     * @param delta     Delta time
     */
    public abstract void update(GameContainer container, int delta);

    @Override
    public abstract void render(Graphics g);

    public Vector2f getPos() {
        return pos;
    }

    protected void setPos(Vector2f pos) {
        this.pos = pos;
    }

    public float getX() {
        return pos.x;
    }

    public float getY() {
        return pos.y;
    }

    public int getWidth() {
        return width;
    }

    protected void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    protected void setHeight(int height) {
        this.height = height;
    }

    public Direction getDirection() {
        return direction;
    }

    protected void setDirection(Direction direction) {
        this.direction = direction;
    }

    public float getWalkingSpeed() {
        return walkingSpeed;
    }

    protected void setWalkingSpeed(float walkingSpeed) {
        this.walkingSpeed = walkingSpeed;
    }

    public boolean isMoving() {
        return isMoving;
    }

    protected void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    /**
     * Moves the entity
     *
     * @param amount A vector representing how much to add onto the position
     *               vector
     */
    protected void move(Vector2f amount) {
        pos.x += amount.x;
        pos.y += amount.y;
    }
}
