package DotSinker;
import java.util.Random;
import java.util.Scanner;

public class DotField {
	private int[][] dotField;
	private int hitsNeeded = 0;
	public int[][] generateRandomField(){
		Scanner s = new Scanner(System.in);
		int row;
		int col;
		do{
			System.out.print("Amount of rows: ");
			row = s.nextInt();
			System.out.print("Amount of columns: ");
			col = s.nextInt();
		}while(row<0 || col<0);
		int[][] theField = new int[row][col];
		Random r = new Random();
		for(int i = 0 ; i<theField.length; i++){
			for(int j = 0; j<theField[i].length; j++){
				theField[i][j] = r.nextInt(2);
			}
		}
		return theField;
	}
	public void useDotField(int[][] toUse){
		dotField = toUse;
		for(int[] x : toUse){
			for(int p : x){
				if(p == 1)
					hitsNeeded++;
			}
		}
	}
	public boolean isSunk(){
		if(hitsNeeded==0)
			return true;
		return false;
	}
	public boolean getAttack(){
		Scanner s;
		int row=0, col=0;
		boolean err = false;
		do{
			try{
				s = new Scanner(System.in);
				System.out.print("Enter row & col to attack: ");
				row = s.nextInt();
				col = s.nextInt();
				if(dotField[row][col] == 1){
					System.out.print("Attacking "+row+", "+col+"... ");
					System.out.println("Hit!");
					hitsNeeded--;
					dotField[row][col] = 0;
					return true;
				}
				err = false;
			}catch(Exception ex){
				err = true;
				System.err.println("An error occured while trying to process your input, try again.");
			}
		}while(err);
		System.out.print("Attacking "+row+", "+col+"... ");
		System.out.println("Miss");
		return false;
	}
	public void outputField(){
		for(int i = 0; i<dotField.length; i++){
			for(int j = 0; j<dotField[i].length; j++){
				System.out.print(dotField[i][j]+" ");
			}
			System.out.println();
		}
	}
}
