package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entidades.Contato;

public class ContatoDao {
	
	public Connection getConexao() throws ClassNotFoundException {

		// Driver
		String driver = "com.mysql.cj.jdbc.Driver";
		Class.forName(driver);

		// Banco de dados
		String url = "jdbc:mysql://localhost:3306/Agenda";

		// Usuario
		String user = "root";

		// Senha
		String password = "root";

		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;

	}
	
	
	public List<Contato> listarContatos() {
		List<Contato> contatos = new ArrayList<>();
		String query = "SELECT * FROM contatos";
		try {
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				String fone  = rs.getString(3);
				String email = rs.getString(4);				
				contatos.add(new Contato(id, nome, fone, email));
			}
			rs.close();
			pst.close();
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
public int inserirContato(Contato novoContato) {
		
		// SQL
		String insert = "INSERT INTO contatos (nome, fone, email) VALUES (?, ?, ?)";
		
		try {
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, novoContato.getNome());
			pst.setString(2, novoContato.getFone());
			pst.setString(3, novoContato.getEmail());			
			pst.executeUpdate();
			
			
			ResultSet rs = pst.getGeneratedKeys();
			int chaveGerada;
			if (rs.next()) {
				chaveGerada = rs.getInt(1);	
				return chaveGerada;
			}	
			rs.close();
			pst.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return 0;	
		
		
	}


	public void excluirContato(int id) {
		String delete = "Delete FROM contatos WHERE (id = ?)";
		try {
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setInt(1, id);
			pst.executeUpdate();	
			
			pst.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	public Contato pesquisarPorId(int id) {
		Contato contato = new Contato(id, null, null, null);
		String query = "SELECT * FROM contatos WHERE Id = ?";
		try {
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
				String nome = rs.getString(2);
				String fone  = rs.getString(3);
				String email = rs.getString(4);				
				contato = new Contato(id, nome, fone, email);
			}
			pst.close();
			pst.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return contato;
	}
	
	
	public void alterarContato (Contato contatoAlterado) {
		
		String update = "UPDATE contatos SET nome = ?, fone = ?, email = ? WHERE Id = ?";
		try {
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, contatoAlterado.getNome());
			pst.setString(2, contatoAlterado.getFone());
			pst.setString(3, contatoAlterado.getEmail());			
			pst.setInt(4, contatoAlterado.getId());
			pst.executeUpdate();
			
			
			pst.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	
	public ArrayList<Contato>  pesquisar(String coluna, String valor) {
		ArrayList<Contato> contatos = new ArrayList<>();
		
		String query = null;		
		if (coluna.equals("email")) {			
			query = "SELECT * FROM contatos WHERE email LIKE '%"  + valor + "%'";			
		} else if (coluna.equals("nome")) {	
			query = "SELECT * FROM contatos WHERE nome LIKE '%" + valor + "%'";
		}		
		
		try {
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);				
				contatos.add(new Contato(id, nome, fone, email));
			}
			rs.close();
			pst.close();
			con.close();			
		} catch (Exception e) {
			System.out.println(e);			
		}
		return contatos;
	}


	
	

}
