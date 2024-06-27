package view;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import dao.ContatoDao;
import entidades.Contato;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Canvas;
import java.awt.Panel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Label;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

public class ControleAgenda {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel modelo;
	private ContatoDao dao = new ContatoDao();
	// private JTextField textField;
	private List<Contato> lista = dao.listarContatos();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControleAgenda window = new ControleAgenda();
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
	public ControleAgenda() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 102, 255));
		frame.setBounds(100, 100, 770, 517);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(10, 79, 556, 381);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setForeground(new Color(0, 0, 0));
		table.setFont(new Font("Verdana", Font.BOLD, 12));
		table.setBackground(new Color(255, 255, 0));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nome", "Fone", "E-mail" }));

		atualizarTabela(dao.listarContatos());

		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		JButton btnNovoContato = new JButton("Novo Contato");
		btnNovoContato.setForeground(new Color(0, 0, 0));
		btnNovoContato.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNovoContato.setBackground(new Color(255, 255, 0));
		btnNovoContato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarContato();
			}
		});
		btnNovoContato.setBounds(595, 79, 137, 38);
		frame.getContentPane().add(btnNovoContato);

		JButton btnApagar = new JButton("Apagar");
		btnApagar.setForeground(new Color(0, 0, 0));
		btnApagar.setBackground(new Color(255, 255, 0));
		btnApagar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apagarContato();
			}
		});
		btnApagar.setBounds(595, 373, 137, 38);
		frame.getContentPane().add(btnApagar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setForeground(new Color(0, 0, 0));
		btnEditar.setBackground(new Color(255, 255, 0));
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarContato();
			}
		});
		btnEditar.setBounds(595, 422, 137, 38);
		frame.getContentPane().add(btnEditar);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setForeground(new Color(0, 0, 0));
		btnPesquisar.setBackground(new Color(255, 255, 0));
		btnPesquisar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarContato();
			}
		});
		btnPesquisar.setBounds(595, 324, 137, 38);
		frame.getContentPane().add(btnPesquisar);

		JTextPane txtpnAgendaContatos = new JTextPane();
		txtpnAgendaContatos.setForeground(new Color(255, 255, 0));
		txtpnAgendaContatos.setBackground(new Color(0, 102, 255));
		txtpnAgendaContatos.setEditable(false);
		txtpnAgendaContatos.setFont(new Font("Verdana", Font.BOLD, 30));
		txtpnAgendaContatos.setText("Agenda de contatos");
		txtpnAgendaContatos.setBounds(200, 11, 353, 57);
		frame.getContentPane().add(txtpnAgendaContatos);

	}

	private void atualizarTabela(List<Contato> lista) {

		modelo = (DefaultTableModel) table.getModel();

		modelo.setRowCount(0);

		for (Contato contato : lista) {

			modelo.addRow(new Object[] { contato.getId(), contato.getNome(), contato.getFone(), contato.getEmail() });
		}

	}

	public void cadastrarContato() {

		MaskFormatter mascara = null;
		try {
			mascara = new MaskFormatter("(**)*****-****");
		} catch (ParseException e) {

			e.printStackTrace();
		}

		JTextField tfNome = new JTextField();
		JFormattedTextField tfFone = new JFormattedTextField(mascara);
		JTextField tfEmail = new JTextField();

		JLabel lblNome = new JLabel("Nome");
		JLabel lblFone = new JLabel("Formatado");
		JLabel lblEmail = new JLabel("Email");

		JPanel painel = new JPanel(new GridLayout(0, 1));
		painel.add(lblNome);
		painel.add(tfNome);
		painel.add(lblFone);
		painel.add(tfFone);
		painel.add(lblEmail);
		painel.add(tfEmail);

		int resultado = JOptionPane.showConfirmDialog(null, painel, "Novo Contato", JOptionPane.OK_CANCEL_OPTION);

		if ((!tfNome.getText().equals("")) && (!tfFone.getText().equals("")) && (!tfEmail.getText().equals(""))
				&& (resultado == JOptionPane.OK_OPTION)) {
			
			String nome = tfNome.getText();
			String fone = tfFone.getText();
			String email = tfEmail.getText();
			Contato contato = new Contato(nome, fone, email);
			dao.inserirContato(contato);
			atualizarTabela(dao.listarContatos());	



		}

		else {
			
			JOptionPane.showMessageDialog(null, "Preencha todos os campos", "ERRO", JOptionPane.ERROR_MESSAGE);
			
		

		}
	}

	public void apagarContato() {

		int linha = table.getSelectedRow();
		if (linha != -1) {
			Object idObj = modelo.getValueAt(linha, 0);
			int id = (Integer) idObj;

			JPanel painel = new JPanel(new GridLayout(0, 1));

			int resultado = JOptionPane.showConfirmDialog(null, painel, "Tem certeza que deseja apagar?",
					JOptionPane.OK_CANCEL_OPTION);

			if (resultado == JOptionPane.OK_OPTION) {
				dao.excluirContato(id);
				atualizarTabela(dao.listarContatos());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Selecione uma linha para remover", "ERRO", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void editarContato() {

		int linha = table.getSelectedRow();
		if (linha != -1) {
			Object idObj = modelo.getValueAt(linha, 0);
			int id = (Integer) idObj;
			Contato contato = dao.pesquisarPorId(id);

			JTextField tfNome = new JTextField(contato.getNome());
			JTextField tfFone = new JTextField(contato.getFone());
			JTextField tfEmail = new JTextField(contato.getEmail());

			JLabel lblNome = new JLabel("Nome");
			JLabel lblFone = new JLabel("Fone");
			JLabel lblEmail = new JLabel("Email");

			JPanel painel = new JPanel(new GridLayout(0, 1));
			painel.add(lblNome);
			painel.add(tfNome);
			painel.add(lblFone);
			painel.add(tfFone);
			painel.add(lblEmail);
			painel.add(tfEmail);

			int resultado = JOptionPane.showConfirmDialog(null, painel, "Cadastro", JOptionPane.OK_CANCEL_OPTION);

			if (resultado == JOptionPane.OK_OPTION) {

				String nome = tfNome.getText();
				String fone = tfFone.getText();
				String email = tfEmail.getText();
				Contato contatoAtualizado = new Contato(id, nome, fone, email);

				dao.alterarContato(contatoAtualizado);
				atualizarTabela(dao.listarContatos());

			}
		} else {
			JOptionPane.showMessageDialog(null, "Selecione uma linha para editar", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void pesquisarContato() {

		JRadioButton opcaoNome = new JRadioButton("Nome");
		JRadioButton opcaoEmail = new JRadioButton("Email");

		JTextField campoTexto = new JTextField();

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(opcaoNome);
		grupo.add(opcaoEmail);

		JPanel painel = new JPanel(new GridLayout(4, 2));
		painel.add(opcaoNome);
		painel.add(opcaoEmail);
		painel.add(campoTexto);

		int resposta = JOptionPane.showConfirmDialog(null, painel, "Pesquisar", JOptionPane.OK_CANCEL_OPTION);

		if (resposta == JOptionPane.OK_OPTION) {

			List<Contato> filtragem;
			String valor = campoTexto.getText();
			if (opcaoNome.isSelected()) {

				filtragem = dao.pesquisar("nome", valor);
				abrirNovaJanela(filtragem);
				atualizarTabela(dao.listarContatos());

			} else {

				filtragem = dao.pesquisar("email", valor);
				abrirNovaJanela(filtragem);
				atualizarTabela(dao.listarContatos());

			}

		}

	}

	public void abrirNovaJanela(List<Contato> filtragem) {

		TabelaPesquisa novaJanela = new TabelaPesquisa(filtragem);
		novaJanela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		novaJanela.setVisible(true);

	}
}
