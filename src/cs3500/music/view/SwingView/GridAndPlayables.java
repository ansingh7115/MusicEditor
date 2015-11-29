package cs3500.music.view.SwingView;

import java.awt.*;

import javax.swing.*;

import cs3500.music.model.Playable;
import cs3500.music.view.ViewModel;

/**
 * Represents the grid and playables of an {@link GuiViewImpl}
 */
public final class GridAndPlayables extends JPanel {
  private final ViewModel vm;

  /**
   * Constructor for a {@link GridAndPlayables}
   *
   * @param vm the view-model
   */
  public GridAndPlayables(ViewModel vm) {
    this.vm = vm;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.paintPlayables(g);
    this.paintGrid(g);
    this.paintCurrBeatLine(GuiViewImpl.currBeat, g);
    this.repaint();
  }

  /**
   * Draws the current beat red line
   *
   * @param currBeat the current beat of the piece of music
   * @param g graphics
   */
  private void paintCurrBeatLine(int currBeat, Graphics g) {
    if (currBeat >= this.vm.getLastBeat() - 1) {
      currBeat++;
    }
    g.setColor(Color.RED);
    g.drawLine(currBeat * GuiViewImpl.BLOCK, 0, currBeat * GuiViewImpl.BLOCK,
        (this.vm.getHighestPlayableVal() - this.vm.getLowestPlayableVal() + 1) * GuiViewImpl.BLOCK);
  }

  /**
   * Draws the grid
   *
   * @param g graphics
   */
  private void paintGrid(Graphics g) {
    g.setColor(Color.BLACK);
    for (int i = 0; i <= this.vm.getHighestPlayableVal() - this.vm.getLowestPlayableVal() + 1;
         i++) {
      ((Graphics2D) g).setStroke(new BasicStroke(2));
      if ((this.vm.getHighestPlayableVal() - i + 1) % 12 == 0) {
        ((Graphics2D) g).setStroke(new BasicStroke(3));
      }
      g.drawLine(0, i * GuiViewImpl.BLOCK, this.vm.getLastBeat() * GuiViewImpl.BLOCK,
          i * GuiViewImpl.BLOCK);
    }

    for (int i = 0; i <= this.vm.getLastBeat(); i++) {
      if (i % 4 == 0 || i == this.vm.getLastBeat()) {
        g.drawLine(i * GuiViewImpl.BLOCK, 0, i * GuiViewImpl.BLOCK,
            (this.vm.getHighestPlayableVal() - this.vm.getLowestPlayableVal() + 1)
                * GuiViewImpl.BLOCK);
      }
    }
  }

  /**
   * Draws all of the {@link Playable}s in the piece of music
   *
   * @param g graphics
   */
  private void paintPlayables(Graphics g) {
    super.paintComponent(g);
    for (int i = 0; i < this.vm.getLastBeat(); i++) {
      for (Playable p : this.vm.getPlayablesAtBeat(i)) {
        if (p.getStartTime() == i) {
          this.paintPlayable(p, g);
        }
      }
    }
  }

  /**
   * Draws one {@link Playable}
   *
   * @param p the {@link Playable} to draw
   * @param g graphics
   */
  private void paintPlayable(Playable p, Graphics g) {
    g.setColor(Color.BLACK);
    g.fillRect(p.getStartTime() * GuiViewImpl.BLOCK,
        (this.vm.getHighestPlayableVal() - p.getPlayableVal()) * GuiViewImpl.BLOCK,
        GuiViewImpl.BLOCK, GuiViewImpl.BLOCK);
    g.setColor(Color.GREEN);
    g.fillRect((p.getStartTime() + 1) * GuiViewImpl.BLOCK,
        (this.vm.getHighestPlayableVal() - p.getPlayableVal()) * GuiViewImpl.BLOCK,
        (p.getBeats() - 1) * GuiViewImpl.BLOCK, GuiViewImpl.BLOCK);
  }
}