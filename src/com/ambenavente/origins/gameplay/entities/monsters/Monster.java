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

import com.ambenavente.origins.gameplay.entities.AnimatedEntity;
import com.ambenavente.origins.gameplay.entities.interfaces.PlayerInteractable;
import com.ambenavente.origins.gameplay.entities.player.Player;
import org.newdawn.slick.GameContainer;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/9/14
 */
public abstract class Monster extends AnimatedEntity implements PlayerInteractable {

    private int interactRange = 30;

    public Monster(float x, float y) {
        super(x, y);
    }

    @Override
    public void onPlayerInteract(Player player) {
        hit(player, getStats().rollDamage());
    }

    @Override
    public abstract void init();

    @Override
    public abstract void update(GameContainer container, int delta);

}
