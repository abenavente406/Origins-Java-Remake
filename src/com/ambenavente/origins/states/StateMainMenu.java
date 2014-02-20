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

import com.ambenavente.origins.ui.ControlManager;
import com.ambenavente.origins.ui.Label;
import com.ambenavente.origins.ui.LinkLabel;
import com.ambenavente.origins.ui.events.ActionArgs;
import com.ambenavente.origins.ui.events.ActionDoer;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/9/14
 */
public class StateMainMenu extends StateBase {

    private Animation background;
    private Image title;
    private ControlManager manager;

    public StateMainMenu(StateBasedGame parent) {
        super(parent);
    }

    @Override
    public void init(GameContainer container,
                     StateBasedGame game) throws SlickException {
        initBackground();
        initTitle();
        initControls(container);
    }

    private void initControls(GameContainer container) {
        final int marginBottom = 25;

        manager = new ControlManager();

        Vector2f startPos = new Vector2f(container.getWidth() / 2, 200);

        LinkLabel lblNewGame = new LinkLabel("lblNewGame", "New Game");
        lblNewGame.setWidth(container.getDefaultFont().getWidth(lblNewGame.getText()));
        lblNewGame.setHeight(container.getDefaultFont().getHeight(lblNewGame.getText()));
        lblNewGame.setPos(startPos.copy().add(new Vector2f(-lblNewGame.getWidth() / 2, 0)));
        lblNewGame.setActionDoer(new ActionDoer() {
            @Override
            public void doAction(ActionArgs e) {
                getParent().enterState(EnumState.GAMEPLAY,
                        new FadeOutTransition(), new FadeInTransition());
            }
        });


        startPos.y += lblNewGame.getHeight() + marginBottom;

        LinkLabel lblContinue = new LinkLabel("lblContinue", "Continue");
        lblContinue.setWidth(container.getDefaultFont().getWidth(lblContinue.getText()));
        lblContinue.setHeight(container.getDefaultFont().getHeight(lblContinue.getText()));
        lblContinue.setPos(startPos.copy().add(new Vector2f(-lblContinue.getWidth() / 2, 0)));
        lblContinue.setActionDoer(new ActionDoer() {
            @Override
            public void doAction(ActionArgs e) {
                // TODO: Continue
            }
        });

        startPos.y += lblNewGame.getHeight() + marginBottom;

        LinkLabel lblLoadGame = new LinkLabel("lblLoadGame", "Load Game");
        lblLoadGame.setWidth(container.getDefaultFont().getWidth(lblLoadGame.getText()));
        lblLoadGame.setHeight(container.getDefaultFont().getHeight(lblLoadGame.getText()));
        lblLoadGame.setPos(startPos.copy().add(new Vector2f(-lblLoadGame.getWidth() / 2, 0)));
        lblLoadGame.setActionDoer(new ActionDoer() {
            @Override
            public void doAction(ActionArgs e) {
                // TODO: Enter the load game state
            }
        });

        startPos.y += lblNewGame.getHeight() + marginBottom;

        LinkLabel lblQuit = new LinkLabel("lblQuit", "Quit");
        lblQuit.setWidth(container.getDefaultFont().getWidth(lblQuit.getText()));
        lblQuit.setHeight(container.getDefaultFont().getHeight(lblQuit.getText()));
        lblQuit.setPos(startPos.copy().add(new Vector2f(-lblQuit.getWidth() / 2, 0)));
        lblQuit.setActionDoer(new ActionDoer() {
            @Override
            public void doAction(ActionArgs e) {
                getParent().enterState(EnumState.EXIT);
            }
        });

        manager.add(lblNewGame);
        manager.add(lblContinue);
        manager.add(lblLoadGame);
        manager.add(lblQuit);
    }

    private void initTitle() throws SlickException {
        title = new Image("res/images/title.png");
    }

    private void initBackground() throws SlickException {
        Image[] images = new Image[8];
        for (int i = 0; i < images.length; i++) {
            images[i] = new Image("res/images/waterfall/water" + i + ".png");
        }
        background = new Animation(images, 100);
    }

    @Override
    public void render(GameContainer container,
                       StateBasedGame game,
                       Graphics g) throws SlickException {
        background.draw(0, 0);
        drawTitle(container, g);
        manager.render(g);
        super.render(container, game, g);
    }

    private void drawTitle(GameContainer container,
                           Graphics g) {
        int centeredX = container.getWidth() / 2 - title.getWidth() / 2;
        g.drawImage(title, centeredX, 50);
    }

    @Override
    public void update(GameContainer container,
                       StateBasedGame game,
                       int delta) throws SlickException {
        manager.update(container.getInput(), delta);
    }

    @Override
    public int getID() {
        return EnumState.MENU.getId();
    }

}
