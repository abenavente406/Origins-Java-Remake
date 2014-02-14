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

package com.ambenavente.origins.gameplay.world.json;

import com.ambenavente.origins.gameplay.managers.TileSheetManager;
import com.ambenavente.origins.gameplay.world.level.Tile;
import com.ambenavente.origins.gameplay.world.level.TiledLayer;
import com.ambenavente.origins.gameplay.world.level.TiledMap;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/14/14
 */
public class ImageToMapSerializer {

    enum ColorTile {

        GRASS(new Color(0, 255, 0)),
        GRASS_BLADES(new Color(0, 120, 0)),
        FLOWER(new Color(255, 255, 0));

        private Color color;

        ColorTile(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }
    }

    private TiledMap parent;
    private Image source;
    private Tile[][] tiles;

    public ImageToMapSerializer(TiledMap parent, String path) {
        try {
            this.parent = parent;
            this.source = new Image(path);
            this.tiles = init(source);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private Tile[][] init(Image image) {
        Tile[][] tiles = new Tile[parent.getHeight()][parent.getWidth()];
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color col = image.getColor(j, i);

                switch (lookUpTile(col)) {
                    case GRASS:
                        tiles[i][j] = new Tile(0, 1);
                        break;
                    case GRASS_BLADES:
                        tiles[i][j] = new Tile(1, 1);
                        break;
                    case FLOWER:
                        tiles[i][j] = new Tile(2, 1);
                        break;
                }
            }
        }
        return tiles;
    }

    public ColorTile lookUpTile(Color color){
        for (ColorTile tile : ColorTile.values()) {
            if (tile.getColor().equals(color)) return tile;
        }
        return null;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public static void main(String[] args) throws LWJGLException {
        Display.create();

        TiledMap map = new TiledMap(70,
                                    70,
                                    32,
                                    32,
                                    new TileSheetManager());
        ImageToMapSerializer serializer = new ImageToMapSerializer(map,
                "res/map_images/test_map.png");

        TiledLayer layer = new TiledLayer(map, serializer.getTiles());

        map.addLayer(layer);

        MapSerializer serializer1 = new MapSerializer(map);
        serializer1.writeToFile("test_map_2.json");

        Display.destroy();
    }
}
