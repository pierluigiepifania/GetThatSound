package it.gestione_brani;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.model.BranoManager;
import it.utility.Convert;

@WebServlet(description = "Restituisce tutti i brani che contengono la stringa cercata", urlPatterns = { "/Search" })

public class CercaBrano extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CercaBrano() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String param = Convert.spaceToUnderscore((String) request.getParameter("search"));
			if(!param.equals(null)) response.getWriter().println(BranoManager.cercaBrano(param));
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().append("Error during servlet Search");
		}
	}
}
