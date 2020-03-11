package br.edu.faculdadedelta.projetoprocessojsf.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.projetoprocessojsf.dao.ProcessoDAO;
import br.edu.faculdadedelta.projetoprocessojsf.modelo.Processo;

@ManagedBean
@SessionScoped
public class ProcessoController {

	private Processo processo = new Processo();
	private ProcessoDAO dao = new ProcessoDAO();
	
	public Processo getProcesso() {
		return processo;
	}
	public void setProcesso(Processo processo) {
		this.processo = processo;
	}
	
	public void limparCampos() {
		processo = new Processo();
	}
	
	private void exiberMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		try {
			if (processo.getId() == null) {
				dao.incluir(processo);
				exiberMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			} else {
				dao.alterar(processo);
				exiberMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exiberMensagem("Erro ao realizar a operação, "
					+ " tente novamente mais tarde! " + e.getMessage());
		}
		return "cadastroProcesso.xhtml";
	}
	
	public String excluir() {
		try {
			dao.excluir(processo);
			exiberMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exiberMensagem("Erro ao realizar a operação, "
					+ " tente novamente mais tarde! " + e.getMessage());			
		}
		return "listaProcesso.xhtml";
	}
	
	public String editar() {
		return "cadastroProcesso.xhtml";
	}
	
	public List<Processo> getLista() {
		List<Processo> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exiberMensagem("Erro ao realizar a operação, "
					+ " tente novamente mais tarde! " + e.getMessage());			
		}
		return listaRetorno;
	}
}










