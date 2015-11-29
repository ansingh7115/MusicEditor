package cs3500.music.model;

import java.util.Objects;

/**
 * Represents a musical note
 */
public final class Note extends Playable {

  /**
   * Constructor for a {@link Note}
   *
   * @param startTime  the starting time of this {@link Note}
   * @param beats      the beat of this {@link Note}
   * @param octave     the octave of this {@link Note}
   * @param pitch      the {@link Pitch} of this {@link Note}
   * @param instrument the instrument of this {@link Note}
   */
  public Note(int startTime, int beats, int octave, Pitch pitch, int instrument, int volume) {
    super(startTime, beats, octave, pitch, instrument, volume);
  }

  /**
   * Creates a new {@link Note} with the given {@code startBeat}
   *
   * @param startBeat the start beat for this new {@link Note}
   * @return the new {@link Note} with the given {@code startBeat}
   */
  @Override
  Note fromPlayable(int startBeat) {
    return new Note(this.getStartTime() + startBeat, this.getBeats(),
        this.getPlayableVal() / 12, Pitch.toPitch(this.getPlayableVal() % 12),
        this.getInstrument(), this.getVolume());
  }

  /**
   * Determines whether a given object is the same as this {@link Note}
   *
   * @param o the object to be compared to
   * @return whether the given object is the same as this {@link Note}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Playable playable = (Playable) o;
    return getStartTime() == playable.getStartTime() &&
        getBeats() == playable.getBeats() &&
        getPlayableVal() == playable.getPlayableVal() &&
        getInstrument() == playable.getInstrument() &&
        getVolume() == playable.getVolume();
  }

  /**
   * Generates a hash code for this {@link Note}
   *
   * @return the hash code for this {@link Note}
   */
  @Override
  public int hashCode() {
    return Objects.hash(getStartTime(), getBeats(), getPlayableVal(), getInstrument(), getVolume());
  }
}