
package cs3500.music.tests;

import cs3500.music.controller.GuiController;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Tests for MusicEditorModelImpl
 */

public class MusicEditorTest {
  @Test(expected = IllegalArgumentException.class)
  public void testCreate() {
    assertEquals("class cs3500.music.view.ConsoleViewImpl",
        GuiController.ViewFactory.create("asda").getClass().toString());
  }
}
