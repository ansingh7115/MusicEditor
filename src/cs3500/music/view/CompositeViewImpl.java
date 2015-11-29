package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.controller.MouseHandler;
import cs3500.music.view.SwingView.GuiViewImpl;

/**
 * Represents a composite view between {@link GuiViewImpl} and {@link MidiViewImpl}
 */
public final class CompositeViewImpl implements GuiView {
  private final GuiView gui;
  private final MusicEditorView midi;
  private ViewModel vm;
  private boolean isPaused = false;

  /**
   * Constructor for a {@link CompositeViewImpl}
   * @param gui the {@link GuiViewImpl} to render
   * @param midi the {@link MidiViewImpl} to render
   */
  public CompositeViewImpl(GuiViewImpl gui, MidiViewImpl midi) {
    this.gui = gui;
    this.midi = midi;
  }

  @Override
  public void render(ViewModel vm) {
    this.vm = vm;
    this.gui.render(this.vm);
  }

  /**
   * Delegates the action for the up arrow key to the {@link GuiViewImpl}
   */
  public void arrowUp() {
    this.gui.arrowUp();
  }

  /**
   * Delegates the action for the down arrow key to the {@link GuiViewImpl}
   */
  public void arrowDown() {
    this.gui.arrowDown();
  }

  /**
   * Delegates the action for the right arrow key to the {@link GuiViewImpl}
   */
  public void arrowRight() {
    this.gui.arrowRight();
  }

  /**
   * Delegates the action for the left arrow key to the {@link GuiViewImpl}
   */
  public void arrowLeft() {
    this.gui.arrowLeft();
  }

  /**
   * Delegates the action for the home key to the {@link GuiViewImpl}
   */
  public void jumpToBeginning() {
    this.gui.jumpToBeginning();
  }

  /**
   * Delegates the action for the end key to the {@link GuiViewImpl}
   */
  public void jumpToEnd() {
    this.gui.jumpToEnd();
  }

  /**
   * Updates the {@link CompositeViewImpl} for every beat in the piece of music
   *
   * @param currBeat the current beat of the piece of music
   */
  public void update(int currBeat) {
    if (!this.isPaused) {
      this.gui.update(currBeat);
      this.midi.render(this.vm);
    }
  }

  /**
   * Switches between a paused and non-paused state for the piece of music
   */
  public void pause() {
    this.isPaused = !this.isPaused;
  }

  /**
   * Adds a {@link KeyboardHandler} to this {@link CompositeViewImpl}
   * @param k the {@link KeyboardHandler} to add
   */
  public void addKeyListener(KeyListener k) {
    this.gui.addKeyListener(k);
  }

  /**
   * Adds a {@link MouseHandler} to this {@link CompositeViewImpl}
   * @param m the {@link MouseHandler} to add
   */
  public void addMouseListener(MouseListener m) {
    this.gui.addMouseListener(m);
  }

  /**
   * Removes a {@link MouseHandler} from this {@link CompositeViewImpl}
   * @param m the {@link MouseHandler} to remove
   */
  public void removeMouseListener(MouseListener m) {
    this.gui.removeMouseListener(m);
  }
}
