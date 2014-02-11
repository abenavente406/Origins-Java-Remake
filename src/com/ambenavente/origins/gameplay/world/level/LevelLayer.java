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
 * A layer in the map.  This is where the tiles are held that are drawn
 * to the screen
 *
 * @author Anthony Benavente
 * @version 2/10/14
 */
public class LevelLayer {

    /**
     * The id of the LevelMap that houses this layer
     */
    private int parentId;

    /**
     * The width of this layer in pixels
     */
    private int width;

    /**
     * The height of this layer in pixels
     */
    private int height;

    /**
     * The width of an individual tile in this layer (gotten from LevelMap)
     */
    private int tileWidth;

    /**
     * The height of an individual tile in this layer (gotten from LevelMap)
     */
    private int tileHeight;

    /**
     * The opacity of this layer used when drawing it to the screen
     */
    private float opacity;

    /**
     * If the layer should be drawn
     */
    private boolean isVisible;

    /**
     * A 2D array containing each tile in the layer
     */
    private LevelTile[][] tiles;

    /**
     * Creates a default layer using its parent's values and a completely
     * null tiles array
     *
     * @param parent The LevelMap object that owns this layer
     */
    public LevelLayer(LevelMap parent) {
        this(parent, new LevelTile[parent.getHeight()][parent.getHeight()]);
    }

    /**
     * Creates a layer with a given map
     *
     * @param parent The LevelMap object that owns this layer
     * @param map    The array of tiles to set to this layer's tiles
     */
    public LevelLayer(LevelMap parent, LevelTile[][] map) {
        this.parentId   = parent.getId();
        this.width      = parent.getWidth();
        this.height     = parent.getHeight();
        this.tileWidth  = parent.getTileWidth();
        this.tileHeight = parent.getTileHeight();
        this.tiles      = map.clone();
    }

    /**
     * @return All the tiles in this layer
     */
    public LevelTile[][] getTiles() {
        return tiles;
    }

    /**
     * @return The width of this layer in tiles (gotten from LevelMap)
     * @see com.ambenavente.origins.gameplay.world.level.LevelMap
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return The height of this layer in tiles (gotten from LevelMap)
     * @see com.ambenavente.origins.gameplay.world.level.LevelMap
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return The width of an individual tile in this layer
     * (gotten from LevelMap)
     * @see com.ambenavente.origins.gameplay.world.level.LevelMap
     */
    public int getTileWidth() {
        return tileWidth;
    }

    /**
     * @return The height of an individual tile in this layer
     * (gotten from LevelMap)
     * @see com.ambenavente.origins.gameplay.world.level.LevelMap
     */
    public int getTileHeight() {
        return tileHeight;
    }

    /**
     * Gets a specific tile at a given point
     *
     * @param x The x coordinate position to get the tile from
     * @param y The y coordinate position to get the tile from
     * @return The tile at the given coordinates.  If the x or y is out of
     * bounds, the function throws an IndexOutOfBoundsException
     * @throws java.lang.IndexOutOfBoundsException
     */
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

    /**
     * @return If the graphics object should draw this layer
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * Sets if the graphics object can draw this layer aka if the layer is
     * visible
     *
     * @param isVisible True if the layer should be drawn, false otherwise
     */
    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    /**
     * @return The opacity of the layer, used when drawing it.  The scale goes
     * from 0.0 being invisible to 1.0 being completely visible
     */
    public float getOpacity() {
        return opacity;
    }

    /**
     * Sets the opacity of this layer
     *
     * @param opacity The opacity used when drawing this layer.  This function
     *                clamps this parameter between 0.0 and 1.0
     */
    public void setOpacity(float opacity) {
        this.opacity = (float) Math.min(1.0, Math.max(0.0, opacity));
    }
}
