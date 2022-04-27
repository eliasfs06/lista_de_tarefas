package lista_tarefas;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TarefasDbUtil {
	
	private static TarefasDbUtil instance;
	
	
	public static TarefasDbUtil getInstance() throws Exception {
		if(instance == null) {
			instance = new TarefasDbUtil();
		}
		
		return instance;
	}
	
	public List<Tarefa> getTarefas() throws Exception {
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet result = null;	
		
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		
		try {
			
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql:tarefas_tracker", "demo", "demo");
			stmt = connection.createStatement();
			result = stmt.executeQuery("SELECT * FROM tarefas WHERE situacao='Em andamento'");
			
			while(result.next()) {
				
				int id_tarefa = result.getInt("id_tarefa");
				String titulo = result.getString("titulo");
				String descricao = result.getString("descricao");
				String responsavel = result.getString("responsavel");
				String prioridade = result.getString("prioridade");
				String deadline = result.getString("deadline");
				String situacao = result.getString("situacao");
				
				Tarefa tempTarefa = new Tarefa(id_tarefa, titulo, descricao, responsavel,
						prioridade, deadline, situacao); 
					tarefas.add(tempTarefa);
				
			}
			
		} catch (ClassNotFoundException ex) {
			System.out.println("Driver do DB não localizado");
		} catch (SQLException ex) {
			System.out.println("Erro ao acessar ao banco" + ex.getMessage());
		} finally {
			connection.close();
			stmt.close();
			result.close();
		}
		
		return tarefas;
	}
	
	public Tarefa getTarefa(int id) throws Exception {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql:tarefas_tracker", "demo", "demo");
			stmt =  connection.prepareStatement("SELECT * FROM tarefas WHERE id_tarefa=?");
			
			stmt.setInt(1, id);
			System.out.println(id);
			result = stmt.executeQuery();
			Tarefa tarefa = null;
			
			if(result.next()) {
				int id_tarefa = result.getInt("id_tarefa");
				String titulo = result.getString("titulo");
				String descricao = result.getString("descricao");
				String responsavel = result.getString("responsavel");
				String prioridade = result.getString("prioridade");
				String deadline = result.getString("deadline");
				String situacao = result.getString("situacao");
				
				tarefa = new Tarefa(id_tarefa, titulo, descricao, responsavel,
						prioridade, deadline, situacao);
				
			} else {
				throw new Exception("Could not find student id: " + id);
			}
			
			return tarefa;
		} finally {
			
			connection.close();
			stmt.close();
		}		
	}
	
	public void addTarefa(Tarefa tarefa) throws Exception {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql:tarefas_tracker", "demo", "demo");
			stmt = connection.prepareStatement("INSERT INTO tarefas (titulo, descricao, responsavel, prioridade, deadline, situacao) VALUES (?, ?, ?, ?, ?, ?)");
			
			stmt.setString(1, tarefa.getTitulo());
			stmt.setString(2, tarefa.getDescricao());
			stmt.setString(3, tarefa.getResponsavel());
			stmt.setString(4, tarefa.getPrioridade());
			stmt.setString(5, tarefa.getDeadline());
			stmt.setString(6, tarefa.getSituacao());
			
			stmt.execute();
			
		} finally {
			
			connection.close();
			stmt.close();
		}
	}
	
	public void updateTarefa(Tarefa tarefa) throws Exception {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql:tarefas_tracker", "demo", "demo");
			stmt = connection.prepareStatement("UPDATE tarefas SET titulo=?, descricao=?, responsavel=?, prioridade=?, deadline=?, situacao=? WHERE id_tarefa=?");
			
			stmt.setString(1, tarefa.getTitulo());
			stmt.setString(2, tarefa.getDescricao());
			stmt.setString(3, tarefa.getResponsavel());
			stmt.setString(4, tarefa.getPrioridade());
			stmt.setString(5, tarefa.getDeadline());
			stmt.setString(6, tarefa.getSituacao());
			stmt.setInt(7, tarefa.getId_tarefa());
			
			stmt.execute();
			
		} finally {
			
			connection.close();
			stmt.close();
		}
	}
	
	public void deleteTarefa(int id) throws Exception {

		Connection connection = null;
		PreparedStatement stmt = null;

		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql:tarefas_tracker", "demo", "demo");
			stmt = connection.prepareStatement("delete from tarefas where id_tarefa=?");

			stmt.setInt(1, id);
			
			stmt.execute();
		}
		finally {
			connection.close();
			stmt.close();
		}		
	}
	
	public void endTarefa(int id) throws Exception {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql:tarefas_tracker", "demo", "demo");
			stmt = connection.prepareStatement("UPDATE tarefas SET situacao='Concluída' WHERE id_tarefa=?");
			
			stmt.setInt(1, id);
			
			stmt.execute();
			
		} finally {
			
			connection.close();
			stmt.close();
		}
	}
	
	public List<Tarefa> getTarefasFiltradas(String filtro_campo, String filtro_valor) throws Exception {
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet result = null;	
		
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		
		try {
			
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql:tarefas_tracker", "demo", "demo");
			stmt = connection.createStatement();
			result = stmt.executeQuery("SELECT * FROM tarefas WHERE " + filtro_campo + " = '" + filtro_valor +"'");
			
			while(result.next()) {
				
				int id_tarefa = result.getInt("id_tarefa");
				String titulo = result.getString("titulo");
				String descricao = result.getString("descricao");
				String responsavel = result.getString("responsavel");
				String prioridade = result.getString("prioridade");
				String deadline = result.getString("deadline");
				String situacao = result.getString("situacao");
				
				Tarefa tempTarefa = new Tarefa(id_tarefa, titulo, descricao, responsavel,
						prioridade, deadline, situacao); 
					tarefas.add(tempTarefa);
				
			}
			
		} catch (ClassNotFoundException ex) {
			System.out.println("Driver do DB não localizado");
		} catch (SQLException ex) {
			System.out.println("Erro ao acessar ao banco" + ex.getMessage());
		} finally {
			connection.close();
			stmt.close();
			result.close();
		}
		
		return tarefas;
	}
	
}

