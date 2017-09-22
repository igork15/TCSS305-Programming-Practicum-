/* 
 * TCSS 305 – Autumn 2016
 * Assignment 6b – Tetris-b
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Board;
import model.MovableTetrisPiece;

/** This class is used to create the information panel for the game.
 * 
 * @author Igor Kalezic
 * @version 9 December 2016
 */
public class InformationPanel extends JPanel implements Observer {
    
    /**
     * Serialization.
     */
    private static final long serialVersionUID = -7942054431238193617L;
    
    /**
     * Initial lines to clear for next level.
     */
    private static final int INITIAL_LINES = 5;
    /**
     * Amount score is incremented.
     */
    private static final int SCORE_INCREMENT = 200;
    /**
     * Static panel size field.
     */
    private static final int PANEL_SIZE = 160;
    
    /**
     * Font Size.
     */
    private static final int FONT_SIZE = 25;
    
    /**
     * Playing board.
     */
    protected Board myBoard;
    
    /**
     * Current score.
     */
    private int myScore;
    
    /**
     * Font style.
     */
    private String myFont;
    
    /**
     * Current level.
     */
    private int myLevel;
    
    /**
     * Lines to clear for next level.
     */
    private int myClearLines;
    
    /**
     * Current amount of lines cleared.
     */
    private int myCurrentLinesCleared;
       
    
    /**
     * Constructor.
     * @param theBoard playing board.
     */
    public InformationPanel(final Board theBoard) {
        super();
        setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
        setBackground(Color.gray);
        myBoard = theBoard;
        myBoard.addObserver(this);
        setVisible(true);
        
        initializeFields();
        
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        drawScore(g2d);
        drawLevel(g2d);
        drawNewLevel(g2d);
        drawCurrentLines(g2d);
    }
    
    /**
     * Initializes fields.
     */
    private void initializeFields() {
        
        myLevel = 1;
        myScore = 0;
        myClearLines = INITIAL_LINES;
        myCurrentLinesCleared = 0;
        myFont = "TimesRoman";
    }
    
    /**
     * Displays the score of the game.
     * @param theGraphics drawing graphics.
     */
    public void drawScore(final Graphics2D theGraphics) {
        theGraphics.setFont(new Font(myFont, Font.PLAIN, FONT_SIZE));
        theGraphics.drawString("Score: " + myScore, PANEL_SIZE / 4, PANEL_SIZE / 2);
    }
    
    /**
     * Displays the current level the player is on.
     * @param theGraphics drawing graphics.
     */
    public void drawLevel(final Graphics2D theGraphics) {
        theGraphics.setFont(new Font(myFont, Font.PLAIN, FONT_SIZE));
        theGraphics.drawString("Level: " + myLevel, PANEL_SIZE / 4, PANEL_SIZE / 4);
    }
    
    /**
     * Displays how many lines to clear to reach next level.
     * @param theGraphics drawing graphics
     */
    public void drawNewLevel(final Graphics2D theGraphics) {
        theGraphics.setFont(new Font(myFont, Font.PLAIN, 14));
        theGraphics.drawString("Level up in " + myClearLines + " lines cleared", 2, 120);
    }
    
    /**
     * Displays the current amount of lines cleared.
     * @param theGraphics drawing graphics.
     */
    public void drawCurrentLines(final Graphics2D theGraphics) {
        theGraphics.setFont(new Font(myFont, Font.PLAIN, 14));
        theGraphics.drawString("Current lines cleared: " + myCurrentLinesCleared, 2, 100);
    }
    
    /**
     * Resets the score to 0.
     */
    public void resetScore() {
        myScore = 0;
    }
    
    /**
     * Resets the score to 0.
     */
    public void resetLinesToClear() {
        myClearLines = INITIAL_LINES;
    }
    /**
     * Resets the level to 1.
     */
    public void resetLevel() {
        myLevel = 1;
    }
    
    /**
     * resets the lines cleared to 0.
     */
    public void resetLinesCleared() {
        myCurrentLinesCleared = 0;
    }
    
    @Override
    public void update(final Observable theObject, final Object theArg) {
        if (theArg instanceof MovableTetrisPiece) {
            myScore++;
            
        } else if (theArg instanceof Boolean) {           
            myClearLines = INITIAL_LINES;
            
        } else if (theArg instanceof Integer[]) {
            int numLines = ((Integer[]) theArg).length;
            
            myScore += SCORE_INCREMENT * numLines;
            
            myCurrentLinesCleared += numLines;
            
            if (numLines > myClearLines) {
                numLines = numLines - myClearLines;
                myClearLines = INITIAL_LINES;
                myClearLines -= numLines;
                myLevel++;
                                
            } else {
                
                myClearLines -= numLines;                
            }
            
            
            
            if (myClearLines == 0) {
                myClearLines = INITIAL_LINES;
                myLevel++;
            }
        }
        repaint();
        
    }

}
