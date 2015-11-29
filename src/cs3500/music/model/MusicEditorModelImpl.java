package cs3500.music.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

import cs3500.music.util.CompositionBuilder;

/**
 * Represents a piece of music
 */
public final class MusicEditorModelImpl implements MusicEditorModel {
  private HashMap<Integer, HashSet<Playable>> playables = new HashMap<>();
  private int lowestPlayableVal = 131, highestPlayableVal, lastBeat;
  private final int tempo;

  /**
   * CLASS INVARIANTS:
   *
   * 0 <= lowestPlayableVal , highestPlayableVal, lastBeat <= 131
   * this.tempo cannot be < 1
   */

  /**
   * Constructor for tests with default tempo of 500000 microseconds
   */
  public MusicEditorModelImpl() {
    this.tempo = 500000;
  }

  /**
   * Constructor for {@link MusicEditorModelImpl} that initializes the music
   *
   * @param playables the {@link Playable}s in this piece of music
   * @param tempo     the tempo of this piece of music
   */
  private MusicEditorModelImpl(ArrayList<Playable> playables, int tempo) {
    playables.forEach(this::addPlayable);
    this.tempo = tempo;
  }

  /**
   * Updates the {@code lastPlayableVal}, {@code highestPlayableVal}, and {@code lastBeat}
   *
   * @param p the {@link Playable} that will be used for the update
   */
  private void updateVals(Playable p) {
    int noteIndex = p.getPlayableVal();
    if (noteIndex < this.getLowestPlayableVal()) {
      this.lowestPlayableVal = noteIndex;
    }
    if (noteIndex > this.getHighestPlayableVal()) {
      this.highestPlayableVal = noteIndex;
    }
    if (p.getBeats() + p.getStartTime() > this.getLastBeat()) {
      this.lastBeat = p.getBeats() + p.getStartTime();
    }
  }

  /**
   * Gets the lowest {@link Playable} in this piece of music
   *
   * @return the lowest {@link Playable} in this piece of music
   */
  @Override
  public int getLowestPlayableVal() {
    return this.lowestPlayableVal;
  }

  /**
   * Gets the highest {@link Playable} in this piece of music
   *
   * @return the highest {@link Playable} in this piece of music
   */
  @Override
  public int getHighestPlayableVal() {
    return this.highestPlayableVal;
  }

  /**
   * Gets the last {@code beat} in this piece of music
   *
   * @return the last {@code beat} in this piece of music
   */
  @Override
  public int getLastBeat() {
    return this.lastBeat;
  }

  /**
   * Gets the tempo of this piece of music
   *
   * @return the tempo of this piece of music
   */
  @Override
  public int getTempo() {
    return this.tempo;
  }

  /**
   * Adds a {@link Playable} to this piece of music
   *
   * @param p the given {@link Playable} to be added
   */
  @Override
  public void addPlayable(Playable p) {
    for (int i = p.getStartTime(); i <= p.getBeats() + p.getStartTime(); i++) {
      if (this.playables.containsKey(i)) {
        this.playables.get(i).add(p);
      } else {
        HashSet<Playable> newBeat = new HashSet<>();
        newBeat.add(p);
        this.playables.put(i, newBeat);
      }
    }
    this.updateVals(p);
  }

  /**
   * Removes a {@link Playable} from this piece of music
   *
   * @param p the given {@link Playable} to be removed
   * @throws IllegalArgumentException if the given {@link Playable} doesn't exist
   */
  @Override
  public void removePlayable(Playable p) throws IllegalArgumentException {
    if (this.playables.get(p.getStartTime()) == null ||
        !this.playables.get(p.getStartTime()).remove(p)) {
      throw new IllegalArgumentException("No such note exists at that start time.");
    }
    for (int i = p.getStartTime(); i <= p.getBeats() + p.getStartTime(); i++) {
      this.playables.get(i).remove(p);
    }
    this.lastBeat = 0;
    this.lowestPlayableVal = 131;
    this.highestPlayableVal = 0;
    for (HashSet<Playable> set : this.playables.values()) {
      set.forEach(this::updateVals);
    }
  }

  /**
   * Gets all of the {@link Playable}s at a given {@code beat}
   *
   * @param beat the {@code beat} at which to find {@link Playable}s
   * @return all of the {@link Playable}s at the given {@code beat}
   * @throws IllegalArgumentException if {@code beat} is not a valid beat
   */
  @Override
  public Collection<Playable> getPlayablesAtBeat(int beat) throws IllegalArgumentException {
    if (beat < 0) {
      throw new IllegalArgumentException("Not a valid beat.");
    }
    return Collections.unmodifiableSet(this.playables.getOrDefault(beat, new HashSet<>()));
  }

  /**
   * Allows two pieces of music to be played at the same time
   *
   * @param model the piece of music to tick with this piece of music
   */
  @Override
  public void playSimultaneously(MusicEditorModel model) {
    for (int i = 0; i < model.getLastBeat(); i++) {
      model.getPlayablesAtBeat(i).forEach(this::addPlayable);
    }
  }

  /**
   * Allows a second piece of music to be queued to be played after this piece of music
   *
   * @param model the second piece of music to be queued
   */
  @Override
  public void queue(MusicEditorModel model) {
    int lastBeat = 0;
    if (this.getLastBeat() != 0) {
      lastBeat = this.getLastBeat() + 1;
    }
    HashSet<Playable> playablesToAdd = new HashSet<>();
    for (int i = 0; i < model.getLastBeat(); i++) {
      Collection<Playable> col = model.getPlayablesAtBeat(i);
      playablesToAdd.addAll(col.stream().collect(Collectors.toList()));
    }
    for (Playable p : playablesToAdd) {
      this.addPlayable(p.fromPlayable(lastBeat));
    }
  }

  /**
   * Represents a builder for this piece of music
   */
  public static final class Builder implements CompositionBuilder<MusicEditorModel> {
    private ArrayList<Playable> playables = new ArrayList<>();
    private int tempo = 500000;

    /**
     * Constructs an actual composition, given the notes that have been added
     *
     * @return The new composition
     */
    @Override
    public MusicEditorModel build() {
      return new MusicEditorModelImpl(playables, tempo);
    }

    /**
     * Sets the tempo of the piece
     *
     * @param tempo The speed, in microseconds per beat
     * @return This builder
     * @throws IllegalArgumentException if the tempo is not valid
     */
    @Override
    public CompositionBuilder<MusicEditorModel> setTempo(int tempo)
        throws IllegalArgumentException {
      if (tempo < 1) {
        throw new IllegalArgumentException("Not a valid tempo.");
      }
      this.tempo = tempo;
      return this;
    }

    /**
     * Adds a new note to the piece
     *
     * @param start      The start time of the note, in beats
     * @param end        The end time of the note, in beats
     * @param instrument The instrument number (to be interpreted by MIDI)
     * @param pitch      The pitch (in the range [0, 127], where 60 represents C4, the middle-C on a
     *                   piano)
     * @param volume     The volume (in the range [0, 127])
     * @throws IllegalArgumentException if {@code end} is < {@code start} or if {@code pitch} > 127
     */
    @Override
    public CompositionBuilder<MusicEditorModel> addNote(int start, int end, int instrument,
                                                        int pitch, int volume)
        throws IllegalArgumentException {
      if (end <= start || pitch > 127) {
        throw new IllegalArgumentException("Invalid note.");
      }
      this.playables.add(new Note(start, (end - start), pitch / 12, Pitch.toPitch(pitch % 12),
          instrument, volume));
      return this;
    }
  }
}
