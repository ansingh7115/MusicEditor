package cs3500.music.tests;

import org.junit.*;

import cs3500.music.model.*;
import cs3500.music.view.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;

/**
 * Tests for ConsoleView
 */
public class ConsoleViewTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @After
  public void cleanUpStreams() {
    System.setOut(null);
    System.setErr(null);
  }

  @Test
  public void testRender() {
    MusicEditorModel music = new MusicEditorModelImpl();
    ViewModel vm = new ViewModel(music);
    MusicEditorView view = new ConsoleViewImpl();
    Note n = new Note(0, 1, 2, Pitch.G, 0, 100);
    music.addPlayable(n);
    view.render(vm);
    assertEquals("   G2\n" +
        "0  X\n", outContent.toString());
  }

  @Test
  public void testRender1() {
    MusicEditorModel music = new MusicEditorModelImpl();
    ViewModel vm = new ViewModel(music);
    MusicEditorView view = new ConsoleViewImpl();
    Note n = new Note(0, 1, 0, Pitch.C, 0, 100);
    music.addPlayable(n);
    view.render(vm);
    assertEquals("   C0\n" +
        "0  X\n", outContent.toString());
  }

  @Test
  public void testRender2() {
    MusicEditorModel music = new MusicEditorModelImpl();
    ViewModel vm = new ViewModel(music);
    MusicEditorView view = new ConsoleViewImpl();
    Note n = new Note(0, 1, 10, Pitch.B, 100, 2);
    music.addPlayable(n);
    view.render(vm);
    assertEquals("  B10\n" +
        "0  X\n", outContent.toString());
  }

  @Test
  public void testRender3() {
    MusicEditorModel music = new MusicEditorModelImpl();
    ViewModel vm = new ViewModel(music);
    MusicEditorView view = new ConsoleViewImpl();
    Note n = new Note(0, 1, 10, Pitch.B, 100, 2);
    Note n1 = new Note(1, 2, 10, Pitch.B, 100, 2);
    music.addPlayable(n);
    music.addPlayable(n1);
    view.render(vm);
    assertEquals("  B10\n" +
        "0  X\n" +
        "1  X\n" +
        "2  |\n", outContent.toString());
  }

  @Test
  public void testRender5() {
    MusicEditorModel music = new MusicEditorModelImpl();
    ViewModel vm = new ViewModel(music);
    MusicEditorView view = new ConsoleViewImpl();
    Note n = new Note(10, 2, 10, Pitch.B, 100, 2);
    music.addPlayable(n);
    view.render(vm);
    assertEquals("   B10\n" +
            " 0   \n" +
            " 1   \n" +
            " 2   \n" +
            " 3   \n" +
            " 4   \n" +
            " 5   \n" +
            " 6   \n" +
            " 7   \n" +
            " 8   \n" +
            " 9   \n" +
            "10  X\n" +
            "11  |\n",
        outContent.toString());
  }

  @Test
  public void testRender6() {
    MusicEditorModel music = new MusicEditorModelImpl();
    ViewModel vm = new ViewModel(music);
    MusicEditorView view = new ConsoleViewImpl();
    view.render(vm);
    assertEquals("", outContent.toString());
  }

  @Test
  public void testRender7() {
    MusicEditorModel music = new MusicEditorModelImpl();
    MusicEditorView view = new ConsoleViewImpl();
    music.addPlayable(new Note(0, 7, 2, Pitch.G, 0, 100));
    music.addPlayable(new Note(0, 2, 3, Pitch.E, 0, 100));
    music.addPlayable(new Note(2, 2, 3, Pitch.D, 0, 100));
    music.addPlayable(new Note(4, 2, 3, Pitch.C, 0, 100));
    music.addPlayable(new Note(6, 2, 3, Pitch.D, 0, 100));
    music.addPlayable(new Note(8, 7, 2, Pitch.G, 0, 100));
    music.addPlayable(new Note(8, 2, 3, Pitch.E, 0, 100));
    music.addPlayable(new Note(10, 2, 3, Pitch.E, 0, 100));
    music.addPlayable(new Note(12, 3, 3, Pitch.E, 0, 100));
    music.addPlayable(new Note(16, 8, 2, Pitch.G, 0, 100));
    music.addPlayable(new Note(16, 2, 3, Pitch.D, 0, 100));
    music.addPlayable(new Note(18, 2, 3, Pitch.D, 0, 100));
    music.addPlayable(new Note(20, 3, 3, Pitch.D, 0, 100));
    music.addPlayable(new Note(24, 2, 2, Pitch.G, 0, 100));
    music.addPlayable(new Note(24, 2, 3, Pitch.E, 0, 100));
    music.addPlayable(new Note(26, 2, 3, Pitch.G, 0, 100));
    music.addPlayable(new Note(28, 4, 3, Pitch.G, 0, 100));
    music.addPlayable(new Note(32, 8, 2, Pitch.G, 0, 100));
    music.addPlayable(new Note(40, 8, 2, Pitch.G, 0, 100));
    music.addPlayable(new Note(48, 8, 2, Pitch.G, 0, 100));
    music.addPlayable(new Note(32, 2, 3, Pitch.E, 0, 100));
    music.addPlayable(new Note(34, 2, 3, Pitch.E, 0, 100));
    music.addPlayable(new Note(36, 2, 3, Pitch.C, 0, 100));
    music.addPlayable(new Note(38, 2, 3, Pitch.D, 0, 100));
    music.addPlayable(new Note(40, 2, 3, Pitch.E, 0, 100));
    music.addPlayable(new Note(42, 2, 3, Pitch.E, 0, 100));
    music.addPlayable(new Note(44, 2, 3, Pitch.E, 0, 100));
    music.addPlayable(new Note(46, 2, 3, Pitch.E, 0, 100));
    music.addPlayable(new Note(48, 2, 3, Pitch.D, 0, 100));
    music.addPlayable(new Note(50, 2, 3, Pitch.D, 0, 100));
    music.addPlayable(new Note(52, 2, 3, Pitch.E, 0, 100));
    music.addPlayable(new Note(54, 2, 3, Pitch.D, 0, 100));
    music.addPlayable(new Note(56, 8, 2, Pitch.E, 0, 100));
    music.addPlayable(new Note(56, 8, 3, Pitch.C, 0, 100));
    ViewModel vm = new ViewModel(music);
    view.render(vm);
    assertEquals("    E2  F2 F#2  G2 G#2  A2 A#2  B2  C3 C#3  D3 D#3  E3  F3 F#3  G3\n" +
            " 0              X                                   X            \n" +
            " 1              |                                   |            \n" +
            " 2              |                           X                    \n" +
            " 3              |                           |                    \n" +
            " 4              |                   X                            \n" +
            " 5              |                   |                            \n" +
            " 6              |                           X                    \n" +
            " 7                                          |                    \n" +
            " 8              X                                   X            \n" +
            " 9              |                                   |            \n" +
            "10              |                                   X            \n" +
            "11              |                                   |            \n" +
            "12              |                                   X            \n" +
            "13              |                                   |            \n" +
            "14              |                                   |            \n" +
            "15                                                               \n" +
            "16              X                           X                    \n" +
            "17              |                           |                    \n" +
            "18              |                           X                    \n" +
            "19              |                           |                    \n" +
            "20              |                           X                    \n" +
            "21              |                           |                    \n" +
            "22              |                           |                    \n" +
            "23              |                                                \n" +
            "24              X                                   X            \n" +
            "25              |                                   |            \n" +
            "26                                                              X\n" +
            "27                                                              |\n" +
            "28                                                              X\n" +
            "29                                                              |\n" +
            "30                                                              |\n" +
            "31                                                              |\n" +
            "32              X                                   X            \n" +
            "33              |                                   |            \n" +
            "34              |                                   X            \n" +
            "35              |                                   |            \n" +
            "36              |                   X                            \n" +
            "37              |                   |                            \n" +
            "38              |                           X                    \n" +
            "39              |                           |                    \n" +
            "40              X                                   X            \n" +
            "41              |                                   |            \n" +
            "42              |                                   X            \n" +
            "43              |                                   |            \n" +
            "44              |                                   X            \n" +
            "45              |                                   |            \n" +
            "46              |                                   X            \n" +
            "47              |                                   |            \n" +
            "48              X                           X                    \n" +
            "49              |                           |                    \n" +
            "50              |                           X                    \n" +
            "51              |                           |                    \n" +
            "52              |                                   X            \n" +
            "53              |                                   |            \n" +
            "54              |                           X                    \n" +
            "55              |                           |                    \n" +
            "56  X                               X                            \n" +
            "57  |                               |                            \n" +
            "58  |                               |                            \n" +
            "59  |                               |                            \n" +
            "60  |                               |                            \n" +
            "61  |                               |                            \n" +
            "62  |                               |                            \n" +
            "63  |                               |                            \n",
        outContent.toString());
  }

  @Test
  public void testRender8() {
    MusicEditorModel music = new MusicEditorModelImpl();
    MusicEditorView view = new ConsoleViewImpl();
    music.addPlayable(new Note(0, 7, 10, Pitch.G, 0, 100));
    music.addPlayable(new Note(0, 7, 0, Pitch.A, 0, 100));
    ViewModel vm = new ViewModel(music);
    view.render(vm);
    assertEquals("   A0 A#0  B0  C1 C#1  D1 D#1  E1  F1 F#1  G1 G#1  A1 A#1  B1  " +
        "C2 C#2  D2 D#2  E2  F2 F#2  G2 G#2  A2 A#2  B2  C3 C#3  D3 D#3  E3  F3 F#3  G3 " +
        "G#3  A3 A#3  B3  C4 C#4  D4 D#4  E4  F4 F#4  G4 G#4  A4 A#4  B4  C5 C#5  D5 D#5  " +
        "E5  F5 F#5  G5 G#5  A5 A#5  B5  C6 C#6  D6 D#6  E6  F6 F#6  G6 G#6  A6 A#6  B6  C7 " +
        "C#7  D7 D#7  E7  F7 F#7  G7 G#7  A7 A#7  B7  C8 C#8  D8 D#8  E8  F8 F#8  G8 G#8  A8 " +
        "A#8  B8  C9 C#9  D9 D#9  E9  F9 F#9  G9 G#9  A9 A#9  B9 C10C#10 D10D#10 E10 F10F#10 " +
        "G10\n" +
        "0  X" + String.format("%471s", "") + "X\n" +
        "1  |" + String.format("%471s", "") + "|\n" +
        "2  |" + String.format("%471s", "") + "|\n" +
        "3  |" + String.format("%471s", "") + "|\n" +
        "4  |" + String.format("%471s", "") + "|\n" +
        "5  |" + String.format("%471s", "") + "|\n" +
        "6  |" + String.format("%471s", "") + "|\n", outContent.toString());
  }

  @Test
  public void testRender9() {
    MusicEditorModel music = new MusicEditorModelImpl();
    MusicEditorView view = new ConsoleViewImpl();
    music.addPlayable(new Note(3, 7, 0, Pitch.A, 0, 100));
    music.addPlayable(new Note(0, 7, 0, Pitch.A, 0, 100));
    ViewModel vm = new ViewModel(music);
    view.render(vm);
    assertEquals("    A0\n" +
        " 0  X\n" +
        " 1  |\n" +
        " 2  |\n" +
        " 3  X\n" +
        " 4  |\n" +
        " 5  |\n" +
        " 6  |\n" +
        " 7  |\n" +
        " 8  |\n" +
        " 9  |\n", outContent.toString());
  }
}
