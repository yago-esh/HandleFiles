package HandleFiles;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frame;
	private JFileChooser explorador;
	private File archivo;
	private String parent_name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		explorador = new JFileChooser("");
		explorador.setDialogTitle("Abrir documento...");
		explorador.setFileFilter(new FileNameExtensionFilter("TXT", "txt"));
		frame = new JFrame();
		frame.setBounds(100, 100, 350, 154);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton Start_bt = new JButton("Start");
		Start_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String ruta = readfile();
				new Read_Data().read(ruta,parent_name);
			}
		});
		Start_bt.setBounds(117, 41, 97, 25);
		frame.getContentPane().add(Start_bt);
	}
	
public String readfile() {
		
		explorador.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int seleccion = explorador.showDialog(null, "Abrir!");
		String ruta="";
		switch(seleccion) {
		case JFileChooser.APPROVE_OPTION:
			archivo = explorador.getSelectedFile();
			parent_name=archivo.getParent();
			ruta = archivo.getPath();
			System.out.println(parent_name);
		 //seleccionó abrir
		 break;

		default: return null;
		}
		return ruta;
	}
}
