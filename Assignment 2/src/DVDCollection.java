import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileWriter; 
import java.io.IOException; 

public class DVDCollection {

	// Data fields
	static char myWord;
	String wholeW = "";
	int wordLocation = 0;
	int makeBigger = 0;
	int numRating = 0;
	int total = 0;
	
	String[] allWords = new String[7 + makeBigger];
	String[] allRating = new String[1 + numRating];
	
	/** The current number of DVDs in the array */
	private int numdvds;
	
	/** The array to contain the DVDs */
	private DVD[] dvdArray;
	
	/** The name of the data file that contains dvd data */
	private String sourceName;
	
	/** Boolean flag to indicate whether the DVD collection was
	    modified since it was last saved. */
	private boolean modified;
	
	/**
	 *  Constructs an empty directory as an array
	 *  with an initial capacity of 7. When we try to
	 *  insert into a full array, we will double the size of
	 *  the array first.
	 */
	public DVDCollection() {
		numdvds = 0;
		dvdArray = new DVD[7];
	}
	
	public String toString() {
		// Return a string containing all the DVDs in the
		// order they are stored in the array along with
		// the values for numdvds and the length of the array.
		// See homework instructions for proper format.

		int i = dvdArray.length;
		return "Number of DVDs: "+ numdvds + "Length of Array: " + dvdArray.length + "DVDs in Array: " + dvdArray[i--] ;
	}

	public void addOrModifyDVD(String title, String rating, String runningTime) {
		// NOTE: Be careful. Running time is a string here
		// since the user might enter non-digits when prompted.
		// If the array is full and a new DVD needs to be added,
		// double the size of the array first.
		Scanner scan = new Scanner(System.in);
		
		try
		{
			FileInputStream inFile = new FileInputStream("dvddata.txt");
			BufferedInputStream myFile = new BufferedInputStream(inFile);
			//String[] allWords = new String[7 + makeBigger];
			
			//Read File put it in array
			while(myFile.available() > 0)
			{
				char singleW = (char)myFile.read();
				
				if(singleW == ',')
				{
					wholeW = "";
					wordLocation++;
				}
				else
				{
					wholeW = wholeW + singleW;
					allWords[wordLocation] = wholeW;
				}
			}
			myFile.close();
			
			//modify choice if title found
			for(int i = 0; i < allWords.length; i++)
			{
				System.out.println(allWords[i]);
				if(title == allWords[i])
				{
					System.out.println("Do you want to modify?(Yes or No)");
					String choice;
					choice = scan.nextLine();
					choice.toUpperCase();
					if(choice == "YES")
					{
						modified = true;
						System.out.println("What will be your new rating?");
						allWords[i+1] = scan.nextLine();
						System.out.println("What will be your new running time?");
						allWords[i+1] = scan.nextLine();
					}
					else
					{
						System.out.println("No modification will be taken?");
					}
				}
				else
				{
					//make room for new DVD
					makeBigger = makeBigger + 3;
					//add the DVD to the end of the array
					allWords[allWords.length - 2] = title;
					allWords[allWords.length - 1] = rating;
					allWords[allWords.length] = runningTime;
					//to sort I would move the data to dvd objects and use getTitle to compare
					
					FileWriter writeFile = new FileWriter("dvddata.txt");
					
					for (int j = 0; j < allWords.length/3; j++) 
					{
						String combineLine = "";
						//not sure if u wanted me to change the delimiter from , to /
						combineLine = combineLine + allWords[j] + "/" + allWords[j+1] + "/" + allWords[j+2];
						writeFile.write(combineLine);
					}
					writeFile.close();
				}
			}
		} catch(IOException e)
		{
			System.out.println("File Not Found");
		}
	}
	
