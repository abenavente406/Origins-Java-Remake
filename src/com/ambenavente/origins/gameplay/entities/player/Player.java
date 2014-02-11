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

package com.ambenavente.origins.gameplay.entities.player;

import com.ambenavente.origins.gameplay.entities.AnimatedEntity;
import com.ambenavente.origins.gameplay.entities.Direction;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

/**
 * Represents a Player that is controlled by the user
 *
 * @author Anthony Benavente
 * @version 2/9/14
 */
public class Player extends AnimatedEntity {

    public Player(float x, float y) {
        super(x, y);
    }

    @Override
    public void init() {
        // Don't do anything yet
        setWalkingSpeed(2.56f);
    }

    @Override
    public void update(GameContainer container, int delta) {
        getInput(container.getInput());
    }

    /**
     * Gets the input from the player
     *
     * @param input The input object that is used by the GameContainer
     */
    private void getInput(Input input) {

        Vector2f vel = new Vector2f(0, 0);

        // Check for arrow keys
        if (input.isKeyDown(Input.KEY_UP)) {
            setDirection(Direction.NORTH);
            vel.y -= getWalkingSpeed();
        }

        if (input.isKeyDown(Input.KEY_DOWN)) {
            setDirection(Direction.SOUTH);
            vel.y += getWalkingSpeed();
        }

        if (input.isKeyDown(Input.KEY_LEFT)) {
            setDirection(Direction.WEST);
            vel.x -= getWalkingSpeed();
        }

        if (input.isKeyDown(Input.KEY_RIGHT)) {
            setDirection(Direction.EAST);
            vel.x += getWalkingSpeed();
        }

        setMoving(vel.x != 0 || vel.y != 0);

        move(vel);
    }
}
