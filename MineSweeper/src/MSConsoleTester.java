import java.util.Scanner;

public class MSConsoleTester
{
	public static void main(String[] args)
	{
		MSField field = new MSField(10, .2);
		Scanner in = new Scanner(System.in);
		int x = 0;
		int y = 0;
		do
		{
			System.out.println(field);
			System.out.print("Enter the x and y coordinates, separated by a space: ");
			x = in.nextInt();
			y = in.nextInt();
		} while (field.exploreVertex(x, y));
		in.close();
	}
}