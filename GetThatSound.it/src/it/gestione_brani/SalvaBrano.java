package it.gestione_brani;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.model.BranoManager;
import it.model.ClientBean;

@WebServlet(description = "Salva un brano", urlPatterns = { "/SalvaBrano" })
public class SalvaBrano extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SalvaBrano() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int brano_id = Integer.parseInt(request.getParameter("brano_id"));
			ClientBean cb = (ClientBean) request.getSession().getAttribute("client");
			
			if(cb==null) response.getWriter().println("Devi essere loggato!");
			
			else if(cb.getRuolo().equals("admin") || cb.getRuolo().equals("manager")) response.getWriter().println("Solo gli utenti standard possono salvare i brani");
			
			else if(BranoManager.checkSalvato(brano_id, cb.getId())) response.getWriter().println("Brano già salvato!");
			
			else {
				BranoManager.salvaBrano(brano_id, cb.getId());
				response.getWriter().println("Brano salvato!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().println("Ci sono stati errori");
		}
	}
}
