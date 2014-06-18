import java.util.*;

public class ThreadDemonstration {
	boolean finished;
	int countTo;
	public static void main(String[] args){
		new ThreadDemonstration();
	}
	public ThreadDemonstration(){
		char runAgain = 'n';
		do{
			finished = false;
			int threadAmt;
		
			Scanner s = new Scanner(System.in);
			do{
				System.out.print("Number to count to: ");
				countTo = s.nextInt();
				System.out.print("Amount of threads to use: ");
				threadAmt = s.nextInt();
			}while(countTo<1||threadAmt<1);
			
			MyRunnable r = new MyRunnable();
			Thread[] myThreads = new Thread[threadAmt];
			
			for(int i=0; i<myThreads.length; i++){
				myThreads[i] = new Thread(r, "Thread #"+i);
			}
			
			long start = System.currentTimeMillis();
			
			for(Thread t : myThreads){
				t.start();
			}
			
			while(!finished){
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			long stop = System.currentTimeMillis();
			System.out.println("Counted to "+countTo+" in " + (stop-start)+" ms using "+threadAmt+" threads."
					+ " (w/ 50ms sleep after each threads' add)");
			System.out.print("Run again? (Y/N): ");
			Scanner sc = new Scanner(System.in);
			runAgain = sc.nextLine().charAt(0);
		}while(Character.toUpperCase(runAgain)=='Y');
	}
	class MyRunnable implements Runnable{
		int i = 0;
		public void run(){
			startAdding();
		}
		public void startAdding(){
			while(i <= countTo){
				synchronized(this){
				if(i==countTo+1) break;
				System.out.println(Thread.currentThread().getName()+" found "+i);
				i++;
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			finished = true;
		}
	}
}