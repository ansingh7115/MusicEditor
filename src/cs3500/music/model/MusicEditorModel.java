package cs3500.music.model;

import java.util.Collection;

/**
 * An interface for representing a music editor
 */
public interface MusicEditorModel {
  /**
   * Gets the lowest {@link Playable} in a piece of music
   *
   * @return the lowest {@link Playable} in a piece of music
   */
  int getLowestPlayableVal();

  /**
   * Gets the highest {@link Playable} in a piece of music
   *
   * @return the highest {@link Playable} in a piece of music
   */
  int getHighestPlayableVal();

  /**
   * Gets the last beat in a piece of music
   *
   * @return the last beat in a piece of music
   */
  int getLastBeat();

  /**
   * Gets the tempo of a piece of music
   *
   * @return the tempo of a piece of music
   */
  int getTempo();

  /**
   * Adds a {@link Playable} to a piece of music
   *
   * @param p the given {@link Playable} to be added
   */
  void addPlayable(Playable p);

  /**
   * Removes a {@link Playable} from a piece of music
   *
   * @param p the given {@link Playable} to be removed
   */
  void removePlayable(Playable p);

  /**
   * Gets all of the {@link Playable}s at a given beat
   *
   * @param beat the beat to find {@link Playable}s at
   * @return a collection of all {@link Playable}s at the given beat
   */
  Collection<Playable> getPlayablesAtBeat(int beat);

  /**
   * Allows two pieces of music to be played at the same time
   *
   * @param model the piece of music to tick with another piece of music
   */
  void playSimultaneously(MusicEditorModel model);

  /**
   * Allows a second piece of music to be queued to be played after a piece of music
   *
   * @param model the second piece of music to be queued
   */
  void queue(MusicEditorModel model);
}