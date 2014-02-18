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
import com.ambenavente.origins.gameplay.managers.SpriteSheetManager;
import com.ambenavente.origins.gameplay.world.level.TiledMap;
import org.newdawn.slick.*;
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

        init();
    }

    public Player(Player player) {
        super(player.getX(), player.getY());

        initFromPlayer(player);
    }

    @Override
    public void init() {
        // Don't do anything yet
        setWalkingSpeed(1.56f);

        Image[] _left   = new Image[3];
        Image[] _right  = new Image[3];
        Image[] _up     = new Image[3];
        Image[] _down   = new Image[3];

        SpriteSheet playerSheet = SpriteSheetManager.get(0);

        for (int x = 0; x < playerSheet.getHorizontalCount(); x++) {
            _down[x]    = playerSheet.getSprite(x, 0);
            _left[x]    = playerSheet.getSprite(x, 1);
            _right[x]   = playerSheet.getSprite(x, 2);
            _up[x]      = playerSheet.getSprite(x, 3);
        }

        setAvatar(Direction.NORTH, _up[1]);
        setAvatar(Direction.SOUTH, _down[1]);
        setAvatar(Direction.EAST,  _right[1]);
        setAvatar(Direction.WEST,  _left[1]);

        setMovingAnimation(Direction.NORTH, new Animation(_up, 150));
        setMovingAnimation(Direction.SOUTH, new Animation(_down, 150));
        setMovingAnimation(Direction.EAST,  new Animation(_right, 150));
        setMovingAnimation(Direction.WEST,  new Animation(_left, 150));

        setTextureWidth(32);
        setTextureHeight(32);
        setWidth(24);
        setHeight(21);

        setName("Player");
    }

    private void initFromPlayer(Player player) {
        init();

        // TODO: Load stuff from the player passed in
    }

    @Override
    public void update(GameContainer container, int delta) {
        if (!isDead()){
            getInput(container.getInput());
        }
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
