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

import com.ambenavente.origins.gameplay.world.level.Layer;
import com.ambenavente.origins.gameplay.world.level.Map;
import com.ambenavente.origins.gameplay.world.level.Tile;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/10/14
 */
public class MapSerializer {

    private Gson gson;
    private Map map;

    public MapSerializer(Map map) {
        this.gson = new Gson();
        this.map = map;
    }

    public String getJson() {
        return gson.toJson(map);
    }

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

    private void verifyFileExists(String fullPath) {
        File file = new File(fullPath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public static void main(String[] args) {

        Tile tiles[][] = new Tile[50][50];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                tiles[i][j] = new Tile(-1, -1);
            }
        }

        Map map = new Map(50, 50, 32, 32);
        Layer layer = new Layer(map, tiles);
        map.addLayer(layer);

        MapSerializer serializer = new MapSerializer(map);
        System.out.println(serializer.getJson());

        Map map1 = new MapDeserializer().readFromJson(serializer.getJson());

        System.out.println(String.valueOf(map.equals(map1)));

        serializer.writeToFile("level_1.json");
    }
}
