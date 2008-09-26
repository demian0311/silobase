package com.neidetcher.silobase.ui.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.neidetcher.silobase.InputField;
import com.neidetcher.silobase.Query;
import com.neidetcher.silobase.Service;
import com.neidetcher.silobase.ServiceImpl;

public class QueryServlet extends HttpServlet
{

   private static final long serialVersionUID = 1L;
   protected Log log = LogFactory.getLog(QueryServlet.class);
   private static final String SUCCESS = "/WEB-INF/query.jsp";
   private static Service service;

   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
         java.io.IOException
   {
      log.debug("request: " + ToStringBuilder.reflectionToString(request));
      response.setContentType("text/html");

      String queryName = request.getParameter("name");

      log.debug("****************************************");
      log.debug("queryName: " + queryName);
      log.debug("****************************************");

      Query query = getService().getQuery(queryName);

      // if we came from a form or we don't need any input fields do the query
      if (null != request.getParameter("form") || query.getInputFields().isEmpty())
      {
         log.debug("we came from the form so we should look for data");
         log.debug("parameter map: " + request.getParameterMap());

         for (InputField currInputField : query.getInputFields())
         {
            String value = request.getParameter(currInputField.getName());
            currInputField.setValue(value);
         }

         getService().performQuery(query);
      }

      HttpSession session = request.getSession();
      session.setAttribute("query", query);

      RequestDispatcher rd = request.getRequestDispatcher(SUCCESS);
      rd.forward(request, response);
   }

   private Service getService()
   {
      if (service == null)
      {
         service = ServiceImpl.createServiceImpl("src/test/resources/silobase.xml");
      }
      return service;
   }
}
