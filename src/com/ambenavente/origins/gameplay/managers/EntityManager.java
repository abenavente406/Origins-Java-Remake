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

import com.ambenavente.origins.gameplay.entities.Entity;
import com.ambenavente.origins.gameplay.entities.monsters.Ghost;
import com.ambenavente.origins.gameplay.entities.monsters.Monster;
import com.ambenavente.origins.gameplay.entities.monsters.Skeleton;
import com.ambenavente.origins.gameplay.entities.player.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

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
            this.player = new Player(32, 32);
        } else {
            // We have to load the player from the player passed in
            this.player = new Player(player);
        }

        monsters = new ArrayList<Monster>();
        monsters.add(new Ghost(32 * 4, 32 * 4));
        monsters.add(new Skeleton(32 * 5, 32 * 4));

        // ------------------------------------------
        // Testing adding monsters and benchmarking
        // 100 monsters:  ~61 fps
        // 200 monsters:  ~61 fps
        // 400 monsters:  ~61 fps
        // 800 monsters:  ~61 fps
        // 1600 monsters: ~61 fps
        // 2000 monsters: ~60 fps
        // 3200 monsters: ~40 fps
        // ------------------------------------------
        // MAX MONSTERS: 2000
        // ------------------------------------------
//        for (int i = 0; i < 2000; i++) {
//            monsters.add(new Ghost(32 * 4, 32 * 4));
//        }
    }

    public Player getPlayer() {
        return player;
    }

    public void update(GameContainer container, int delta) {
        List<Entity> all = new ArrayList<Entity>();
        all.addAll(monsters);
        all.add(player);

        for (int i = all.size() - 1; i >= 0; i--) {
            Entity current = all.get(i);

            if (current.isDead()) {
                all.remove(current);

                if (current instanceof Monster) monsters.remove(current);
            } else {
                current.update(container, delta);
            }
        }
    }

    public void render(Graphics g) {
        List<Entity> all = new ArrayList<Entity>();
        all.addAll(monsters);
        all.add(player);

        for (int i = all.size() - 1; i >= 0; i--) {
            all.get(i).render(g);
        }
    }
}
