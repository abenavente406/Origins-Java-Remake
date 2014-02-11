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
 * A single tile that gets drawn to the map.
 *
 * @author Anthony Benavente
 * @version 2/10/14
 */
public class Tile {

    /**
     * The id that the tile resides in the tile sheet
     */
    private int tileId;

    /**
     * The id of the tile sheet containing this tile's image
     */
    private int tileSetId;

    /**
     * Creates a Tile object
     *
     * @param tileId    The id of the tile
     * @param tileSetId The id of the tile sheet containing the tile's image
     */
    public Tile(int tileId, int tileSetId) {
        this.tileId = tileId;
        this.tileSetId = tileSetId;
    }

    /**
     * @return The tile's id
     */
    public int getTileId() {
        return tileId;
    }

    /**
     * @return The id of the tile sheet containing this tile's image
     */
    public int getTileSetId() {
        return tileSetId;
    }

}
