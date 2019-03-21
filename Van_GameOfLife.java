
/*****************************************************************
*	author: Theresa Van
*	file: Van_GameOfLife.java
*	class: CS 141 - Programming & problem Solving
*
*	date last modified: 3/16/17
*	assignment: Program #6
*
*	purpose: To create a program that will implement Conway's
*	Game of Life
*
******************************************************************/
import java.util.Scanner;
import java.io.*;

public class Van_GameOfLife 
{
	private char[][] board;
	private char[][] temp;
	private int columns;
	private int rows;
	private static int generation;
	private int numGen = 1;
	
	//method: Van_GameOfLife
	//purpose: Prompts the user for the file name and loads the game board
	//data from the file
	public Van_GameOfLife() throws IOException
	{
		String filename, fileString = "";
		int index = 0;
		Scanner input = new Scanner(System.in);
		System.out.print("\nEnter file name: ");
		filename = input.next();
		input.nextLine();
		
		File file = new File(filename);
		Scanner read = new Scanner(file);
		columns = read.nextInt();
		rows = read.nextInt();
		read.nextLine();
		
		board = new char[rows][columns];
		
		while(read.hasNext())
		{
			fileString += read.next();
		}
		read.close();
		
		while(index < fileString.length())
		{
			for(int r = 0; r < board.length; r++)
			{
				for(int c = 0; c < board[r].length; c++)
				{
					board[r][c] = fileString.charAt(index);
					index++;
				}
			}	
		}
	}
	
	//method: getColumns
	//purpose: returns the number of columns in the game board
	public int getColumns()
	{
		return columns;
	}
	
	//method: getRows
	//purpose: returns the number of rows in the game board
	public int getRows()
	{
		return rows;
	}
	
	//method: getCell
	//purpose: gets the value of the cell at a certain index and
	//returns 0 if the column or row is outside the bounds of the 
	//game board
	public char getCell(int r, int c)
	{
		if ((r >= rows) || (r < 0))
		{
			return '0';
		}
		
		if ((c >= columns) || (c < 0))
		{
			return '0';
		}
		
		else
			return board[r][c];
	}
	
	//method: setCell
	//purpose: set the value of the cell at a certain index (does
	//not handle out-of-bounds columns or rows)
	public void setCell(int r, int c, char value)
	{

		board[r][c] = value;
		
	}
	
	
	public void computeNextGeneration(int gen)
	{	
		temp = new char[rows][columns];
		
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[i].length; j++)
			{
				temp[i][j] = board[i][j];
			}
		}
		
		if ( gen == 1 )
		{
			System.out.println("Generation "+numGen+"\n");
			print();
		}
		if (gen > 1)
		{
			System.out.println("Generation "+numGen+"\n");
			print();

			numGen++;
			
			for (int r = 0; r < temp.length; r++)
			{
				for (int c = 0; c < temp[r].length; c++)
				{	
					int count = 0;
					
						if(getCell(r-1, c-1) == 'X')
							count++;
						if(getCell(r-1, c) == 'X')
							count++;
						if(getCell(r-1, c+1) == 'X')
							count++;
						if(getCell(r, c-1) == 'X')
							count++;
						if(getCell(r, c+1) == 'X')
							count++;
						if(getCell(r+1, c-1) == 'X')
							count++;
						if(getCell(r+1, c) == 'X')
							count++;
						if(getCell(r+1, c+1) == 'X')
							count++;
					
					if((temp[r][c] == '0') && (count == 3))
						temp[r][c] = 'X';
					
					if((temp[r][c] == 'X') && ((count < 2) || (count > 3)))
						temp[r][c] = '0';
					
					setCell(r, c, temp[r][c]);
				}
			}
			
			gen--;
			computeNextGeneration(gen);
		}
			
	}
	
	//method: print
	//purpose: prints out the board to the console
	public void print()
	{
		for(int r = 0; r < board.length; r++)
		{
			for (int c = 0; c < board[r].length; c++)
			{
				System.out.print(board[r][c]+ " ");
			}
			
			System.out.println();
		}
		
		System.out.println();
	}
	
	//method: main
	//purpose: creates an object of the constructor class and accepts
	//user input for number of generations to calculate for then calls
	//the computeNextGeneration method and passes the number of 
	//generations as an argument
	public static void main(String []args) throws IOException
	{
		Scanner keyboard = new Scanner(System.in);
		Van_GameOfLife test = new Van_GameOfLife();
		
		System.out.print("\nEnter how many generations to compute: ");
		generation = keyboard.nextInt();
		System.out.println();
		
		test.computeNextGeneration(generation);
	}
}
