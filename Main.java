import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
          skipListMenu(input);
          flag=false;
          break;
        case 3:
          dijkstraAlgorithm(input);
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

  private static void skipListMenu(Scanner input) {
    SkipList sL = new SkipList();
    boolean sLFlag = true;
    while(sLFlag){
      System.out.println("Choose an option:\n1 - Insert integers from file\n2 - Search for an integer\n3 - Delete an integer\n4 - Print Skip List\n5 - Quit");
      int selection = input.nextInt();
      switch(selection){
        case 1:
          loadDataStructureInput(input);
          createSkipList(sL);
          break;
        case 2:
        case 3:
        case 4:
          System.out.println("\nSkipList:");
          sL.printEverything();
          System.out.println();
          break;
        case 5:
          sLFlag = false;
          break; 
        default:
          System.out.println("ERROR: Invalid option");
      }
    }
  }

  private static void createSkipList(SkipList sL){
    for(int num: inputValues){
      sL.insert(num);
    }
  }

  private static void dijkstraAlgorithm(Scanner input) {
    try {
      getFileName(input); //Use Dijkstra if another custom .txt file was not made
      ArrayList<String[]> payload = loadAlgorithmInput(input);
      //Create Graph
      for(int i=1; i<payload.size(); i++){
        
      }
      //Call algorithm using 
      //source = payload[0][0]
      //destination = payload[0][1] 
    } catch (FileNotFoundException e) {
      System.out.println("ERROR: "+e);
    }
  }

  private static void loadDataStructureInput(Scanner input){
    getFileName(input);
    try {
      readInputFile();
    } catch (FileNotFoundException e) {
      System.out.println("ERROR: "+e);
    }
  }


  /**
  * Private functions related to reading input files
  */


  // Gets the file name from user
  private static void getFileName(Scanner input){
    System.out.println("Enter file name (EXCLUDE .txt)");
    fileName = input.next()+".txt";
  }

  /**
   * 
   * @param input - scanner used to get the fileName
   * @return matrix that is formatted as
   * [
   *  [source node, destination node],
   *  [ node key, node key, weight],
   *  [ node key, node key, weight],
   *  ...
   * ]
   * 
   * Always assumes first line is the source and destination for the algorithm
   * Assumes following lines are the nodes that make the edge and the weight for the edge
   */
  private static ArrayList<String[]> loadAlgorithmInput(Scanner input) throws FileNotFoundException{
    File file = new File(fileName);
    Scanner scan = new Scanner(file);
    ArrayList<String[]> fileContent = new ArrayList<>();

    while(scan.hasNextLine()){
      fileContent.add(scan.nextLine().split(" "));
    }
    scan.close();

    return fileContent;
  }

  /**
   * Using file name in global variable
   * 1. Counts the number of integers in file
   * 2. Loads integers to inputValues array
   */
  private static void readInputFile() throws FileNotFoundException{
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
    inputValues = intArray;
  }
}