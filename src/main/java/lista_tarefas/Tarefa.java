package lista_tarefas;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean

public class Tarefa {
	private int id_tarefa;
	private String titulo;
	private String descricao;
	private String responsavel;
	private String prioridade;
	private String deadline;
	private String situacao;
	private String filtro_campo;
	private String filtro_valor;
	

	List<String> opcoes_situacao;
	List<String> opcoes_prioridade;
	
	public Tarefa() {		
		opcoes_situacao = new ArrayList<>();
		opcoes_situacao.add("Em andamento");
		opcoes_situacao.add("Concluída");
		
		opcoes_prioridade = new ArrayList<>();
		opcoes_prioridade.add("alta");
		opcoes_prioridade.add("media");
		opcoes_prioridade.add("baixa");
	}
	
	public Tarefa(int id_tarefa, String titulo, String descricao, 
				String responsavel, String prioridade, String deadline, String situacao) {
		
		this.id_tarefa = id_tarefa;
		this.titulo = titulo;
		this.descricao = descricao;
		this.responsavel = responsavel;
		this.prioridade = prioridade;
		this.deadline = deadline;
		this.situacao = situacao;	
		
		opcoes_situacao = new ArrayList<>();
		opcoes_situacao.add("Em andamento");
		opcoes_situacao.add("Concluída");
		
		opcoes_prioridade = new ArrayList<>();
		opcoes_prioridade.add("alta");
		opcoes_prioridade.add("media");
		opcoes_prioridade.add("baixa");
	}

	
	
	public List<String> getOpcoes_situacao() {
		return opcoes_situacao;
	}
	
	public List<String> getOpcoes_prioridade() {
		return opcoes_prioridade;
	}

	public int getId_tarefa() {
		return id_tarefa;
	}

	public void setId_tarefa(int id_tarefa) {
		this.id_tarefa = id_tarefa;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	public String getFiltro_campo() {
		return filtro_campo;
	}

	public void setFiltro_campo(String filtro_campo) {
		this.filtro_campo = filtro_campo;
	}

	public String getFiltro_valor() {
		return filtro_valor;
	}

	public void setFiltro_valor(String filtro_valor) {
		this.filtro_valor = filtro_valor;
	}

	@Override
	public String toString() {
		return "Tarefa [id_tarefa=" + id_tarefa + ", titulo=" + titulo + ", descricao="
				+ descricao + ", responsavel=" + responsavel + ", prioridade="
				+ prioridade + ", deadline=" + deadline + ", situacao=" + situacao
				+ ", filtro_campo=" + filtro_campo + ", filtro_valor=" + filtro_valor + "]";
	}
	

}
