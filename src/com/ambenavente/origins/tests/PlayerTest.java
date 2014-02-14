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

package com.ambenavente.origins.tests;

import com.ambenavente.origins.gameplay.entities.player.Player;
import com.ambenavente.origins.gameplay.world.json.MapDeserializer;
import com.ambenavente.origins.gameplay.world.level.TiledMap;
import com.ambenavente.origins.util.Camera;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/10/14
 */
public class PlayerTest extends BasicGame {

    private Camera camera;
    private Player player;
    private TiledMap map;

    public PlayerTest(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        map     = new MapDeserializer()
                     .readFromJsonFile("res/json/test_map_2.json");
        camera  = new Camera(0,
                             0,
                             container.getWidth(),
                             container.getHeight());
        player  = new Player(0, 0);

        camera.setMax(new Vector2f(map.getRealWidth(), map.getRealHeight()));
        camera.setMin(new Vector2f(0, 0));
    }

    @Override
    public void update(GameContainer container, int i) throws SlickException {
        player.update(container, map, i);
        camera.setX(player.getX() - camera.getViewWidth() / 2);
        camera.setY(player.getY() - camera.getViewHeight() / 2);
    }

    @Override
    public void render(GameContainer container,
                       Graphics graphics) throws SlickException {
        graphics.pushTransform();
        graphics.translate(-camera.getX(), -camera.getY());

        map.render(camera, graphics);
        player.render(graphics);

        graphics.popTransform();
    }

    public static void main(String[] args) {
        try {
            PlayerTest test = new PlayerTest("Player Test");
            AppGameContainer container = new AppGameContainer(test);
            container.setDisplayMode(800, 480, false);
            container.setTargetFrameRate(60);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}