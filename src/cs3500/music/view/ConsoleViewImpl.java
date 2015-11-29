package cs3500.music.view;

import java.util.Collection;

import cs3500.music.model.Pitch;
import cs3500.music.model.Playable;

/**
 * Represents a string representation of a piece of music
 */
public final class ConsoleViewImpl implements MusicEditorView {
  @Override
  public void render(ViewModel vm) {
    int timeBuffer = (Integer.toString(vm.getLastBeat()).length());
    int count = 0;
    StringBuilder result = new StringBuilder(String.format("%" + timeBuffer + "s", ""));
    for (int i = vm.getLowestPlayableVal(); i <= vm.getHighestPlayableVal(); i++) {
      result.append(String.format("%" + 4 + "s", Pitch.toString(i % 12) + i / 12));
    }
    for (int i = 0; i < vm.getLastBeat(); i++) {
      result.append(String.format("\n%" + timeBuffer + "d ", i)).append(makeLine(vm, i));
      count += vm.getPlayablesAtBeat(i).size();
    }
    if (count != 0) {
      System.out.println(result);
    }
  }

  /**
   * Creates a string representation of the {@link Playable}s at a given {@code currBeat}
   *
   * @param vm    the particular editor to be displayed
   * @param currBeat the beat to be printed
   * @return a string representation of the {@link Playable}s at the given {@code currBeat}
   */
  private StringBuilder makeLine(ViewModel vm, int currBeat) {
    Collection<Playable> arr = vm.getPlayablesAtBeat(currBeat);
    StringBuilder result;
    int buffer = (4 * (vm.getHighestPlayableVal() - vm.getLowestPlayableVal())) + 2;
    result = new StringBuilder(String.format("%" + buffer + "s", ""));
    for (Playable p : arr) {
      int index = (4 * (p.getPlayableVal() - vm.getLowestPlayableVal())) + 1;
      if (p.getStartTime() == currBeat) {
        result.setCharAt(index, 'X');
      } else if (currBeat > p.getStartTime() && currBeat < (p.getStartTime() + p.getBeats())) {
        if (result.charAt(index) == 'X') {
          continue;
        }
        result.setCharAt(index, '|');
      }
    }
    return result;
  }
}
