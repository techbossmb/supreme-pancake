
import java.io.*;
import java.util.*;

public class SumParallelPerf{
	public static void main(String[] args){
		SumParallelPerf summer = new SumParallelPerf();
		String endIndex;
		endIndex = args != null ? "10000000" : args[0] ;
		System.out.println("Creating list, please wait.");
		ArrayList<Long> numberList = summer.createLongList(1, Long.parseLong(endIndex));
		System.out.println("List created");
		System.out.print("Summing Sequential:");
		long seqStartTime = System.currentTimeMillis();
		for(int i = 0; i <= 100; i++){
			long finalSum = summer.sumNumbers(numberList);
		}
		long seqEndTime = System.currentTimeMillis();
		System.out.println(seqEndTime - seqStartTime);
		System.out.print("Summing Parallized:");
		long parStartTime = System.currentTimeMillis();
		for(int i = 0; i <= 100; i++){
			long finalSumParallel = summer.sumNumbersParallelized(numberList);
		}
		long parEndTime = System.currentTimeMillis();
		System.out.println(parEndTime - parStartTime);
	}
	
	
	private long sumNumbers(ArrayList<Long> numberList){
		long sum = 0;
		for(Long list:numberList){
			sum += list;
		}
		return sum;
	}
	
	private long sumNumbersParallelized(ArrayList<Long> numberList){
		long sum = numberList
			.parallelStream()
			.reduce((long)0, Long::sum);
		return sum;
	}
	
	private ArrayList<Long> createLongList(long iStart, long iEnd){
		ArrayList<Long> longList = new ArrayList<>();
		for(long i = iStart; i <= iEnd; i++){
			longList.add(i);
		}
		return longList;
	}
}
