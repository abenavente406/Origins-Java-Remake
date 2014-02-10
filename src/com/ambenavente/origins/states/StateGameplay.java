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

package com.ambenavente.origins.states;

import com.ambenavente.origins.util.Camera;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/9/14
 */
public class StateGameplay extends BasicGameState {

    @Override
    public void init(GameContainer container,
                     StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
    public void render(GameContainer container,
                       StateBasedGame stateBasedGame,
                       Graphics graphics) throws SlickException {

    }

    @Override
    public void update(GameContainer container,
                       StateBasedGame stateBasedGame,
                       int i) throws SlickException {

    }

    @Override
    public int getID() {
        return EnumState.GAMEPLAY.getId();
    }
}
