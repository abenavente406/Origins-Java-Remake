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

import com.ambenavente.origins.gameplay.world.level.Tile;
import com.ambenavente.origins.gameplay.world.level.TiledLayer;
import com.ambenavente.origins.gameplay.world.level.TiledMap;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Writes a map object to a json file.
 *
 * @author Anthony Benavente
 * @version 2/10/14
 */
public class MapSerializer {

    /**
     * The Gson object for writing json
     */
    private Gson gson;

    /**
     * The map object to write
     */
    private TiledMap map;

    /**
     * Creates a MapSerializer object
     *
     * @param map The map object to write to a file
     */
    public MapSerializer(TiledMap map) {
        this.gson = new Gson();
        this.map = map;
    }

    /**
     * @return A json representation of the map object passed into the
     * constructor
     */
    public String getJson() {
        return gson.toJson(map);
    }

    /**
     * Writes a json file that represents the map object passed into the
     * constructor
     *
     * @param path The path to write to.  <em>Note: 'res/json' is tacked
     *             onto the path that is passed in.  So really, only pass
     *             in the name of the json file you want.</em>
     * @return If the write operation completed with no errors, this function
     * will return true.  False otherwise.
     */
    public boolean writeToFile(String path) {
        boolean success;
        String fullPath = "res/json/" + path;

        verifyFileExists(fullPath);

        try {
            PrintWriter writer = new PrintWriter(fullPath);
            writer.print(getJson());
            writer.close();
            success = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            success = false;
        }

        return success;
    }

    /**
     * Verifies that the user is creating exists or does not exist
     *
     * @param fullPath The file to check
     */
    private void verifyFileExists(String fullPath) {
        File file = new File(fullPath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
    }

    /**
     * Test for serializing map data
     *
     * @param args No arguments are used in this test
     */
    public static void main(String[] args) {

        Tile tiles[][] = new Tile[50][50];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                tiles[i][j] = new Tile(-1, -1);
            }
        }

        TiledMap map = new TiledMap(50, 50, 32, 32);
        TiledLayer layer = new TiledLayer(map, tiles);
        map.addLayer(layer);

        MapSerializer serializer = new MapSerializer(map);
        System.out.println(serializer.getJson());

        TiledMap map1 = new MapDeserializer().readFromJson(serializer.getJson());

        System.out.println(String.valueOf(map.equals(map1)));

        serializer.writeToFile("level_1.json");
    }
}
