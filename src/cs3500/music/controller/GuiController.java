package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.model.Playable;
import cs3500.music.view.CompositeViewImpl;
import cs3500.music.view.GuiView;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.SwingView.GuiViewImpl;
import cs3500.music.view.ViewModel;

/**
 * Represents a GUI Controller
 */
public final class GuiController {
  private MusicEditorModel model;
  private GuiView view;
  private final Timer timer = new Timer();
  private int currBeat;
  private boolean isPaused = false, isRFlagged = false, isEFlagged = false, isEditing = false,
      isAdding = false;
  private Playable currentEditPlayable;
  private Playable currentAddPlayable;

  /**
   * A Runnable lambda for pausing and playing a piece of music
   */
  private final Runnable playPause = () -> {
    this.view.pause();
    this.isPaused = !this.isPaused;
  };

  /**
   * A Runnable lambda for scrolling up on a piece of music using the arrow key
   */
  private final Runnable arrowUp = () -> this.view.arrowUp();

  /**
   * A Runnable lambda for scrolling down on a piece of music using the arrow key
   */
  private final Runnable arrowDown = () -> this.view.arrowDown();

  /**
   * A Runnable lambda for scrolling to the right on a piece of music using the arrow key
   */
  private final Runnable arrowRight = () -> this.view.arrowRight();

  /**
   * A Runnable lambda for scrolling to the left on a piece of music using the arrow key
   */
  private final Runnable arrowLeft = () -> this.view.arrowLeft();

  /**
   * A Runnable lambda for jumping to the beginning of a piece of music using the Home key
   */
  private final Runnable jumpToBeginning = () -> this.view.jumpToBeginning();

  /**
   * A Runnable lambda for jumping to the end of a piece of music using the End key
   */
  private final Runnable jumpToEnd = () -> this.view.jumpToEnd();

  /**
   * A Runnable lambda for setting a flag whether the "R" key is pressed (for removing mode)
   */
  private final Runnable rFlag = () -> {
    this.isEFlagged = false;
    this.isRFlagged = !this.isRFlagged;
    if (this.isRFlagged) {
      this.view.removeMouseListener(this.mouseHandler);
      this.view.removeMouseListener(this.mouseHandlerEditMode);
      this.view.addMouseListener(this.mouseHandlerRemoveMode);
    } else {
      this.view.removeMouseListener(this.mouseHandlerRemoveMode);
      this.view.addMouseListener(this.mouseHandler);
    }
  };

  /**
   * A Runnable lambda for setting a flag whether the "E" key is pressed (for editing note)
   */
  private final Runnable eFlag = () -> {
    this.isRFlagged = false;
    this.isEFlagged = !this.isEFlagged;
    if (this.isEFlagged) {
      this.view.removeMouseListener(this.mouseHandlerRemoveMode);
      this.view.removeMouseListener(this.mouseHandler);
      this.view.addMouseListener(this.mouseHandlerEditMode);
    } else {
      this.view.removeMouseListener(this.mouseHandlerEditMode);
      this.view.addMouseListener(this.mouseHandler);
    }
  };

  /**
   * A Runnable lambda for adding a playable to a piece of music
   */
  private final Runnable addPlayable = () -> {
    this.isAdding = !this.isAdding;
    int clickedYCoordinate = this.mouseHandler.getCurrMouseEvent().getY();
    int start = (this.mouseHandler.getCurrMouseEvent().getX() / GuiViewImpl.BLOCK) - 1;
    int playableVal = -clickedYCoordinate / GuiViewImpl.BLOCK +
        this.model.getHighestPlayableVal() + 1;
    int xPos = this.mouseHandler.getCurrMouseEvent().getX() / GuiViewImpl.BLOCK;
    if (clickedYCoordinate > GuiViewImpl.BLOCK
        && xPos <= this.model.getLastBeat()
        && clickedYCoordinate <= (this.model.getHighestPlayableVal()
        - this.model.getLowestPlayableVal() + 2) * GuiViewImpl.BLOCK
        && this.currBeat < this.model.getLastBeat()) {
      if (this.isAdding) {
        this.currentAddPlayable = new Note(start, 100, playableVal / 12,
            Pitch.toPitch(playableVal % 12), 1, 100);
      } else if (xPos - this.currentAddPlayable.getStartTime() > 0) {
        //this.model.addPlayable(new Note(10, 1, 5, Pitch.C, 1, 100));
        this.model.addPlayable(new Note(this.currentAddPlayable.getStartTime(),
            xPos - this.currentAddPlayable.getStartTime(),
            this.currentAddPlayable.getPlayableVal() / 12,
            Pitch.toPitch(this.currentAddPlayable.getPlayableVal() % 12),
            this.currentAddPlayable.getInstrument(), this.currentAddPlayable.getVolume()));
      }
    }
  };

  /**
   * A Runnable lambda for removing a playable from a piece of music
   */
  private final Runnable removePlayable = () -> {
    int beat = (this.mouseHandlerRemoveMode.getCurrMouseEvent().getX() / GuiViewImpl.BLOCK) - 1;
    int playableVal = -this.mouseHandlerRemoveMode.getCurrMouseEvent().getY() / GuiViewImpl.BLOCK +
        this.model.getHighestPlayableVal() + 1;
    Playable p = this.getClickedPlayable(beat, playableVal);
    if (p != null && this.currBeat < this.model.getLastBeat()) {
      this.model.removePlayable(p);
    }
  };

