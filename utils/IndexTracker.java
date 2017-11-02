package utils;
public class IndexTracker {

	int[] indexOne;
	int[] indexTwo;
	
	int number_filled;
	//Allow for two parallel index for now
	public IndexTracker(int intialSize){
		//TODO: Find a good collection for this class
		indexOne = new int[intialSize];
		indexTwo = new int[intialSize];
		number_filled = 0;
	}
	
	//do a exception check here
	public int getIndexOne(int i){
		return indexOne[i];
	}
	//do a exception check here
	public int getIndexTwo(int i){
		return indexTwo[i];
	}
	
	public void add(int dex1, int dex2){
		//add an excpetion throw here
		indexOne[number_filled] = dex1;
		indexTwo[number_filled] = dex2;
		number_filled++;
	}
}
