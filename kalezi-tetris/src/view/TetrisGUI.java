/* 
 * TCSS 305 – Autumn 2016
 * Assignment 6b – Tetris-b
 */

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import keylisteners.KeyListeners;
import model.Board;
import model.TetrisPiece;

/** This class is used to create the GUI for the game.
 * 
 * @author Igor Kalezic
 * @version 2 December 2016
 */
public class TetrisGUI extends JFrame implements Observer {
    
    /**
     * Serialization.
     */
    private static final long serialVersionUID = 4207812010507283766L;

    /**
     * Static next panel size field.
     */
    private static final int NEXT_PAN_SIZE = 160;
    
    /**
     * Static panel width field.
     */
    private static final int PAN_WIDTH = 400;
    
    /**
     * Static panel height field.
     */
    private static final int PAN_HEIGHT = 400;
    
    /**
     * Static frame size field.
     */
    private static final int FRAME_SIZE = 860;
    
    /**
     * Static width field.
     */
    private static final int WIDTH = 10;
    
    /**
     * Static height field.
     */
    private static final int HEIGHT = 20;
    
    /**
     * Board field.
     */
    private final Board myBoard;
    
    
    /**
     * Board panel field.
     */
    private final BoardPanel myGameBoard;
    
    /**
     * Panel that displays statistics.
     */
    private final InformationPanel myInfo;    
    
    /**
     * Whether game is over or not.
     */
    private boolean myGameStatus;
    
    /**
     * Constructor.
     */
    public TetrisGUI() {
        super();
        myBoard = new Board(WIDTH, HEIGHT);
        myGameBoard = new BoardPanel(myBoard);
        myGameStatus = false;
        myInfo = new InformationPanel(myBoard);
        final ArrayList<TetrisPiece> sequence = new ArrayList<TetrisPiece>();
        
        addToSequence(sequence);
        
        
        myBoard.setPieceSequence(sequence);
       
    }
    
    /**
     * Adds pieces to ArrayList.
     * @param theSequence ArrayList.
     */
    private void addToSequence(final ArrayList<TetrisPiece> theSequence) {
        theSequence.add(TetrisPiece.I);
        theSequence.add(TetrisPiece.J);
        theSequence.add(TetrisPiece.L);
        theSequence.add(TetrisPiece.O);
        theSequence.add(TetrisPiece.S);
        theSequence.add(TetrisPiece.T);
        theSequence.add(TetrisPiece.Z);
    }
    
    /**
     * Start the GUI.
     */
    public void start() {
               
        
        setTitle("Igor Kalezic - Tetris");
        
        setPreferredSize(new Dimension(FRAME_SIZE, FRAME_SIZE));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        final KeyListeners control = new KeyListeners(myBoard, myGameBoard);
        
        addKeyListener(control);
        
        myBoard.addObserver(this);
        
        
        
        
        add(myGameBoard, BorderLayout.WEST);
        
        final JPanel pan = new JPanel();
        pan.setBackground(Color.black);
        pan.setVisible(true);
        pan.setPreferredSize(new Dimension(PAN_WIDTH, PAN_HEIGHT));
        
        

        
        
        add(pan);
        final NextPiecePanel next = new NextPiecePanel(myBoard);
       
        next.setBackground(Color.GRAY);
        next.setPreferredSize(new Dimension(NEXT_PAN_SIZE, NEXT_PAN_SIZE));
        pan.add(next);
        pan.add(myInfo);
        setJMenuBar(createMenuBar());
        
        setResizable(false);
        pack();
        setVisible(true);
        
       
    }
    
    /**
     * Creates the menu bar.
     * @return JMenuBar.
     */
    public JMenuBar createMenuBar() {
        final ButtonGroup group = new ButtonGroup();
        
        final JMenuBar bar = new JMenuBar();
        final JMenu help = new JMenu("Help");
        final JMenuItem endGame = new JMenuItem("End game");
        final JMenuItem newGame = new JMenuItem("New game");
        final JMenuItem controls = new JMenuItem("Controls");
        final JMenuItem algorithm = new JMenuItem("Scoring Algorithm");
        
        
        final JMenu bColor = new JMenu("Choose background color");
        final JRadioButton green = new JRadioButton("Green");
        final JRadioButton black = new JRadioButton("Black");
        final JRadioButton white = new JRadioButton("White (Default)");
        
        group.add(green);
        group.add(black);
        group.add(white);
        
        white.setSelected(true);
        
        final GreenBackgroundListener greenAction = new GreenBackgroundListener(myGameBoard);
        final BlackBackgroundListener blackAction = new BlackBackgroundListener(myGameBoard);
        final WhiteBackgroundListener whiteAction = new WhiteBackgroundListener(myGameBoard);
        
        green.addActionListener(greenAction);
        black.addActionListener(blackAction);
        white.addActionListener(whiteAction);
        
        bColor.add(green);
        bColor.add(black);
        bColor.add(white);
        
        final HelpListener controlAction = new HelpListener();
        final EndGameListener endGameAction = new EndGameListener();
        final NewGameListener newGameAction = new NewGameListener();
        final AlgorithmListener algorithmAction = new AlgorithmListener();
        
        
        
        
        controls.addActionListener(controlAction);
        newGame.addActionListener(newGameAction);
        endGame.addActionListener(endGameAction);
        algorithm.addActionListener(algorithmAction);
        
        help.add(bColor);
        help.add(controls);
        help.add(algorithm);
        help.add(newGame);
        help.add(endGame);
        
        
        
        bar.add(help);
        bar.setVisible(true);
        
        
        return bar;       
    }
    
    
    @Override
    public void update(final Observable theObject, final Object theArg) {
        if (theArg instanceof Boolean) {
            myGameStatus = false;
        }
    }
    
    
    
    /** This class is the action for the new game button.
     * 
     * 
     * @author Igor Kalezic
     * @version 9 December 2016 
     */
    public class NewGameListener extends AbstractAction {
              

        /**
         * Serialization.
         */
        private static final long serialVersionUID = -5976323027260724065L;
        
        /**
         * What occurs when button is pressed.
         * @param theEvent the event that occurs.
         */
        public void actionPerformed(final ActionEvent theEvent) {
            if (!myGameStatus) {
                myGameStatus = true;
                myGameBoard.startGame(); 
                myInfo.resetScore();
                myInfo.resetLevel();
                myInfo.resetLinesCleared();
                myInfo.resetLinesToClear();
                repaint();
            }
                      
        }

    }
    
    /** This class is the action for the end game button.
     * 
     * 
     * @author Igor Kalezic
     * @version 9 December 2016 
     */
    public class EndGameListener extends AbstractAction {
              
        /**
         * Serialization.
         */
        private static final long serialVersionUID = -1858841409434735474L;

        
        /**
         * What occurs when button is pressed.
         * @param theEvent the event that occurs.
         */
        public void actionPerformed(final ActionEvent theEvent) {
            if (myGameStatus) {
                myGameStatus = false;
                myGameBoard.endGame();
                
            }
                      
        }

    }

}
