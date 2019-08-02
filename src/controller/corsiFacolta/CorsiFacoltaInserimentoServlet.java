package controller.corsiFacolta;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.implementations.CorsoDao;
import dao.implementations.FacoltaDao;
import dao.interfaces.CorsoInterface;
import dao.interfaces.FacoltaInterface;
import model.Corso;
import model.Facolta;

/**
 * Servlet implementation class CorsiFacoltaInserimentoServlet
 */
@WebServlet("/CorsiFacolta")
public class CorsiFacoltaInserimentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CorsiFacoltaInserimentoServlet() {
    	
        super();
        // TODO Auto-generated constructor stub
    }

	// Recupero tutte le facolta (con i corsi a loro annessi) e i corsi per mostrare le diverse form
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FacoltaInterface fDao = new FacoltaDao();
    	CorsoInterface cDao = new CorsoDao();
    	
    	request.setAttribute("facolta", fDao.getAll());
    	request.setAttribute("corsi", cDao.getAll());
    	
    	request.getRequestDispatcher("corsoFacoltaForm.jsp").forward(request, response);
	}

	// Recupero sia la facolta (con la lista corsi inizializzata) che il corso tramite id
	// poi si aggiungono a vicenda ed infine effettuo l'update di uno qualsiasi dei due
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FacoltaDao fDao = new FacoltaDao();
    	CorsoDao cDao = new CorsoDao();
    	    	
    	Facolta f = fDao.getByIdWithCorsi(Integer.parseInt(request.getParameter("facolta")));
    	Corso c = cDao.getById(Integer.parseInt(request.getParameter("corso")));
    	
    	f.addCorso(c);
    	c.addFacolta(f);
    	
    	fDao.update(f);
    	
    	response.sendRedirect("Facolta/"+f.getId());
	}

}
