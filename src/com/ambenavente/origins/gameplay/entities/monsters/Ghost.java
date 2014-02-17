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

package com.ambenavente.origins.gameplay.entities.monsters;

import com.ambenavente.origins.gameplay.entities.ai.ChasePlayerBehavior;
import com.ambenavente.origins.gameplay.entities.ai.LookForPlayerBehavior;
import com.ambenavente.origins.gameplay.entities.ai.RandomMoveBehavior;
import com.ambenavente.origins.gameplay.entities.player.Player;
import com.ambenavente.origins.gameplay.world.World;
import org.newdawn.slick.GameContainer;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/17/14
 */
public class Ghost extends Monster {

    private LookForPlayerBehavior lookForPlayerAi;
    private RandomMoveBehavior randomMoveAi;
    private ChasePlayerBehavior chasePlayerAi;

    public Ghost(float x, float y) {
        super(x, y);
        init();
    }

    @Override
    public void init() {
        setMaxHealth(10);
        setWidth(28);
        setHeight(21);
        setWalkingSpeed(.93f);

        // TODO: Set the ghost's textures

        lookForPlayerAi = new LookForPlayerBehavior(this, getDetectRange());
        randomMoveAi = new RandomMoveBehavior(this);
        chasePlayerAi = new ChasePlayerBehavior(this, getInteractRange());
    }

    @Override
    public void update(GameContainer container, int delta) {

    }

    private Player getPlayerInSight() {
        // TODO: Write a player detection algorithm
        return null;
    }
}
