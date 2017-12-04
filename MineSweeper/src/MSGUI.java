import javax.swing.ButtonGroup;
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
	private JPanel startPanel;
	private JPanel fieldPanel;
	private JPanel[][] panels;
	private MSField field;
	
	public MSGUI()
	{
		frame = new JFrame();
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
	}
}