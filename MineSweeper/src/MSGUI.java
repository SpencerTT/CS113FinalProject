import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
/**
 * 
 * Graphics class that creates a frame which contains an MSField and a startPanel for MSGame
 *
 */
public class MSGUI
{
	// instances 
	private JFrame frame;
	private JLabel lengthLabel;
	private ButtonGroup length;
	private JRadioButton small;
	private JRadioButton medium;
	private JRadioButton large;
	private JLabel densityLabel;
	private ButtonGroup density;
	private JRadioButton thin;
	private JRadioButton normal;
	private JRadioButton thick;
	private JButton startButton;
	private JLabel message;
	private JLabel totalMines;
	private JLabel totalFlags;
	private JPanel startPanel;
	private JPanel fieldPanel;
	private JLabel[][] labels; 
	private BufferedImage all;
	private BufferedImage[] images;
	private MSField field; // the MineSweeper field
	private boolean gameOver;
	/**
	 * Constructs a new GUI with a start panel and a null mine field. Uses JRadioButtons to determine the
	 * size of the field to populate and the total amounts of mines. Relays information to the user with
	 * JLabels.
	 */
	public MSGUI()
	{
		frame = new JFrame("MineSweeper"); // title
		frame.setLayout(new BorderLayout());
		frame.setSize(500, 600); 
		lengthLabel = new JLabel("Field Length: ");// options for field size
		length = new ButtonGroup();
		small = new JRadioButton("8 x 8 Field", true); // default selection is an 8 x 8 field
		medium = new JRadioButton("10 x 10 Field");
		large = new JRadioButton("12 x 12 Field");
		length.add(small);
		length.add(medium);
		length.add(large);
		densityLabel = new JLabel("Total % of Mines: "); // options for field density
		density = new ButtonGroup();
		thin = new JRadioButton("10% Mines", true); //default selection is 10 mines
		normal = new JRadioButton("15% Mines");
		thick = new JRadioButton("20% Mines");
		density.add(thin);
		density.add(normal);
		density.add(thick);
		startButton = new JButton("New Game");
		message = new JLabel("");
		totalMines = new JLabel("");
		totalFlags = new JLabel("");
		startPanel = new JPanel();
		setImages(); // set the images in for the field
		setStartPanel(); 
		labels = null;
		fieldPanel = null;
		addStartListener();
		frame.add(startPanel, BorderLayout.NORTH); // add the panel to the fram
		frame.setVisible(true); 
	}
	/**
	 * Reads the file which contains the images for the MSField. Adds each image into an array of images.
	 */
	private void setImages()
	{
		try
		{
			File allFile = new File("src/MSTiles.jpg"); // contains images for tiles in field
			all = ImageIO.read(allFile);
			images = new BufferedImage[13];
			//Get all the images
			for(int y = 0; y < 2; y++)
			{
				for(int x = 1; x < 5; x++)
				{
					images[4 * y + x] = all.getSubimage(128 * (x-1), 128 * (y+1), 128, 128);
				}
			}
			images[0] = all.getSubimage(384, 0, 128, 128);
			images[9] = all.getSubimage(0, 0, 128, 128);
			images[10] = all.getSubimage(128, 0, 128, 128);
			images[11] = all.getSubimage(256, 0, 128, 128);
			//Resize images to 32x32
			for(int x = 0; x < 12; x++)
			{
				BufferedImage small = new BufferedImage(32, 32, BufferedImage.TYPE_INT_RGB);
				Graphics g = small.createGraphics();
				g.drawImage(images[x], 0, 0, 32, 32, null);
				g.dispose();
				images[x] = small;
			}
			//Make image13 (only used during win screen)
			BufferedImage top = images[10];
			BufferedImage bot = images[11];
			BufferedImage mash = new BufferedImage(32, 32, BufferedImage.TYPE_INT_RGB);
			Graphics g = mash.createGraphics();
			g.drawImage(top, 0, 0, 32, 16, null);
			g.drawImage(bot, 0, 16, 32, 16, null);
			g.dispose();
			images[12] = mash;
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * creates a start panel for the game. Uses GridBagLayout and adds each item with a specific call.
	 */
	private void setStartPanel()
	{
		// create startpanel and add labels and buttons
		startPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		// add each item 1 at a time (no loop)
		startPanel.add(lengthLabel, c);
		c.gridx = 1;
		startPanel.add(small, c);
		c.gridx = 2;
		startPanel.add(medium, c);
		c.gridx = 3;
		startPanel.add(large, c);
		c.gridx = 0;
		c.gridy = 1;
		startPanel.add(densityLabel, c);
		c.gridx = 1;
		startPanel.add(thin, c);
		c.gridx = 2;
		startPanel.add(normal, c);
		c.gridx = 3;
		startPanel.add(thick, c);
		c.gridx = 0;
		c.gridy = 2;
		startPanel.add(startButton, c);
		c.gridx = 1;
		startPanel.add(message, c);
		c.gridx = 2;
		startPanel.add(totalMines, c);
		c.gridx = 3;
		startPanel.add(totalFlags, c);
	}
	/**
	 * Populates a mine field with the amount of mines and the size determined by the user.
	 */
	private void addField()
	{
		if (fieldPanel != null) // leftover game
		{
			frame.remove(fieldPanel);  // remove the old field
		}
		fieldPanel = new JPanel(); // start a new game 
		int theLength = chooseLength();
		double theDensity = chooseDensity();
		field = new MSField(theLength, theDensity);
		message.setText("");
		totalMines.setText("Mines: " + field.getTotalMines()); // add the chosen mines and flags
		totalFlags.setText("Flags: "+ field.getTotalFlags());
		gameOver = false; // reset gameOver
		labels = new JLabel[theLength][theLength];  
		fieldPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL; 
		for(int x = 0; x < field.getLength(); x++) // adds each label to the specified locaition
		{
			for(int y = 0; y < field.getLength(); y++) 
			{
				labels[x][y] = new JLabel();
				c.gridx = x;
				c.gridy = y;
				fieldPanel.add(labels[x][y], c);
			}
		}
		frame.add(fieldPanel, BorderLayout.CENTER); // add the new field to the GUI
		addLabelListeners(); // add listeners to the tiles
		updateField();
		
	}
	/**
	 * Adds an ActionListener to the start button. 
	 */
	private void addStartListener()
	{
		startButton.addActionListener(new ActionListener() // ensures the start button responds.
		{
			public void actionPerformed(ActionEvent event)
			{
				addField(); // add the new field
			}
		});
	}
	/**
	 * adds listeners to each label in the MSfield
	 */
	private void addLabelListeners()
	{
		for(int x = 0; x < field.getLength(); x++)
		{
			int x1 = x;
			for(int y = 0; y < field.getLength(); y++)
			{
				int y1 = y;
				labels[x][y].addMouseListener(new MouseAdapter() // adds a listener to each button
				{
					private int x = x1;
					private int y = y1;
					
					public void mousePressed(MouseEvent e) // relays the button pressed to the listener
					{
						if(gameOver == false) // match is still in process
						{
							if(e.getButton() == 1) // left button
							{
								if(field.getMSVertex(x, y).isFlagged() == false) // cannot check flagged mines
								{
									if(field.exploreVertex(x, y) == false)
									{
										//loss message
										message.setText("You Lost!");
										//Freeze the board
										gameOver = true;
									}
								}
							}
							else if(e.getButton() == 3) // right button
							{
								MSVertex current = field.getMSVertex(x, y);
								boolean flagged = current.isFlagged();// set the flag to the tile
								if(!current.isExplored())
								{
									current.setFlagged(!flagged); 
									if(flagged)
									{
										field.setTotalFlags(field.getTotalFlags()-1); // decrements the leftover flags 
										if(current.isMine())
										{
											field.setCorrectFlags(field.getCorrectFlags()-1); // correct flag so decrement the leftover flags
										}
									}
									else
									{
										field.setTotalFlags(field.getTotalFlags()+1); 
										if(current.isMine())
										{
											field.setCorrectFlags(field.getCorrectFlags()+1);
										}
									}
									if(field.getTotalMines() == field.getCorrectFlags() &&
									   field.getTotalMines() == field.getTotalFlags())
									{
										//win message
										message.setText("You Won!");
										//Freeze the board
										gameOver = true;
									}
								}
								totalFlags.setText("Flags: "+ field.getTotalFlags());
							}
							updateField();
						}
					}	
				});
			}
		}
	}
	/**
	 * Helper method to set the length of the field. Returns an int dependant on user selection of the length.
	 * @return the chosen length
	 */
	private int chooseLength()
	{
		if(small.isSelected())
		{
			return 8;
		}
		else if (medium.isSelected())
		{
			return 10;
		}
		else
		{
			return 12;
		}
	}
	/**
	 * Helper method to determine the density dependant on the user selection
	 * @return the chosen density
	 */
	private double chooseDensity()
	{
		if(thin.isSelected())
		{
			return .1;
		}
		else if (normal.isSelected())
		{
			return .15;
		}
		else
		{
			return .2;
		}
	}
	/**
	 * updates the images held in the MSField with the current image. 
	 */
	private void updateField()
	{
		for(int x = 0; x < field.getLength(); x++)
		{
			for(int y = 0; y < field.getLength(); y++)
			{
				if(!gameOver)
				{
					labels[x][y].setIcon(new ImageIcon(images[field.getMSVertex(x, y).getImage()]));
				}
				else
				{
					labels[x][y].setIcon(new ImageIcon(images[field.getMSVertex(x, y).getFinalImage()]));
				}
				
			}
		}
		fieldPanel.repaint();
	}
}