package p1admin;

import javax.sql.DataSource;
import javax.swing.DefaultListModel;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import p1admin.adminDB.DBFacade;
import p1admin.adminDB.GenericDBFacade;
import p1admin.admincontroller.AllQuestionsController;
import p1admin.adminview.AllQuestionsEditor;
import p1admin.model.Opcion;
import p1admin.model.Pregunta;

public class Main {
	public static void main(String[] args) {
		// TODO Inicializar DataSource
		
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setJdbcUrl("jdbc:mysql://localhost/practica_510");
		cpds.setUser("AdminP1");
		cpds.setPassword("PasswordP1");
		cpds.setAcquireRetryAttempts(1);
		cpds.setAcquireRetryDelay(1);
		cpds.setBreakAfterAcquireFailure(true);
		
		DataSource ds = cpds;


		// TODO Cambiar inicializaciÃƒÂ³n de fachada a BD aÃƒÂ±adiendo
		// los parÃƒÂ¡metros que sean necesarios
		// GenericDBFacade<Pregunta, Opcion> facade = new FauxDBFacade();
		GenericDBFacade<Pregunta, Opcion> facade = new DBFacade(ds);
		DefaultListModel<Pregunta> model = new DefaultListModel<>();
		AllQuestionsController controller = new AllQuestionsController(model, facade);
		AllQuestionsEditor ed = new AllQuestionsEditor(model, controller);
		ed.setModal(true);
		ed.setVisible(true);
		
		// TODO: Cerrar pool de conexiones
		cpds.close();
	}
}
