// The example will show some special behavior in catch and finally blocks:

import java.io.*;

class Example2 {

  public static FileInputStream f1(String fileName)
  {
    FileInputStream fis = null;
    try
    {
      fis = new FileInputStream(fileName);
    }
    catch (FileNotFoundException ex)
    {
      System.out.println("f1: Oops, FileNotFoundException caught");
      throw new Error("f1: File not found");
    }
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
      return fis;
    }
    finally
    {
      System.out.println("f2: finally block");
      return fis;
    }

    // Compiler error: statement not reacheable
    // System.out.println("f2: Returning from f2");
    // return fis;
  }

  public static void main(String args[])
  {
    FileInputStream fis1 = null;
    FileInputStream fis2 = null;
    String fileName = "foo.bar";
    // String fileName = null;

    System.out.println(  "main: Starting " + Example2.class.getName()
                       + " with file name = " + fileName);

    // get file input stream 1
    try {
      fis1 = f1(fileName);
    }
    catch (Exception ex)
    {
      System.out.println("main: Oops, general exception caught");
    }
    catch (Throwable th)
    {
      System.out.println("main: Oops, throwable object caught");
    }

    // get file input stream 2
    fis2 = f2(fileName);

    System.out.println("main: " + Example2.class.getName() + " ended");
  }
}

// This example will generate the following output:

//    main: Starting Demo2 with file name = foo.bar
//    f1: Oops, FileNotFoundException caught
//    main: Oops, throwable object caught
//    f2: Oops, FileNotFoundException caught
//    f2: finally block
//    main: Demo2 ended
