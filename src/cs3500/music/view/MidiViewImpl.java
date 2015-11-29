package cs3500.music.view;

import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.model.Playable;
import cs3500.music.view.MockMidi.MockMidiDevice;

/**
 * Represents Playback for MIDIs in a MusicEditor
 */
public final class MidiViewImpl implements MusicEditorView {
  private final Synthesizer synth;
  private final Receiver receiver;
  private int currBeat = 0;
  public static StringBuilder result = new StringBuilder("");

  /**
   * Constructor for a MidiViewImpl
   */
  public MidiViewImpl() {
    Synthesizer tempSynth = null;
    Receiver tempReceiver = null;
    try {
      tempSynth = MidiSystem.getSynthesizer();
      tempReceiver = tempSynth.getReceiver();
      tempSynth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    this.synth = tempSynth;
    this.receiver = tempReceiver;
  }

  /**
   * Constructor for a MidiViewImpl used for testing
   * @param sb the {@link StringBuilder} that will be used for the output stream
   */
  public MidiViewImpl(StringBuilder sb) {
    MidiViewImpl.result = sb;
    Synthesizer tempSynth = null;
    Receiver tempReceiver = null;
    try {
      tempSynth = new MockMidiDevice();
      tempReceiver = tempSynth.getReceiver();
      tempSynth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    this.synth = tempSynth;
    this.receiver = tempReceiver;
  }

  /**
   * Plays the {@link Playable}s in the piece of music
   *
   * @param vm the piece of music to tick
   */
  @Override
  public void render(ViewModel vm) {
    for (Playable p : vm.getPlayablesAtBeat(this.currBeat)) {
      try {
        if (this.currBeat == p.getStartTime()) {
          this.playNote(p);
        } else if (this.currBeat == p.getBeats() + p.getStartTime()) {
          this.stopNote(p);
        }
      } catch (InvalidMidiDataException e) {
        e.printStackTrace();
      }
    }
    this.currBeat++;
    if (this.currBeat >= vm.getLastBeat()) {
      this.receiver.close();
    }
  }

  /**
   * Plays the given {@link Playable}
   *
   * @param p the {@link Playable} to tick
   */
  public void playNote(Playable p) throws InvalidMidiDataException {
    Instrument[] instruments = this.synth.getDefaultSoundbank().getInstruments();
    this.synth.getChannels()[0].programChange(instruments[p.getInstrument()].getPatch()
        .getProgram());
    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, 0, p.getPlayableVal(),
        p.getVolume());
    this.receiver.send(start, p.getStartTime());
  }

  /**
   * Stops the given {@link Playable}
   *
   * @param p the {@link Playable} to stop
   */
  public void stopNote(Playable p) throws InvalidMidiDataException {
    Instrument[] instruments = this.synth.getDefaultSoundbank().getInstruments();
    this.synth.getChannels()[0].programChange(instruments[p.getInstrument()].getPatch()
        .getProgram());
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, 0, p.getPlayableVal(),
        p.getVolume());
    this.receiver.send(stop, p.getStartTime() + p.getBeats() - 1);
  }
}
