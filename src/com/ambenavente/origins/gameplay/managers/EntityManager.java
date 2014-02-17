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

package com.ambenavente.origins.gameplay.managers;

import com.ambenavente.origins.gameplay.entities.monsters.Monster;
import com.ambenavente.origins.gameplay.entities.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/9/14
 */
public class EntityManager {

    private Player player;
    private List<Monster> monsters;

    // TODO: Add array list for NPCs

    public EntityManager() {
        this(null);
    }

    public EntityManager(Player player) {
        if (player == null) {
            // Create a new player
            player = new Player(0, 0);
        } else {
            // We have to load the player from the player passed in
            player = new Player(player);
        }

        monsters = new ArrayList<Monster>();
    }

    public Player getPlayer() {
        return player;
    }
}