  /**
   * A Runnable lambda for editing a playable in a piece of music
   */
  private final Runnable editPlayable = () -> {
    this.isEditing = !this.isEditing;
    int beat = (this.mouseHandlerEditMode.getCurrMouseEvent().getX() / GuiViewImpl.BLOCK) - 1;
    int playableVal = -this.mouseHandlerEditMode.getCurrMouseEvent().getY() / GuiViewImpl.BLOCK +
        this.model.getHighestPlayableVal() + 1;
    if (this.isEditing) {
      this.currentEditPlayable = this.getClickedPlayable(beat, playableVal);
      if (this.currentEditPlayable != null && this.currBeat < this.model.getLastBeat()) {
        this.model.removePlayable(this.currentEditPlayable);
      }
    } else {
      if (this.currentEditPlayable != null && this.currBeat < this.model.getLastBeat()) {
        this.model.addPlayable(new Note(beat, this.currentEditPlayable.getBeats(),
            playableVal / 12, Pitch.toPitch(playableVal % 12),
            this.currentEditPlayable.getInstrument(), this.currentEditPlayable.getVolume()));
      }
    }
  };

  /**
   * A {@link KeyboardHandler} for handling input from the keybaord
   */
  private final KeyboardHandler keyboardHandler = new KeyboardHandler.Builder()
      .addKeyReleased(KeyEvent.VK_SPACE, this.playPause)
      .addKeyPressed(KeyEvent.VK_UP, this.arrowUp)
      .addKeyPressed(KeyEvent.VK_DOWN, this.arrowDown)
      .addKeyPressed(KeyEvent.VK_RIGHT, this.arrowRight)
      .addKeyPressed(KeyEvent.VK_LEFT, this.arrowLeft)
      .addKeyPressed(KeyEvent.VK_HOME, this.jumpToBeginning)
      .addKeyPressed(KeyEvent.VK_END, this.jumpToEnd)
      .addKeyPressed(KeyEvent.VK_R, this.rFlag)
      .addKeyPressed(KeyEvent.VK_E, this.eFlag)
      .build();

  /**
   * A {@link MouseHandler} for handling input from the mouse
   */
  private final MouseHandler mouseHandler = new MouseHandler.Builder()
      .addMousePressed(MouseEvent.BUTTON1, this.addPlayable)
      .addMouseReleased(MouseEvent.BUTTON1, this.addPlayable)
      .build();

  /**
   * A {@link MouseHandler} for handling input from the mouse in remove mode
   */
  private final MouseHandler mouseHandlerRemoveMode = new MouseHandler.Builder()
      .addMousePressed(MouseEvent.BUTTON1, this.removePlayable)
      .build();

  /**
   * A {@link MouseHandler} for handling input from the mouse in edit mode
   */
  private final MouseHandler mouseHandlerEditMode = new MouseHandler.Builder()
      .addMousePressed(MouseEvent.BUTTON1, this.editPlayable)
      .addMouseReleased(MouseEvent.BUTTON1, this.editPlayable)
      .build();

  /**
   * Gets the {@link Playable} that was clicked in the piece of music
   *
   * @param beat        the beat of the {@link Playable} clicked
   * @param playableVal the playable val [0 - 127] of the {@link Playable} clicked
   * @return the {@link Playable} that was clicked
   */
  private Playable getClickedPlayable(int beat, int playableVal) {
    for (Playable p : this.model.getPlayablesAtBeat(beat)) {
      if ((p.getStartTime() == beat) && (p.getPlayableVal() == playableVal)) {
        return p;
      }
    }
    return null;
  }

  /**
   * Initializes this {@link GuiController} to a {@link MusicEditorModel}, {@link GuiView}, and adds
   * the {@code keyboardHandler} and the {@code mouseHandler}
   *
   * @param model the {@link MusicEditorModel} to initialize to
   * @param view  the {@link GuiView} to initialize to
   */
  public void init(MusicEditorModel model, GuiView view) {
    this.model = model;
    this.view = view;
    this.view.addKeyListener(this.keyboardHandler);
    this.view.addMouseListener(this.mouseHandler);
    this.view.render(new ViewModel(this.model));
    this.tick();
  }

  /**
   * Updates the {@code Timer} at a rate of the piece of music's tempo
   */
  private void tick() {
    this.timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        if (!isPaused) {
          view.update(currBeat++);
        }
        if (currBeat >= model.getLastBeat()) {
          timer.cancel();
        }
      }
    }, 0, this.model.getTempo() / 1000);
  }

  /**
   * A factory for creating a type of view for the piece of music
   */
  public static final class ViewFactory {
    /**
     * Creates a new instance of a view based on the user input
     *
     * @param view the type of view to be created
     * @return a new instance of the view based on the user input
     * @throws IllegalArgumentException if not a valid type of view
     */
    public static GuiView create(String view) throws IllegalArgumentException {
      switch (view) {
        case "visual":
          return new GuiViewImpl();
        case "composite":
          return new CompositeViewImpl(new GuiViewImpl(), new MidiViewImpl());
        default:
          throw new IllegalArgumentException("Not a valid type of view.");
      }
    }
  }
}
