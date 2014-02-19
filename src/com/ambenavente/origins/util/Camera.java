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

package com.ambenavente.origins.util;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

/**
 * Represents a camera that is used for navigating the game world
 *
 * @author Anthony Benavente
 * @version 2/9/14
 */
public class Camera {

    /**
     * The position of the camera on the screen
     */
    private Vector2f pos;

    /**
     * The maximum point that the position can be
     */
    private Vector2f max;

    /**
     * The minimum point that the position can be
     */
    private Vector2f min;

    /**
     * The width of the camera's view area
     */
    private int viewWidth;

    /**
     * The height of the camera's view area
     */
    private int viewHeight;

    /**
     * The rectangular bounding area of the camera
     */
    private Rectangle bounds;

    /**
     * The zoom setting that the camera sees
     */
    private float zoom = 1.0f;

    /**
     * Creates a default camera with a position at (0,0),
     * a width of 0, and a height of 0
     */
    public Camera() {
        this(0, 0, 0, 0);
    }

    /**
     * Creates a camera with a position at (0, 0) and a
     * specified width and height
     *
     * @param viewWidth  The width of the camera's viewing area in pixels
     * @param viewHeight The height of the camera's viewing area in pixels
     */
    public Camera(int viewWidth, int viewHeight) {
        this(0, 0, viewWidth, viewHeight);
    }

    /**
     * Creates a camera with a specified x coordinate,
     * y coordinate, width of the viewing area, and
     * height of the viewing area
     *
     * @param x          The x position of the camera
     * @param y          The y position of the camera
     * @param viewWidth  The width of the camera's viewing area in pixels
     * @param viewHeight The height of the camera's viewing area in pixels
     */
    public Camera(float x, float y, int viewWidth, int viewHeight) {
        this(new Vector2f(x, y), viewWidth, viewHeight);
    }

    /**
     * Creates a camera with a specified vector position,
     * width of the viewing area, and height of the viewing
     * area
     *
     * @param pos        The position of the camera
     * @param viewWidth  The width of the camera's viewing area in pixels
     * @param viewHeight The height of the camera's viewing area in pixels
     */
    public Camera(Vector2f pos, int viewWidth, int viewHeight) {
        this.pos = pos;
        this.viewWidth = viewWidth;
        this.viewHeight = viewHeight;
        this.max = new Vector2f(Float.MAX_VALUE, Float.MAX_VALUE);
        this.min = new Vector2f(-Float.MAX_VALUE - 1,
                -Float.MAX_VALUE - 1);
        this.bounds = new Rectangle(pos.x, pos.y, viewWidth, viewHeight);
    }

    /**
     * @return The camera's x coordinate from its vector position
     */
    public float getX() {
        return pos.x;
    }

    /**
     * Sets the x coordinate of the camera's x position
     *
     * @param x The new x position of the camera
     */
    public void setX(float x) {
        pos = checkBounds(new Vector2f(x, pos.y));
        updateRect();
    }

    /**
     * @return The camera's y coordinate from its vector position
     */
    public float getY() {
        return pos.y;
    }

    /**
     * Sets the y coordinate of the camera's y position
     *
     * @param y The new y position of the camera
     */
    public void setY(float y) {
        pos = checkBounds(new Vector2f(pos.x, y));
        updateRect();
    }

    /**
     * @return The width of the camera's viewing area in pixels
     */
    public int getViewWidth() {
        return viewWidth;
    }

    /**
     * Sets the width of the camera's viewing area
     *
     * @param viewWidth The width of the camera's viewing area in pixels
     */
    public void setViewWidth(int viewWidth) {
        this.viewWidth = viewWidth;
        updateRect();
    }

    /**
     * @return The height of the camera's viewing area in pixels
     */
    public int getViewHeight() {
        return viewHeight;
    }

    /**
     * Sets the height of the camera's viewing area
     *
     * @param viewHeight The height of the camera's viewing area in pixels
     */
    public void setViewHeight(int viewHeight) {
        this.viewHeight = viewHeight;
        updateRect();
    }

    /**
     * @return The vector representing where the top left point of the camera
     * exists in the game
     */
    public Vector2f getPos() {
        return pos;
    }

    /**
     * Sets the vector position representing the camera's top left point
     *
     * @param pos The new position of the camera
     */
    public void setPos(Vector2f pos) {
        this.pos = checkBounds(pos);
        updateRect();
    }

    /**
     * Makes sure that the camera's bounds is updated with the its position
     * and size
     */
    private void updateRect() {
        bounds.setX(pos.x);
        bounds.setY(pos.y);
        bounds.setWidth(viewWidth);
        bounds.setHeight(viewHeight);
    }

    /**
     * Clamps the point passed in in between 2 Vector points (max and min)
     *
     * @param pos The point to clamp
     * @return The position passed in, but adjusted to the max and min bounds
     */
    private Vector2f checkBounds(Vector2f pos) {
        // Verify the x bounds
        if (pos.x < min.x) pos.x = min.x;
        else if (pos.x > max.x - viewWidth) pos.x = max.x - viewWidth;

        // Verify the y bounds
        if (pos.y < min.y) pos.y = min.y;
        else if (pos.y > max.y - viewHeight) pos.y = max.y - viewHeight;

        return pos;
    }

    /**
     * @return The max point that the camera can be at
     */
    public Vector2f getMax() {
        return max;
    }

    /**
     * Sets the maximum point that the camera's position can be
     *
     * @param max The maximum point the camera's position can be
     */
    public void setMax(Vector2f max) {
        this.max = max;
    }

    /**
     * @return The min point that the camera can be at
     */
    public Vector2f getMin() {
        return min;
    }

    /**
     * Sets the minimum point that the camera can be at
     *
     * @param min The new minimum point that the camera's position can be at
     */
    public void setMin(Vector2f min) {
        this.min = min;
    }

    /**
     * @return The rectangular bounds that represents what the camera can see
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * @return the zoom value of the camera
     */
    public float getZoom() {
        return zoom;
    }

    /**
     * Sets the zoom value of the camera
     *
     * @param zoom The zoom setting that the camera sees
     */
    public void setZoom(float zoom) {
        if (zoom < .05f) zoom = .05f;
        else if (zoom > 15.0f) zoom = 15.0f;

        this.zoom = zoom;
    }

    /**
     * Zooms in the camera
     *
     * @param amount The amount to zoom the camera in
     */
    public void zoomIn(float amount) {
        setZoom(zoom + amount);
    }

    /**
     * Zooms out the camera
     *
     * @param amount The amount to zoom the camera out
     */
    public void zoomOut(float amount) {
        setZoom(zoom - amount);
    }
}
