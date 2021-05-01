import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
  private static String fileName;
  private static int[] inputValues;

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter file name (EXCLUDE .txt)");
    fileName = input.next()+".txt";

    try {
      inputValues = readInputFile();
    } catch (FileNotFoundException e) {
      System.out.println("Error: "+e);
    }
    RedBlackTree tree = new RedBlackTree();
    for(int num: inputValues){
      tree.insert(num);
    }
    tree.print();
  }

  private static int[] readInputFile() throws FileNotFoundException{
    Scanner counterScanner;
    Scanner fileScanner;
    File file = new File(fileName);
    
    //Counts amount of doubles
    counterScanner = new Scanner(file);
    int counter = 0;
    while(counterScanner.hasNextInt()){
        counter++;
        counterScanner.nextInt();
    }
    counterScanner.close();

    //Creates and populates double array
    fileScanner = new Scanner(file);
    int[] intArray = new int[counter];
    for(int i =0; i<counter; i++){
        intArray[i] = fileScanner.nextInt();
    }
    fileScanner.close();

    return intArray;
  } 
}