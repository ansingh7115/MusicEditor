package cs3500.music.view.SwingView;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.*;

import cs3500.music.model.Pitch;
import cs3500.music.view.GuiView;
import cs3500.music.view.ViewModel;

/**
 * Represents a GUI view using Swing
 */
public final class GuiViewImpl extends JFrame implements GuiView {
  public static final int BLOCK = 20;
  private final JFrame frame = new JFrame("Music Editor - Thomas Pedbereznak & Brian Roth");
  private final JPanel root = new JPanel(new BorderLayout());
  private final JScrollPane scroll = new JScrollPane(root);
  private ViewModel vm;
  static int currBeat;

  @Override
  public void render(ViewModel vm) {
    this.vm = vm;
    this.init();
  }

  @Override
  public void arrowUp() {
    this.scroll.getVerticalScrollBar().setValue(this.scroll.getVerticalScrollBar().getValue() -
        this.scroll.getVerticalScrollBar().getUnitIncrement());
  }

  @Override
  public void arrowDown() {
    this.scroll.getVerticalScrollBar().setValue(this.scroll.getVerticalScrollBar().getValue() +
        this.scroll.getVerticalScrollBar().getUnitIncrement());
  }

  @Override
  public void arrowRight() {
    this.scroll.getHorizontalScrollBar().setValue(this.scroll.getHorizontalScrollBar().getValue() +
        this.scroll.getHorizontalScrollBar().getUnitIncrement());
  }

  @Override
  public void arrowLeft() {
    this.scroll.getHorizontalScrollBar().setValue(this.scroll.getHorizontalScrollBar().getValue() -
        this.scroll.getHorizontalScrollBar().getUnitIncrement());
  }

  @Override
  public void jumpToBeginning() {
    this.scroll.getHorizontalScrollBar().setValue(0);
  }

  @Override
  public void jumpToEnd() {
    this.scroll.getHorizontalScrollBar().setValue(this.vm.getLastBeat() * BLOCK);
  }

  @Override
  public void pause() {}

  @Override
  public void addKeyListener(KeyListener k) {
    this.frame.addKeyListener(k);
  }

  @Override
  public void addMouseListener(MouseListener m) {
    this.root.addMouseListener(m);
  }

  @Override
  public void removeMouseListener(MouseListener m) {
    this.root.removeMouseListener(m);
  }

  /**
   * Updates the view based on the current beat of the piece of music
   *
   * @param currBeat the current beat of the piece of music
   */
  public void update(int currBeat) {
    GuiViewImpl.currBeat = currBeat;
    if (GuiViewImpl.currBeat * GuiViewImpl.BLOCK >= this.frame.getBounds().getSize().getWidth()
        - GuiViewImpl.BLOCK) {
      this.scroll.getHorizontalScrollBar().setValue(currBeat * GuiViewImpl.BLOCK);
    }
  }

  /**
   * Initializes the view
   */
  private void init() {
    GridAndPlayables gridAndPlayables = new GridAndPlayables(this.vm);
    Box pitchesPanel = drawPitches();
    Box timesBox = drawTimes();

    this.root.add(pitchesPanel, BorderLayout.WEST);
    this.root.add(timesBox, BorderLayout.NORTH);
    this.root.add(gridAndPlayables, BorderLayout.CENTER);

    this.scroll.getVerticalScrollBar().setUnitIncrement(16);
    this.scroll.getHorizontalScrollBar().setUnitIncrement(16);

    this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.frame.setPreferredSize(new Dimension(
        (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()),
        500));
    this.frame.setBackground(Color.WHITE);
    this.frame.add(scroll);
    this.frame.pack();
    this.frame.setVisible(true);
  }

  /**
   * Draws the pitches
   *
   * @return a vertical box with the pitches
   */
  private Box drawPitches() {
    Box pitches = Box.createVerticalBox();
    for (int i = this.vm.getHighestPlayableVal(); i >= this.vm.getLowestPlayableVal(); i--) {
      JLabel label = new JLabel(Pitch.toString(i % 12) + i / 12);
      pitches.add(Box.createVerticalStrut(BLOCK / 5));
      pitches.add(label);
    }
    return pitches;
  }

  /**
   * Draws the times
   *
   * @return a horizontal box with the times
   */
  private Box drawTimes() {
    Box times = Box.createHorizontalBox();
    times.add(Box.createHorizontalStrut((BLOCK * 2) - 16));
    for (int i = 0; i <= this.vm.getLastBeat(); i++) {
      if (i % 16 == 0) {
        Box container = Box.createHorizontalBox();
        container.setMinimumSize(new Dimension(BLOCK * 4, BLOCK));
        container.setMaximumSize(new Dimension(BLOCK * 4, BLOCK));
        container.setPreferredSize(new Dimension(BLOCK * 4, BLOCK));
        JLabel label = new JLabel(Integer.toString(i));
        container.add(label);
        times.add(container);
        i += 3;
      } else {
        times.add(Box.createHorizontalStrut(BLOCK));
      }
    }
    return times;
  }
}
