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

package com.ambenavente.origins.gameplay.world.level.generator;

import com.ambenavente.origins.gameplay.world.level.Tile;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/11/14
 */
public class DrunkenWalkGenerator {

    private static Random rand = new Random();

    public static Tile[][] generateDungeon(int width,
                                           int height,
                                           int groundId,
                                           int wallId,
                                           int tileSheetId) {

        Tile[][] tiles = initTiles(width, height, wallId, tileSheetId);
        List<Room> rooms = new ArrayList<Room>();

        addRooms(width, height, rooms);
        addCorridors(width, height, groundId, tileSheetId, rooms, tiles);

        for (Room r : rooms) {
            for (int y = r.getY(); y < r.getY() + r.getHeight(); y++) {
                for (int x = r.getX(); x < r.getX() + r.getWidth(); x++) {
                    tiles[y][x] = new Tile(groundId, tileSheetId);
                }
            }
        }

        return tiles;
    }

    private static Tile[][] initTiles(int width,
                                      int height,
                                      int wallId,
                                      int tileSheetId) {
        Tile[][] tiles = new Tile[height][width];
        // Initialize the tiles to be walls
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[y][x] = new Tile(wallId, tileSheetId);
            }
        }
        return tiles;
    }

    private static void addCorridors(int mapWidth,
                                     int mapHeight,
                                     int groundId,
                                     int tileSheetId,
                                     List<Room> rooms,
                                     Tile[][] tiles) {
        List<Room> connectedRooms = cloneList(rooms);
        List<Cell> connectedTiles = new ArrayList<Cell>();

        int connections = rooms.size();
        int index = 0;

        while (!allRoomsConnected(rooms)) {
            Room room = rooms.get(rand.nextInt(rooms.size()));
            Room connectRoom;
            do {
                connectRoom = rooms.get(rand.nextInt(rooms.size()));
            } while (connectRoom != room);

            // The chance that the algorithm will stray off of the straight
            // path
            double sideStepChance = 0.5;

            Vector2f pointA = new Vector2f(
                    rand.nextInt(room.getX()) + room.getWidth(),
                    rand.nextInt(room.getY()) + room.getHeight());

            Vector2f pointB = new Vector2f(
                    rand.nextInt(connectRoom.getX()) + connectRoom.getWidth(),
                    rand.nextInt(connectRoom.getY()) + connectRoom.getHeight());

            while (!pointB.equals(pointA)) {
                if (rand.nextDouble() > sideStepChance) {
                    // Don't side step
                    if (pointB.x != pointA.x)
                        pointB.x += (pointB.x < pointA.x) ? 1 : -1;

                } else if (pointB.y != pointA.y) {
                    pointB.y += (pointB.y < pointA.y) ? 1 : -1;
                }

                if (pointB.x < mapWidth && pointB.y < mapHeight) {
                    tiles[((int) pointB.y)][((int) pointB.x)] =
                            new Tile(groundId, tileSheetId);
                }
            }

            room.setHasConnection(true);
            connectRoom.setHasConnection(true);
        }
    }

    private static boolean allRoomsConnected(List<Room> rooms) {
        for (Room r : rooms) if (!r.getConnected()) return false;
        return true;
    }

    private static Stack<Room> createStack(List<Room> rooms) {
        Stack<Room> result = new Stack<Room>();
        for (Room r : rooms) {
            result.push(r);
        }
        return result;
    }

    private static void addRooms(int width,
                                 int height,
                                 List<Room> rooms) {
        int iterations  = 0;
        int roomCount   = randBetween(Math.max(1, getMaxRooms(width, height) / 5),
                getMaxRooms(width, height));
        int maxArea     = getMaxRoomSize(roomCount);
        int maxLength   = (int)(Math.sqrt(maxArea));
        do {
            boolean ok = true;
            Room room = new Room();

            int rLength = randBetween(maxLength / 2, (maxLength * 2) - (maxLength / 2));
            int rHeight = (maxLength * 2) - rLength;

            room.setX(randBetween(1, width - rLength - 1));
            room.setY(randBetween(1, height - rHeight - 1));
            room.setWidth(rLength);
            room.setHeight(rHeight);

            for (Room r : rooms) {
                ok &= !(r.getBounds().intersects(room.getBounds()));
            }

            if (ok) rooms.add(room);

        } while (++iterations < 10000 && rooms.size() < roomCount);
    }

    private static int getMaxRooms(int width, int height) {
        return (int) ((1 / 480f) * (width * height));
    }

    private static int getMaxRoomSize(int maxRooms) {
        return (int)(22.5 * maxRooms);
    }

    private static List<Room> cloneList(List<Room> list) {
        List<Room> clone = new ArrayList<Room>();
        for (Room o : list) {
            clone.add(o);
        }
        return clone;
    }

    public static Tile[][] badGenerateDungeon(int mapWidth,
                                               int mapHeight,
                                               int groundId,
                                               int wallId,
                                               int tileSheetId) {

        Tile[][] tiles = new Tile[mapHeight][mapWidth];
        List<Room> rooms = new ArrayList<Room>();

        initTiles(mapWidth, mapHeight, wallId, tileSheetId);

        int roomsMin = (int) (mapWidth * mapHeight) / 300;
        int roomsMax = (int) (mapWidth * mapHeight) / 150;
        int roomCount = 30;

        int widthRoot = (int) Math.sqrt(mapWidth * 2);
        int heightRoot = (int) Math.sqrt(mapHeight * 2);

        int minimumWidth    = (int) 4;
        int maximumWidth    = (int) 8;
        int minimumHeight   = (int) 3;
        int maximumHeight   = (int) 10;

        do {
            boolean ok = false;
            Room room = new Room();

            room.setX(randBetween(0, mapWidth));
            room.setY(randBetween(0, mapHeight));
            room.setWidth(randBetween(minimumWidth, maximumWidth));
            room.setHeight(randBetween(minimumHeight, maximumHeight));

            if (room.getX() < 0 || room.getX() > mapWidth - room.getWidth() ||
                    room.getY() < 0 || room.getY() > mapHeight - room.getHeight())
                continue;

            ok = true;

            if (rooms.size() > 0) {
                for (Room r : rooms)
                    if (r.getBounds().intersects(room.getBounds()))
                        ok = false;
            }

            if (ok)
                rooms.add(room);

        } while (rooms.size() < roomCount);

        rooms.add(new Room(0, 0, 10, 10));

        List<Room> usableRooms = rooms;
        List<Cell> connectedTiles = new ArrayList<Cell>();
        int connections = roomCount;
        int index = 0;

        for (int i = 0; i < connections - 1; i++) {
            Room room = rooms.get(index);
            usableRooms.remove(room);

            Room connectToRoom = usableRooms.get(rand.nextInt(usableRooms.size()));

            double sideStepChance = 0.4;

            Vector2f pointA = new Vector2f(randBetween(room.getBounds().getX(), room.getBounds().getX() + room.getWidth()),
                    randBetween(room.getBounds().getY(), room.getBounds().getY() + room.getHeight()));
            Vector2f pointB = new Vector2f(randBetween(connectToRoom.getBounds().getX(), connectToRoom.getBounds().getX() + connectToRoom.getWidth()),
                    randBetween(connectToRoom.getBounds().getY(), connectToRoom.getBounds().getY() + connectToRoom.getHeight()));

            while (!pointB.equals(pointA)) {
                if (rand.nextDouble() < sideStepChance) {
                    if (pointB.x != pointA.x) {
                        if (pointB.x < pointA.x)
                            pointB.x++;
                        else
                            pointB.x--;
                    }
                } else if (pointB.y != pointA.y) {
                    if (pointB.y < pointA.y)
                        pointB.y++;
                    else
                        pointB.y--;
                }

                if (pointB.x < mapWidth && pointB.y < mapHeight) {
                    tiles[((int) pointB.y)][((int) pointB.x)]
                            = new Tile(groundId, tileSheetId);
                }
            }
        }

        for (Room r : rooms) {
            for (int y = r.getY(); y < r.getHeight() + r.getY(); y++) {
                for (int x = r.getX(); x < r.getWidth() + r.getX(); x++) {

                    if ((x == r.getX() || x == (r.getWidth() + r.getX()) - 1) ||
                            (y == r.getY() || y == (r.getHeight() + (r.getY()) - 1))) {

                    } else {
                        tiles[y][x] = new Tile(groundId, tileSheetId);
                    }
                }
            }
        }

        return tiles;
    }

    private static float randBetween(float y, float v) {
        return randBetween((int) y, (int) v);
    }

    private static int randBetween(int low, int high) {
        return rand.nextInt(high - low) + low;
    }
}
