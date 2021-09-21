class WordContainsException extends Exception
{
      public WordContainsException() { }

      public WordContainsException(String message)
      {
         super(message);
      }
 }

public class Custom {

	public static void main(String[] args) {
		
		String word = "Hello World";
		
		try
		 {
		     if(word.contains(" "))
		     {
		          throw new WordContainsException();
		     }
		 }
		 catch(WordContainsException ex)
		 {
		     System.out.println("Caught the exception!");
		 }
	}
}
