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
public class Tile {

    private int tileId;
    private int tileSetId;

    public Tile(int tileId, int tileSetId) {
        this.tileId = tileId;
        this.tileSetId = tileSetId;
    }

    public int getTileId() {
        return tileId;
    }

    public void setTileId(int tileId) {
        this.tileId = tileId;
    }

    public int getTileSetId() {
        return tileSetId;
    }

    public void setTileSetId(int tileSetId) {
        this.tileSetId = tileSetId;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = true;
        if (obj instanceof Tile) {
            Tile tile   = (Tile)obj;
            result      &= tile.getTileId()      == tileId;
            result      &= tile.getTileSetId()   == tileSetId;
        }
        return result;
    }
}
