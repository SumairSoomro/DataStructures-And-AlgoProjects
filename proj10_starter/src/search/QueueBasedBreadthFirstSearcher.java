package search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 */
public class QueueBasedBreadthFirstSearcher<T> extends Searcher<T> {

	public QueueBasedBreadthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
	}

	@Override
	public List<T> solve() {
        	// TODO
	    T initialState = searchProblem.getInitialState();
		Queue<List<T>> frontierQueue = new LinkedList<>();
		List<T> discoveredList = new ArrayList<>();
		
		List<T> initialPath = new ArrayList<>();
		initialPath.add(initialState);
		frontierQueue.add(initialPath);
		discoveredList.add(initialState);
		
		while (!frontierQueue.isEmpty()) {
			List<T> currentPath = frontierQueue.poll();
			T currentV = currentPath.get(currentPath.size() - 1);
		
			if (searchProblem.isGoalState(currentV) && isValid(currentPath)) {
				return currentPath;
			}
		
			for (T i : searchProblem.getSuccessors(currentV)) {
				if (!discoveredList.contains(i)) {
					List<T> newPath = new ArrayList<>(currentPath);
					newPath.add(i);
					frontierQueue.add(newPath);
					discoveredList.add(i);
				}
			}
		}
		
		return new ArrayList<>();
	}
}

