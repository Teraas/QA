import java.util.Arrays;

public class Test {



	/*
	Q2: There are 2 sorted arrays a and b, suggest an algo to create a sorted Array c having all elements of a and b

	Array a = [2,4,5,7,8,10]
	Array b = [5,9,12,14,16]
	2,4,5
	 */
//	start at a[0];
//	If a[i] > b[j]
//		increment j and add to res arrat
//	If a[i] < b[j]
//	increment i

	public static void main(String[] args) {
		int[]  A  = {1, 2, 3};
		int[]  B = {2,4,5,7,8,10};
	System.out.println(Arrays.toString(mergeArray(A, B)));


	}

	private static int[] mergeArray( int[] a, int[] b) {
		int lenA = a.length;
		int lenB = b.length;
		int[] res = new int[lenA + lenB];
		int i = 0;
		int j = 0;
		int k=0;
//		if(a[0] >=b[0]) {
//			res[0]=b[0];
//			j=1;
//		}
//
//		else {
//			res[0] = a[0];
//			i=1;
//		}

		while (i < lenA || j < lenB) {
			while (i < lenA && j < lenB) {
				if (a[i] >= b[j]) {
					System.out.println(" if block " + i + " j value " + j);
					res[k] = b[j];
					j++;
					k++;
				}
				System.out.println(" else block " + i + " j value " + j);

				res[k] = a[i];
				i++;
				k++;

			}
		  {
				if (i < lenA) {
					System.out.println(" else1 block " + i + " j value " + j);
					res[k] = a[i];
					i++;
					k++;
				}
				if (j < lenB) {
					System.out.println(" else2 block " + i + " j value " + j + " k " + k);
					res[k] = b[j];
					j++;
					k++;
				}

			}
		}
		return res;
	}
}
