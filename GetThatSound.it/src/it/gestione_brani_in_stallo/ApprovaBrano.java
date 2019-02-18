package it.gestione_brani_in_stallo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.model.BranoManager;
import it.model.ClientBean;

@WebServlet(description = "Approva un brano", urlPatterns = { "/ApprovaBrano" })
public class ApprovaBrano extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ApprovaBrano() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClientBean client = (ClientBean) request.getSession().getAttribute("client");
		if((client.getRuolo()).equals("manager")){
			try {
				int id = Integer.parseInt(request.getParameter("brano_id"));
				if(id!=0 && BranoManager.approvaBrano(id)) response.getWriter().println("Il brano è stato approvato con successo");
				else response.getWriter().println("Ci sono stati errori duranto il processo, ripeti");
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().println("Ci sono stati errori duranto il processo, ripeti");
			}
		}
	}
}
