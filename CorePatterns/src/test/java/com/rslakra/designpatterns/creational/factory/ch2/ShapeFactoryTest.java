package com.rslakra.designpatterns.creational.factory.ch2;

import com.rslakra.designpatterns.bos.Shape;
import com.rslakra.designpatterns.bos.shapes.Circle;
import com.rslakra.designpatterns.bos.shapes.Rectangle;
import com.rslakra.designpatterns.bos.shapes.Square;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ShapeFactoryTest {

    @Test
    void createShape_returnsCircle() {
        Shape shape = ShapeFactory.createShape(ShapeType.CIRCLE);
        assertInstanceOf(Circle.class, shape);
    }

    @Test
    void createShape_returnsRectangle() {
        Shape shape = ShapeFactory.createShape(ShapeType.RECTANGLE);
        assertInstanceOf(Rectangle.class, shape);
    }

    @Test
    void createShape_returnsSquare() {
        Shape shape = ShapeFactory.createShape(ShapeType.SQUARE);
        assertInstanceOf(Square.class, shape);
    }

    @Test
    void createShape_nullShapeType_throws() {
        assertThrows(NullPointerException.class, () -> ShapeFactory.createShape(null));
    }
}
