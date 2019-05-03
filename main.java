/*
 * Part A
 * Write a function called Quick_select to find the kth least element on a given array. 
 * (The average running time of your algorithm should be O(n)) (Hint: Use partitioning algorithm)
 	* 1. Request the user to enter a positive integer, and call it n.
 	* 2. Generate n random integers between -100 to 100 and save them in array a.
 	* 3. Print the generated array.
 	* 4. Request the user to enter a number between 1 to n (as the kth least element).
 	* 5. Call your function to find and print the kth least element.
 * Part B
 * Explain an algorithm to return the max k numbers from an unsorted array. 
 * (The average running time of your algorithm should be O(n)) (Hint: You could modify your Quick_select algorithm to solve this question.)
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class main{

	public static void main(String[] args){
		
		Scanner keyboard = new Scanner(System.in);
		List<Integer> a = new ArrayList<>();
		Random randomGenerator = new Random();
		
		System.out.println("Enter a positive integer");
		int n = keyboard.nextInt();
		
		for(int i = 0; i < n; i++){
			int num = -100 + randomGenerator.nextInt(200);
			while(a.contains(num) == true){
				num = -100 + randomGenerator.nextInt(200);
			}
			a.add(num);
		}
		
		System.out.println("Generated Array:");
		for(int i = 0; i < a.size(); i++){
			System.out.print(a.get(i) + ", ");
		}
		
		System.out.println("\nEnter a positive integer between 1 and " + n);
		int k = keyboard.nextInt() - 1;
		
		int kthLeast = Quick_select(a, 0, n - 1, k);
		System.out.println("The kth least element is: " + a.get(kthLeast));
		int kthMax = Quick_select(a, 0, n - 1, n - 1 - k);
		System.out.print("The kth max elements are: ");
		for(int i = kthMax; i < a.size(); i++){
			System.out.print(a.get(i) + ", ");
		}
	}
	
	public static int partition(List<Integer> a, int lower, int upper){
		int pIndex = (lower + upper) / 2;
		int pValue = a.get(pIndex);
		while(lower < upper){
			while(a.get(lower) < pValue)
				lower++;
			while(a.get(upper) > pValue)
				upper--;
			if(lower <= upper){
				if(a.get(lower) == pValue)
					pIndex = upper;
				else if(a.get(upper) == pValue)
					pIndex = lower;
				int temp = a.get(lower);
				a.set(lower, a.get(upper));
				a.set(upper, temp);
			}
		}
		return pIndex;
	}
	
	public static int Quick_select(List<Integer> a,int start, int end, int k){
		int index = partition(a, start, end);
		if(index == k)
			return index;
		else if(index > k)
			return Quick_select(a, start, index - 1, k);
		else 
			return Quick_select(a, index + 1, end, k);
	}

}
