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
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/10/14
 */
public class Map {

    private static int ID_TRACK;

    private int id;
    private int width;
    private int height;
    private int tileWidth;
    private int tileHeight;
    private int realWidth;
    private int realHeight;
    private int selectedLayer;
    private List<Layer> layers;
    private List<TileSheet> tileSheets;
    private boolean[][] collisionMap;

    public Map() {
        this(0, 0, 0, 0);
    }

    public Map(int width, int height, int tileWidth, int tileHeight) {
        this.id             = ID_TRACK++;
        this.width          = width;
        this.height         = height;
        this.tileWidth      = tileWidth;
        this.tileHeight     = tileHeight;
        this.realWidth      = width  * tileWidth;
        this.realHeight     = height * tileHeight;
        this.layers         = new ArrayList<Layer>();
        this.tileSheets     = new ArrayList<TileSheet>();
    }

    public void render(Camera camera, Graphics g) {
        int minX = toTileX(camera.getX());
        int minY = toTileY(camera.getY());
        int maxX = toTileX(camera.getX()) + toTileX(camera.getViewWidth());
        int maxY = toTileY(camera.getY()) + toTileY(camera.getViewHeight());

        for (Layer l : layers) {
            for (int y = minY; y < maxY + 1; y++) {
                if (y < height && y >= 0) {
                    for (int x = minX; x < maxX + 1; x++) {
                        if (x < width && x >= 0) {
                            Tile tile = l.getTile(x, y);
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

    public int getSelectedLayer() {
        return selectedLayer;
    }

    public void setSelectedLayer(int selectedLayer) {
        this.selectedLayer = selectedLayer;
    }

    public void addLayer(Layer layer) {
        layers.add(layer);
    }

    public int toTileX(float x) {
        return (int) (x / tileWidth);
    }

    public int toTileY(float y) {
        return (int) (y / tileHeight);
    }

    public Vector2f toTileVector(Vector2f v) {
        return new Vector2f((int)(v.x / tileWidth),
                            (int)(v.y / tileHeight));
    }

    public void loadMap(String path) {
        Map map;
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
            Tile[] tiles = new Tile[height * width];

            while ((line = reader.readLine()) != null) {

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(Map map) {

    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = true;
        if (obj instanceof Map) {
            Map map = (Map)obj;

            result &= map.getHeight()       == getHeight();
            result &= map.getId()           == getId();
            result &= map.getTileWidth()    == getTileWidth();
            result &= map.getTileHeight()   == getTileHeight();
            result &= map.getWidth()        == getWidth();
        }
        return result;
    }
}
