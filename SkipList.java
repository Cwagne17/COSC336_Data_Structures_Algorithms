import java.util.Random;

class SkipNode {
  private int value;
  private int level;
  public SkipNode[] forward;

  //Used to initialize head of SkipList
  public SkipNode(int levels){
    this.value = 0;
    this.forward = new SkipNode[levels+1];
    for (int i = 0; i <= levels; i++)
        this.forward[i] = null;
  }

  //Make Node for SkipList before adding it to the list
  public SkipNode(int value, int levels){
    this.value = value;
    this.level = levels;
    this.forward = (SkipNode[]) new SkipNode[levels+1];
    for (int i = 0; i <= levels; i++)
        this.forward[i] = null;
  }

  // adjust this node to have newLevel forward pointers.
  public void adjustLevel( int newLevel ) {
    level = newLevel;
    SkipNode[] oldf = this.forward;
    this.forward = new SkipNode[newLevel+1];
    for (int i = 0; i < oldf.length; i++)
        this.forward[i] = oldf[i];
    for (int i = oldf.length; i <= newLevel; i++)
        this.forward[i] = null; // newly added levels that aren't pointing to anything yet
  }
  
  //Getters
  public int getValue() { return this.value; }
  public int getLevel() { return this.level; }
}

public class SkipList {
  private SkipNode head; // first node
  private int level;  // max number of levels in the list
  private int maxLevel;
  static private Random value = new Random(); // Hold the Random class object

  //constructor that creates head node
  public SkipList() {
    this.level = 1;
    this.head = new SkipNode( this.level );
  }
  
  public void adjustHead(int newLevel) {  
    this.head.adjustLevel(newLevel);
  }

  /** Create a random number function from the standard Java Random
    class. Turn it into a uniformly distributed value within the
    range 0 to n-1 by taking the value mod n.
  */
  static int random(int n) {
      return Math.abs(value.nextInt()) % n;
  }
  /** Pick a level using a geometric distribution */
  public int randomLevel() {
    int lev;
    for (lev=0; random(2) == 0; lev++); // Do nothing
    return lev;
  }

  /** Insert a record into the skiplist */
  public void insert(int newValue) {
    int newLevel = randomLevel();
    if (newLevel > this.head.getLevel()){
      adjustHead(newLevel); 
      this.level = this.head.getLevel();   
    }
    
    if(newLevel>maxLevel) //Used for printing
      maxLevel = newLevel;

    SkipNode[] update = (SkipNode[]) new SkipNode[level+1]; //used to track end of level
    SkipNode x = this.head; // Start at header node
    for (int i=level; i>=0; i--) { // Find insert position
      while((x.forward[i] != null) && (newValue > x.forward[i].getValue()))
        x = x.forward[i];
      update[i] = x; // Saves the end pointer at level i
    }

    x = new SkipNode(newValue, newLevel);
    for (int i=0; i <= newLevel; i++) { // Corrects who is pointing to who
      x.forward[i] = update[i].forward[i]; 
      update[i].forward[i] = x;            
    }
  }

  public void search(int value){
    SkipNode ptr = this.head;
    boolean found = false;
    while(!found && ptr!=null){
      if(ptr.getValue()==value)
        found=true;
      ptr = ptr.forward[0];
    }
    System.out.println(found?value+" found":value+" not found");
  }

  public void delete(int value){
    SkipNode ptr = this.head;
    boolean deleted = false;
    if(ptr.getValue()==value){
      this.head = ptr.forward[0];
    } else {
      while(!deleted && ptr.forward[0]!=null){
        if(ptr.forward[0].getValue()==value){
          ptr.forward = ptr.forward[0].forward;
          deleted=true;
        }
        ptr = ptr.forward[0];
      }
    }
  }

  //Work on print out
  public void printEverything() {
    for(int i=maxLevel; i>=0; i--){
      SkipNode ptr = this.head;
      ptr = ptr.forward[0];
      System.out.print("Level "+i+": ");
      while (ptr != null) {
          if(ptr.getLevel() >= i){
            System.out.print(ptr.getValue()+" ");
          }
          ptr = ptr.forward[0];
      }
      System.out.println();   
    }
  }
}