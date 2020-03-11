package br.edu.faculdadedelta.projetoprocessojsf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.projetoprocessojsf.modelo.Processo;
import br.edu.faculdadedelta.projetoprocessojsf.util.Conexao;

public class ProcessoDAO {
	
	// C R U D
	
	public void incluir(Processo processo) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO processos (numero_proc, valor_proc, "
				+ " assunto_proc, quantidade_proc) VALUES (?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, processo.getNumero().trim());
		ps.setDouble(2, processo.getValor());
		ps.setString(3, processo.getAssunto().trim());
		ps.setInt(4, processo.getQuantidade());
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
	
	public void alterar(Processo processo) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBancoDeDados();
		String sql = "UPDATE processos "
				+ "	SET numero_proc = ?, "
				+ " valor_proc = ?, "
				+ " assunto_proc = ?, "
				+ " quantidade_proc = ? "
				+ " WHERE id_processo = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, processo.getNumero().trim());
		ps.setDouble(2, processo.getValor());
		ps.setString(3, processo.getAssunto().trim());
		ps.setInt(4, processo.getQuantidade());
		ps.setLong(5, processo.getId());
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
	
	public void excluir(Processo processo) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM processos WHERE id_processo = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, processo.getId());
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
	
	public List<Processo> listar() throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_processo, numero_proc, "
				+ " valor_proc, assunto_proc, quantidade_proc FROM processos";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Processo> listaRetorno = new ArrayList<>();
		while(rs.next()) {
			Processo processo = new Processo();
			processo.setId(rs.getLong("id_processo"));
			processo.setNumero(rs.getString("numero_proc").trim());
			processo.setValor(rs.getDouble("valor_proc"));
			processo.setAssunto(rs.getString("assunto_proc").trim());
			processo.setQuantidade(rs.getInt("quantidade_proc"));
			listaRetorno.add(processo);
		}
		rs.close();
		ps.close();
		conn.close();
		return listaRetorno;
	}
}











