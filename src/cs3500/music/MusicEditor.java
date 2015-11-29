package cs3500.music;

import java.io.FileReader;

import cs3500.music.controller.GuiController;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.MusicEditorModelImpl;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;

/**
 * Main method for MusicEditor program
 */
public final class MusicEditor {
  public static void main(String[] args) throws Exception {
    CompositionBuilder<MusicEditorModel> builder = new MusicEditorModelImpl.Builder();
    GuiController controller = new GuiController();
    controller.init(MusicReader.parseFile(new FileReader(args[1]), builder),
        GuiController.ViewFactory.create(args[0]));
  }
}
