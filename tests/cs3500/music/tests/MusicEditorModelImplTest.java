package cs3500.music.tests;

import cs3500.music.model.*;
import cs3500.music.util.CompositionBuilder;

import org.junit.*;

import java.util.HashSet;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * Tests for MusicEditorModelImpl
 */
public class MusicEditorModelImplTest {
  @Test
  public void testConstructor() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    for (int i = 0; i < 131; i++) {
      assertEquals(0, music.getPlayablesAtBeat(i).size());
    }
  }

  @Test
  public void testGetLowestPlayableVal() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    assertEquals(131, music.getLowestPlayableVal());
  }

  @Test
  public void testGetHighestPlayableVal() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    assertEquals(0, music.getHighestPlayableVal());
  }

  @Test
  public void testGetLastBeat() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    assertEquals(0, music.getLastBeat());
  }

  @Test
  public void testGetTempo() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    assertEquals(500000, music.getTempo());
  }

  @Test
  public void testGetTempo1() {
    CompositionBuilder<MusicEditorModel> builder = new MusicEditorModelImpl.Builder();
    MusicEditorModel music = builder.setTempo(10).build();
    assertEquals(10, music.getTempo());
  }

  @Test
  public void testGets() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    Note n = new Note(0, 4, 1, Pitch.C, 1, 100);
    music.addPlayable(n);
    assertEquals(12, music.getHighestPlayableVal());
    assertEquals(12, music.getLowestPlayableVal());
    assertEquals(500000, music.getTempo());
    assertEquals(4, music.getLastBeat());
    assertEquals(new HashSet<>(Collections.singletonList(n)),
        music.getPlayablesAtBeat(2));
  }

  @Test
  public void testAddPlayable() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    Note n = new Note(0, 4, 2, Pitch.C, 1, 100);
    assertEquals(0, music.getLastBeat());
    assertEquals(131, music.getLowestPlayableVal());
    assertEquals(0, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(), music.getPlayablesAtBeat(0));
    music.addPlayable(n);
    assertEquals(4, music.getLastBeat());
    assertEquals(24, music.getLowestPlayableVal());
    assertEquals(24, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Collections.singletonList(n)),
        music.getPlayablesAtBeat(0));
  }

  @Test
  public void testAddPlayable1() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    Note n = new Note(0, 4, 2, Pitch.C, 1, 100);
    Note n1 = new Note(10, 1, 10, Pitch.D, 1, 100);
    music.addPlayable(n);
    assertEquals(4, music.getLastBeat());
    assertEquals(24, music.getLowestPlayableVal());
    assertEquals(24, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Collections.singletonList(n)),
        music.getPlayablesAtBeat(0));
    music.addPlayable(n1);
    assertEquals(11, music.getLastBeat());
    assertEquals(24, music.getLowestPlayableVal());
    assertEquals(122, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Collections.singletonList(n)),
        music.getPlayablesAtBeat(0));
    assertEquals(new HashSet<>(Collections.singletonList(n1)),
        music.getPlayablesAtBeat(10));
  }

  @Test
  public void testAddPlayable2() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    Note n = new Note(0, 4, 2, Pitch.C, 1, 100);
    Note n1 = new Note(0, 1, 0, Pitch.D, 1, 100);
    music.addPlayable(n);
    assertEquals(4, music.getLastBeat());
    assertEquals(24, music.getLowestPlayableVal());
    assertEquals(24, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Collections.singletonList(n)),
        music.getPlayablesAtBeat(0));
    music.addPlayable(n1);
    assertEquals(4, music.getLastBeat());
    assertEquals(2, music.getLowestPlayableVal());
    assertEquals(24, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Arrays.asList(n, n1)), music.getPlayablesAtBeat(0));
  }

  @Test
  public void testAddPlayable3() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    Note n = new Note(1, 6, 7, Pitch.D, 1, 33);
    Note n1 = new Note(5, 2, 7, Pitch.D, 1, 22);
    music.addPlayable(n);
    assertEquals(7, music.getLastBeat());
    assertEquals(86, music.getLowestPlayableVal());
    assertEquals(86, music.getHighestPlayableVal());
    music.addPlayable(n1);
    assertEquals(7, music.getLastBeat());
    assertEquals(86, music.getLowestPlayableVal());
    assertEquals(86, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Arrays.asList(n, n1)), music.getPlayablesAtBeat(5));
  }

  @Test
  public void testAddPlayable4() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    Note n = new Note(10, 10, 10, Pitch.ASHARP, 10, 100);
    Note n1 = new Note(10, 10, 10, Pitch.ASHARP, 10, 100);
    music.addPlayable(n);
    assertEquals(20, music.getLastBeat());
    assertEquals(130, music.getLowestPlayableVal());
    assertEquals(130, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Collections.singletonList(n)),
        music.getPlayablesAtBeat(15));
    music.addPlayable(n1);
    assertEquals(20, music.getLastBeat());
    assertEquals(130, music.getLowestPlayableVal());
    assertEquals(130, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Collections.singletonList(n)), music.getPlayablesAtBeat(15));
  }

  @Test
  public void testAddPlayable5() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    Note n = new Note(10, 10, 10, Pitch.A, 10, 100);
    Note n1 = new Note(10, 10, 10, Pitch.ASHARP, 10, 100);
    music.addPlayable(n);
    assertEquals(20, music.getLastBeat());
    assertEquals(129, music.getLowestPlayableVal());
    assertEquals(129, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Collections.singletonList(n)),
        music.getPlayablesAtBeat(15));
    music.addPlayable(n1);
    assertEquals(20, music.getLastBeat());
    assertEquals(129, music.getLowestPlayableVal());
    assertEquals(130, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Arrays.asList(n, n1)), music.getPlayablesAtBeat(15));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemovePlayable() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    Note n = new Note(1, 1, 1, Pitch.C, 1, 100);
    music.removePlayable(n);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemovePlayable1() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    Note n = new Note(1, 1, 1, Pitch.C, 1, 100);
    Note n1 = new Note(2, 2, 2, Pitch.C, 1, 22);
    music.addPlayable(n1);
    music.removePlayable(n);
  }

  @Test
  public void testRemovePlayable2() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    Note n = new Note(10, 20, 4, Pitch.FSHARP, 0, 100);
    music.addPlayable(n);
    assertEquals(30, music.getLastBeat());
    assertEquals(54, music.getLowestPlayableVal());
    assertEquals(54, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Collections.singletonList(n)),
        music.getPlayablesAtBeat(11));
    music.removePlayable(n);
    assertEquals(0, music.getLastBeat());
    assertEquals(131, music.getLowestPlayableVal());
    assertEquals(0, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(),
        music.getPlayablesAtBeat(11));
  }

  @Test
  public void testRemovePlayable3() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    Note n = new Note(10, 20, 4, Pitch.FSHARP, 0, 100);
    Note n1 = new Note(1, 2, 0, Pitch.ASHARP, 0, 100);
    music.addPlayable(n);
    music.addPlayable(n1);
    assertEquals(30, music.getLastBeat());
    assertEquals(10, music.getLowestPlayableVal());
    assertEquals(54, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Collections.singletonList(n)),
        music.getPlayablesAtBeat(11));
    assertEquals(new HashSet<>(Collections.singletonList(n1)),
        music.getPlayablesAtBeat(1));
    music.removePlayable(n);
    assertEquals(3, music.getLastBeat());
    assertEquals(10, music.getLowestPlayableVal());
    assertEquals(10, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Collections.singletonList(n1)),
        music.getPlayablesAtBeat(1));
    assertEquals(new HashSet<>(), music.getPlayablesAtBeat(11));
  }

  @Test
  public void testRemovePlayable4() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    Note n = new Note(10, 20, 4, Pitch.FSHARP, 0, 100);
    Note n1 = new Note(1, 2, 0, Pitch.ASHARP, 0, 100);
    music.addPlayable(n);
    music.addPlayable(n1);
    assertEquals(30, music.getLastBeat());
    assertEquals(10, music.getLowestPlayableVal());
    assertEquals(54, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Collections.singletonList(n)),
        music.getPlayablesAtBeat(11));
    assertEquals(new HashSet<>(Collections.singletonList(n1)),
        music.getPlayablesAtBeat(1));
    music.removePlayable(n1);
    assertEquals(30, music.getLastBeat());
    assertEquals(54, music.getLowestPlayableVal());
    assertEquals(54, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Collections.singletonList(n)),
        music.getPlayablesAtBeat(11));
    assertEquals(new HashSet<>(), music.getPlayablesAtBeat(1));
  }

  @Test
  public void testRemovePlayable5() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    Note n = new Note(10, 20, 4, Pitch.FSHARP, 0, 100);
    Note n1 = new Note(1, 2, 0, Pitch.ASHARP, 0, 100);
    music.addPlayable(n);
    music.addPlayable(n1);
    assertEquals(30, music.getLastBeat());
    assertEquals(10, music.getLowestPlayableVal());
    assertEquals(54, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Collections.singletonList(n)),
        music.getPlayablesAtBeat(11));
    assertEquals(new HashSet<>(Collections.singletonList(n1)),
        music.getPlayablesAtBeat(1));
    music.removePlayable(n1);
    music.removePlayable(n);
    assertEquals(0, music.getLastBeat());
    assertEquals(131, music.getLowestPlayableVal());
    assertEquals(0, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(), music.getPlayablesAtBeat(11));
    assertEquals(new HashSet<>(), music.getPlayablesAtBeat(1));
  }

  @Test
  public void testRemovePlayable6() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    Note n = new Note(10, 20, 4, Pitch.FSHARP, 0, 100);
    Note n1 = new Note(1, 2, 0, Pitch.ASHARP, 0, 100);
    Note n2 = new Note(5, 6, 7, Pitch.B, 1, 100);
    music.addPlayable(n);
    music.addPlayable(n1);
    music.addPlayable(n2);
    assertEquals(30, music.getLastBeat());
    assertEquals(10, music.getLowestPlayableVal());
    assertEquals(95, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Arrays.asList(n2, n)), music.getPlayablesAtBeat(10));
    assertEquals(new HashSet<>(Collections.singletonList(n1)),
        music.getPlayablesAtBeat(1));
    music.removePlayable(n);
    assertEquals(11, music.getLastBeat());
    assertEquals(10, music.getLowestPlayableVal());
    assertEquals(95, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Collections.singletonList(n2)),
        music.getPlayablesAtBeat(10));
    assertEquals(new HashSet<>(Collections.singletonList(n1)),
        music.getPlayablesAtBeat(1));
  }

  @Test
  public void testRemovePlayable7() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    Note n = new Note(10, 20, 4, Pitch.FSHARP, 0, 100);
    Note n1 = new Note(1, 2, 0, Pitch.ASHARP, 0, 100);
    Note n2 = new Note(5, 6, 7, Pitch.B, 1, 100);
    music.addPlayable(n);
    music.addPlayable(n1);
    music.addPlayable(n2);
    assertEquals(30, music.getLastBeat());
    assertEquals(10, music.getLowestPlayableVal());
    assertEquals(95, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Arrays.asList(n2, n)), music.getPlayablesAtBeat(10));
    assertEquals(new HashSet<>(Collections.singletonList(n1)),
        music.getPlayablesAtBeat(1));
    music.removePlayable(n1);
    assertEquals(30, music.getLastBeat());
    assertEquals(54, music.getLowestPlayableVal());
    assertEquals(95, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Arrays.asList(n2, n)), music.getPlayablesAtBeat(10));
    assertEquals(new HashSet<>(), music.getPlayablesAtBeat(1));
  }

  @Test
  public void testRemovePlayable8() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    Note n = new Note(10, 20, 4, Pitch.FSHARP, 0, 100);
    Note n1 = new Note(1, 2, 0, Pitch.ASHARP, 0, 100);
    Note n2 = new Note(5, 6, 7, Pitch.B, 1, 100);
    music.addPlayable(n);
    music.addPlayable(n1);
    music.addPlayable(n2);
    assertEquals(30, music.getLastBeat());
    assertEquals(10, music.getLowestPlayableVal());
    assertEquals(95, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Arrays.asList(n2, n)), music.getPlayablesAtBeat(10));
    assertEquals(new HashSet<>(Collections.singletonList(n1)),
        music.getPlayablesAtBeat(1));
    music.removePlayable(n2);
    assertEquals(30, music.getLastBeat());
    assertEquals(10, music.getLowestPlayableVal());
    assertEquals(54, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Collections.singletonList(n)),
        music.getPlayablesAtBeat(10));
    assertEquals(new HashSet<>(Collections.singletonList(n1)),
        music.getPlayablesAtBeat(1));
  }

  @Test
  public void testGetPlayablesAtBeat() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    assertEquals(new HashSet<>(), music.getPlayablesAtBeat(0));
  }

  @Test
  public void testGetPlayablesAtBeat1() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    Note n = new Note(0, 10, 2, Pitch.G, 1, 100);
    music.addPlayable(n);
    assertEquals(new HashSet<>(Collections.singletonList(n)),
        music.getPlayablesAtBeat(0));
    assertEquals(new HashSet<>(Collections.singletonList(n)),
        music.getPlayablesAtBeat(9));
    assertEquals(new HashSet<>(Collections.singletonList(n)),
        music.getPlayablesAtBeat(5));
  }

  @Test
  public void testGetPlayablesAtBeat2() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    Note n = new Note(0, 10, 2, Pitch.G, 1, 100);
    music.addPlayable(n);
    assertEquals(new HashSet<>(), music.getPlayablesAtBeat(11));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPlayablesAtBeat3() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    Note n = new Note(0, 10, 2, Pitch.G, 1, 100);
    music.addPlayable(n);
    assertEquals(new HashSet<>(), music.getPlayablesAtBeat(-1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPlayablesAtBeat4() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    Note n = new Note(0, 10, 2, Pitch.G, 1, 100);
    music.addPlayable(n);
    assertEquals(new HashSet<>(), music.getPlayablesAtBeat(-112));
  }

  @Test
  public void testGetPlayablesAtBeat5() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    Note n = new Note(0, 11, 1, Pitch.G, 1, 100);
    Note n1 = new Note(10, 10, 1, Pitch.A, 2, 100);
    music.addPlayable(n);
    music.addPlayable(n1);
    assertEquals(new HashSet<>(Arrays.asList(n, n1)), music.getPlayablesAtBeat(10));
  }

  @Test
  public void testGetPlayablesAtBeat6() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    Note n = new Note(0, 10, 1, Pitch.G, 1, 100);
    Note n1 = new Note(5, 10, 1, Pitch.A, 2, 100);
    music.addPlayable(n);
    music.addPlayable(n1);
    assertEquals(new HashSet<>(Arrays.asList(n, n1)), music.getPlayablesAtBeat(6));
  }

  @Test
  public void testPlaySimultaneously() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    MusicEditorModelImpl music1 = new MusicEditorModelImpl();
    Note n = new Note(1, 5, 2, Pitch.C, 0, 100);
    Note n1 = new Note(3, 7, 6, Pitch.ASHARP, 1, 100);
    Note n2 = new Note(10, 7, 6, Pitch.G, 3, 100);
    Note n3 = new Note(10, 7, 6, Pitch.B, 1, 100);
    music.addPlayable(n);
    music.addPlayable(n1);
    assertEquals(10, music.getLastBeat());
    assertEquals(24, music.getLowestPlayableVal());
    assertEquals(82, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Arrays.asList(n, n1)), music.getPlayablesAtBeat(4));
    music1.addPlayable(n2);
    music1.addPlayable(n3);
    assertEquals(17, music1.getLastBeat());
    assertEquals(79, music1.getLowestPlayableVal());
    assertEquals(83, music1.getHighestPlayableVal());
    assertEquals(new HashSet<>(Arrays.asList(n2, n3)), music1.getPlayablesAtBeat(15));
    music.playSimultaneously(music1);
    assertEquals(17, music.getLastBeat());
    assertEquals(24, music.getLowestPlayableVal());
    assertEquals(83, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Arrays.asList(n, n1)), music.getPlayablesAtBeat(4));
    assertEquals(new HashSet<>(Arrays.asList(n2, n3)), music.getPlayablesAtBeat(15));
  }

  @Test
  public void testPlaySimultaneously1() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    MusicEditorModelImpl music1 = new MusicEditorModelImpl();
    Note n = new Note(1, 5, 2, Pitch.C, 0, 23);
    Note n1 = new Note(3, 7, 6, Pitch.ASHARP, 1, 11);
    music.addPlayable(n);
    music.addPlayable(n1);
    assertEquals(10, music.getLastBeat());
    assertEquals(24, music.getLowestPlayableVal());
    assertEquals(82, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Arrays.asList(n, n1)), music.getPlayablesAtBeat(4));
    assertEquals(0, music1.getLastBeat());
    assertEquals(131, music1.getLowestPlayableVal());
    assertEquals(0, music1.getHighestPlayableVal());
    music.playSimultaneously(music1);
    assertEquals(10, music.getLastBeat());
    assertEquals(24, music.getLowestPlayableVal());
    assertEquals(82, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Arrays.asList(n, n1)), music.getPlayablesAtBeat(4));
  }

  @Test
  public void testPlaySimultaneously3() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    MusicEditorModelImpl music1 = new MusicEditorModelImpl();
    Note n = new Note(1, 5, 2, Pitch.C, 0, 2);
    Note n1 = new Note(3, 7, 6, Pitch.ASHARP, 1, 1);
    music.addPlayable(n);
    music.addPlayable(n1);
    assertEquals(10, music.getLastBeat());
    assertEquals(24, music.getLowestPlayableVal());
    assertEquals(82, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Arrays.asList(n, n1)), music.getPlayablesAtBeat(4));
    assertEquals(0, music1.getLastBeat());
    assertEquals(131, music1.getLowestPlayableVal());
    assertEquals(0, music1.getHighestPlayableVal());
    music1.playSimultaneously(music);
    assertEquals(10, music.getLastBeat());
    assertEquals(24, music.getLowestPlayableVal());
    assertEquals(82, music.getHighestPlayableVal());
    assertEquals(new HashSet<>(Arrays.asList(n, n1)), music1.getPlayablesAtBeat(4));
  }

  @Test
  public void testPlaySimultaneously4() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    MusicEditorModelImpl music1 = new MusicEditorModelImpl();
    music.playSimultaneously(music1);
    assertEquals(0, music.getLastBeat());
    assertEquals(131, music.getLowestPlayableVal());
    assertEquals(0, music.getHighestPlayableVal());
    for (int i = 0; i < 131; i++) {
      assertEquals(0, music.getPlayablesAtBeat(i).size());
    }
  }

  @Test
  public void testQueueMusic() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    MusicEditorModelImpl music1 = new MusicEditorModelImpl();
    music.queue(music1);
    assertEquals(0, music.getLastBeat());
    assertEquals(131, music.getLowestPlayableVal());
    assertEquals(0, music.getHighestPlayableVal());
    for (int i = 0; i < 131; i++) {
      assertEquals(0, music.getPlayablesAtBeat(i).size());
    }
  }

  @Test
  public void testQueueMusic1() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    MusicEditorModelImpl music1 = new MusicEditorModelImpl();
    Note n = new Note(0, 2, 3, Pitch.C, 1, 100);
    Note n1 = new Note(0, 2, 7, Pitch.ASHARP, 0, 2);
    music.addPlayable(n);
    music.addPlayable(n1);
    assertEquals(2, music.getLastBeat());
    assertEquals(36, music.getLowestPlayableVal());
    assertEquals(94, music.getHighestPlayableVal());
    assertEquals(new HashSet<Playable>(Arrays.asList(n, n1)), music.getPlayablesAtBeat(1));
    music.queue(music1);
    assertEquals(2, music.getLastBeat());
    assertEquals(36, music.getLowestPlayableVal());
    assertEquals(94, music.getHighestPlayableVal());
    assertEquals(new HashSet<Playable>(Arrays.asList(n, n1)), music.getPlayablesAtBeat(1));
  }

  @Test
  public void testQueueMusic2() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    MusicEditorModelImpl music1 = new MusicEditorModelImpl();
    Note n = new Note(0, 2, 3, Pitch.C, 1, 100);
    Note n1 = new Note(0, 2, 7, Pitch.ASHARP, 0, 100);
    music.addPlayable(n);
    music.addPlayable(n1);
    assertEquals(2, music.getLastBeat());
    assertEquals(36, music.getLowestPlayableVal());
    assertEquals(94, music.getHighestPlayableVal());
    assertEquals(new HashSet<Playable>(Arrays.asList(n, n1)), music.getPlayablesAtBeat(1));
    music1.queue(music);
    assertEquals(2, music.getLastBeat());
    assertEquals(36, music.getLowestPlayableVal());
    assertEquals(94, music.getHighestPlayableVal());
    assertEquals(new HashSet<Playable>(Arrays.asList(n1, n)), music1.getPlayablesAtBeat(1));
  }

  @Test
  public void testQueueMusic3() {
    MusicEditorModelImpl music = new MusicEditorModelImpl();
    MusicEditorModelImpl music1 = new MusicEditorModelImpl();
    Note n = new Note(0, 2, 3, Pitch.C, 1, 27);
    Note n1 = new Note(0, 2, 7, Pitch.ASHARP, 0, 100);
    Note n2 = new Note(0, 20, 0, Pitch.A, 1, 100);
    Note n3 = new Note(10, 3, 3, Pitch.A, 3, 66);
    music.addPlayable(n);
    music.addPlayable(n1);
    assertEquals(2, music.getLastBeat());
    assertEquals(36, music.getLowestPlayableVal());
    assertEquals(94, music.getHighestPlayableVal());
    assertEquals(new HashSet<Playable>(Arrays.asList(n, n1)), music.getPlayablesAtBeat(1));
    music1.addPlayable(n2);
    music1.addPlayable(n3);
    assertEquals(20, music1.getLastBeat());
    assertEquals(9, music1.getLowestPlayableVal());
    assertEquals(45, music1.getHighestPlayableVal());
    assertEquals(new HashSet<Playable>(Arrays.asList(n2, n3)), music1.getPlayablesAtBeat(12));
    music.queue(music1);
    assertEquals(23, music.getLastBeat());
    assertEquals(9, music.getLowestPlayableVal());
    assertEquals(94, music.getHighestPlayableVal());
    assertEquals(new HashSet<Playable>(Arrays.asList(n, n1)), music.getPlayablesAtBeat(1));
    assertEquals(new HashSet<Playable>(Arrays.asList(new Note(3, 20, 0, Pitch.A, 1, 100),
        new Note(13, 3, 3, Pitch.A, 3, 66))), music.getPlayablesAtBeat(15));
  }

  @Test
  public void testBuild() {
    CompositionBuilder<MusicEditorModel> builder = new MusicEditorModelImpl.Builder();
    MusicEditorModel music = builder.build();
    assertEquals(0, music.getHighestPlayableVal());
    assertEquals(131, music.getLowestPlayableVal());
    assertEquals(500000, music.getTempo());
    assertEquals(0, music.getLastBeat());
    for (int i = 0; i < 131; i++) {
      assertEquals(0, music.getPlayablesAtBeat(i).size());
    }
  }

  @Test
  public void testBuild1() {
    CompositionBuilder<MusicEditorModel> builder = new MusicEditorModelImpl.Builder();
    MusicEditorModel music = builder.setTempo(10).build();
    assertEquals(0, music.getHighestPlayableVal());
    assertEquals(131, music.getLowestPlayableVal());
    assertEquals(10, music.getTempo());
    assertEquals(0, music.getLastBeat());
    for (int i = 0; i < 131; i++) {
      assertEquals(0, music.getPlayablesAtBeat(i).size());
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBuild2() {
    CompositionBuilder<MusicEditorModel> builder = new MusicEditorModelImpl.Builder();
    MusicEditorModel music = builder.setTempo(0).build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBuild3() {
    CompositionBuilder<MusicEditorModel> builder = new MusicEditorModelImpl.Builder();
    MusicEditorModel music = builder.setTempo(-23).build();
  }

  @Test
  public void testBuild4() {
    CompositionBuilder<MusicEditorModel> builder = new MusicEditorModelImpl.Builder();
    MusicEditorModel music = builder.addNote(10, 20, 2, 30, 100).build();
    assertEquals(new HashSet<Playable>(
            Collections.singletonList(new Note(10, 10, 2, Pitch.FSHARP, 2, 100))),
        music.getPlayablesAtBeat(12));
  }

  @Test
  public void testBuild5() {
    CompositionBuilder<MusicEditorModel> builder = new MusicEditorModelImpl.Builder();
    MusicEditorModel music = builder.addNote(10, 20, 2, 30, 100)
        .addNote(15, 16, 2, 0, 7).build();
    assertEquals(new HashSet<Playable>(Arrays.asList(new Note(10, 10, 2, Pitch.FSHARP, 2, 100),
        new Note(15, 1, 0, Pitch.C, 2, 7))),
        music.getPlayablesAtBeat(15));
  }

  @Test
  public void testBuild6() {
    CompositionBuilder<MusicEditorModel> builder = new MusicEditorModelImpl.Builder();
    MusicEditorModel music = builder.addNote(10, 20, 2, 30, 100)
        .addNote(15, 16, 2, 0, 7).setTempo(1).build();
    assertEquals(new HashSet<Playable>(Arrays.asList(new Note(10, 10, 2, Pitch.FSHARP, 2, 100),
        new Note(15, 1, 0, Pitch.C, 2, 7))),
        music.getPlayablesAtBeat(15));
    assertEquals(1, music.getTempo());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBuild7() {
    CompositionBuilder<MusicEditorModel> builder = new MusicEditorModelImpl.Builder();
    MusicEditorModel music = builder.addNote(10, 9, 2, 30, 100).build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBuild8() {
    CompositionBuilder<MusicEditorModel> builder = new MusicEditorModelImpl.Builder();
    MusicEditorModel music = builder.addNote(15, 15, 2, 0, 7).addNote(10, 3, 2, 30, 100).build();
  }

  @Test
  public void testBuilder() {
    CompositionBuilder<MusicEditorModel> builder = new MusicEditorModelImpl.Builder();
    assertEquals("class cs3500.music.model.MusicEditorModelImpl$Builder",
        builder.getClass().toString());
  }
}
