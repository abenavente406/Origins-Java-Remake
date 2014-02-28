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

package com.ambenavente.origins.gameplay.world;

import com.ambenavente.origins.gameplay.entities.player.Player;
import com.ambenavente.origins.gameplay.world.json.MapDeserializer;
import com.ambenavente.origins.gameplay.world.level.TiledMap;
import com.ambenavente.origins.util.Camera;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Singleton class to manage all levels in the game
 *
 * @author Anthony Benavente
 * @version 2/9/14
 */
public class World {

    private static int activeId;
    private static Hashtable<Integer, TiledMap> maps;

    public static void init() {
        maps = new Hashtable<Integer, TiledMap>();
        activeId = 0;

        addMaps();
    }

    private static void addMaps() {
        MapDeserializer deserializer = new MapDeserializer();
        maps.put(0, deserializer.readFromJsonFile("res/json/test_map.json"));
        maps.put(1, deserializer.readFromJsonFile("res/json/test_map_2.json"));
    }

    public static TiledMap getActiveMap() {
        return maps.get(activeId);
    }

    public static void selectMap(int activeId) {
        if (activeId >= 0 && activeId < maps.size()) {
            World.activeId = activeId;
        }
    }

    public static void update(GameContainer container, int delta) {
        getActiveMap().update(container, delta);
    }

    public static void render(Camera camera, Graphics g) {
        getActiveMap().render(camera, g);
    }

    public static void nextMap() {
        activeId = (activeId + 1) % maps.size();
    }

    public static boolean getCollision(float x, float y) {
        return getActiveMap().getCollision(x, y);
    }

    public static int getWidth() {
        return getActiveMap().getWidth();
    }

    public static int getHeight() {
        return getActiveMap().getHeight();
    }

    public static int getTileWidth() {
        return getActiveMap().getTileWidth();
    }

    public static int getTileHeight() {
        return getActiveMap().getTileHeight();
    }

    public static Player getPlayer() {
        return getActiveMap().getPlayer();
    }

    public static void goTo(int targetId) {
        activeId = targetId;
    }
}
