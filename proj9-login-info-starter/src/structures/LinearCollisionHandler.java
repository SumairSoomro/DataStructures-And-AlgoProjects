package structures;

public class LinearCollisionHandler <K> implements CollisionHandler <K>{
    private int probeLength;

    /**
  * Constructors to set probeLength to 1, or a different length.
  */
    public LinearCollisionHandler(){
        this.probeLength = 1;
    }

    public LinearCollisionHandler(int probeLength){
        this.probeLength = probeLength;
    }

/**
  * Method starts at index and searches linearly until an open spot
  * is found in the array. This could include index itself.
  * index = (index + probeLength) % size
  */
   public int probe(int index, boolean[] activeArray, int M) {
      //TODO: Implement this method.
      int i = 0;
      while(activeArray[index] && i < M){
        index = (index + probeLength) % M;
        i++;
      }
      return index;
   }

  /**
* Start at index and search the array linearly until the target
* is found. Then return the array index of the target. 
* Return -1 if not found.
*/
   public int search(int startIndex, K target, K[] keyArray, boolean [] activeArray, int M){
      //TODO: Implement this method.
    if (keyArray == null || activeArray == null) {
      throw new NullPointerException();
    }
    int currentIndex = startIndex;
    while(keyArray[currentIndex] != null){
      if(activeArray[currentIndex] && keyArray[currentIndex].equals(target)){
        return currentIndex;
      }
      else{
        currentIndex = (currentIndex + probeLength) % M;
      }

    }
    return -1;
  }
}
