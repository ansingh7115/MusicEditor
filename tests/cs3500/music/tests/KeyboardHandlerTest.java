package cs3500.music.tests;

import cs3500.music.controller.KeyboardHandler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;

/**
 * Tests the {@link KeyboardHandler} class
 */
public class KeyboardHandlerTest {
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

  KeyEvent upArrow = new KeyEvent(new Component() {}, 0, 10,
      InputEvent.BUTTON1_MASK, KeyEvent.VK_UP, ' ', 0);

  KeyEvent downArrow = new KeyEvent(new Component() {}, 0, 10,
      InputEvent.BUTTON1_MASK, KeyEvent.VK_DOWN, ' ', 0);

  KeyEvent leftArrow = new KeyEvent(new Component() {}, 0, 10,
      InputEvent.BUTTON1_MASK, KeyEvent.VK_LEFT, ' ', 0);

  KeyEvent rightArrow = new KeyEvent(new Component() {}, 0, 10,
      InputEvent.BUTTON1_MASK, KeyEvent.VK_RIGHT, ' ', 0);

  KeyEvent homeKey = new KeyEvent(new Component() {}, 0, 10,
      InputEvent.BUTTON1_MASK, KeyEvent.VK_HOME, ' ', 0);

  KeyEvent endKey = new KeyEvent(new Component() {}, 0, 10,
      InputEvent.BUTTON1_MASK, KeyEvent.VK_END, ' ', 0);

  KeyEvent r = new KeyEvent(new Component() {}, 0, 10,
      InputEvent.BUTTON1_MASK, KeyEvent.VK_R, ' ', 0);

  KeyEvent e = new KeyEvent(new Component() {}, 0, 10,
      InputEvent.BUTTON1_MASK, KeyEvent.VK_E, ' ', 0);

  private Runnable arrowUp = () -> System.out.print("moved up");

  private Runnable arrowDown = () -> System.out.print("moved down");

  private Runnable arrowLeft = () -> System.out.print("moved left");

  private Runnable arrowRight = () -> System.out.print("moved right");

  private Runnable home = () -> System.out.print("moved home");

  private Runnable end = () -> System.out.print("moved end");

  private Runnable rKey = () -> System.out.print("r pressed");

  private Runnable eKey = () -> System.out.print("e pressed");

  @Test
  public void testUpArrow() {
    KeyboardHandler kb = new KeyboardHandler.Builder()
        .addKeyPressed(KeyEvent.VK_UP, arrowUp).build();
    kb.keyPressed(upArrow);
    assertEquals("moved up", outContent.toString());
  }

  @Test
  public void testDownArrow() {
    KeyboardHandler kb = new KeyboardHandler.Builder()
        .addKeyPressed(KeyEvent.VK_DOWN, arrowDown).build();
    kb.keyPressed(downArrow);
    assertEquals("moved down", outContent.toString());
  }

  @Test
  public void testLeftArrow() {
    KeyboardHandler kb = new KeyboardHandler.Builder()
        .addKeyPressed(KeyEvent.VK_LEFT, arrowLeft).build();
    kb.keyPressed(leftArrow);
    assertEquals("moved left", outContent.toString());
  }

  @Test
  public void testRightArrow() {
    KeyboardHandler kb = new KeyboardHandler.Builder()
        .addKeyPressed(KeyEvent.VK_RIGHT, arrowRight).build();
    kb.keyPressed(rightArrow);
    assertEquals("moved right", outContent.toString());
  }

  @Test
  public void testHome() {
    KeyboardHandler kb = new KeyboardHandler.Builder()
        .addKeyPressed(KeyEvent.VK_HOME, home).build();
    kb.keyPressed(homeKey);
    assertEquals("moved home", outContent.toString());
  }

  @Test
  public void testEnd() {
    KeyboardHandler kb = new KeyboardHandler.Builder().addKeyPressed(KeyEvent.VK_END, end).build();
    kb.keyPressed(endKey);
    assertEquals("moved end", outContent.toString());
  }

  @Test
  public void testR() {
    KeyboardHandler kb = new KeyboardHandler.Builder().addKeyPressed(KeyEvent.VK_R, rKey).build();
    kb.keyPressed(r);
    assertEquals("r pressed", outContent.toString());
  }

  @Test
  public void testE() {
    KeyboardHandler kb = new KeyboardHandler.Builder().addKeyPressed(KeyEvent.VK_E, eKey).build();
    kb.keyPressed(e);
    assertEquals("e pressed", outContent.toString());
  }

  @Test
  public void testTyped() {
    KeyboardHandler kb = new KeyboardHandler.Builder().addKeyTyped(KeyEvent.VK_E, eKey).build();
    kb.keyTyped(e);
    assertEquals("e pressed", outContent.toString());
  }

  @Test
  public void testReleased() {
    KeyboardHandler kb = new KeyboardHandler.Builder().addKeyReleased(KeyEvent.VK_E, eKey).build();
    kb.keyReleased(e);
    assertEquals("e pressed", outContent.toString());
  }
}
