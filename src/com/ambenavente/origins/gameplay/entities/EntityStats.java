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

package com.ambenavente.origins.gameplay.entities;

/**
 * This class is used by the Entity class as a method of keeping track of how
 * much the player's character and other entities have become more powerful.
 * The higher each of the levels are in this class, the more powerful the
 * entity will be
 *
 * @author Anthony Benavente
 * @version 2/17/14
 */
public class EntityStats {

    private static final int MAX_LEVEL = 100;

    /**
     * The amount of xp points that this entity has
     */
    private int experience;

    /**
     * The actual level of the entity.  This goes up based on the amount of
     * hp that the entity has
     */
    private int level;

    /**
     * The level of strength.  This pertains to how much damage
     * an entity can do
     */
    private int strength;

    /**
     *  The level of speed.  This pertains to how long it takes for the
     *  entity's cool down rate after attacking
     */
    private int speed;

    /**
     * The level of constitution.  This pertains to the likelihood that the
     * entity will land a critical hit
     */
    private int constitution;

    /**
     * The level of athleticism.  This pertains to how fast the entity
     * recovers its stamina
     */
    private int athleticism;

    public EntityStats() {
        this.strength = 1;
        this.speed = 1;
        this.constitution = 1;
        this.athleticism = 1;
        this.level = 1;
        this.experience = 0;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = clampToMax(strength);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = clampToMax(speed);
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = clampToMax(constitution);
    }

    public int getAthleticism() {
        return athleticism;
    }

    public void setAthleticism(int athleticism) {
        this.athleticism = clampToMax(athleticism);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = clampToMax(level);
    }

    public int getExperience() {
        return experience;
    }

    public void addExperience(int amount) {
        experience += amount;

        // TODO: Check for level up
    }

    public int clampToMax(int level) {
        return Math.min(level, MAX_LEVEL);
    }
}
