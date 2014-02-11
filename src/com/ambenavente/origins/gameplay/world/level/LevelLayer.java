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

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/10/14
 */
public class LevelLayer {

    private int         parentId;
    private LevelTile[][]    tiles;
    private boolean     isVisible;
    private float       opacity;
    private int         width;
    private int         height;
    private int         tileWidth;
    private int         tileHeight;

    public LevelLayer(LevelMap parent) {
        this(parent, new LevelTile[parent.getHeight()][parent.getHeight()]);
    }

    public LevelLayer(LevelMap parent, LevelTile[][] map) {
        this.parentId   = parent.getId();
        this.width      = parent.getWidth();
        this.height     = parent.getHeight();
        this.tileWidth  = parent.getTileWidth();
        this.tileHeight = parent.getTileHeight();
        this.tiles      = map.clone();
    }

    public LevelTile[][] getTiles() {
        return tiles;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public LevelTile getTile(int x, int y) {
        if (x >= 0 &&
            x < width &&
            y >= 0 &&
            y < height) {
            return tiles[y][x];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public float getOpacity() {
        return opacity;
    }

    public void setOpacity(float opacity) {
        this.opacity = (float) Math.min(1.0, Math.max(0.0, opacity));
    }
}
