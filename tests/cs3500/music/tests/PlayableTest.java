package cs3500.music.tests;

import cs3500.music.model.*;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Test for Playable
 */
public class PlayableTest {
  @Test
  public void testConstructor() {
    Playable p = new Note(0, 1, 0, Pitch.C, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1() {
    Playable p = new Note(-1, 0, 0, Pitch.C, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    Playable p = new Note(-10, 0, 0, Pitch.C, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    Playable p = new Note(0, -1, 0, Pitch.C, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4() {
    Playable p = new Note(0, -10, 0, Pitch.C, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor5() {
    Playable p = new Note(0, 0, -1, Pitch.C, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor6() {
    Playable p = new Note(0, 0, -22, Pitch.C, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor7() {
    Playable p = new Note(0, 0, 11, Pitch.C, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor8() {
    Playable p = new Note(0, 0, 56, Pitch.C, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor9() {
    Playable p = new Note(0, 0, 56, Pitch.C, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor10() {
    Playable p = new Note(0, 0, 0, Pitch.C, -122, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor11() {
    Playable p = new Note(0, 0, 0, Pitch.C, 128, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor12() {
    Playable p = new Note(0, 0, 0, Pitch.C, 454, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor13() {
    Playable p = new Note(1, 123, 2, Pitch.C, 5, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor14() {
    Playable p = new Note(1, 123, 2, Pitch.C, 5, -100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor15() {
    Playable p = new Note(-1, -123, 77, Pitch.C, 454, 111);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor16() {
    Playable p = new Note(1, 0, 4, Pitch.C, 2, 2);
  }

  @Test
  public void testGetStartTime() {
    Playable p = new Note(0, 1, 1, Pitch.ASHARP, 1, 53);
    assertEquals(0, p.getStartTime());
  }

  @Test
  public void testGetStartTime1() {
    Playable p = new Note(14, 1, 1, Pitch.ASHARP, 1, 33);
    assertEquals(14, p.getStartTime());
  }

  @Test
  public void testGetBeats() {
    Playable p = new Note(14, 1, 1, Pitch.ASHARP, 1, 33);
    assertEquals(1, p.getBeats());
  }

  @Test
  public void testGetBeats1() {
    Playable p = new Note(14, 22, 4, Pitch.ASHARP, 1, 22);
    assertEquals(22, p.getBeats());
  }

  @Test
  public void testGetPlayableVal() {
    Playable p = new Note(0, 1, 4, Pitch.E, 1, 17);
    assertEquals(52, p.getPlayableVal());
  }

  @Test
  public void testGetPlayableVal1() {
    Playable p = new Note(0, 1, 0, Pitch.C, 1, 65);
    assertEquals(0, p.getPlayableVal());
  }

  @Test
  public void testGetPlayableVal2() {
    Playable p = new Note(0, 1, 10, Pitch.B, 1, 52);
    assertEquals(131, p.getPlayableVal());
  }

  @Test
  public void testGetInstrument() {
    Playable p = new Note(0, 1, 10, Pitch.E, 22, 22);
    assertEquals(22, p.getInstrument());
  }

  @Test
  public void testGetVolume() {
    Playable p = new Note(0, 1, 2, Pitch.ASHARP, 3, 1);
    assertEquals(1, p.getVolume());
  }
}
