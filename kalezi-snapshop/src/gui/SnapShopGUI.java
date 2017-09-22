/* 
 * TCSS 305 – Autumn 2016
 * Assignment 4 - SnapShot
 */



package gui;


import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import filters.EdgeDetectFilter;
import filters.EdgeHighlightFilter;
import filters.Filter;
import filters.FlipHorizontalFilter;
import filters.FlipVerticalFilter;
import filters.GrayscaleFilter;
import filters.SharpenFilter;
import filters.SoftenFilter;

import image.PixelImage;



/** This class creates the GUI for SnapShop.
 * 
 * @author Igor Kalezic
 * @version 4 November 2016 
 */
public class SnapShopGUI extends JFrame implements ActionListener {
    
    /**
     * Serialization of class.
     */
    private static final long serialVersionUID = -5834318901291821510L;
    
    /**
     * Class constant that holds how many filter buttons there are.
     */
    private static final int NUM_BUTTONS = 7;

    /**
     * Array that holds the buttons.
     */
    private JButton[] myButtons = new JButton[NUM_BUTTONS];
    
    /**
     * Declaring the image that is opened.
     */
    private PixelImage myImage;
    
    /**
     * "Open..." button object.
     */
    private JButton myOpen = new JButton("Open...");
    
    /**
     * "Save As..." button object.
     */
    private JButton mySave = new JButton("Save As...");
    
    /**
     * "Close" button object.
     */
    private JButton myClose = new JButton("Close Image");
    
    /**
     * Initialized file chooser.
     */
    private JFileChooser myChooseFile = new JFileChooser(".");
    
    /**
     * Initialized JLabel.
     */
    private JLabel myLabel = new JLabel();
    
    /**
     * Initialized JPanel.
     */
    private JPanel myPanel = new JPanel();
    
    /**
     * Constructor.
     */
    public SnapShopGUI() {
        super();
    }
    /**
     * 
     */
    public void start() {
        
        
        setLayout(new BorderLayout());
        
        
        setTitle("TCSS 305 SnapShop");
        setLocationRelativeTo(null);
        
        
        final JPanel southPanel = new JPanel();
        final JPanel westPanel = new JPanel();
        
        
        westPanel.setLayout(new GridLayout(0, 1));
        
        add(westPanel, BorderLayout.WEST);
        add(southPanel, BorderLayout.SOUTH);
        
        
        createButtons(westPanel);
        
        southPanel.add(myOpen);
        myOpen.addActionListener(this);
        
        southPanel.add(mySave);
        mySave.addActionListener(this);
        mySave.setEnabled(false);
        southPanel.add(myClose);
        myClose.addActionListener(this);
        myClose.setEnabled(false);
        
       

        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        pack();
        setVisible(true);
        
        
    }
    
    /**
     * Creates the buttons and filters and puts them in an array.
     * 
     * @param theWestPanel left side of GUI where the buttons will be placed.
     */
    private void createButtons(final JPanel theWestPanel) {
        

        final Filter [] filters = {new EdgeDetectFilter(), new EdgeHighlightFilter(),
                                   new FlipHorizontalFilter(), new FlipVerticalFilter(),
                                   new GrayscaleFilter(), new SharpenFilter(),
                                   new SoftenFilter()};
        
        int i = 0;
        
        for (Filter f : filters) {
            
            final JButton button = new JButton(f.getDescription());
            theWestPanel.add(button);
            button.setEnabled(false);
            myButtons[i] = button;
            i++;
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent theEvent) {
                    f.filter(myImage);
                    loadImage();
                    
                }
            });
            
            
            
            
        }
        
        
        
    }
    
    /**
     * Handles when the user selects either, "Open...", "Save As...", or "Close Image" button.
     * 
     * @param theEvent the event the user selects.
     */
    public void actionPerformed(final ActionEvent theEvent) {
        
        if (theEvent.getSource() == myOpen) {
            final int returnVal = myChooseFile.showOpenDialog(SnapShopGUI.this);
            

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                myPanel = new JPanel(new GridBagLayout());
                
               
                  
                
                final File file = myChooseFile.getSelectedFile();
                
                try {
                    final PixelImage image = PixelImage.load(file);
                    
                    myLabel.setIcon(new ImageIcon(image));
                    
                    myImage = image;
                    
                    add(myPanel);
                    
                    myPanel.add(myLabel);
                    myPanel.revalidate();
                    myPanel.repaint();
                    enableButtons(true);
                    
                    pack();
                } catch (final IOException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Message", 
                                                  JOptionPane.INFORMATION_MESSAGE);
                }
                        
                pack();

            }
        } 
        if (theEvent.getSource() == myClose) {
            myPanel.removeAll();
            myLabel.removeAll();
            myPanel.revalidate();
            myPanel.repaint();
            myLabel.revalidate();
            myLabel.repaint();
            enableButtons(false);
           
           
        } 
        
        if (theEvent.getSource() == mySave) {
            
            final int returnVal = myChooseFile.showSaveDialog(SnapShopGUI.this);
            

            if (returnVal == JFileChooser.APPROVE_OPTION && myImage != null) {
                
                
                
                final File file = myChooseFile.getSelectedFile();
                
                try {
                    myImage.save(file);
                } catch (final IOException e) {
                    JOptionPane.showMessageDialog(this, "Couldn't save", "!!", 
                                                  JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
            
            
        
    }

    /**
     * Enables or disables the buttons.
     * 
     * @param theTruthValue what controls whether the buttons are disabled
     *        or enabled.
     */
    public void enableButtons(final boolean theTruthValue) {
        for (JButton b : myButtons) {
            b.setEnabled(theTruthValue);
        }
        mySave.setEnabled(theTruthValue);
        myClose.setEnabled(theTruthValue);
    }
    
    /**
     * Just displays the image.
     */
    public void loadImage() {
        myLabel.setIcon(new ImageIcon(myImage));
        myPanel.revalidate();
        myPanel.repaint();
    }
        
}