package lista_tarefas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class TarefasController {
	
	private List<Tarefa> tarefas;
	private TarefasDbUtil tarefasDbUtil;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	public TarefasController() throws Exception {		
		tarefas = new ArrayList<>();
		
		tarefasDbUtil = TarefasDbUtil.getInstance();
	}
	
	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	
	public void loadTarefas() {
		logger.info("Loading tarefas");
		
		tarefas.clear();
		
		try {
			tarefas = tarefasDbUtil.getTarefas();
			
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error loading tarefas", ex);
			addErrorMessage(ex);
		}		
	}
	
	public String loadTarefa(int id) {
		
		logger.info("loading tarefa: " + id);
		
		try {
			Tarefa tarefa = tarefasDbUtil.getTarefa(id);
			
			ExternalContext externalContext = 
					FacesContext.getCurrentInstance().getExternalContext();
			
			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("tarefa", tarefa);
			
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error loading tarefa:" + id, ex);
			addErrorMessage(ex);
			
			return null;
		}
		
		return "update_tarefa_form.xhtml";
	}
	
	public String addTarefa(Tarefa tarefa) {
		
		logger.info("Adding tarefa: + tarefa");
		
		try {
			
			tarefasDbUtil.addTarefa(tarefa);
			
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error adding tarefa", ex);
			addErrorMessage(ex);
			
			return null;
		}
		
		return "test_list?faces-redirect=true";
	}
	
	public String updateTarefa(Tarefa tarefa) {
		
		logger.info("Updating tarefa");
		
		try {
			
			tarefasDbUtil.updateTarefa(tarefa);
			
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error updating tarefa", ex);
			addErrorMessage(ex);
			
			return null;
		}
		
		return "test_list?faces-redirect=true";
	}
	
	public String deleteTarefa(int id) {
		
		logger.info("Deleting tarefa id:" + id);
		
		try {
			
			tarefasDbUtil.deleteTarefa(id);
			
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error deleting tarefa id:" + id, ex);
			addErrorMessage(ex);
			
			return null;
		}
		
		return "test_list";
	}
	
	public String endTarefa(int id) {
		
		logger.info("COncluindo tarefa");
		
		try {
			
			tarefasDbUtil.endTarefa(id);
			
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error concluindo tarefa", ex);
			addErrorMessage(ex);
			
			return null;
		}
		
		return "test_list";
	}

	public String filterTarefas(String filtro_campo, String filtro_valor) {
		logger.info("FIltering tarefas");
		
		
		tarefas.clear();
		
		try {
			tarefas = tarefasDbUtil.getTarefasFiltradas(filtro_campo, filtro_valor);
			
			
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error filtering tarefas", ex);
			addErrorMessage(ex);
		}		
		
		return "filter_tarefas_form.xhtml";
	}

	private void addErrorMessage(Exception ex) {
		FacesMessage message = new FacesMessage("Error: " + ex.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
