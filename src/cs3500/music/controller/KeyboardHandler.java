package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a way to handle input from the keyboard
 */
public final class KeyboardHandler implements KeyListener {
  private final Map<Integer, Runnable> typed, pressed, released;

  /**
   * Constructor for a {@link KeyboardHandler}
   *
   * @param typed    the {@code Map} for typed keys
   * @param pressed  the {@code Map} for pressed keys
   * @param released the {@code Map} for released keys
   */
  private KeyboardHandler(Map<Integer, Runnable> typed, Map<Integer, Runnable> pressed,
                          Map<Integer, Runnable> released) {
    this.typed = typed;
    this.pressed = pressed;
    this.released = released;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    if (this.typed.get(e.getKeyCode()) != null) {
      this.typed.get(e.getKeyCode()).run();
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (this.pressed.get(e.getKeyCode()) != null) {
      this.pressed.get(e.getKeyCode()).run();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (this.released.get(e.getKeyCode()) != null) {
      this.released.get(e.getKeyCode()).run();
    }
  }

  /**
   * Represents a builder for this keyboard handler
   */
  public static final class Builder {
    private final Map<Integer, Runnable> typed = new HashMap<>();
    private final Map<Integer, Runnable> pressed = new HashMap<>();
    private final Map<Integer, Runnable> released = new HashMap<>();

    /**
     * Constructs an actual {@link KeyboardHandler}, given the {@code Map}s for each type of key
     * handler
     *
     * @return the keyboard handler
     */
    public KeyboardHandler build() {
      return new KeyboardHandler(this.typed, this.pressed, this.released);
    }

    /**
     * Adds a typed key {@link Runnable} for a specific {@code keyCode}
     *
     * @param keyCode  the key code for the key
     * @param runnable the {@link Runnable} that will be added
     * @return This builder
     */
    public Builder addKeyTyped(int keyCode, Runnable runnable) {
      this.typed.put(keyCode, runnable);
      return this;
    }

    /**
     * Adds a pressed key {@link Runnable} for a specific {@code keyCode}
     *
     * @param keyCode  the key code for the key
     * @param runnable the {@link Runnable} that will be added
     * @return This builder
     */
    public Builder addKeyPressed(int keyCode, Runnable runnable) {
      this.pressed.put(keyCode, runnable);
      return this;
    }

    /**
     * Adds a released key {@link Runnable} for a specific {@code keyCode}
     *
     * @param keyCode  the key code for the key
     * @param runnable the {@link Runnable} that will be added
     * @return This builder
     */
    public Builder addKeyReleased(int keyCode, Runnable runnable) {
      this.released.put(keyCode, runnable);
      return this;
    }
  }
}
