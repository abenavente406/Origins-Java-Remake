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

package com.ambenavente.origins.gameplay.world.level;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/10/14
 */
public class TileSheet {

    private Image sourceImage;
    private int tileWidth;
    private int tileHeight;
    private int width;
    private int height;
    private int realWidth;
    private int realHeight;
    private Rectangle[] sourceRects;
    private String path;

    public TileSheet(String path, int tileHeight, int tileWidth) {
        this.tileHeight = tileHeight;
        this.tileWidth  = tileWidth;
        this.path       = path;
        init();
    }

    private void init() {

        loadImage();

        this.realWidth = sourceImage.getWidth();
        this.realHeight = sourceImage.getHeight();
        this.width = realWidth / tileWidth;
        this.height = realHeight / tileHeight;

        initTiles();
    }

    private boolean loadImage() {
        boolean success = true;
        if (path != null) {
            try {
                sourceImage = new Image(path);
                success = true;
            } catch (SlickException e) {
                e.printStackTrace();
                success = false;
            }
        }
        return success;
    }

    private void initTiles() {
        int totalTiles = width * height;
        sourceRects = new Rectangle[totalTiles];

        int tile = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                sourceRects[tile++] = new Rectangle(x * tileWidth,
                                                    y * tileHeight,
                                                    tileWidth,
                                                    tileHeight);
            }
        }
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getRealWidth() {
        return realWidth;
    }

    public void setRealWidth(int realWidth) {
        this.realWidth = realWidth;
    }

    public int getRealHeight() {
        return realHeight;
    }

    public void setRealHeight(int realHeight) {
        this.realHeight = realHeight;
    }

    public Rectangle getRect(int id) {
        if (id >= 0 && id < sourceRects.length){
            return sourceRects[id];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public String getPath() {
        return path;
    }

    public Image getImage() {
        return sourceImage;
    }
}
