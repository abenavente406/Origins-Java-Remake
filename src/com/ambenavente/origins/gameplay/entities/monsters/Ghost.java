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

package com.ambenavente.origins.gameplay.entities.monsters;

import com.ambenavente.origins.gameplay.entities.Direction;
import com.ambenavente.origins.gameplay.managers.SpriteSheetManager;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/17/14
 */
public class Ghost extends Monster {

    public Ghost(float x, float y) {
        super(x, y);
        init();
    }

    @Override
    public void init() {
        setMaxHealth(10);
        setTextureDimensions(32, 32);
        setDimensions(28, 21);
        setWalkingSpeed(.93f);

        initImages();

        setName("Ghost");
    }

    private void initImages() {
        Image[] left = new Image[3];
        Image[] right = new Image[3];
        Image[] up = new Image[3];
        Image[] down = new Image[3];

        final int startX = 0;
        final int startY = 0;
        final int horizontalCount = 3;

        SpriteSheet entitySheet = SpriteSheetManager.get(
                SpriteSheetManager.ENTITY_SHEET_ID);

        for (int x = startX; x < startX + horizontalCount; x++) {
            down[x] = entitySheet.getSprite(x, startY);
            left[x] = entitySheet.getSprite(x, startY + 1);
            right[x] = entitySheet.getSprite(x, startY + 2);
            up[x] = entitySheet.getSprite(x, startY + 3);
        }

        initAvatars(up, down, right, left);
        initAnimations(up, down, right, left);
    }

    private void initAvatars(Image[] up,
                             Image[] down,
                             Image[] right,
                             Image[] left) {
        setAvatar(Direction.NORTH, up[1]);
        setAvatar(Direction.SOUTH, down[1]);
        setAvatar(Direction.EAST, right[1]);
        setAvatar(Direction.WEST, left[1]);
    }

    private void initAnimations(Image[] up,
                                Image[] down,
                                Image[] right,
                                Image[] left) {
        setMovingAnimation(Direction.NORTH, new Animation(up, 150));
        setMovingAnimation(Direction.SOUTH, new Animation(down, 150));
        setMovingAnimation(Direction.EAST, new Animation(right, 150));
        setMovingAnimation(Direction.WEST, new Animation(left, 150));
    }
}
