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

package com.ambenavente.origins.gameplay.entities.ai;

import com.ambenavente.origins.gameplay.entities.Direction;
import com.ambenavente.origins.gameplay.entities.Entity;
import com.ambenavente.origins.gameplay.entities.interfaces.Behavior;
import com.ambenavente.origins.gameplay.entities.interfaces.PlayerInteractable;
import com.ambenavente.origins.gameplay.entities.player.Player;
import com.ambenavente.origins.gameplay.world.World;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/17/14
 */
public class ChasePlayerBehavior implements Behavior {

    private Entity owner;
    private int interactRange;

    public ChasePlayerBehavior(Entity owner, int interactRange) {
        this.owner = owner;
        this.interactRange = interactRange;
    }

    @Override
    public void behave() {
        Player player = World.getPlayer();

        if (owner.getPos().distance(player.getPos()) > interactRange) {
            owner.move(getDirectionToPlayer(player));
            owner.setIsMoving(true);
        } else {
            owner.setIsMoving(false);

            if (owner instanceof PlayerInteractable) {
                ((PlayerInteractable) owner).onPlayerInteract(player);
            }
        }

        facePlayer(player);
    }

    private Vector2f getDirectionToPlayer(Player player) {
        // Get the vector in between this entity and the player
        Vector2f dir = getDirectionVector(player);

        // Normalize the direction vector
        dir = dir.normalise();

        return new Vector2f(dir.x * owner.getWalkingSpeed(),
                dir.y * owner.getWalkingSpeed());
    }

    private void facePlayer(Player player) {
        // Get the vector from this entity to the player
        Vector2f dir = getDirectionToPlayer(player);

        // Find the angle of the direction vector
        float angle = (float) Math.toRadians(Math.atan(dir.y / dir.x));

        if (angle < 0) {
            angle = (float) (Math.PI - angle);
        }

        if (owner.getY() < player.getY()) {
            if (angle > -Math.PI / 4 && angle <= Math.PI / 4) {
                owner.setDirection(Direction.EAST);
            } else if (angle > Math.PI / 4 && angle <= 3 * Math.PI / 4) {
                owner.setDirection(Direction.NORTH);
            } else if (angle > 3 * Math.PI / 4 && angle <= 5 * Math.PI / 4) {
                owner.setDirection(Direction.WEST);
            } else if (angle > 5 * Math.PI / 4 && angle < 7 * Math.PI / 4) {
                owner.setDirection(Direction.SOUTH);
            }
        } else {
            if (angle > -Math.PI / 4 && angle <= Math.PI / 4) {
                owner.setDirection(Direction.WEST);
            } else if (angle > Math.PI / 4 && angle <= 3 * Math.PI / 4) {
                owner.setDirection(Direction.NORTH);
            } else if (angle > 3 * Math.PI / 4 && angle <= 5 * Math.PI / 4) {
                owner.setDirection(Direction.EAST);
            } else if (angle > 5 * Math.PI / 4 && angle < 7 * Math.PI / 4) {
                owner.setDirection(Direction.SOUTH);
            }
        }
    }

    private Vector2f getDirectionVector(Player player) {
        Vector2f dir = new Vector2f(player.getX() - owner.getX(),
                player.getY() - owner.getY());
        return dir;
    }
}
