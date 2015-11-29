//package cs3500.music.tests;
//
//
//import cs3500.music.model.MusicEditorModelImpl;
//import cs3500.music.model.Note;
//
//import org.junit.*;
//
//import javax.sound.midi.MidiUnavailableException;
//
//import cs3500.music.model.Pitch;
//import cs3500.music.view.MidiViewImpl;
//import cs3500.music.view.ViewModel;
//
//import static org.junit.Assert.*;
//
//public class MidiViewTest {
//  @Test
//  public void testMidi() throws MidiUnavailableException {
//    MidiViewImpl view = new MidiViewImpl(new StringBuilder(""));
//    MusicEditorModelImpl music = new MusicEditorModelImpl();
//    music.addPlayable(new Note(0, 2, 5, Pitch.C, 1, 64));
//    ViewModel vm = new ViewModel(music);
//    view.render(vm);
//    assertEquals("Open\nnote 0 60 64\n" +
//        "note 1 60 64\n" +
//        "Closed.", MidiViewImpl.result.toString());
//  }
//  @Test
//  public void testMidi1() throws MidiUnavailableException {
//    MidiViewImpl view = new MidiViewImpl(new StringBuilder(""));
//    MusicEditorModelImpl music = new MusicEditorModelImpl();
//    music.addPlayable(new Note(0, 2, 5, Pitch.C, 1, 64));
//    music.addPlayable(new Note(10, 2, 5, Pitch.C, 1, 64));
//    ViewModel vm = new ViewModel(music);
//    view.render(vm);
//    assertEquals("Open\nnote 0 60 64\n" +
//        "note 1 60 64\n" +
//        "note 10 60 64\n" +
//        "note 11 60 64\n" +
//        "Closed.", MidiViewImpl.result.toString());
//  }
//  @Test
//  public void testMidi2() throws MidiUnavailableException {
//    MidiViewImpl view = new MidiViewImpl(new StringBuilder(""));
//    MusicEditorModelImpl music = new MusicEditorModelImpl();
//    music.addPlayable(new Note(0, 2, 0, Pitch.C, 1, 7));
//    music.addPlayable(new Note(10, 2, 10, Pitch.C, 1, 2));
//    ViewModel vm = new ViewModel(music);
//    view.render(vm);
//    assertEquals("Open\nnote 0 0 7\n" +
//        "note 1 0 7\n" +
//        "note 10 120 2\n" +
//        "note 11 120 2\n" +
//        "Closed.", MidiViewImpl.result.toString());
//  }
//  @Test
//  public void testMidi3() throws MidiUnavailableException {
//    MidiViewImpl view = new MidiViewImpl(new StringBuilder(""));
//    MusicEditorModelImpl music = new MusicEditorModelImpl();
//    music.addPlayable(new Note(0, 2, 0, Pitch.C, 1, 7));
//    music.addPlayable(new Note(0, 2, 10, Pitch.C, 1, 2));
//    ViewModel vm = new ViewModel(music);
//    view.render(vm);
//    assertEquals("Open\nnote 0 120 2\n" +
//        "note 0 0 7\n" +
//        "note 1 120 2\n" +
//        "note 1 0 7\n" +
//        "Closed.", MidiViewImpl.result.toString());
//  }
//  @Test
//  public void testMidi4() throws MidiUnavailableException {
//    MidiViewImpl view = new MidiViewImpl(new StringBuilder(""));
//    MusicEditorModelImpl music = new MusicEditorModelImpl();
//    music.addPlayable(new Note(0, 2, 0, Pitch.A, 1, 7));
//    music.addPlayable(new Note(2, 2, 4, Pitch.FSHARP, 1, 22));
//    ViewModel vm = new ViewModel(music);
//    view.render(vm);
//    assertEquals("Open\nnote 0 9 7\n" +
//        "note 1 9 7\n" +
//        "note 2 54 22\n" +
//        "note 3 54 22\n" +
//        "Closed.", MidiViewImpl.result.toString());
//  }
//  @Test
//  public void testMidi5() throws MidiUnavailableException {
//    MidiViewImpl view = new MidiViewImpl(new StringBuilder(""));
//    MusicEditorModelImpl music = new MusicEditorModelImpl();
//    music.addPlayable(new Note(0, 2, 0, Pitch.A, 1, 7));
//    music.addPlayable(new Note(2, 2, 4, Pitch.FSHARP, 1, 22));
//    music.addPlayable(new Note(3, 2, 4, Pitch.G, 1, 1));
//    music.addPlayable(new Note(2222, 5, 6, Pitch.A, 1, 4));
//    music.addPlayable(new Note(123, 2, 1, Pitch.D, 1, 100));
//    ViewModel vm = new ViewModel(music);
//    view.render(vm);
//    assertEquals("Open\nnote 0 9 7\n" +
//        "note 1 9 7\n" +
//        "note 2 54 22\n" +
//        "note 3 55 1\n" +
//        "note 3 54 22\n" +
//        "note 4 55 1\n" +
//        "note 123 14 100\n" +
//        "note 124 14 100\n" +
//        "note 2222 81 4\n" +
//        "note 2226 81 4\n" +
//        "Closed.", MidiViewImpl.result.toString());
//  }
//}
