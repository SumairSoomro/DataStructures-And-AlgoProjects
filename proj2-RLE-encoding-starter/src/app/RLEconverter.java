package app;

import java.util.Scanner;
import java.io.*;

public class RLEconverter {
   private final static int DEFAULT_LEN = 100; // used to create arrays.

   /*
    *  This method reads in an uncompressed ascii image file that contains 
    *  2 characters. It stores each line of the file in an array.
    *  It then calls compressAllLines to get an array that stores the compressed
    *  version of each uncompressed line from the file. The compressed array
    *  is then passed to the getCompressedFileStr method which returns a String
    *  of all compressed lines (the two charcaters are written in the first line)
    *  in CSV format. This String is written to a text file with the prefix "RLE_"
    *  added to the original, uncompressed file name.
    *  Note that dataSize keeps track of the number of lines in the file. The array 
    *  that holds the lines of the file is initialized to the DEFAULT_LEN, which 
    *  is assumed to be << the number of lines in the file.
    */   
  public void compressFile(String fileName) throws IOException{
    Scanner scan = new Scanner(new FileReader(fileName));
    String line = null;
    String[] decompressed = new String [DEFAULT_LEN];
    int dataSize = 0;
    while(scan.hasNext()){
      line = scan.next();
      if(line != null && line.length()>0)
        decompressed[dataSize]=line;
        dataSize++;
    }
    scan.close();
    char[] fileChars = discoverAsciiChars(decompressed, dataSize); 
    String[] compressed = compressAllLines(decompressed, dataSize, fileChars);
    writeFile(getCompressedFileStr(compressed, fileChars), "RLE_"+fileName);
  }
  
   
/*
 * This method implements the RLE compression algorithm. It takes a line of uncompressed data
 * from an ascii file and returns the RLE encoding of that line in CSV format.
 * The two characters that make up the image file are passed in as a char array, where
 * the first cell contains the first character that occurred in the file.
*/
public String compressLine(String line, char[] fileChars){
  int count = 0;
  String comma = ",";
  StringBuilder w = new StringBuilder();
    for(int i = 0; i < line.length(); i++){ // loops thrugh line
      if(i == 0) { 
        if(line.charAt(i) != fileChars[0]){ // checks to see if first char is not the first char in the array
          w.append(count+ comma);
          count++;
        }
        else {
          count++;
        }
      }
      else {
        if(line.charAt(i) == fileChars[0]){ 
          if (line.charAt(i - 1) != line.charAt(i)) {// this if block resets the count to check if the one before is not the same 
            w.append(count+comma);
            count = 0;
          }
          count++;
        }
        else if(line.charAt(i) == fileChars[1]){// does same thing but for other char
          if (line.charAt(i - 1) != line.charAt(i)) {
            w.append(count+comma);
            count = 0;
          }
          count++;
        }
      }
    }
  w.append(count);
  return w.toString();
}


    


