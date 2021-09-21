import java.io.*;

class Example1 {

  public static FileInputStream f1(String fileName)
    throws FileNotFoundException
  {
    FileInputStream fis = new FileInputStream(fileName);
    System.out.println("f1: File input stream created");
    return fis;
  }

  public static FileInputStream f2(String fileName)
  {
    FileInputStream fis = null;
    try
    {
      fis = new FileInputStream(fileName);
    }
    catch (FileNotFoundException ex)
    {
      System.out.println("f2: Oops, FileNotFoundException caught");
    }
    finally
    {
      System.out.println("f2: finally block");
    }
    System.out.println("f2: Returning from f2");
    return fis;
  }

  public static void main(String args[])
  {
    FileInputStream fis1 = null;
    FileInputStream fis2 = null;
    String fileName = "foo.bar";
    // String fileName = null;

    System.out.println(  "main: Starting " + Example1.class.getName()
                       + " with file name = " + fileName);

    // get file input stream 1
    try {
      fis1 = f1(fileName);
    }
    catch (FileNotFoundException ex)
    {
      System.out.println("main: Oops, FileNotFoundException caught");
    }
    catch (Exception ex)
    {
      System.out.println("main: Oops, genreal exception caught");
    }

    // get file input stream 2
    fis2 = f2(fileName);

    System.out.println("main: " + Example1.class.getName() + " ended");
  }
}

// This class will generate output as follows (assuming that the file foo.bar does not exist):

//    main: Starting Demo1 with file name = foo.bar
//    main: Oops, FileNotFoundException caught
//    f2: Oops, FileNotFoundException caught
//    f2: finally block
//    f2: Returning from f2
//    main: Demo1 ended

// The example program tries to create two file input streams.
// Therefore two methods f1 and f2 are implemented. First, the main program calls f1 method.
// Because the constructor of the FileInputStream throws a FileNotFoundException the method
//  f1 must be defined with the throws FileNotFoundException in the method definition.
//  The thrown exception is not handled in the method but forwarded to the invoker.
//  Note, that the system output before the return statement is never executed.
