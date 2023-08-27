package search;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Stack. This results in a
 * depth-first search.
 * 
 */
public class StackBasedDepthFirstSearcher<T> extends Searcher<T> {
	
	public StackBasedDepthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
	}

	@Override
	public List<T> solve() {
        List<T> path = new ArrayList<>();
        Stack<List<T>> stack = new Stack<>();
        List<T> visited = new ArrayList<>();
    
        T initialState = searchProblem.getInitialState();
        path.add(initialState);
        stack.push(path);
    
        while (!stack.isEmpty()) {
            List<T> currentPath = stack.pop();
            T currentState = currentPath.get(currentPath.size() - 1);
    
            if (visited.contains(currentState)) {
                continue;
            }
    
            visited.add(currentState);
    
            if (searchProblem.isGoalState(currentState)) {
                List<T> solution = new ArrayList<>(currentPath);
                if (isValid(solution)) {
                    return solution;
                }
            } else {
               
                for (T successor : searchProblem.getSuccessors(currentState)) {
                    if (!visited.contains(successor)) {
                        List<T> newPath = new ArrayList<>(currentPath);
                        newPath.add(successor);
                        stack.push(newPath);
                    }
                }
            }
        }
    
        return new ArrayList<>();
    }
}


