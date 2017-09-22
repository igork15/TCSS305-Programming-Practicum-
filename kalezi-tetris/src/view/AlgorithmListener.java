/* 
 * TCSS 305 – Autumn 2016
 * Assignment 6b – Tetris-b
 */
package view;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;


/** This class is the action for the scoring algorithm button.
 * 
 * 
 * @author Igor Kalezic
 * @version 9 December 2016 
 */
public class AlgorithmListener extends AbstractAction {
    

    /**
     * Serialization.
     */
    private static final long serialVersionUID = -873390410990601702L;


    
    /**
     * What occurs when button is pressed.
     * @param theEvent the event that occurs.
     */
    public void actionPerformed(final ActionEvent theEvent) {
        JOptionPane.showMessageDialog(null, "If a piece reaches the bottom 1 point is earned\n"
                                             + "If a line is cleared 200 points are earned.");
    }

}
