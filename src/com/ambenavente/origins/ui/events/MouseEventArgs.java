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

package com.ambenavente.origins.ui.events;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/19/14
 */
public class MouseEventArgs extends ActionArgs {

    private Vector2f pos;
    private boolean leftClick;
    private boolean rightClick;
    private boolean middleClick;

    public MouseEventArgs(Input input) {
        pos = new Vector2f(input.getMouseX(), input.getMouseY());
        leftClick = input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);
        rightClick = input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON);
        middleClick = input.isMouseButtonDown(Input.MOUSE_MIDDLE_BUTTON);
    }

    public Vector2f getPos() {
        return pos;
    }

    public boolean isLeftClick() {
        return leftClick;
    }

    public boolean isRightClick() {
        return rightClick;
    }

    public boolean isMiddleClick() {
        return middleClick;
    }
}
