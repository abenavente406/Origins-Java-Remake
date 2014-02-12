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

/**
 * An enumeration containing different level generation algorithms
 *
 * @author Anthony Benavente
 * @version 2/11/14
 */
public enum EnumGenerationAlgorithm {
    /**
     * This generates a completely random level
     */
    RANDOM,

    /**
     * This generates a level following perlin noise
     */
    PERLIN,

    /**
     * This generates a dungeon using the drunken-walk algorithm
     */
    DRUNKEN
}
