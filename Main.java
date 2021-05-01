import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
  private static String fileName;
  private static int[] inputValues;
  private static int choice;
  private static boolean flag = true;

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("COSC 336 - HW03 Algorithms and Data Structures\n");

    while(flag){
      System.out.println("Choose an option:\n1 - RedBlackTree\n2 - Skiplist\n3 - Dijkstras Algorithm\n4 - Quit");
      choice = input.nextInt();
      
      switch(choice){
        case 1:
          loadDataStructureInput(input);
          createRedBlackTree();
          flag=false;
          break;
        case 2:
          loadDataStructureInput(input);
          //create skip list
          flag=false;
          break;
        case 3:
          loadAlgorithmInput(input);
          //Run algorithm
          flag=false;
          break;
        case 4:
          flag=false;
          break;
        default:
          System.out.println("ERROR: Invalid option");
          flag=true;
      }
    }
    input.close();
    System.out.println("Thank you for using HW03 Algorithm and Data Structures program\nGoodbye. . .");
  }
  
  private static void createRedBlackTree(){
    RedBlackTree tree = new RedBlackTree();
    for(int num: inputValues){
      tree.insert(num);
    }
    System.out.println("\nRed Black Tree:");
    tree.print();
    System.out.println();
  }

  private static void loadAlgorithmInput(Scanner input){
    getFileName(input); //Use Dijkstra if another custom .txt file was not made
    
  }

  private static void loadDataStructureInput(Scanner input){
    getFileName(input);
    try {
      inputValues = readInputFile();
    } catch (FileNotFoundException e) {
      System.out.println("ERROR: "+e);
    }
  }

  private static void getFileName(Scanner input){
    System.out.println("Enter file name (EXCLUDE .txt)");
    fileName = input.next()+".txt";
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