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

package com.ambenavente.origins.gameplay.entities.interfaces;

import com.ambenavente.origins.gameplay.entities.Entity;

/**
 * Implemented by objects that have events associated with colliding with other
 * entities or solid objects
 *
 * @author Anthony Benavente
 * @version 2/10/14
 */
public interface Collidable {

    /**
     * Gets called when an entity collides with another entity
     *
     * @param other The entity that collided with this one
     */
    void onEntityCollision(Entity other);

    /**
     * Gets called when an entity collides with a wall/tile
     */
    void onWallCollision();

}