	public void removeDVD(String title) {
		try
		{
			FileInputStream inFile = new FileInputStream("dvddata.txt");
			BufferedInputStream myFile = new BufferedInputStream(inFile);
			//String[] allWords = new String[7 + makeBigger];
			
			//Read File put it in array
			while(myFile.available() > 0)
			{
				char singleW = (char)myFile.read();
				
				if(singleW == ',')
				{
					wholeW = "";
					wordLocation++;
				}
				else
				{
					wholeW = wholeW + singleW;
					allWords[wordLocation] = wholeW;
				}
			}
			myFile.close();
			for(int i = 0; i < allWords.length; i++)
			{
				System.out.println(allWords[i]);
				if(title == allWords[i])
				{
					allWords[i] = allWords[allWords.length - 2];
					allWords[i + 1] = allWords[allWords.length - 1];
					allWords[i + 2] = allWords[allWords.length];
					makeBigger = makeBigger - 3;
				}
				FileWriter writeFile = new FileWriter("dvddata.txt");
				
				for (int j = 0; j < allWords.length/3; j++) 
				{
					String combineLine = "";
					//not sure if u wanted me to change the delimiter from , to /
					combineLine = combineLine + allWords[j] + "/" + allWords[j+1] + "/" + allWords[j+2];
					writeFile.write(combineLine);
				}
				writeFile.close();
			}
		} catch(IOException e)
		{
			System.out.println("File Not Found");
		}
	}
	
	public String getDVDsByRating(String rating) {
		try
		{
			FileInputStream inFile = new FileInputStream("dvddata.txt");
			BufferedInputStream myFile = new BufferedInputStream(inFile);
			//String[] allWords = new String[7 + makeBigger];
			//String[] allRating = new String[1 + numRating];
			String ratingType = "";
			
			//Read File put it in array
			while(myFile.available() > 0)
			{
				char singleW = (char)myFile.read();
				
				if(singleW == ',')
				{
					wholeW = "";
					wordLocation++;
				}
				else
				{
					wholeW = wholeW + singleW;
					allWords[wordLocation] = wholeW;
				}
			}
			myFile.close();
			
			for(int i = 0; i < allWords.length; i++)
			{
				System.out.println(allWords[i]);
				if(rating == allWords[i])
				{
					int a = 0;
					ratingType = ratingType + allWords[i - 1] + "/" + allWords[i] + "/" + allWords[i + 1] + "   ";
					allRating[a] = ratingType;
					ratingType = "";
					a++;
					numRating++;
				}
			}
			
		} catch(IOException e)
		{
			System.out.println("File Not Found");
		}
		//return String.format("%n", allRating[1])
		
		return Arrays.toString(allRating);

	}

	public int getTotalRunningTime() {
		try
		{
			FileInputStream inFile = new FileInputStream("dvddata.txt");
			BufferedInputStream myFile = new BufferedInputStream(inFile);
			//String[] allWords = new String[7 + makeBigger];
			//String[] allRating = new String[1 + numRating];
			
			//Read File put it in array
			while(myFile.available() > 0)
			{
				char singleW = (char)myFile.read();
				
				if(singleW == ',')
				{
					wholeW = "";
					wordLocation++;
				}
				else
				{
					wholeW = wholeW + singleW;
					allWords[wordLocation] = wholeW;
				}
			}
			myFile.close();
			
			for(int i = 0; i < allWords.length; i++)
			{
				if(i % 3 == 0)
				{
					if(i % 3 == 0)
					{
						total = total + Integer.valueOf(allWords[i]);
					}
				}
			}
			
		} catch(IOException e)
		{
			System.out.println("File Not Found");
		}
		
		return total;	// STUB: Remove this line.
	}

	
	public void loadData(String filename) {
		try
		{
			FileInputStream inFile = new FileInputStream(filename);
			BufferedInputStream myFile = new BufferedInputStream(inFile);
			//String[] allWords = new String[7 + makeBigger];
			
			//Read File put it in array
			while(myFile.available() > 0)
			{
				char singleW = (char)myFile.read();
				
				if(singleW == ',')
				{
					wholeW = "";
					wordLocation++;
				}
				else
				{
					wholeW = wholeW + singleW;
					allWords[wordLocation] = wholeW;
					/////
					//How I would do it but finding it difficult
					//				wholeW[i]   whileW[i+1]		wholeW[i+2]
					//DVD obj = new DVD(wholeW, wholeW, Integer.valueOf(allWords[i]));
					////
				}
			}
			myFile.close();
			
		} catch(IOException e)
		{
			System.out.println("File Not Found");
		}
		
	}
	
	public void save() {

		
		try 
		{
			FileWriter writeFile = new FileWriter("dvddata.txt");
			for (int j = 0; j < allWords.length; j++) 
			{
				String combineLine = "";
				combineLine = combineLine + allWords[j] + "/" + allWords[j+1] + "/" + allWords[j+2];
				writeFile.write(combineLine);
			}
			writeFile.close();
		} catch(IOException e)
		{
			System.out.println("File Not Found");
		}
	}
	// Additional private helper methods go here:	
}
