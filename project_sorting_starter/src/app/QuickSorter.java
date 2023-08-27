package app;

import java.util.Comparator;



public class QuickSorter<T> extends AbstractSorter<T> {

	public QuickSorter(SwapList<T> list, Comparator<T> comparator) {
		super(list, comparator);
	}

	@Override
	public SwapList<T> sort() {
		// TODO sort
		quicksort(list, 0, list.size() - 1);
    	return list;
	}
	public int partition(SwapList<T> list, int low, int high){
		int middle = (low + high) / 2;
		list.swap(high,middle);
		int storeIndex = low;
		for(int i = low;  i < high; i++){
			if(list.compare(i,high,comparator) <= 0 ){
				list.swap(i,storeIndex);
				storeIndex++;
			}
			}
			list.swap(storeIndex, high);
			return storeIndex;
	}
	public void quicksort(SwapList<T> list, int low, int high) {
		if (low < high) { //if there is more than one element in list
			int pivotIndex = partition(list, low, high); //partitions list and gets pivot
			quicksort(list, low, pivotIndex - 1); //quicksorts the subarray
			quicksort(list, pivotIndex + 1, high); //quicksorts the other subarray
		}
	}
}
