package HandleFiles;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class Main {

	private JFrame frame;
	private JFileChooser explorador;
	private File archivo;
	private String parent_name;
	private JButton Start_bt;

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
	
	public void activateBtn() {
		Start_bt.setEnabled(true);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		explorador = new JFileChooser("\\\\FONETIC-vdf\\locuciones\\Edit");
		explorador.setDialogTitle("Abrir documento...");
		explorador.setFileFilter(new FileNameExtensionFilter("TXT", "txt"));
		frame = new JFrame();
		frame.setTitle("HandleFIles");
		frame.setBounds(810, 425, 319, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Start_bt = new JButton("Start");
		Start_bt.setFont(new Font("Tahoma", Font.BOLD, 13));
		Start_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Read_Data data = new Read_Data(Main.this);
				String ruta = readfile();
				if(ruta!=null) {
					data.countLines(ruta);
					data.read(ruta,parent_name);
					Start_bt.setEnabled(false);
				}
			}
		});
		Start_bt.setBounds(75, 11, 150, 50);
		frame.getContentPane().add(Start_bt);
		
		JLabel lblNewLabel = new JLabel("Versi\u00F3n 1.0.7");
		lblNewLabel.setBounds(12, 74, 100, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JButton Info_btn = new JButton("");
		
		Info_btn.setBorder(null);
		Info_btn.setOpaque(false);
		Info_btn.setBounds(257, 58, 32, 32);
		Info_btn.setBackground(Color.LIGHT_GRAY);
		Info_btn.setMargin(new Insets(0, 0, 0, 0));
		
		Info_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"Created by: Yago Echave-Sustaeta Hernán");
			}
		});
		Info_btn.setIcon(new ImageIcon(Main.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png")));
		frame.getContentPane().add(Info_btn);
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
		 //seleccionó abrir
		 break;

		default: return null;
		}
		return ruta;
	}
	public void changebtn(Float num) {
		Start_bt.setText(num+"%");
	}
}
