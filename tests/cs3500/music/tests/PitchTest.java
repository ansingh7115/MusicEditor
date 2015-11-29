package cs3500.music.tests;

import cs3500.music.model.Note;
import cs3500.music.model.Playable;

import org.junit.*;

import cs3500.music.model.Pitch;

import static org.junit.Assert.*;

/**
 * Tests for Pitch
 */
public class PitchTest {
  @Test
  public void testToString() {
    assertEquals("C", Pitch.toString(0));
  }

  @Test
  public void testToString1() {
    assertEquals("C#", Pitch.toString(1));
  }

  @Test
  public void testToString2() {
    assertEquals("D", Pitch.toString(2));
  }

  @Test
  public void testToString3() {
    assertEquals("D#", Pitch.toString(3));
  }

  @Test
  public void testToString4() {
    assertEquals("E", Pitch.toString(4));
  }

  @Test
  public void testToString5() {
    assertEquals("F", Pitch.toString(5));
  }

  @Test
  public void testToString6() {
    assertEquals("F#", Pitch.toString(6));
  }

  @Test
  public void testToString7() {
    assertEquals("G", Pitch.toString(7));
  }

  @Test
  public void testToString8() {
    assertEquals("G#", Pitch.toString(8));
  }

  @Test
  public void testToString9() {
    assertEquals("A", Pitch.toString(9));
  }

  @Test
  public void testToString10() {
    assertEquals("A#", Pitch.toString(10));
  }

  @Test
  public void testToString11() {
    assertEquals("B", Pitch.toString(11));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testToString12() {
    assertEquals("B", Pitch.toString(14));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testToString13() {
    assertEquals("B", Pitch.toString(-1));
  }

  @Test
  public void testToPitch() {
    assertEquals(Pitch.C, Pitch.toPitch(0));
  }

  @Test
  public void testToPitch1() {
    assertEquals(Pitch.CSHARP, Pitch.toPitch(1));
  }

  @Test
  public void testToPitch2() {
    assertEquals(Pitch.D, Pitch.toPitch(2));
  }

  @Test
  public void testToPitch3() {
    assertEquals(Pitch.DSHARP, Pitch.toPitch(3));
  }

  @Test
  public void ttestToPitch4() {
    assertEquals(Pitch.E, Pitch.toPitch(4));
  }

  @Test
  public void testToPitch5() {
    assertEquals(Pitch.F, Pitch.toPitch(5));
  }

  @Test
  public void testToPitch6() {
    assertEquals(Pitch.FSHARP, Pitch.toPitch(6));
  }

  @Test
  public void testToPitch7() {
    assertEquals(Pitch.G, Pitch.toPitch(7));
  }

  @Test
  public void testToPitch8() {
    assertEquals(Pitch.GSHARP, Pitch.toPitch(8));
  }

  @Test
  public void testToPitch9() {
    assertEquals(Pitch.A, Pitch.toPitch(9));
  }

  @Test
  public void testToPitch10() {
    assertEquals(Pitch.ASHARP, Pitch.toPitch(10));
  }

  @Test
  public void testToPitch11() {
    assertEquals(Pitch.B, Pitch.toPitch(11));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testToPitch12() {
    assertEquals(Pitch.B, Pitch.toPitch(14));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testToPitch13() {
    assertEquals(Pitch.B, Pitch.toPitch(-1));
  }
}
