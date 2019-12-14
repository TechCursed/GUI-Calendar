import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class SCalendar extends JFrame {

	DefaultTableModel tableModel;
	Calendar mCal = new GregorianCalendar();
	JLabel mLabel;

	SCalendar() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Calendar");
		this.setSize(300, 200);
		this.setLayout(new BorderLayout());
		this.setVisible(true);

		mLabel = new JLabel();
		mLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JButton mPrevious = new JButton("Previous");
		mPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				mCal.add(Calendar.MONTH, -1);
				changeMonth();
			}
		});

		JButton mNext = new JButton("Next");
		mNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				mCal.add(Calendar.MONTH, +1);
				changeMonth();
			}
		});

		JPanel mPanel = new JPanel();
		mPanel.setLayout(new BorderLayout());
		mPanel.add(mPrevious, BorderLayout.WEST);
		mPanel.add(mLabel, BorderLayout.CENTER);
		mPanel.add(mNext, BorderLayout.EAST);

		
		String[]  mColumns = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
		tableModel = new DefaultTableModel(null, mColumns);
		JTable mTable = new JTable(tableModel);
		JScrollPane mPane = new JScrollPane(mTable);

		this.add(mPanel, BorderLayout.NORTH);
		this.add(mPane, BorderLayout.CENTER);

		this.changeMonth();
	}

	void changeMonth() {
		mCal.set(Calendar.DAY_OF_MONTH, 1);
		String month = mCal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
		int year = mCal.get(Calendar.YEAR);
		mLabel.setText(month + " " + year);

		int startDay = mCal.get(Calendar.DAY_OF_WEEK);
		int numberOfDays = mCal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int weeks = mCal.getActualMaximum(Calendar.WEEK_OF_MONTH);

		tableModel.setRowCount(0);
		tableModel.setRowCount(weeks);

		int i = startDay - 1;
		for(int day = 1; day <= numberOfDays; day++) {
			tableModel.setValueAt(day, i / 7, i % 7);
			i = i + 1;
		}
	}

	public static void main(String[] args) {
		SCalendar cal = new SCalendar();
	}
}
	

