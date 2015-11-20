package de.hshannover.f4.trust.ironloggui.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7034670749972011389L;

	JTabbedPane mTabbedPane;

	public MainWindow() {
		init();
		initMenu();
	}

	public void init() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1280, 720);
		this.setResizable(true);
		mTabbedPane = new JTabbedPane();
		this.getContentPane().add(mTabbedPane);
	}

	synchronized public JTextArea addTab(String component) {
		JTextArea editorPane = new JTextArea();
		editorPane.setEditable(false);
		JScrollPane jsp = new JScrollPane(editorPane);
		mTabbedPane.addTab(component, null, jsp, component);
		return editorPane;
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
