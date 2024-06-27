package view;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import dao.ContatoDao;
import entidades.Contato;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Font;


public class TabelaPesquisa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modelo;
	private ContatoDao dao = new ContatoDao();
	private List<Contato> lista = dao.listarContatos();
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TabelaPesquisa frame = new TabelaPesquisa();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public TabelaPesquisa(List<Contato> filtragem) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 259);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(0, 0, 591, 226);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Verdana", Font.BOLD, 12));
		table.setBackground(new Color(255, 255, 51));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Fone", "E-mail"
			}
		));
		
		
		atualizarTabela(filtragem);
	}

	private void atualizarTabela(List<Contato> lista) {
		
		
		
		modelo = (DefaultTableModel) table.getModel();

		modelo.setRowCount(0);

		for (Contato contato : lista) {

			modelo.addRow(new Object[] { contato.getId(), contato.getNome(), contato.getFone(), contato.getEmail() });
		}
		
	}
}
