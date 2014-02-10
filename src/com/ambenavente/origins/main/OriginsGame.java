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

package com.ambenavente.origins.main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/9/14
 */
public class OriginsGame extends StateBasedGame {

    /**
     * If the game is in debug mode, there will several features
     * that will be enabled.
     */
    private boolean debug = false;

    public OriginsGame(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer container)
            throws SlickException {

    }

    /**
     * @return If the game is in debug mode this will return true, otherwise,
     * it will return false
     */
    public boolean getDebug() {
        return debug;
    }

    /**
     * Sets the game to our out of debug mode
     *
     * @param debug True puts the game in debug mode while false
     *              takes it out
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
