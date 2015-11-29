Files:
- MusicEditorModel.java (interface)
- MusicEditorModelImpl.java (implements MusicEditorModel)
- Note.java (extends Playable)
- Pitch.java (enum)
- Playable.java (abstract class)
- MusicEditorView.java (interface)
- ConsoleViewImpl.java (implements MusicEditorView)
- GuiViewImpl.java (implements GuiView, extends JFrame)
- GridAndPlayables.java (extends JFrame)
- CompositeViewImpl.java (implements GuiView)
- MidiViewImpl.java (implements MusicEditorView)
- MockMidi files (for testing)
- ViewModel.java
- GuiController.java
- KeyboardHandler (implements KeyListener)
- MouseHandler (implements MouseListener)

Test Files:
- ConsoleViewTest
- MusicEditorTest
- MusicEditorImplTest
- NoteTest
- PlayableTest
- MidiViewTest
- KeyboardHandlerTest
- MouseHandlerTest
- ViewModelTest

Class invariants are included as comments in the individual concrete classes, and are enforced
throughout the implementation.

For the model, we have an interface that contains methods to add playables, remove playables,
get playables at a beat, etc. Our implementation of this uses a TreeMap as the main data structure,
and has other fields to keep track of important values. All of the fields are private.

We also have an abstract Playable class with a package-local constructor. This allows us to
show this type in the interface. We have a concrete class that extends this called Note, which
contains a starting time, beats, and note index (0 - 127 value) fields. We also have a Pitch enum
with all of the different pitches a note can have.

For the view, we have an interface that contains one method, render. It takes in a model for us to
work with. There are two concrete implementations for this interface: the console UI and the GUI UI.
The GUI UI uses JavaFX, while console view essentially builds up to StringBuilders and combines
them at the end before printing.

There is a ViewModel in order to hide certain parts of the model from the view.

In order to begin using the main method, arg[0] is the type of view you'd like (either "visual"
or "composite") and arg[1] is the name of the txt file with MIDI information. There is no way to
create a console view at this time because we need to create a controller specifically for the
console. Instead, in order to have the view factory make sense, we converted "console" to "visual."

Documented Changes (1):
- ArrayList<ArrayList<Note>> to TreeMap<Integer, HashSet<Note>>
  - The keys of the TreeMap are the beats of the piece and the HashSet<Note> is every note
    at that key
- Changed the fields that a Note has - converts straight to 0 - 127 value (simpler implementation)
- Added method in interface to getPlayablesAtBeat(int) for convenience
- Shuffled around getters and removed all setters to make Playables immutable
- Created an abstract Playable class that Notes will implement to clean up method signatures
- Removed method to edit notes in the model
- Added fields in the model implementation to keep track of lowest note, highest note, last beat
- Added tempo field to the model to support MIDI
- Implemented utils package (CompositionBuilder, MusicReader)
- Created main method
- Created ViewModel and ViewModelConcrete to not allow the view to create or remove a playable

Documented Changes (2):
- ViewModel became a concrete instead of an abstract class for implementation in the GuiController
- Modified addPlayable and getPlayablesAtBeat to be more efficient since GuiView scrolled slow
on long pieces
- Modified from sending all notes to MIDI to Timer to send one note at a time
- Converted GuiViewImpl from JavaFX to Swing because JavaFX sucks
- Updated tests to reflect minor changes (less intensive tests for MIDI)
- Updated MusicEditor.java (main method) to reflect addition of controller
- MIDI tests are commented out because, as discussed in class, it is no longer possible to reliably
  test MIDI output

How to add, remove, and edit notes in GuiViewImpl:
- Add note: click mouse on the pitch and beat you'd like to add to. The length can be set by
  dragging the note however long you'd like it to be
- Remove note: type "r", and then click the head (black part) of a note to remove it
- Edit note: type "e", and then click and drag the head (black part) of a note to a new location