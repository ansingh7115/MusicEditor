package cs3500.music.model;

/**
 * The pitches that a {@link Playable} could be
 */
public enum Pitch {
  C, CSHARP, D, DSHARP, E, F, FSHARP, G, GSHARP, A, ASHARP, B;

  /**
   * Converts a given {@code pitch} to a string representation of a {@link Pitch}
   *
   * @param pitch the integer representation of a {@link Pitch}
   * @return the {@link Pitch} represented as a string
   * @throws IllegalArgumentException if {@code pitch} is not a valid integer representation of a
   *                                  {@link Pitch}
   */
  public static String toString(int pitch) throws IllegalArgumentException {
    switch (pitch) {
      case 0:
        return "C";
      case 1:
        return "C#";
      case 2:
        return "D";
      case 3:
        return "D#";
      case 4:
        return "E";
      case 5:
        return "F";
      case 6:
        return "F#";
      case 7:
        return "G";
      case 8:
        return "G#";
      case 9:
        return "A";
      case 10:
        return "A#";
      case 11:
        return "B";
      default:
        throw new IllegalArgumentException("Not a valid pitch.");
    }
  }

  /**
   * Converts a given integer to its corresponding {@link Pitch}
   *
   * @param pitch the integer representation of the {@link Pitch} to be found
   * @return the {@link Pitch} that the given integer represents
   * @throws IllegalArgumentException if there is no {@link Pitch} corresponding to the given
   *                                  integer
   */
  public static Pitch toPitch(int pitch) throws IllegalArgumentException {
    switch (pitch) {
      case 0:
        return C;
      case 1:
        return CSHARP;
      case 2:
        return D;
      case 3:
        return DSHARP;
      case 4:
        return E;
      case 5:
        return F;
      case 6:
        return FSHARP;
      case 7:
        return G;
      case 8:
        return GSHARP;
      case 9:
        return A;
      case 10:
        return ASHARP;
      case 11:
        return B;
      default:
        throw new IllegalArgumentException("Not a valid pitch.");
    }
  }
}