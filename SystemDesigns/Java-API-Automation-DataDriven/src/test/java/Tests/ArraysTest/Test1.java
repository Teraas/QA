package Tests.ArraysTest;

import java.util.ArrayList;
import java.util.Arrays;

public class Test1 {

	public static void main(String args[]) {
		ArrayList<ArrayList<Integer>> A =  new ArrayList<ArrayList<Integer>>();;
		A.add( new ArrayList( Arrays.asList(new int[]{1, 2, 3, 4}) ) ); //[[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]];
		A.add( new ArrayList( Arrays.asList(new int[]{5, 6, 7, 8}) ) );
		A.add( new ArrayList( Arrays.asList(new int[]{9, 10, 11, 12}) ) );
		ArrayList<ArrayList<Integer>> B = performOps(A);
		for (int i = 0; i < B.size(); i++) {
			for (int j = 0; j < B.get(i).size(); j++) {
				System.out.print(B.get(i).get(j) + " ");

			}
		}
	}
	public static ArrayList<ArrayList<Integer>> performOps(ArrayList<ArrayList<Integer>> A) {
		ArrayList<ArrayList<Integer>> B = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < A.size(); i++) {
			B.add(new ArrayList<Integer>());

			for (int j = 0; j < A.get(i).size(); j++) {
				B.get(i).add(0);
			}

			for (int j = 0; j < A.get(i).size(); j++) {
				B.get(i).set(A.get(i).size() - 1 - j, A.get(i).get(j));
			}
		}
		return B;
	}


}
