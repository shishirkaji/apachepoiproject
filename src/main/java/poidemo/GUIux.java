package poidemo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

public class GUIux extends JFrame {
	public GUIux(ArrayList<String> errors, LinkedList<Metadata> metadata) {
		JFrame f = new JFrame("Harvest DataCheck DEMO");
		String[] columnNames = { "error no" };
		JList<String> displayList = new JList<>(errors.toArray(new String[0]));
		JList<Metadata> metaDataList = new JList<>(metadata.toArray(new Metadata[0]));
		JScrollPane scrollPane = new JScrollPane(displayList);
//		JButton b = new JButton("Find FIle");
//		b.setBounds(50, 100, 95, 30);
//		b.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//		});
//		f.add(b);
//		f.add(displayList);
		f.add(metaDataList);
		f.add(displayList);
		f.setSize(1000, 1000);
		f.setVisible(true);
	}

}
