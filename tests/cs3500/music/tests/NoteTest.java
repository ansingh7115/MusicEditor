package cs3500.music.tests;

import cs3500.music.model.*;

import org.junit.*;

import cs3500.music.model.Pitch;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Tests for Note
 */
public class NoteTest {
  @Test
  public void testConstructor() {
    Note n = new Note(0, 1, 0, Pitch.C, 0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1() {
    Note n = new Note(-1, 0, 0, Pitch.C, 0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    Note n = new Note(-10, 0, 0, Pitch.C, 0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    Note n = new Note(0, -1, 0, Pitch.C, 0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4() {
    Note n = new Note(0, -10, 0, Pitch.C, 0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor5() {
    Note n = new Note(0, 0, -1, Pitch.C, 0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor6() {
    Note n = new Note(0, 0, -22, Pitch.C, 0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor7() {
    Note n = new Note(0, 0, 11, Pitch.C, 0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor8() {
    Note n = new Note(0, 0, 56, Pitch.C, 0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor9() {
    Note n = new Note(0, 0, 56, Pitch.C, -1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor10() {
    Note n = new Note(0, 0, 0, Pitch.C, -122, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor11() {
    Note n = new Note(0, 0, 0, Pitch.C, 128, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor12() {
    Note n = new Note(0, 0, 0, Pitch.C, 454, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor13() {
    Note n = new Note(1, 1, 1, Pitch.C, 10, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor14() {
    Note n = new Note(1, 1, 1, Pitch.C, 10, -12);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor15() {
    Note n = new Note(-1, -123, 77, Pitch.C, 454, -21);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor16() {
    Note n = new Note(0, 0, 0, Pitch.C, 0, 1);
  }

  @Test
  public void testGetStartTime() {
    Note n = new Note(0, 1, 1, Pitch.ASHARP, 1, 1);
    assertEquals(0, n.getStartTime());
  }

  @Test
  public void testGetStartTime1() {
    Note n = new Note(14, 1, 1, Pitch.ASHARP, 1, 1);
    assertEquals(14, n.getStartTime());
  }

  @Test
  public void testGetBeats() {
    Note n = new Note(14, 1, 1, Pitch.ASHARP, 1, 1);
    assertEquals(1, n.getBeats());
  }

  @Test
  public void testGetBeats1() {
    Note n = new Note(14, 22, 4, Pitch.ASHARP, 1, 1);
    assertEquals(22, n.getBeats());
  }

  @Test
  public void testGetNoteVal() {
    Note n = new Note(0, 1, 4, Pitch.E, 1, 1);
    assertEquals(52, n.getPlayableVal());
  }

  @Test
  public void testGetNoteVal1() {
    Note n = new Note(0, 1, 0, Pitch.C, 1, 1);
    assertEquals(0, n.getPlayableVal());
  }

  @Test
  public void testGetNoteVal2() {
    Note n = new Note(0, 1, 10, Pitch.B, 1, 1);
    assertEquals(131, n.getPlayableVal());
  }

  @Test
  public void testGetInstrument() {
    Note n = new Note(0, 1, 10, Pitch.E, 22, 1);
    assertEquals(22, n.getInstrument());
  }

  @Test
  public void testGetVolume() {
    Note n = new Note(0, 1, 10, Pitch.ASHARP, 90, 27);
    assertEquals(27, n.getVolume());
  }

  @Test
  public void testEquals() {
    Note n = new Note(0, 1, 1, Pitch.C, 10, 1);
    assertTrue(n.equals(n));
  }

  @Test
  public void testEquals1() {
    Note n = new Note(0, 1, 1, Pitch.C, 10, 3);
    Note n1 = n;
    assertTrue(n.equals(n1));
  }

  @Test
  public void testEquals2() {
    Note n = new Note(0, 1, 1, Pitch.C, 10, 4);
    Note n1 = n;
    assertTrue(n1.equals(n));
  }

  @Test
  public void testEquals3() {
    Note n = new Note(0, 1, 1, Pitch.C, 10, 1);
    ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 2, 3));
    assertFalse(n.equals(arr));
  }

  @Test
  public void testEquals4() {
    Note n = new Note(0, 1, 1, Pitch.C, 10, 100);
    Note n1 = new Note(0, 1, 1, Pitch.C, 10, 100);
    assertTrue(n1.equals(n));
  }

  @Test
  public void testEquals5() {
    Note n = new Note(0, 1, 1, Pitch.C, 10, 3);
    Note n1 = new Note(0, 1, 1, Pitch.C, 10, 3);
    assertTrue(n.equals(n1));
  }

  @Test
  public void testEquals6() {
    Note n = new Note(0, 1, 1, Pitch.C, 10, 12);
    Note n1 = new Note(1, 1, 1, Pitch.C, 10, 12);
    assertFalse(n.equals(n1));
  }

  @Test
  public void testEquals7() {
    Note n = new Note(0, 1, 1, Pitch.C, 10, 12);
    Note n1 = new Note(0, 2, 1, Pitch.C, 10, 12);
    assertFalse(n.equals(n1));
  }

  @Test
  public void testEquals8() {
    Note n = new Note(0, 1, 1, Pitch.C, 10, 12);
    Note n1 = new Note(0, 1, 2, Pitch.C, 10, 12);
    assertFalse(n.equals(n1));
  }

  @Test
  public void testEquals9() {
    Note n = new Note(0, 1, 1, Pitch.C, 10, 12);
    Note n1 = new Note(0, 1, 1, Pitch.CSHARP, 10, 12);
    assertFalse(n.equals(n1));
  }

  @Test
  public void testEquals10() {
    Note n = new Note(0, 1, 1, Pitch.C, 10, 12);
    Note n1 = new Note(0, 1, 1, Pitch.C, 11, 12);
    assertFalse(n.equals(n1));
  }

  @Test
  public void testEquals11() {
    Note n = new Note(0, 1, 1, Pitch.C, 10, 12);
    Note n1 = new Note(0, 1, 1, Pitch.C, 10, 2);
    assertFalse(n.equals(n1));
  }

  @Test
  public void testEquals12() {
    Note n = new Note(0, 1, 1, Pitch.C, 10, 12);
    Note n1 = new Note(1, 2, 2, Pitch.B, 12, 13);
    assertFalse(n.equals(n1));
  }

  @Test
  public void testHashCode() {
    Note n = new Note(0, 1, 1, Pitch.C, 10, 22);
    assertEquals(28670806, n.hashCode());
  }

  @Test
  public void testHashCode1() {
    Note n = new Note(10, 15, 4, Pitch.ASHARP, 0, 90);
    assertEquals(38367054, n.hashCode());
  }

  @Test
  public void testHashCode2() {
    Note n = new Note(20, 120, 10, Pitch.CSHARP, 111, 17);
    assertEquals(50794230, n.hashCode());
  }

  @Test
  public void testHashCode3() {
    Note n = new Note(20, 120, 10, Pitch.CSHARP, 111, 88);
    Note n1 = new Note(1, 1, 1, Pitch.D, 1, 1);
    assertFalse(n1.hashCode() == n.hashCode());
  }

  @Test
  public void testHashCode4() {
    Note n = new Note(20, 120, 10, Pitch.CSHARP, 111, 33);
    Note n1 = n;
    assertTrue(n1.hashCode() == n.hashCode());
  }
}
