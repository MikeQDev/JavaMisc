import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Simple multiple count-down timer
 * @author Mike
 *
 */
public class CountDownGUI extends JFrame {
	final int AMOUNTOFCOUNTERS;
	final int COUNTDOWNTIMESECS;
	boolean[] isRunning;
	JTextField[] countDownFields;
	JButton[] startButtons;
	
	/**
	 * Constructor
	 * @param time Time (in seconds) each watch has
	 * @param amount Amount of watches to display
	 */
	public CountDownGUI(int time, int amount) {
		super("Countdown");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);

		COUNTDOWNTIMESECS = time;
		AMOUNTOFCOUNTERS = amount;

		countDownFields = new JTextField[AMOUNTOFCOUNTERS];
		startButtons = new JButton[AMOUNTOFCOUNTERS];
		isRunning = new boolean[AMOUNTOFCOUNTERS];

		buildPanel();

		pack();
		setVisible(true);
	}
	/**
	 * Stops timer for specified watch
	 * @param timerNum Watch to stop
	 */
	public void stopTimer(int timerNum){
		isRunning[timerNum] = false;
		startButtons[timerNum].setText("Start #" + timerNum);
	}
	
	/**
	 * Starts timer for specified watch
	 * @param timerNum Watch to start
	 * @return New count-down thread
	 */
	public Thread startTimer(final int timerNum) {
		isRunning[timerNum] = true;
		return new Thread(new Runnable() {
			public void run() {
				try {
					for (int t = COUNTDOWNTIMESECS; t > 0; t--) {
						countDownFields[timerNum].setText(t + "");
						Thread.sleep(1000);
					}
					countDownFields[timerNum].setText("0");
				} catch (InterruptedException e) {
					return;
				}
				java.awt.Toolkit.getDefaultToolkit().beep();
				stopTimer(timerNum);
			}
		});
	}
	/**
	 * Build the timer UI
	 */
	public void buildPanel() {
		JPanel itemHolder = new JPanel(new GridLayout(AMOUNTOFCOUNTERS, 2));

		for (int i = 0; i < AMOUNTOFCOUNTERS; i++) {
			final int cur = i;
			countDownFields[cur] = new JTextField(COUNTDOWNTIMESECS + "");
			startButtons[cur] = new JButton("Start #" + cur);
			isRunning[cur] = false;
			startButtons[cur].addActionListener(new ActionListener() {
				Thread cT = null;

				public void actionPerformed(ActionEvent e) {
					if (isRunning[cur]) {
						cT.interrupt();
						stopTimer(cur);
					} else {
						cT = startTimer(cur);
						cT.start();
						startButtons[cur].setText("Stop #"+cur);
					}
				}
			});
			itemHolder.add(countDownFields[cur]);
			itemHolder.add(startButtons[cur]);
		}

		add(itemHolder);
	}
	
	/**
	 * Entry & set up
	 * @param args
	 */
	public static void main(String[] args) {
		boolean error = true;
		do {
			try {
				String strT = JOptionPane.showInputDialog("How much time per watch?");
				if(strT == null)
					return;
				String strA = JOptionPane.showInputDialog("How many watches?");
				if(strA == null)
					return;
				
				int t = Integer.parseInt(strT);
				int a = Integer.parseInt(strA);
				
				if(t < 1 || a < 1)
					throw new Exception();
				error = false;
				new CountDownGUI(t, a);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error; make sure input values are >1");
				error = true;
			}
		} while (error);
	}

}
