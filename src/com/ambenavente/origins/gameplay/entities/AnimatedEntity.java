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
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/10/14
 */
public abstract class AnimatedEntity extends StaticEntity {

    private boolean isMoving;
    private Animation[] movingAnimations;
    private Animation[] stillAnimations;

    public AnimatedEntity(float x, float y) {
        super(x, y);

        this.isMoving           = false;
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

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    protected void setStillAnimation(Direction dir, Animation animation) {
        stillAnimations[dir.ordinal()] = animation;
    }

    protected void setMovingAnimation(Direction dir, Animation animation) {
        movingAnimations[dir.ordinal()] = animation;
    }

    public Animation getMovingAnimation(Direction dir) {
        return movingAnimations[dir.ordinal()];
    }

    public Animation getStillAnimation(Direction dir) {
        return stillAnimations[dir.ordinal()];
    }
}
