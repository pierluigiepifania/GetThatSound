package it.gestione_brani;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetAudio
 */
@WebServlet("/GetAudio")

public class GetAudio extends HttpServlet {
	
private static final long serialVersionUID = 781010634462987446L;

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
	  String fullPath = (String) request.getParameter("path");
      
      if (fullPath == null || fullPath.equals(""))
           throw new ServletException(
            "Invalid or non-existent file parameter in SendMp3 servlet.");
      
      if (fullPath.indexOf(".mp3") == -1)
    	  fullPath = fullPath + ".mp3";
          
      ServletOutputStream stream = null;
      BufferedInputStream buf = null;
      try{
     
      stream = response.getOutputStream();
      File mp3 = new File(fullPath);
     
      response.setContentType("audio/mpeg");
      
      response.addHeader("Content-Disposition","attachment; filename="+"song" );

      response.setContentLength( (int) mp3.length() );
      
      FileInputStream input = new FileInputStream(mp3);
      buf = new BufferedInputStream(input);
      int readBytes = 0;

      while((readBytes = buf.read()) != -1)
         stream.write(readBytes);

     } catch (IOException ioe){
     
        throw new ServletException(ioe.getMessage());
         
     } finally {
     if(stream != null)
         stream.close();
      if(buf != null)
          buf.close();
          }
    
    }
}
