import java.util.Scanner;
/**
 * Tests the MSField class by creating a console based MSGame
 * 
 *
 */
public class MSConsoleTester
{
	public static void main(String[] args)
	{
		MSField field = new MSField(10, .2);
		Scanner in = new Scanner(System.in);
		int x = 0;
		int y = 0;
		// start a game and run until game over
		do
		{
			System.out.println(field);
			System.out.print("Enter the x and y coordinates, separated by a space: ");
			x = in.nextInt();
			y = in.nextInt();
		} while (field.exploreVertex(x, y));
		in.close(); // end of game
	}
}