/* 
 * TCSS 305 – Autumn 2016
 * Assignment 6b – Tetris-b
 */

package view;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;



/** This class is the action for the black background button.
 * 
 * 
 * @author Igor Kalezic
 * @version 9 December 2016 
 */
public class WhiteBackgroundListener extends AbstractAction {
    
    /**
     * Serialization.
     */
    private static final long serialVersionUID = -4614938600109962867L;
    

    /**
     * Playing board.
     */
    private final BoardPanel myBoard;
    

    /**
     * Default constructor.
     * @param theBoard playing board.
     */
    public WhiteBackgroundListener(final BoardPanel theBoard) {
        super();
        myBoard = theBoard;
    }
    
    /**
     * What occurs when button is pressed.
     * @param theEvent the event that occurs.
     */
    public void actionPerformed(final ActionEvent theEvent) {
        myBoard.setBackground(Color.WHITE);
    }

}