  /*
   *  This method discovers the two ascii characters that make up the image. 
   *  It iterates through all of the lines and writes each compressed line
   *  to a String array which is returned. The method compressLine is called on 
   *  each line.
   *  The dataSize is the number of lines in the file, which is likely to be << the length of lines.
   */
  public String[] compressAllLines(String[] lines, int dataSize, char[] fileChars){
    String[] w = new String[lines.length];  
    if (lines.length == 0) {
      return null;
    }
    for(int i = 0; i < dataSize; i++) {
      w[i] = compressLine(lines[i], fileChars); // use the compress line method  on each line and add to string array
    }
    return w;
}

/*
 *  This method assembles the lines of compressed data for
 *  writing to a file. The first line must be the 2 ascii characters
 *  in comma-separated format. 
 */
public String getCompressedFileStr(String[] compressed, char[] fileChars) {
  String w = "";
  w += fileChars[0] + "," + fileChars[1] + "\n" ;
    for(int i = 0; i < compressed.length; i++){
      w += compressed[i] + "\n"; // adds each compressed line to the string

    }
  
  return w;
  
}
   /*
    *  This method reads in an RLE compressed ascii image file that contains 
    *  2 characters. It stores each line of the file in an array.
    *  It then calls decompressAllLines to get an array that stores the decompressed
    *  version of each compressed line from the file. The first row contains the two 
    *  ascii charcaters used in the original image file. The decompressed array
    *  is then passed to the getDecompressedFileStr method which returns a String
    *  of all decompressed lines, thus restoring the original, uncompressed image.
    *  This String is written to a text file with the prefix "DECOMP_"
    *  added to the original, compressed file name.
    *  Note that dataSize keeps track of the number of lines in the file. The array 
    *  that holds the lines of the file is initialized to the DEFAULT_LEN, which 
    *  is assumed to be << the number of lines in the file.
    */   
  public void decompressFile(String fileName) throws IOException{
    Scanner scan = new Scanner(new FileReader(fileName));
    String line = null;
    String[] compressed = new String [DEFAULT_LEN];
    int dataSize =0;
    while(scan.hasNext()){
      line = scan.next();
      if(line != null && line.length()>0)
        compressed[dataSize]=line;
        dataSize++;
    }
    scan.close();
    String[] decompressed = decompressAllLines(compressed, dataSize);
    writeFile(getDecompressedFileStr(decompressed), "DECOMP_"+fileName);
  }
 
   /*
   * This method decodes lines that were encoded by the RLE compression algorithm. 
   * It takes a line of compressed data and returns the decompressed, or original version
   * of that line. The two characters that make up the image file are passed in as a char array, 
   * where the first cell contains the first character that occurred in the file.
   */
   public String decompressLine(String line, char[] fileChars){
      //TODO: Implement this method
    int tracker = 0;
    StringBuilder w = new StringBuilder();
    String[] imp = line.split(","); // to get rid of comma and create an array with the words that make the string
      for(int i = 0; i < imp.length; i++){
        for(int j = 0; j < Integer.parseInt(imp[i]);j++){ // use integer.parse method to change string to int and nested for loop for each number
         w.append(fileChars[tracker]); 
          
      }
      tracker = 1 - tracker; // to switch between the two chars to add to the stringbuilder object



    
      }
    return w.toString();
    }






   
    /*
   *  This method iterates through all of the compressed lines and writes 
   *  each decompressed line to a String array which is returned. 
   *  The method decompressLine is called on each line. The first line in
   *  the compressed array passed in are the 2 ascii characters used to make
   *  up the image. 
   *  The dataSize is the number of lines in the file, which is likely to be << the length of lines.
   *  The array returned contains only the decompressed lines to be written to the decompressed file.
   */
  public String[] decompressAllLines(String[] lines, int dataSize){
     //TODO: Implement this method
     char[] s = new char[2];
     s[0] = lines[0].charAt(0);
     s[1] = lines[0].charAt(2); // we do this to get rid of comma
     String[] w = new String[lines.length];
      for(int i = 1; i < dataSize; i++) {
        w[i] = (decompressLine(lines[i], s)); // use decompress method and add to string array
      }
    return w;
  }
  
  /*
   *  This method assembles the lines of decompressed data for
   *  writing to a file. 
   */
  public String getDecompressedFileStr(String[] decompressed){
    
   //TODO: Implement this method
    String data = "";
    for(int i = 0; i < decompressed.length; i++){
      data += decompressed[i] + "\n";
    }
    return data;
  }

  // assume the file contains only 2 different ascii characters.
  public char[] discoverAsciiChars(String[] decompressed, int dataSize){
//TODO: Implement this method
  char[] chara = new char[2];
  chara[0] = decompressed[0].charAt(0);
  for(int i = 0; i < decompressed.length; i++) {
    for(int j = 0; j < decompressed[i].length(); j++) { // nested for loop to iterate through each line and lines content
      if(decompressed[i].charAt(j) != chara[0]) {
        chara[1] = decompressed[i].charAt(j) ;
        break; // break statement to exit once it finds other character 
      }
    }
  }
  return chara;
  }




   
   public void writeFile(String data, String fileName) throws IOException{
		PrintWriter pw = new PrintWriter(fileName);
      pw.print(data);
      pw.close();
   }
}