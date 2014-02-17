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

package com.ambenavente.origins.gameplay.entities.ai;

import com.ambenavente.origins.gameplay.entities.Direction;
import com.ambenavente.origins.gameplay.entities.Entity;
import com.ambenavente.origins.gameplay.entities.interfaces.Behavior;
import com.ambenavente.origins.util.MyRandom;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/17/14
 */
public class RandomMoveBehavior implements Behavior {

    private Entity owner;

    private int timeSinceMove;
    private int maxTime;
    private int timeMoving;
    private int timeOut;
    private Direction randDirection;
    private float walkingSpeed;
    private boolean isMoving;

    public RandomMoveBehavior(Entity owner) {
        this.owner          = owner;
        this.timeSinceMove  = 0;
        this.timeMoving     = 0;
        this.timeOut        = 0;
        this.maxTime        = 0;
        this.isMoving       = false;
        this.randDirection  = Direction.getRandDirection();
        this.walkingSpeed   = owner.getWalkingSpeed();
    }

    @Override
    public void behave() {
        if (!isMoving) {
            if (timeSinceMove++ >= timeOut) {
                isMoving = true;
                timeMoving = 0;
                maxTime = MyRandom.getRandBetween(50, 300);
                timeSinceMove = 0;
                timeOut = MyRandom.getRandBetween(10, 80);
                randDirection = Direction.getRandDirection();
            }
        } else {
            moveEntity();

            if (timeMoving++ >= maxTime) {
                timeSinceMove = 0;
                isMoving = false;
            }
        }

        owner.setIsMoving(isMoving);
    }

    /**
     * Moves the entity in a random direction
     */
    private void moveEntity() {

        Vector2f amount = new Vector2f(0, 0);

        if (randDirection == Direction.NORTH) {
            amount.y -= walkingSpeed;
        } else if (randDirection == Direction.EAST) {
            amount.x += walkingSpeed;
        } else if (randDirection == Direction.SOUTH) {
            amount.y += walkingSpeed;
        } else {
            amount.x -= walkingSpeed;
        }

        owner.setDirection(randDirection);
        owner.move(amount);
    }
}
