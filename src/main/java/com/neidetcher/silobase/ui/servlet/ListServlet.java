package com.neidetcher.silobase.ui.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.neidetcher.silobase.Service;
import com.neidetcher.silobase.ServiceImpl;

public class ListServlet extends HttpServlet
{

   private static final long serialVersionUID = 1L;
   protected Log log = LogFactory.getLog(ListServlet.class);

   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
         java.io.IOException
   {
      //log.debug("request: " + ToStringBuilder.reflectionToString(request));

      response.setContentType("text/html");
      Service si = ServiceImpl.createServiceImpl("silobase.xml");
      HttpSession session = request.getSession();
      session.setAttribute("queryNames", si.getQueryNames());

      RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/listQueryNames.jsp");
      rd.forward(request, response);
   }
}
