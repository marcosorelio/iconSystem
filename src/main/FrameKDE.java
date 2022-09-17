package main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class FrameKDE extends JFrame 
{

	private JPanel contentPane;
	private JTextField txtId;
	private JLabel lblIdPegawai;
	private JTextField txtNama;
	private JTextArea textAlamat;
	private JLabel lblNama;
	private JLabel lblAlamat;
	private JTable tabel;
	@SuppressWarnings("rawtypes")
	private JComboBox cbJenkel;
	private JButton btnSimpan;
	private JButton btnRefresh;
	private JLabel lblJenkel;
	String data[] = {"ID","Nama","Alamat","JenKel"};
	DefaultTableModel tabelModel;
	private JLabel lblBg;
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FrameKDE()
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblIdPegawai = new JLabel("Id Pegawai : ");
		lblIdPegawai.setForeground(new Color(255, 255, 255));
		lblIdPegawai.setBounds(12, 12, 98, 15);
		contentPane.add(lblIdPegawai);
		
		txtId = new JTextField();
		txtId.setBounds(131, 10, 114, 19);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		lblNama = new JLabel("Nama : ");
		lblNama.setForeground(new Color(255, 255, 255));
		lblNama.setBounds(12, 47, 70, 15);
		contentPane.add(lblNama);
		
		txtNama = new JTextField();
		txtNama.setBounds(131, 41, 258, 19);
		contentPane.add(txtNama);
		txtNama.setColumns(10);
		
		lblAlamat = new JLabel("Alamat : ");
		lblAlamat.setForeground(new Color(255, 255, 255));
		lblAlamat.setBounds(12, 80, 70, 15);
		contentPane.add(lblAlamat);
		
		textAlamat = new JTextArea();
		textAlamat.setBounds(131, 80, 258, 94);
		contentPane.add(textAlamat);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(131, 317, 324, 132);
		contentPane.add(scrollPane);
		
		tabelModel = new DefaultTableModel(null,data);
		tabel = new JTable();
		tabel.setModel(tabelModel);
		scrollPane.setViewportView(tabel);
		
		btnSimpan = new JButton("Simpan");
		btnSimpan.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent act) 
			{
				int id = Integer.parseInt(txtId.getText());
				String nama = txtNama.getText();
				String alamat = textAlamat.getText();
				String jenkel = "";
				if(cbJenkel.getSelectedIndex() == 1)
				{
					jenkel = "Pria";
				}
				else if(cbJenkel.getSelectedIndex() == 2)
				{
					jenkel = "Wanita";
				}
				
				Object obj[] = new Object[4];
				obj[0] = id;
				obj[1] = nama;
				obj[2] = alamat;
				obj[3] = jenkel;
				tabelModel.addRow(obj);
			}
		});
		//btnSimpan.setIcon(new ImageIcon("/home/resa/Aplikasi Java/SwingComponents/src/Gambar/Save.png"));
		btnSimpan.setBounds(131, 265, 117, 40);
		contentPane.add(btnSimpan);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent act) 
			{
				txtId.setText("");
				txtNama.setText("");
				textAlamat.setText("");
				cbJenkel.setSelectedIndex(0);
				txtId.requestFocus();
			}
		});
		//btnRefresh.setIcon(new ImageIcon("/home/resa/Aplikasi Java/SwingComponents/src/Gambar/Refresh.png"));
		btnRefresh.setBounds(272, 265, 117, 40);
		contentPane.add(btnRefresh);
		
		lblJenkel = new JLabel("Jenkel : ");
		lblJenkel.setForeground(new Color(255, 255, 255));
		lblJenkel.setBounds(12, 216, 70, 15);
		contentPane.add(lblJenkel);
		
		
		cbJenkel = new JComboBox();
		cbJenkel.setModel(new DefaultComboBoxModel(new String[] {"-------", "Pria ", "Wanita"}));
		
		cbJenkel.setBounds(131, 201, 175, 30);
		contentPane.add(cbJenkel);
		
		JLabel lblIcon = new JLabel("");
		//lblIcon.setIcon(new ImageIcon("/home/resa/Aplikasi Java/SwingComponents/src/Gambar/KDE.png"));
		lblIcon.setBounds(497, 286, 134, 163);
		contentPane.add(lblIcon);
		
		lblBg = new JLabel("");
		//lblBg.setIcon(new ImageIcon("/home/resa/Aplikasi Java/SwingComponents/src/Gambar/KDEWall.png"));
		lblBg.setBounds(0, 0, 656, 483);
		contentPane.add(lblBg);
		setLocationRelativeTo(null);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] act)
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run()
			{
				try 
				{
					// KDE Look and Feel
					UIManager.setLookAndFeel("joxy.JoxyLookAndFeel");
					FrameKDE frame = new FrameKDE();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
}