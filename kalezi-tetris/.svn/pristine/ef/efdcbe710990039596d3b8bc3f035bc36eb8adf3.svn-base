/* 
 * TCSS 305 – Autumn 2016
 * Assignment 6b – Tetris-b
 */

package view;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;


/** This class is the action for the help button.
 * 
 * 
 * @author Igor Kalezic
 * @version 9 December 2016 
 */
public class HelpListener extends AbstractAction {
    
    /**
     * Serialization.
     */
    private static final long serialVersionUID = 3328140104843550600L;

    /**
     * Default constructor.
     */
    public HelpListener() {
        super("Help");
        
    }
    
    /**
     * What occurs when button is pressed.
     * @param theEvent the event that occurs.
     */
    public void actionPerformed(final ActionEvent theEvent) {
        JOptionPane.showMessageDialog(null, "Move left: Left Arrow\n"
                                            +  "Move Right: Right Arrow\n"
                                            + "Move Down: Down Arrow\n"
                                            + "Drop Piece: Space\n"
                                            + "Pause game: P");
    }

}

