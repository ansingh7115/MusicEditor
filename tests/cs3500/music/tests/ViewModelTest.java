package cs3500.music.tests;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

import cs3500.music.model.*;
import cs3500.music.view.*;

/**
 * Tests for the ViewModel
 */
public class ViewModelTest {
  @Test
  public void testConstructor() {
    MusicEditorModel model = new MusicEditorModelImpl();
    ViewModel vm = new ViewModel(model);
  }

  @Test
  public void testGetLowestPlayableVal() {
    MusicEditorModel model = new MusicEditorModelImpl();
    ViewModel vm = new ViewModel(model);
    model.addPlayable(new Note(1, 1, 5, Pitch.A, 0, 100));
    model.addPlayable(new Note(10, 2, 5, Pitch.G, 0, 100));
    assertEquals(67, vm.getLowestPlayableVal());
  }

  @Test
  public void testGetHighestPlayableVal() {
    MusicEditorModel model = new MusicEditorModelImpl();
    ViewModel vm = new ViewModel(model);
    model.addPlayable(new Note(1, 1, 5, Pitch.A, 0, 100));
    model.addPlayable(new Note(10, 2, 5, Pitch.G, 0, 100));
    assertEquals(69, vm.getHighestPlayableVal());
  }

  @Test
  public void testGetLastBeat() {
    MusicEditorModel model = new MusicEditorModelImpl();
    ViewModel vm = new ViewModel(model);
    model.addPlayable(new Note(1, 1, 5, Pitch.A, 0, 100));
    model.addPlayable(new Note(10, 2, 5, Pitch.G, 0, 100));
    assertEquals(12, vm.getLastBeat());
  }

  @Test
  public void testGetTempo() {
    MusicEditorModel model = new MusicEditorModelImpl();
    ViewModel vm = new ViewModel(model);
    model.addPlayable(new Note(1, 1, 5, Pitch.A, 0, 100));
    model.addPlayable(new Note(10, 2, 5, Pitch.G, 0, 100));
    assertEquals(500000, vm.getTempo());
  }

  @Test
  public void testGetPlayablesAtBeat() {
    MusicEditorModel model = new MusicEditorModelImpl();
    ViewModel vm = new ViewModel(model);
    model.addPlayable(new Note(1, 1, 5, Pitch.A, 0, 100));
    model.addPlayable(new Note(10, 2, 5, Pitch.G, 0, 100));
    assertEquals(new HashSet<Playable>(Arrays.asList(new Note(10, 2, 5, Pitch.G, 0, 100))),
        vm.getPlayablesAtBeat(10));
  }
}
