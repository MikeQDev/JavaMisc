import java.util.*;

public class BubbleSort {

	public static void main(String [] args) {
		int[] intList = getNumbers();
		sort(intList);
	}
	public static void sort(int[] nums){
		int temp;
		int t = nums.length;
		boolean wasChanged = true;
		System.out.print("Unsorted list: ");
		sayNums(nums);
		System.out.println("Starting bubblesort...");
		while(wasChanged){
			t--;
			wasChanged = false;
			for(int i=0; i<t; i++){
				if(nums[i]>nums[i+1]){
					System.out.print("[Swapping "+nums[i]+","+nums[i+1]+"]");
					temp = nums[i];
					nums[i] = nums[i+1];
					nums[i+1] = temp;
					wasChanged = true;
					sayNums(nums);
				}
			}
		}
		System.out.print("Bubblesort completed.\nSorted list: ");
		sayNums(nums);
	}
	public static void sayNums(int[] nums){
		for(int p : nums)
			System.out.print("["+p+"]");
		System.out.println();
	}
	public static int[] getNumbers(){
		Scanner s = new Scanner(System.in);
		Scanner m = new Scanner(System.in);
		int size;
		int[] nums;
		boolean fillRand = false;
		do{
			System.out.print("Enter size of array you want: ");
			size = s.nextInt();
		}while(size<0);
		nums = new int[size];
		System.out.print("Fill array with random numbers? (Y/N): ");
		char next = m.nextLine().charAt(0);
		if(Character.toUpperCase(next) == 'Y')
			fillRand = true;
		if(fillRand){
			Random r = new Random();
			for(int i=0; i<size; i++){
				nums[i] = r.nextInt(500)-250;
			}
		}else{
			System.out.print("Enter numbers for each element seperated by spaces: ");
			for(int i=0; i<size; i++){
				nums[i] = s.nextInt();
			}
		}
		return nums;
	}
}
