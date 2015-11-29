package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a way to handle input from the mouse
 */
public final class MouseHandler implements MouseListener {
  private final Map<Integer, Runnable> mouseClicked, mousePressed, mouseReleased, mouseEntered,
      mouseExited;
  private MouseEvent currMouseEvent;

  /**
   * Constructor for a {@link MouseHandler}
   *
   * @param mouseClicked  the {@code Map} for mouse clicks
   * @param mousePressed  the {@code Map} for mouse presses
   * @param mouseReleased the {@code Map} for mouse releases
   * @param mouseEntered  the {@code Map} for mouse enters
   * @param mouseExited   the {@code Map} for mouse exits
   */
  private MouseHandler(Map<Integer, Runnable> mouseClicked, Map<Integer, Runnable> mousePressed,
                       Map<Integer, Runnable> mouseReleased, Map<Integer, Runnable> mouseEntered,
                       Map<Integer, Runnable> mouseExited) {
    this.mouseClicked = mouseClicked;
    this.mousePressed = mousePressed;
    this.mouseReleased = mouseReleased;
    this.mouseEntered = mouseEntered;
    this.mouseExited = mouseExited;
  }

  /**
   * Gets the current mouse event
   *
   * @return the current mouse event
   */
  public MouseEvent getCurrMouseEvent() {
    return this.currMouseEvent;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    this.currMouseEvent = e;
    if (this.mouseClicked.get(e.getButton()) != null) {
      this.mouseClicked.get(e.getButton()).run();
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
    this.currMouseEvent = e;
    if (this.mousePressed.get(e.getButton()) != null) {
      this.mousePressed.get(e.getButton()).run();
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    this.currMouseEvent = e;
    if (this.mouseReleased.get(e.getButton()) != null) {
      this.mouseReleased.get(e.getButton()).run();
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    this.currMouseEvent = e;
    if (this.mouseEntered.get(e.getButton()) != null) {
      this.mouseEntered.get(e.getButton()).run();
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {
    this.currMouseEvent = e;
    if (this.mouseExited.get(e.getButton()) != null) {
      this.mouseExited.get(e.getButton()).run();
    }
  }

  /**
   * Represents a builder for this mouse handler
   */
  public static final class Builder {
    private final Map<Integer, Runnable> mouseClicked = new HashMap<>();
    private final Map<Integer, Runnable> mousePressed = new HashMap<>();
    private final Map<Integer, Runnable> mouseReleased = new HashMap<>();
    private final Map<Integer, Runnable> mouseEntered = new HashMap<>();
    private final Map<Integer, Runnable> mouseExited = new HashMap<>();

    /**
     * Constructs an actual {@link MouseHandler}, given the {@code Map}s for each type of mouse
     * handler
     *
     * @return the mouse handler
     */
    public MouseHandler build() {
      return new MouseHandler(this.mouseClicked, this.mousePressed, this.mouseReleased,
          this.mouseEntered, this.mouseExited);
    }

    /**
     * Adds a clicked mouse {@link Runnable} for a specific {@code mouse button}
     *
     * @param button   the button code for the key
     * @param runnable the {@link Runnable} that will be added
     * @return This builder
     */
    public Builder addMouseClicked(int button, Runnable runnable) {
      this.mouseClicked.put(button, runnable);
      return this;
    }

    /**
     * Adds a pressed mouse {@link Runnable} for a specific {@code mouse button}
     *
     * @param button   the button code for the key
     * @param runnable the {@link Runnable} that will be added
     * @return This builder
     */
    public Builder addMousePressed(int button, Runnable runnable) {
      this.mousePressed.put(button, runnable);
      return this;
    }

    /**
     * Adds a released mouse {@link Runnable} for a specific {@code mouse button}
     *
     * @param button   the button code for the key
     * @param runnable the {@link Runnable} that will be added
     * @return This builder
     */
    public Builder addMouseReleased(int button, Runnable runnable) {
      this.mouseReleased.put(button, runnable);
      return this;
    }

    /**
     * Adds a entered mouse {@link Runnable} for a specific {@code mouse button}
     *
     * @param button   the button code for the key
     * @param runnable the {@link Runnable} that will be added
     * @return This builder
     */
    public Builder addMouseEntered(int button, Runnable runnable) {
      this.mouseEntered.put(button, runnable);
      return this;
    }

    /**
     * Adds a exited mouse {@link Runnable} for a specific {@code mouse button}
     *
     * @param button   the button code for the key
     * @param runnable the {@link Runnable} that will be added
     * @return This builder
     */
    public Builder addMouseExited(int button, Runnable runnable) {
      this.mouseExited.put(button, runnable);
      return this;
    }
  }
}
