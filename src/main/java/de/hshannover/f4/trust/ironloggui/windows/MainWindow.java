package de.hshannover.f4.trust.ironloggui.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7034670749972011389L;

	private JTabbedPane mTabbedPane;
	
	private HashMap<String,JTextArea> mTextAreas = new HashMap<String,JTextArea>();

	public MainWindow() {
		init();
		initMenu();
	}

	public void init() {
		this.setTitle("IronLogGUI");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1280, 720);
		this.setResizable(true);
		mTabbedPane = new JTabbedPane();
		this.getContentPane().add(mTabbedPane);
	}

	synchronized public void addTab(String logFileName) {
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane jsp = new JScrollPane(textArea);
		mTabbedPane.addTab(logFileName, null, jsp, logFileName);
		mTextAreas.put(logFileName, textArea);
	}
	
	synchronized public void appendTextInTab(String logFileName,String text) {
		JTextArea textArea = mTextAreas.get(logFileName);
		textArea.append(text);
	}
	

	public void initMenu() {

		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;

		menuBar = new JMenuBar();

		menu = new JMenu("File");
		menuBar.add(menu);

		menuItem = new JMenuItem("Exit", null);
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(NORMAL);
			}
		});
		menu.add(menuItem);
		
		this.setJMenuBar(menuBar);
	}
}
