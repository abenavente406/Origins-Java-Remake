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

import com.ambenavente.origins.gameplay.entities.interfaces.Renderable;
import com.ambenavente.origins.gameplay.world.World;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

/**
 * Represents an Entity.  An entity is an object in the game that is rendered,
 * has updatable logic, has a position, and has dimensions.  These are
 * generally exemplified by the Player class, or by any of the other
 * "character" classes.  However, another example of entities would be chests
 * or arrows that are fired by other entities.  This is because they have to be
 * rendered to the screen in a way that can be easily manipulated through its
 * coordinates.  Do not get this confused with Tiles though; tiles cannot be
 * manipulated per pixel coordinate.
 *
 * @author Anthony Benavente
 * @version 2/9/14
 */
public abstract class Entity implements Renderable {

    protected static final int DEFAULT_HEIGHT = 21;
    protected static final int DEFAULT_WIDTH = 30;

    /**
     * The image that is used by entities that don't have an animation
     * or texture set because the user failed to call the entities
     * setAvatar or setAnimation method
     */
    protected static Image NO_TEX;

    /**
     * Attempts to initialize the no-texture image.  This is in a static method
     * because the Image constructor throws a SlickException which I wrapped
     * in a try catch block.
     */
    static {
        try {
            NO_TEX = new Image("no_tex.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    /**
     * The name to reference this entity by (not as an identifier)
     */
    private String name;

    /**
     * The position of the entity in the world
     */
    private Vector2f pos;

    /**
     * The width of the entity in pixels
     */
    private int width;

    /**
     * The height of the entity in pixels
     */
    private int height;

    /**
     * The width of this entity's texture.  May be 0 if no texture is present.
     */
    private int textureWidth;

    /**
     * The height of this entity's texture.  May be 0 if no texture is present.
     */
    private int textureHeight;

    /**
     * The direction that the entity is facing
     */
    private Direction direction;

    /**
     * The default speed that the entity moves at normally
     */
    private float walkingSpeed;

    /**
     * If the entity is in motion (if its position is changing)
     */
    private boolean isMoving;

    /**
     * The maximum amount of health an entity can have
     */
    private int maxHealth;

    /**
     * The amount of health that the entity has out of its max health
     */
    private int health;

    /**
     * If the entity still has health; used for deletion of entities
     */
    private boolean dead;

    /**
     * The stats for this entity
     */
    private EntityStats stats;

    /**
     * The bounding box used for collision detection with entities
     */
    private Rectangle bounds;

    /**
     * Creates an Entity object
     *
     * @param x The x coordinate to set the entity at
     * @param y The y coordinate to set the entity at
     */
    public Entity(float x, float y) {
        this(new Vector2f(x, y));
    }

    /**
     * Creates an Entity object
     *
     * @param pos The position to set the entity at
     */
    public Entity(Vector2f pos) {
        this.pos            = pos;
        this.width          = 0;
        this.height         = 0;
        this.direction      = Direction.SOUTH;
        this.walkingSpeed   = 1.0f;
        this.isMoving       = false;
        this.maxHealth      = 30;
        this.health         = maxHealth;
        this.stats          = new EntityStats();
        this.bounds         = new Rectangle(pos.x, pos.y, width, height);
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

    /**
     * Draws the shadow of the entity as a dark ellipse underneath the texture
     *
     * @param g The graphics object to draw the ellipse
     */
    protected void drawShadow(Graphics g) {
        Color col = g.getColor();
        g.setColor(new Color(0, 0, 0, 0.25f));
        g.fillOval(getX() + 5,
                getY() + getTextureHeight() - 2,
                getTextureWidth() - 8,
                5);
        g.setColor(col);
    }

    /**
     * @return The entity's coordinate position in the world
     */
    public Vector2f getPos() {
        return pos;
    }

    /**
     * Sets the entity's position to the passed in coordinate
     *
     * @param pos The position to set the entity at
     */
    protected void setPos(Vector2f pos) {
        this.pos = pos;
        updateBounds();
    }

    /**
     * @return The entity's x coordinate of its position in the world
     */
    public float getX() {
        return pos.x;
    }

    /**
     * @return The entity's y coordinate of its position in the world
     */
    public float getY() {
        return pos.y;
    }

    /**
     * @return The entity's width in pixels
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the entity in pixels
     *
     * @param width The new width to set the entity to
     */
    protected void setWidth(int width) {
        this.width = width;
        updateBounds();
    }

    /**
     * @return The entity's height in pixels
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the entity in pixels
     *
     * @param height The new height to set the entity to
     */
    protected void setHeight(int height) {
        this.height = height;
        updateBounds();
    }

    /**
     * @return The direction that the entity is facing
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Sets the direction that the entity is facing
     *
     * @param direction The new direction the entity will be facing
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * @return How fast the entity moves when it is moving normally
     * (pixels/frame)
     */
    public float getWalkingSpeed() {
        return walkingSpeed;
    }

    /**
     * Sets how fast the entity moves when it is moving normally
     *
     * @param walkingSpeed The new speed at which the entity will normally move
     */
    protected void setWalkingSpeed(float walkingSpeed) {
        this.walkingSpeed = walkingSpeed;
    }

    /**
     * @return The percentage of health that the entity has left
     */
    public float getRemainingHealthPercent() {
        return (float) health / maxHealth;
    }

    /**
     * @return If the entity is moving or not (if its position is changing)
     */
    public boolean isMoving() {
        return isMoving;
    }

    protected void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    /**
     * Moves the entity by a Vector2f amount
     *
     * @param amount A vector representing how much to add onto the position
     *               vector
     */
    public void move(Vector2f amount) {
        Vector2f v = new Vector2f(0, 0);

        float x1 = pos.x + amount.x + (textureWidth - width);
        float y1 = pos.y + amount.y + (textureWidth - height);
        float x2 = pos.x + amount.x + width;
        float y2 = pos.y + amount.y + textureHeight;

        if (amount.x < 0) {
            if (!World.getCollision(x1, y2)) {
                v.x += amount.x;
            }
        } else {
            if (!World.getCollision(x2, y2)) {
                v.x += amount.x;
            }
        }

        if (amount.y < 0) {
            if (!World.getCollision(x2, y1) && !World.getCollision(x1, y1)) {
                v.y += amount.y;
            }
        } else {
            if (!World.getCollision(x2, y2) && !World.getCollision(x1, y2)) {
                v.y += amount.y;
            }
        }

        setPos(new Vector2f(pos.x + v.x, pos.y + v.y));
    }

    /**
     * This method inflicts damage on another entity
     *
     * @param other The entity to hurt
     */
    protected void hit(Entity other, float amount) {
        // TODO: Add weapon system for damage adjustment

        System.out.println(this + " hit " + other +
                " for " + amount + " damage");

        other.health -= amount;

        if (other.health <= 0) {
            other.kill();
        }
    }

    /**
     * Immediately kills this entity
     */
    protected void kill() {
        dead = true;
    }

    /**
     * An entity is considered dead if its health is equal to or below 0
     *
     * @return If the entity is dead or not
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * @return Gets the maximum amount of health an entity can have
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Sets the maximum amount of health that an entity can have.  After this
     * adjustment, the method then checks to make sure that the current health
     * for the entity is less than or equal to the max health
     *
     * @param maxHealth The new max health for this entity
     */
    protected void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        health = Math.min(health, maxHealth);
    }

    /**
     * Adds to this entity's health and makes sure that it doesn't exceed the
     * max health
     *
     * @param amount The amount of hp to add
     */
    protected void heal(float amount) {
        health += amount;
        health = Math.min(maxHealth, health);
    }

    /**
     * @return The stats for the entity
     */
    protected EntityStats getStats() {
        return stats;
    }

    /**
     * Sets if the entity is in motion or not
     *
     * @param isMoving If the entity is moving, this should be true.
     */
    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    /**
     * @return The width of the entity's main texture
     */
    public int getTextureWidth() {
        return textureWidth;
    }

    /**
     * Sets the width of the entity's main texture
     *
     * @param textureWidth The new width to set for the entity's texture
     */
    protected void setTextureWidth(int textureWidth) {
        this.textureWidth = textureWidth;
        updateBounds();
    }

    /**
     * @return The height of the entity's main texture
     */
    public int getTextureHeight() {
        return textureHeight;
    }

    /**
     * Sets the height of the entity's main texture
     *
     * @param textureHeight The new width to set for the entity's texture
     */
    protected void setTextureHeight(int textureHeight) {
        this.textureHeight = textureHeight;
        updateBounds();
    }

    /**
     * Sets the width and height of this entity that will be used for collision
     * detection
     *
     * @param width The new width to set the entity to
     * @param height The new height to set the entity to
     */
    protected void setDimensions(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    /**
     * Sets the width and height of this entity's main texture
     *
     * @param width The new width to set the entity to
     * @param height The new height to set the entity to
     */
    protected void setTextureDimensions(int width, int height) {
        setTextureWidth(width);
        setTextureHeight(height);
    }

    /**
     * @return The name that this entity goes by
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name that this entity will be called
     *
     * @param name The new name of the entity
     */
    protected void setName(String name) {
        this.name = name;
    }

    /**
     * @return The bounding box for this entity
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Call this whenever you change the dimension or position of this entity
     */
    private void updateBounds() {
        bounds.setBounds(pos.x, pos.y, width, height);
    }

    @Override
    public String toString() {
        return name;
    }
}
