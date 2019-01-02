import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CubePuzzle 
{
	/* order without being scrambled 
	 {"blue 3", "green 2", "red 1", 
	  "green 1", "red 3", "blue 2", 
	  "red 2", "blue 1", "green 3"};
	  
	  order after scramble
	  {"green 3", "red 3", "blue 2", 
	   "blue 3", "green 2", "red 1",
	   "red 2", "blue 1", "green 1"};
	   
	   old board
	   {3, 2, 1,
		1, 3, 2,
		2, 1, 3};	  
	 */
	//Colors are blue, purple, green, orange, red, yellow
	private static final String[] input = { "blue 1", "purple 1", "green 1", "orange 1", "red 1", "yellow 1",
											"blue 2", "purple 2", "green 2", "orange 2", "red 2", "yellow 2",
											"blue 3", "purple 3", "green 3", "orange 3", "red 3", "yellow 3",
											"blue 4", "purple 4", "green 4", "orange 4", "red 4", "yellow 4",
											"blue 5", "purple 5", "green 5", "orange 5", "red 5", "yellow 5",
											"blue 6", "purple 6", "green 6", "orange 6", "red 6", "yellow 6"  };
	//number indicates necessary height of pieces.
	private static final int[] board = {5, 3, 2, 1, 4, 6,
										4, 1, 5, 2, 5, 3, 
										6, 5, 3, 4, 1, 2, 
										1, 2, 6, 3, 6, 4,
										2, 4, 1, 6, 3, 5,
										3, 6, 4, 5, 2, 1 };
	
//---------------No User Input Below--------------------------------------------------------
	
	private static final ArrayList<String> pieces = new ArrayList<>(Arrays.asList(input));
	private static final int rowsAndCol = (int)Math.sqrt(input.length);
	private static int count = 0; 
	
	public static void main(String[] args)
	{
		long startTime = System.nanoTime();
		ArrayList<String> result = solve(0, pieces);
		long endTime = System.nanoTime();
		
		System.out.println("Number of Tests: " + count);
		System.out.println("Execution time took: " + ((endTime - startTime)/1000000000) + " seconds");
		for(int i = 0; i < board.length; i++)
		{
			if(i%rowsAndCol == 0)
				System.out.println("");
			System.out.print(result.get(i) + "\t");
		}
	}
	
	public static ArrayList<String> solve(int start, ArrayList<String> pieces)
	{	
		count++;
		if(correct(pieces))
			return pieces; 
		else if(start == pieces.size()-1)
			return null;

		for(int i = start+1; i<pieces.size(); i++)
		{
			ArrayList<String> tem = new ArrayList(pieces);
			Collections.swap(tem, start, i);
			ArrayList<String> result = solve(start+1, tem);
			if(result != null)
				return result;
		}
		
		return solve(start+1, pieces);
		
				
	}
	
	public static boolean correct(ArrayList<String> pieces)
	{
		ArrayList<String> uniqueColors = new ArrayList<String>();
		for(int i = 0; i < rowsAndCol; i++)
		{
			uniqueColors.clear();
			for(int ii = i*rowsAndCol; ii < i*rowsAndCol + rowsAndCol; ii++)
			{
				String[] both = pieces.get(ii).split(" ");
				String color = both[0];
				int num = Integer.parseInt(both[1]);
				
				if(!uniqueColors.contains(color))
					uniqueColors.add(color);
				else 
					return false; 
				if(num != board[ii])
					return false; 
			}
		}
		
		for(int i = 0; i < rowsAndCol; i++)
		{
			uniqueColors.clear();
			for(int ii = i, c = 0; c<rowsAndCol; ii+=rowsAndCol, c++)
			{
				String color = pieces.get(ii).split(" ")[0];
				if(!uniqueColors.contains(color))
					uniqueColors.add(color);
				else
					return false;
			}
		}
		
		return true;
	}
	
	/*public static boolean correctChanges(int index, ArrayList<String> currentBoard) //column up is - or + rowsAndCol
	{
		int posInRow = index%rowsAndCol; 
		String colur = currentBoard.get(index).split(" ")[0]; 
		for(int r = index-posInRow; r < index+(rowsAndCol-posInRow); r++) 
		{
			if(r == index)
				continue;
			if(currentBoard.get(r).split(" ")[0].equals(colur))
				return false;
		}
		
		int tempIndex = index;
		while((tempIndex-=rowsAndCol) > 0)
			if(currentBoard.get(tempIndex).split(" ")[0].equals(colur))
				return false;
		while((index+=rowsAndCol) < currentBoard.size())
			if(currentBoard.get(index).split(" ")[0].equals(colur))
				return false;
		
		return true;
	}*/
}





