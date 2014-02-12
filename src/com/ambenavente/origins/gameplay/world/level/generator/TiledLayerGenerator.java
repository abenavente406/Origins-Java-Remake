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

package com.ambenavente.origins.gameplay.world.level.generator;

import com.ambenavente.origins.gameplay.world.level.Tile;
import com.ambenavente.origins.gameplay.world.level.TiledLayer;
import com.ambenavente.origins.gameplay.world.level.TiledMap;

import java.util.Random;

/**
 * Used for generating layers with various algorithms
 *
 * @author Anthony Benavente
 * @version 2/11/14
 * @see EnumGenerationAlgorithm
 */
public class TiledLayerGenerator {

    private static final Random RAND = new Random();

    public static TiledLayer generateLayer(TiledMap map,
                                           int groundId,
                                           int wallId,
                                           int tileSheetId,
                                           EnumGenerationAlgorithm algorithm) {
        switch (algorithm) {
            case RANDOM:
                return generateFromRandom(map, groundId, wallId, tileSheetId);
            case DRUNKEN:
                return generateFromDrunk(map, groundId, wallId ,tileSheetId);
            case PERLIN:
            default:
                return generateFromRandom(map, groundId, wallId, tileSheetId);
        }

    }

    private static TiledLayer generateFromRandom(TiledMap map,
                                                 int groundId,
                                                 int wallId,
                                                 int tileSheetId) {
        final int width = map.getWidth();
        final int height = map.getHeight();
        Tile[][] tiles = new Tile[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                boolean ground = RAND.nextBoolean();
                tiles[y][x] = new Tile(ground ? groundId : wallId,
                                       tileSheetId);
            }
        }
        TiledLayer result = new TiledLayer(map, tiles);
        return result;
    }

    private static TiledLayer generateFromDrunk(TiledMap map,
                                                int groundId,
                                                int wallId,
                                                int tileSheetId) {
        final int width = map.getWidth();
        final int height = map.getHeight();
        Tile[][] tiles = DrunkenWalkGenerator.generateDungeon(width,
                height, groundId, wallId, tileSheetId);
        TiledLayer result = new TiledLayer(map, tiles);
        return result;
    }
}
