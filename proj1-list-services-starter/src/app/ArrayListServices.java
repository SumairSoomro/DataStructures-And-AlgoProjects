package app;
import java.util.ArrayList;

/**
 *  This class provides methods that perform operations on a list of generic objects.
 *  The Objects are assumed to have an appropriate implementation of the equals method.
 */
public class ArrayListServices <T> {

   /**
    * This method takes an ArrayList as a parameter and returns a new ArrayList that 
    * does not contain any duplicate data. For example, if this list was passed in: 
    * [A, B, D, A, A, E, B], the method would return a list containing: [A, B, D, E]. 
    * The ordering of the data does not matter. 
    * Assume that the parameter is not null, but it may be an empty ArrayList, in which case 
    * your method returns a new, empty ArrayList.
    * Also note that the ArrayList that is returned must be a new ArrayList which is not the 
    * same as the ArrayList passed in. In other words, the parameter must not be changed by your method code.
    */  
    

   public ArrayList<T> deDuplicate(ArrayList<T> inputList){
      //Write your comments on how you implemented the method.
      /**
      **/
      ArrayList<T> Arr = new ArrayList<T>(); //created an arraylist

      for(T thing : inputList){  //iterated through the original array
       if(!Arr.contains(thing)){ // used an enhanced for loop to see if the new Arraylist does not contain a repeated object T
         Arr.add(thing);  //if it does not contain it, add object to the list
       }
       
      }
      return Arr; //return the new Array without modifying the original one
      
     
      
   }

   /**
    * This method takes two ArrayLists as parameters and returns a new ArrayList that 
    * contains the intersection of the data in the ArrayLists passed in. The intersection 
    * contains the elements that occur in both lists.
    * For example, if these lists were passed in: [A, B, D, A, A, E, B], [B, E, C], the method 
    * would return a list containing: [B, E]. The ordering of the data does not matter. Note that 
    * the result does not contain any duplicates.
    * Assume that the parameters are not null, but one or both may be an empty ArrayList, in which 
    * case your method returns a new, empty ArrayList.
    * Also note that the ArrayList that is returned must be a new ArrayList which is not the same as 
    * the ArrayList passed in. In other words, the parameter must not be changed by your method code.
    */
   public ArrayList<T> getSetIntersection(ArrayList<T> listA, ArrayList<T> listB){
      ArrayList<T> Arr = new ArrayList<T>();
      for(T comm : listA){ //iterate through listA
       for(T ob : listB){ // nexted for loop to check listB too
        if(comm.equals(ob) && !Arr.contains(ob)){ // if statement to check if both objects are the same in the array, the second && statement makes sure that there are no duplicates
          Arr.add(ob);
        }

        

       }


       }
        return Arr;

      
      }
      
      
      
   

   /**
    *  This method takes two ArrayLists as parameters and returns a new ArrayList that 
    * contains the set difference of the data in the ArrayLists passed in. The set difference 
    * contains the elements that occur only in one or the other list, but not in both.
    * For example, if these lists were passed in: [A, B, D, A, A, E, B], [B, E, C], the method 
    * would return a list containing: [A, C]. The ordering of the data does not matter. Note 
    * that the result does not contain any duplicates.
    * Assume that the parameters are not null, but one or both may be an empty ArrayList. In the 
    * case where one list is empty, your method returns a new ArrayList that contains all of the 
    * elements on the other list- with no duplicates. In the case where both lists are empty, your 
    * method returns a new, empty ArrayList.
    * Also note that the ArrayList that is returned must be a new ArrayList which is not the same 
    * as the ArrayList passed in. In other words, the parameter must not be changed by your method code.
    */
   public ArrayList<T> getSetDifference(ArrayList<T> listA, ArrayList<T> listB){
      ArrayList<T> Arr = new ArrayList<T>();
      for(T ob: listA ){// iterate through listA
         if((!listB.contains(ob) && !Arr.contains(ob))){ //checks if listB does not have the object and the created array does not have object so that there are no duplicates
            Arr.add(ob); // the obj is added to the created array
         }
      }
        for(T a : listB){ // iterate through listB
         if((!listA.contains(a) && !Arr.contains(a) )) { // does same thing where difference is calculated by seeing if listA does not have object and = duplicates are checked
            Arr.add(a);
         }
      }
   
     
      
      
      return Arr;
   }

}