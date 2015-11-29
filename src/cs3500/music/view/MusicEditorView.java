package cs3500.music.view;

/**
 * An interface for visually representing a music editor
 */
public interface MusicEditorView {
  /**
   * Displays the state of the music editor for the user
   *
   * @param vm the particular editor to be displayed
   */
  void render(ViewModel vm);
}