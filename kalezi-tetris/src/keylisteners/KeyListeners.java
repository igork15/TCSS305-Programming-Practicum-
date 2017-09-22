/* 
 * TCSS 305 � Autumn 2016
 * Assignment 6b � Tetris-b
 */

package keylisteners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



import model.Board;
import view.BoardPanel;

/** This class is used to create the controls for the game.
 * 
 * @author Igor Kalezic
 * @version 9 December 2016
 */
public class KeyListeners extends KeyAdapter {
    
    
    /**
     * Board field.
     */
    private final Board myBoard;
    
    /**
     * Board panel field.
     */
    private final BoardPanel myBoardPanel;
    
    
    /**
     * Constructor.
     * 
     * @param theBoard board.
     * @param theBoardPanel game panel.
     */
    public KeyListeners(final Board theBoard, final BoardPanel theBoardPanel) {
        super();
        myBoard = theBoard;
        myBoardPanel = theBoardPanel;
        
        
        
    }
    
    @Override
    public void keyPressed(final KeyEvent theEvent) {
        
            
        if (theEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            myBoard.left();
        } else if (theEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            myBoard.right();           
        } else if (theEvent.getKeyCode() == KeyEvent.VK_UP) {
            myBoard.rotate();
        } else if (theEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            myBoard.down();
        } else if (theEvent.getKeyCode() == KeyEvent.VK_SPACE) {
            myBoard.drop();
        } else if (theEvent.getKeyCode() == KeyEvent.VK_P) {
            myBoardPanel.pauseGame();
        }
                
    }
    

}
