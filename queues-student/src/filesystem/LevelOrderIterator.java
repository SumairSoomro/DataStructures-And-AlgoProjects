package filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;

import structures.Queue;


/**
 * An iterator to perform a level order traversal of part of a 
 * filesystem.
 */
public class LevelOrderIterator extends FileIterator<File> {
	
	/**
	 * Instantiate a new LevelOrderIterator, rooted at the rootNode.
	 * @param rootNode
	 * @throws FileNotFoundException if the rootNode does not exist
	 */
	Queue<File> files = new Queue<>();
	 public LevelOrderIterator(File rootNode) throws FileNotFoundException {
        	// TODO 1
		if (rootNode == null) {
			throw new IllegalArgumentException();
		}
		if(!rootNode.exists()){
			throw new FileNotFoundException();
        
		}
		else{
		files.enqueue(rootNode);
		}
	}
	
	@Override
	public boolean hasNext() {
        	// TODO 2
		
		if (files == null || files.isEmpty()) {
			return false;
		} 
		else {
				return true;
		}
	}

	@Override
	public File next() throws NoSuchElementException {
        	// TODO 3
			if (hasNext() == false) {
				throw new NoSuchElementException();
			}
			
			while (files.peek().isDirectory()) {
				File nextFile = files.dequeue();
				File[] insideListFiles = nextFile.listFiles();
				Arrays.sort(insideListFiles);
					for (File file : insideListFiles) {
						files.enqueue(file);
					}
			return nextFile;
			}
		return files.dequeue();

		} 
		
	

	@Override
	public void remove() {
		// Leave this one alone.
		throw new UnsupportedOperationException();		
	}

}
