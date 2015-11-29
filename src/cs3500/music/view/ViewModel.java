package cs3500.music.view;

import java.util.Collection;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Playable;

/**
 * A view-model in order to conceal information from the model in the view
 */
public final class ViewModel {
  private MusicEditorModel model;

  /**
   * Constructor for a ViewModel
   *
   * @param model the model to create a ViewModel for
   */
  public ViewModel(MusicEditorModel model) {
    this.model = model;
  }

  /**
   * Gets the lowest {@link Playable} in this piece of music
   *
   * @return the lowest {@link Playable} in this piece of music
   */
  public int getLowestPlayableVal() {
    return this.model.getLowestPlayableVal();
  }

  /**
   * Gets the highest {@link Playable} in this piece of music
   *
   * @return the highest {@link Playable} in this piece of music
   */
  public int getHighestPlayableVal() {
    return this.model.getHighestPlayableVal();
  }

  /**
   * Gets the last {@code beat} in this piece of music
   *
   * @return the last {@code beat} in this piece of music
   */
  public int getLastBeat() {
    return this.model.getLastBeat();
  }

  /**
   * Gets the tempo of this piece of music
   *
   * @return the tempo of this piece of music
   */
  public int getTempo() {
    return this.model.getTempo();
  }

  /**
   * Gets all of the {@link Playable}s at a given {@code beat}
   *
   * @param beat the {@code beat} at which to find {@link Playable}s
   * @return all of the {@link Playable}s at the given {@code beat}
   * @throws IllegalArgumentException if {@code beat} is not a valid beat
   */
  public Collection<Playable> getPlayablesAtBeat(int beat) throws IllegalArgumentException {
    return this.model.getPlayablesAtBeat(beat);
  }
}