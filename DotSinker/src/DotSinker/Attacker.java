package DotSinker;

import java.util.Scanner;

public class Attacker {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		char playAgain = 'N';
		do{
			DotField d = new DotField();
			int[][] myF = d.generateRandomField();
			d.useDotField(myF);
			d.outputField();
			while(!d.isSunk()){
				d.getAttack();
				d.outputField();
			}
			System.out.println("You sunk all of the dots. Good job!");
			System.out.print("Play again? Y/N: ");
			playAgain = s.nextLine().charAt(0);
		}while(Character.toUpperCase(playAgain)=='Y');
	}
}