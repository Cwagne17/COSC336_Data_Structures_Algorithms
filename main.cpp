#include <iostream>
#include <fstream>
#include <string>

using namespace std;

//Variables declarations
string textFile;
int to_doOption;
int* inputArray;

//Function declarations
void readFile();
bool isNumber(string s);

int main() {
  cout << "COSC 336 Homework 3" << endl;

  //Decides the datastructure or algorithm to be used
  cout << "What would you like to do?" 
  << "\n1 - Construct a RB-tree" 
  << "\n2 - Construct a randomized Skip-list"
  << "\n3 - Use Dijkstra’s algorithm on an undirected graph" << endl;
  cin >> to_doOption;
  
  //Gets the input file to be used
  cout << "What input text file would you like to read from? (DO NOT INCLUDE .txt)" << endl;
  cin >> textFile;
  
  //Uses appropriate read file
  switch(to_doOption){
    case 1:
      readFile();
      //RB-tree call here
      break;
    case 2:
      readFile();
      //Skip-list call here
      break;
    case 3:
      //Read special file here
      //Dijkstra alg. here
      break;
    default:
      cout << "Error: Bad TO_DO Option" << endl;
  }
  
  return 0;
}


/**
 * @brief used to read the file of integers for the data parts 1 and 2 of HW03
 * 1. Construct RB-tree
 * 2. Construct Skip-list
 * @returns integer array from input textFile
 */
void readFile() {
  //Opens file to count array size needed
  ifstream InFile;
  InFile.open(textFile+".txt");

  //Reads the amount of integers in the file
  int count; int i;
  while(InFile >> i)
    count++;
  //close input file
  InFile.close();

  //Opens input file to read to array
  ifstream input_file;
  input_file.open(textFile+".txt");

  //Assigns file values to array if they are positive integers
  int* arr; arr = new int[count]; int index=0;
  while(input_file >> i) {
    if(isNumber(to_string(i)) && i>=0){
      arr[index] = i;
      index++;
    }
  }
  //close input file
  InFile.close();
  inputArray = arr;
}

// Returns true if s is a number else false
bool isNumber(string s)
{
    for (int i = 0; i < s.length(); i++)
        if (isdigit(s[i]) == false)
            return false;
 
    return true;
}