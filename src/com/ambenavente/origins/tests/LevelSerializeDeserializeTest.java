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

import com.ambenavente.origins.gameplay.world.json.MapDeserializer;
import com.ambenavente.origins.gameplay.world.json.MapSerializer;
import com.ambenavente.origins.gameplay.world.level.LevelLayer;
import com.ambenavente.origins.gameplay.world.level.LevelMap;
import com.ambenavente.origins.gameplay.world.level.LevelTile;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/10/14
 */
public class LevelSerializeDeserializeTest {
    public static void main(String[] args) {
        final int SIZE = 20;
        final int TILE_SIZE = 32;

        LevelTile[][] testMap = new LevelTile[SIZE][SIZE];
        LevelMap map = new LevelMap(SIZE, SIZE, TILE_SIZE, TILE_SIZE);
        LevelLayer testLayer = new LevelLayer(map, testMap);
        map.addLayer(testLayer);

        MapSerializer serializer = new MapSerializer(map);
        serializer.writeToFile("test_map.json");
        System.out.printf("File created: %s%n",
                new File("res/json/test_map.json").exists());

        MapDeserializer deserializer = new MapDeserializer();
        LevelMap map2 = deserializer.readFromJsonFile("res/json/test_map.json");

        System.out.println("The map saved is the same as the map " +
                "loaded: " + map2.equals(map));
    }
}
