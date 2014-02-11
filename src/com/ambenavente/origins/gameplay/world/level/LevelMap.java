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

import com.ambenavente.origins.util.Camera;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a map that contains multiple layers that may be drawn to the
 * screen
 *
 * @author Anthony Benavente
 * @version 2/10/14
 */
public class LevelMap {

    /**
     * An integer used to tack IDs onto newly created level objects
     */
    private static int ID_TRACK;

    /**
     * The id for this object
     */
    private int id;

    /**
     * The width of this map in tiles
     */
    private int width;

    /**
     * The height of this map in tiles
     */
    private int height;

    /**
     * The width of an individual tile in this map
     */
    private int tileWidth;

    /**
     * The height of an individual tile in this map
     */
    private int tileHeight;

    /**
     * The width of this map in pixels (found by multiplying
     * width * tileWidth)
     */
    private int realWidth;

    /**
     * The height of this map in pixels (found by multiplying
     * height * tileHeight)
     */
    private int realHeight;

    /**
     * The current selected layer (used for map editing)
     */
    private int selectedLayer;

    /**
     * An array list of layers in the map
     */
    private List<LevelLayer> layers;

    /**
     * An array list of tile sheets used by the layers in this map
     */
    private List<TileSheet> tileSheets;

    /**
     * A 2D array representing where entities may walk.  If an element returns
     * true, the block is solid.  If it is false, an entity may walk through
     * it.
     */
    private boolean[][] collisionMap;

    /**
     * Creates a default LevelMap with the dimensions of [width:0, height:0]
     * and with tileWidth and tileHeight of 0
     */
    public LevelMap() {
        this(0, 0, 0, 0);
    }

    /**
     * Creates a LevelMap with given dimensions and tile dimensions
     *
     * @param width      The width of the map in tiles
     * @param height     The height of the map in tiles
     * @param tileWidth  The width of an individual tile in the map
     * @param tileHeight The height of an individual tile in the map
     */
    public LevelMap(int width, int height, int tileWidth, int tileHeight) {
        this.id             = ID_TRACK++;
        this.width          = width;
        this.height         = height;
        this.tileWidth      = tileWidth;
        this.tileHeight     = tileHeight;
        this.realWidth      = width  * tileWidth;
        this.realHeight     = height * tileHeight;
        this.layers         = new ArrayList<LevelLayer>();
        this.tileSheets     = new ArrayList<TileSheet>();
    }

    /**
     * Draws each layer of the map to the screen
     *
     * @param camera The camera object used for translating the viewport
     * @param g      The graphics object to draw the map
     */
    public void render(Camera camera, Graphics g) {

        // --------------------------------------------------------- //
        // Optimized rendering function to only draw what the camera //
        // can see                                                   //
        // --------------------------------------------------------- //
        int minX = toTileX(camera.getX());
        int minY = toTileY(camera.getY());
        int maxX = toTileX(camera.getX()) + toTileX(camera.getViewWidth());
        int maxY = toTileY(camera.getY()) + toTileY(camera.getViewHeight());

        for (LevelLayer l : layers) {
            for (int y = minY; y < maxY + 1; y++) {
                if (y < height && y >= 0) {
                    for (int x = minX; x < maxX + 1; x++) {
                        if (x < width && x >= 0) {
                            LevelTile tile = l.getTile(x, y);
                            TileSheet sheet =
                                    tileSheets.get(tile.getTileSetId());
                            Rectangle rect = sheet.getRect(tile.getTileId());
                            g.drawImage(sheet.getImage(),
                                        x,
                                        y,
                                        x + tileWidth,
                                        y + tileHeight,
                                        rect.getX(),
                                        rect.getY(),
                                        rect.getWidth(),
                                        rect.getHeight());
                        }
                    }
                }
            }
        }
    }

    /**
     * @return The width of the map in tiles
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the number of tiles wide the map is
     *
     * @param width The width of the map in tiles
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return The height of the map in tiles
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the number of tiles tall the map is
     *
     * @param height The height of the map in tiles
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return The width of an individual tile in the map
     */
    public int getTileWidth() {
        return tileWidth;
    }

    /**
     * @return The height of an individual tile in the map
     */
    public int getTileHeight() {
        return tileHeight;
    }

    /**
     * @return The selected layer of the map (used for editing)
     */
    public int getSelectedLayer() {
        return selectedLayer;
    }

    /**
     * Sets the layer to be edited
     *
     * @param selectedLayer The selected layer index of the map
     */
    public void setSelectedLayer(int selectedLayer) {
        this.selectedLayer = selectedLayer;
    }

    /**
     * Adds a layer to the map
     *
     * @param layer The layer to add
     */
    public void addLayer(LevelLayer layer) {
        layers.add(layer);
    }

    /**
     * Converts a passed in x-coordinate to its rounded tile position
     *
     * @param x The x coordinate to convert
     * @return The x coordinate, but divided by the tileWidth
     */
    public int toTileX(float x) {
        return (int) (x / tileWidth);
    }

    /**
     * Converts a passed in y-coordinate to its rounded tile position
     *
     * @param y The y coordinate to convert
     * @return The y coordinate, but divided by the tileHeight
     */
    public int toTileY(float y) {
        return (int) (y / tileHeight);
    }

    /**
     * Converts a passed in vector coordinate to its rounded tile position
     *
     * @param v The position to convert to the tile position
     * @return The position but rounded to convert it to the tile position.
     * The x coordinate is divided by the tileWidth and the y coordinate is
     * divided by the tileHeight
     */
    public Vector2f toTileVector(Vector2f v) {
        return new Vector2f(toTileX(v.x), toTileY(v.y));
    }

    /**
     * Loads a map from a given path (only accepts json file)
     *
     * @param path The path to the json file in the classpath
     */
    public void loadMap(String path) {
        LevelMap map;
        String line;
        BufferedReader reader;
        FileInputStream stream;
        InputStreamReader streamReader;

        try {
            stream = new FileInputStream(path);
            streamReader = new InputStreamReader(stream);
            reader = new BufferedReader(streamReader);

            line = reader.readLine();
            int width  = Integer.parseInt(line.split(" ")[0]);
            int height = Integer.parseInt(line.split(" ")[1]);
            LevelTile[] tiles = new LevelTile[height * width];

            while ((line = reader.readLine()) != null) {

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a map to the passed in map object.  This takes all the fields from
     * the map object passed in and inserts it into this object
     *
     * @param map The map to load values from
     */
    public void loadMap(LevelMap map) {

    }

    /**
     * @return The id of this LevelMap object
     */
    public int getId() {
        return id;
    }

    /**
     * Gets if two map objects are equal to each other by comparing dimensions,
     * tile dimensions, and the id number.
     *
     * @param obj The object to compare against. If this is not a LevelMap
     *            object, the function will return false
     * @return If this map object is equal to the object passed in
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = true;
        if (obj instanceof LevelMap) {
            LevelMap map = (LevelMap)obj;

            result &= map.getHeight()       == getHeight();
            result &= map.getId()           == getId();
            result &= map.getTileWidth()    == getTileWidth();
            result &= map.getTileHeight()   == getTileHeight();
            result &= map.getWidth()        == getWidth();
        }
        return result;
    }
}
