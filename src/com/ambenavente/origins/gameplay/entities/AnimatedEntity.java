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

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Represents an entity that has animations for each direction it faces
 *
 * @author Anthony Benavente
 * @version 2/10/14
 */
public abstract class AnimatedEntity extends StaticEntity {

    /**
     * The animations for the entity while it is moving
     */
    private Animation[] movingAnimations;

    /**
     * The animations for the entity while it is standing still
     */
    private Animation[] stillAnimations;

    public AnimatedEntity(float x, float y) {
        super(x, y);

        this.movingAnimations   = new Animation[Direction.values().length];
        this.stillAnimations    = new Animation[Direction.values().length];
    }

    @Override
    public void render(Graphics g) {
        if (!isMoving()) {
            super.render(g);
        } else {
            Animation anim = movingAnimations[getDirection().ordinal()];

            if (anim != null) {
                anim.draw(getX(), getY());
            } else {
                Entity.NO_TEX.draw(getX(), getY());
            }
        }
    }

    /* ------------------------ */
    /* Methods to be overloaded */
    /* ------------------------ */
    @Override
    public abstract void update(GameContainer container, int delta);
    /* ------------------------ */

    /**
     * Sets the standing still animation object at the specified direction
     *
     * @param dir       The direction to set the animation for
     * @param animation The animation to insert
     */
    protected void setStillAnimation(Direction dir, Animation animation) {
        stillAnimations[dir.ordinal()] = animation;
    }

    /**
     * Sets the moving animation object at the specified direction
     *
     * @param dir       The direction to set the animation for
     * @param animation The animation to insert
     */
    protected void setMovingAnimation(Direction dir, Animation animation) {
        movingAnimations[dir.ordinal()] = animation;
    }

    /**
     * Gets the moving animation at the specified direction
     *
     * @param dir The direction to get the animation from
     * @return The animation for the object's moving at the specified
     * direction
     */
    public Animation getMovingAnimation(Direction dir) {
        return movingAnimations[dir.ordinal()];
    }

    /**
     * Gets the still animation at the specified direction
     *
     * @param dir The direction to get the animation from
     * @return The animation for the object's standing still at the specified
     * direction
     */
    public Animation getStillAnimation(Direction dir) {
        return stillAnimations[dir.ordinal()];
    }
}
