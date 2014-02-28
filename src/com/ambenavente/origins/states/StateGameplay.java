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

import com.ambenavente.origins.gameplay.managers.SpriteSheetManager;
import com.ambenavente.origins.gameplay.world.World;
import com.ambenavente.origins.util.Camera;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/9/14
 */
public class StateGameplay extends StateBase {

    enum GameplayState {
        PLAYING,
        PAUSED;
    }

    private Camera camera;

    public StateGameplay(StateBasedGame parent) {
        super(parent);
    }

    @Override
    public void init(GameContainer container,
                     StateBasedGame stateBasedGame) throws SlickException {
        SpriteSheetManager.init();
        World.init();

        camera = new Camera(0,
                0,
                container.getWidth(),
                container.getHeight());

        camera.setMax(new Vector2f(World.getActiveMap().getRealWidth(),
                World.getActiveMap().getRealHeight()));
        camera.setMin(new Vector2f(0, 0));
    }

    @Override
    public void render(GameContainer container,
                       StateBasedGame stateBasedGame,
                       Graphics g) throws SlickException {
        super.render(container, stateBasedGame, g);

        // TODO: Draw the game
        g.pushTransform();
        g.translate(-camera.getX(), -camera.getY());

        World.render(camera, g);

        g.popTransform();
    }

    @Override
    public void update(GameContainer container,
                       StateBasedGame stateBasedGame,
                       int i) throws SlickException {
        World.update(container, i);
        camera.setX(World.getPlayer().getX() - camera.getViewWidth() / 2);
        camera.setY(World.getPlayer().getY() - camera.getViewHeight() / 2);
    }

    @Override
    public int getID() {
        return EnumState.GAMEPLAY.getId();
    }
}
