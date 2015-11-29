package cs3500.music.model;

public abstract class Playable {
  private int startTime, beats, playableVal, instrument, volume;

  /**
   * CLASS INVARIANTS:
   *
   * this.startTime cannot be < 0
   * this.beats cannot be < 1
   * this.instrument [0, 127]
   * this.volume >= 0
   */

  /**
   * Constructor for a {@link Playable}
   *
   * @param startTime  the starting time of this {@link Playable}
   * @param beats      the beat of this {@link Playable}
   * @param octave     the octave of this {@link Playable}
   * @param pitch      the {@link Pitch} of this {@link Playable}
   * @param instrument the instrument of this {@link Playable}
   * @param volume     the volume of this {@link Playable}
   * @throws IllegalArgumentException if {@code startTime} < 0, {@code beats} < 0, {@code octave} <
   *                                  0 or > 10, {@code instrument} < 0 or > 127
   */
  Playable(int startTime, int beats, int octave, Pitch pitch, int instrument, int volume)
      throws IllegalArgumentException {
    if (startTime < 0 || beats < 1 || octave < 0 || octave > 10 || instrument < 0 ||
        instrument > 127 || volume < 0) {
      throw new IllegalArgumentException("Invalid note.");
    }
    this.startTime = startTime;
    this.beats = beats;
    this.instrument = instrument;
    this.volume = volume;
    this.playableVal = (12 * octave) + pitch.ordinal();
  }

  /**
   * Gets the {@code startTime} of this {@link Playable}
   *
   * @return the {@code startTime} of this {@link Playable}
   */
  public int getStartTime() {
    return this.startTime;
  }

  /**
   * Gets the number of {@code beats} of this {@link Playable}
   *
   * @return the number of {@code beats} of this {@link Playable}
   */
  public int getBeats() {
    return this.beats;
  }

  /**
   * Gets the {@code noteValue} of this {@link Playable} on a 0 - 131 scale
   *
   * @return the {@code noteValue} of this {@link Playable}
   */
  public int getPlayableVal() {
    return this.playableVal;
  }

  /**
   * Gets the {@code instrument} of this {@link Playable}
   *
   * @return the {@code instrument} of this {@link Playable}
   */
  public int getInstrument() {
    return this.instrument;
  }

  /**
   * Gets the volume of this {@link Playable}
   *
   * @return the volume of this {@link Playable}
   */
  public int getVolume() {
    return this.volume;
  }

  /**
   * Creates a new {@link Playable} with the given {@code startBeat}
   *
   * @param startBeat the start beat for this new {@link Playable}
   * @return the new {@link Playable} with the given {@code startBeat}
   */
  abstract Playable fromPlayable(int startBeat);
}
