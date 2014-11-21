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
public class CameraTest extends BasicGame {

    private final int DEFAULT_WIDTH = 100;
    private final int DEFAULT_HEIGHT = 100;
    private final int DEFAULT_TILE_WIDTH = 32;
    private final int DEFAULT_TILE_HEIGHT = 32;

    private Random rand;
    private Camera camera;
    private ColorTile[][] tiles;

    public CameraTest(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        rand = new Random();
        camera = new Camera(0,
                0,
                container.getWidth(),
                container.getHeight());
        tiles = new ColorTile[DEFAULT_HEIGHT][DEFAULT_WIDTH];

        for (int y = 0; y < DEFAULT_HEIGHT; y++) {
            for (int x = 0; x < DEFAULT_WIDTH; x++) {
                tiles[y][x] = new ColorTile(x,
                        y,
                        getRandColor());
            }
        }
    }

    private Color getRandColor() {
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        return new Color(r, g, b);
    }

    @Override
    public void update(GameContainer container, int i) throws SlickException {
        Input input = container.getInput();
        Vector2f amount = new Vector2f(0, 0);
        if (input.isKeyDown(Input.KEY_LEFT)) amount.x -= 5 * camera.getZoom();
        if (input.isKeyDown(Input.KEY_RIGHT)) amount.x += 5 * camera.getZoom();
        if (input.isKeyDown(Input.KEY_UP)) amount.y -= 5 * camera.getZoom();
        if (input.isKeyDown(Input.KEY_DOWN)) amount.y += 5 * camera.getZoom();
        camera.setX(camera.getPos().x + amount.x);
        camera.setY(camera.getPos().y + amount.y);

        if (input.isKeyDown(Input.KEY_ADD)) camera.zoomIn(.01f);
        if (input.isKeyDown(Input.KEY_SUBTRACT)) camera.zoomOut(.01f);
    }

    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {

        graphics.pushTransform();
        graphics.translate(-camera.getX(), -camera.getY());
        graphics.scale(camera.getZoom(), camera.getZoom());
        int minX = toTileX(camera.getX()/camera.getZoom());
        int minY = toTileY(camera.getY()/camera.getZoom());
        int maxX = minX +
                toTileX(camera.getViewWidth()/camera.getZoom());
        int maxY = minY +
                toTileY(camera.getViewHeight()/camera.getZoom());
        for (int y = minY; y <= maxY + 1; y++) {
            if (y < DEFAULT_HEIGHT && y >= 0) {
                for (int x = minX; x <= maxX + 1; x++) {
                    if (x < DEFAULT_WIDTH && x >= 0) {
                        graphics.setColor(tiles[y][x].getColor());
                        graphics.fillRect(x * DEFAULT_TILE_WIDTH,
                                y * DEFAULT_TILE_HEIGHT,
                                DEFAULT_TILE_WIDTH,
                                DEFAULT_TILE_HEIGHT);
                    }
                }
            }
        }

        graphics.popTransform();
    }

    private int toTileX(float x) {
        return (int) (x / DEFAULT_TILE_WIDTH);
    }

    private int toTileY(float y) {
        return (int) (y / DEFAULT_TILE_HEIGHT);
    }

    public static void main(String[] args) {
        try {
            CameraTest test = new CameraTest("Camera Test");
            AppGameContainer container = new AppGameContainer(test);
            container.setDisplayMode(800, 480, false);
            container.setTargetFrameRate(60);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}

