package cs3500.music.tests;

import cs3500.music.controller.MouseHandler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.InputEvent;

import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;

/**
 * Tests the {@link MouseHandler} class
 */
public class MouseHandlerTest {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @After
  public void cleanUpStreams() {
    System.setOut(null);
    System.setErr(null);
  }

  MouseEvent leftClick = new MouseEvent(new Component() {
  }, 0, 10, InputEvent.BUTTON1_MASK, 0, 0, MouseEvent.BUTTON1, false, MouseEvent.BUTTON1);

  Runnable left = () -> System.out.print("left clicked");

  @Test
  public void testClick() {
    MouseHandler mh = new MouseHandler.Builder().addMouseClicked(MouseEvent.BUTTON1, left).build();
    mh.mouseClicked(leftClick);
    assertEquals("left clicked", outContent.toString());
  }

  @Test
  public void testPressed() {
    MouseHandler mh = new MouseHandler.Builder().addMousePressed(MouseEvent.BUTTON1, left).build();
    mh.mousePressed(leftClick);
    assertEquals("left clicked", outContent.toString());
  }

  @Test
  public void testEntered() {
    MouseHandler mh = new MouseHandler.Builder().addMouseEntered(MouseEvent.BUTTON1, left).build();
    mh.mouseEntered(leftClick);
    assertEquals("left clicked", outContent.toString());
  }

  @Test
  public void testReleased() {
    MouseHandler mh = new MouseHandler.Builder().addMouseReleased(MouseEvent.BUTTON1, left).build();
    mh.mouseReleased(leftClick);
    assertEquals("left clicked", outContent.toString());
  }

  @Test
  public void testExited() {
    MouseHandler mh = new MouseHandler.Builder().addMouseExited(MouseEvent.BUTTON1, left).build();
    mh.mouseExited(leftClick);
    assertEquals("left clicked", outContent.toString());
  }
}
