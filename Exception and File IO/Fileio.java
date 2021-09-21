 import java.io.*; 

class Fileio
{
	static char myWord;
  public static void main(String args[])
  {
  try 
  {
  // put file in project main folder, not the src folder
  FileInputStream fin = new FileInputStream("input.txt");
  BufferedInputStream bis = new BufferedInputStream(fin);
  
  // Now read the buffered stream.
  while (bis.available() > 0) 
  {
	  System.out.print((char)bis.read());
  }
  	bis.close();
  } 
  catch (Exception e) 
  {
	  System.err.println("My Message is Error reading file: " + e);
  }
  }
  
}