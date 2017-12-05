import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
public class MSGUI
{
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
	private MSField field;
	
	public MSGUI()
	{
		frame = new JFrame("MineSweeper");
		frame.setLayout(new BorderLayout());
		frame.setSize(800, 600);
		lengthLabel = new JLabel("Field Length: ");
		length = new ButtonGroup();
		small = new JRadioButton("8 x 8 Field", true);
		medium = new JRadioButton("10 x 10 Field");
		large = new JRadioButton("12 x 12 Field");
		length.add(small);
		length.add(medium);
		length.add(large);
		densityLabel = new JLabel("Total % of Mines: ");
		density = new ButtonGroup();
		thin = new JRadioButton("10% Mines", true);
		normal = new JRadioButton("15% Mines");
		thick = new JRadioButton("20% Mines");
		density.add(thin);
		density.add(normal);
		density.add(thick);
		startButton = new JButton("New Game");
		//add listener for field creation
		message = new JLabel("");
		totalMines = new JLabel("");
		totalFlags = new JLabel("");
		startPanel = new JPanel();
		setStartPanel();
		fieldPanel = new JPanel();
		setImages();
		addField();
		updateField();
		
		frame.add(startPanel, BorderLayout.NORTH);
		frame.add(fieldPanel, BorderLayout.CENTER);
		frame.setVisible(true);
	}
	
	private void setImages()
	{
		try
		{
			File allFile = new File("src/MSTiles.jpg");
			all = ImageIO.read(allFile);
			images = new BufferedImage[12];
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
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void setStartPanel()
	{
		startPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
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
	
	private void addField()
	{
		int theLength = chooseLength();
		double theDensity = chooseDensity();
		field = new MSField(theLength, theDensity);
		labels = new JLabel[theLength][theLength];
		fieldPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		for(int x = 0; x < field.getLength(); x++)
		{
			for(int y = 0; y < field.getLength(); y++)
			{
				labels[x][y] = new JLabel();
				c.gridx = x;
				c.gridy = y;
				fieldPanel.add(labels[x][y], c);
			}
		}
	}
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
	
	private void updateField()
	{
		for(int x = 0; x < field.getLength(); x++)
		{
			for(int y = 0; y < field.getLength(); y++)
			{
				labels[x][y].setIcon(new ImageIcon(images[field.getMSVertex(x, y).getImage()]));
			}
		}
	}
}