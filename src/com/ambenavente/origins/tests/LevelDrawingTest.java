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

import com.ambenavente.origins.gameplay.managers.TileSheetManager;
import com.ambenavente.origins.gameplay.world.json.MapDeserializer;
import com.ambenavente.origins.gameplay.world.level.TiledMap;
import com.ambenavente.origins.util.Camera;
import org.lwjgl.LWJGLException;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/12/14
 */
public class LevelDrawingTest extends BasicGame {

    private TileSheetManager manager;
    private MapDeserializer deserializer;
    private TiledMap map;
    private Camera camera;

    /**
     * Create a new basic game
     *
     * @param title The title for the game
     */
    public LevelDrawingTest(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        manager = new TileSheetManager();
        deserializer = new MapDeserializer();
        map = deserializer.readFromJsonFile("res/json/test_map_2.json");

        camera = new Camera(800, 480);
        camera.setMax(new Vector2f(map.getRealWidth(), map.getRealHeight()));
        camera.setMin(new Vector2f(0, 0));
    }

    @Override
    public void update(GameContainer container,
                       int delta) throws SlickException {
        Input input = container.getInput();
        Vector2f amount = new Vector2f(0, 0);
        if (input.isKeyDown(Input.KEY_LEFT)) amount.x -= 5;
        if (input.isKeyDown(Input.KEY_RIGHT)) amount.x += 5;
        if (input.isKeyDown(Input.KEY_UP)) amount.y -= 5;
        if (input.isKeyDown(Input.KEY_DOWN)) amount.y += 5;
        camera.setX(camera.getPos().x + amount.x);
        camera.setY(camera.getPos().y + amount.y);
    }

    @Override
    public void render(GameContainer container,
                       Graphics g) throws SlickException {
        g.pushTransform();
        g.translate(-camera.getX(), -camera.getY());
        map.render(camera, g);
        g.popTransform();
    }

    public static void main(String[] args) throws LWJGLException {

//        LevelSerializeDeserializeTest.main(new String[] {});

        try {
            LevelDrawingTest test = new LevelDrawingTest("Level Drawing Test");
            AppGameContainer container = new AppGameContainer(test);
            container.setDisplayMode(800, 480, false);
            container.setTargetFrameRate(60);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
