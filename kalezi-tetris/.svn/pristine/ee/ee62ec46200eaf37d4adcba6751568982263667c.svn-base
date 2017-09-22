/* 
 * TCSS 305 – Autumn 2016
 * Assignment 6b – Tetris-b
 */

package view;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;



/** This class is the action for the green background button.
 * 
 * 
 * @author Igor Kalezic
 * @version 9 December 2016 
 */
public class GreenBackgroundListener extends AbstractAction {
    
    /**
     * Serialization.
     */
    private static final long serialVersionUID = -8966514131945752422L;
    /**
     * Playing board.
     */
    private final BoardPanel myBoard;
    

    /**
     * Default constructor.
     * @param theBoard playing board.
     */
    public GreenBackgroundListener(final BoardPanel theBoard) {
        super();
        myBoard = theBoard;
    }
    
    /**
     * What occurs when button is pressed.
     * @param theEvent the event that occurs.
     */
    public void actionPerformed(final ActionEvent theEvent) {
        myBoard.setBackground(Color.GREEN);
    }

}