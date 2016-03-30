
import java.io.*;
import java.util.*;

/**
*Demonstrates using parallel stream in java8. 
*Uses multiple parallel threads to sum  numbers in the range of 1 to n
*caution: there is overhead cost to parallel systems
* performance improvement should only be expected in multi-core systems
*@author: Ishola Babatunde
*@date: 03/29/16
*/
public class SumParallelPerf{

	public static void main(String[] args){
		
		SumParallelPerf summer = new SumParallelPerf();
		
		String endIndex;
		endIndex = args != null ? "10000000" : args[0] ;
		
		//create a list of numbers between 1 and endIndex
		System.out.println("Creating list, please wait.");
		ArrayList<Long> numberList = summer.createLongList(1, Long.parseLong(endIndex));
		System.out.println("List created");
		
		//test sum numbers using for-loop 
		System.out.print("Summing Sequential:");
		long seqStartTime = System.currentTimeMillis();
		//for(int i = 0; i <= 100; i++){
		long finalSum = summer.sumNumbers(numberList);
		//}
		long seqEndTime = System.currentTimeMillis();
		System.out.println(seqEndTime - seqStartTime);
		
		//test parallelized sum numbers
		//be aware of the overhead cost introduced by thread pooling
		System.out.print("Summing Parallized:");
		long parStartTime = System.currentTimeMillis();
		//for(int i = 0; i <= 100; i++){
		long finalSumParallel = summer.sumNumbersParallelized(numberList);
		//}
		long parEndTime = System.currentTimeMillis();
		System.out.println(parEndTime - parStartTime);
	}
	
	//uses a for loop to sum numbers in the list
	private long sumNumbers(ArrayList<Long> numberList){
		long sum = 0;
		for(Long number:numberList){
			sum += number;
		}
		return sum;
	}
	
	//uses parallel stream to sum numbers in the list
	private long sumNumbersParallelized(ArrayList<Long> numberList){
		long sum = numberList
			.parallelStream()
			.reduce((long)0, Long::sum);
		return sum;
	}
	
	//create a list of long numbers in the range iSart to iEnd
	private ArrayList<Long> createLongList(long iStart, long iEnd){
		ArrayList<Long> longList = new ArrayList<>();
		for(long i = iStart; i <= iEnd; i++){
			longList.add(i);
		}
		return longList;
	}
}
