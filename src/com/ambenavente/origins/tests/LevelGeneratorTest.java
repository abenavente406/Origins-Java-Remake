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

package com.ambenavente.origins.tests;

import com.ambenavente.origins.gameplay.world.level.Tile;
import com.ambenavente.origins.gameplay.world.level.TiledLayer;
import com.ambenavente.origins.gameplay.world.level.TiledMap;
import com.ambenavente.origins.gameplay.world.level.generator.EnumGenerationAlgorithm;
import com.ambenavente.origins.gameplay.world.level.generator.TiledLayerGenerator;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/11/14
 */
public class LevelGeneratorTest extends JFrame {

    private static final int DEFAULT_WIDTH  = 120;
    private static final int DEFAULT_HEIGHT = 80;
    private static final int TILE_SIZE      = 5;

    public LevelGeneratorTest(int type) {
        EnumGenerationAlgorithm algorithm =
                (type == 1)
                ? EnumGenerationAlgorithm.RANDOM
                : EnumGenerationAlgorithm.DRUNKEN;
        add(new LevelDrawer(algorithm));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
}

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the algorithm:");
        System.out.println("  >> 1 - Random");
        System.out.println("  >> 2 - Dungeon");
        int type = in.nextInt();
        if (type > 0 && type <= 2) {
            new LevelGeneratorTest(type);
        } else {
            System.out.println("Invalid choice!");
        }
    }

    class LevelDrawer extends Canvas {

        private EnumGenerationAlgorithm algorithm;
        private Tile[][] tiles;

        public LevelDrawer(EnumGenerationAlgorithm algorithm) {
            this.algorithm = algorithm;

            setSize(DEFAULT_WIDTH * TILE_SIZE,
                    DEFAULT_HEIGHT * TILE_SIZE);

            generateTiles(algorithm);

            repaint();
        }

        private void generateTiles(EnumGenerationAlgorithm algorithm) {
            TiledMap map = new TiledMap(DEFAULT_WIDTH,
                                        DEFAULT_HEIGHT,
                                        TILE_SIZE,
                                        TILE_SIZE);
            TiledLayer layer =
                    TiledLayerGenerator.generateLayer(map, 0, 1, 0,
                            algorithm);

            tiles = layer.getTiles();
        }

        @Override
        public void paint(Graphics g) {
            g.clearRect(0,
                    0,
                    DEFAULT_WIDTH * TILE_SIZE,
                    DEFAULT_HEIGHT * TILE_SIZE);
            for (int y = 0; y < tiles.length; y++) {
                for (int x = 0; x < tiles[y].length; x++) {
                    Tile t = tiles[y][x];

                    switch (t.getTileId()) {
                        case 0:
                            g.setColor(Color.white);
                            g.fillRect(x * TILE_SIZE,
                                       y * TILE_SIZE,
                                       TILE_SIZE,
                                       TILE_SIZE);
                            break;
                        case 1:
                        default:
                            g.setColor(Color.black);
                            g.fillRect(x * TILE_SIZE,
                                    y * TILE_SIZE,
                                    TILE_SIZE,
                                    TILE_SIZE);
                            break;
                    }
                }
            }
        }
    }
}
