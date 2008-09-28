package com.neidetcher.silobase.configure;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.w3c.dom.NodeList;

import com.neidetcher.silobase.InputField;
import com.neidetcher.silobase.Query;
import com.neidetcher.silobase.util.XPathHelper;

/**
 * Provides configuration information.  Must be set with the path and filename of
 * a valid SiloBase XML file.
 */
public class FileConfiguration implements Configuration
{

   protected Log log = LogFactory.getLog(FileConfiguration.class);

   private String file;

   @Override
   public Query getQuery(String queryName)
   {
      XPathHelper xPathHelper = new XPathHelper(file);

      String queryPrefix = "//query[@id=\'" + queryName + "']/";

      String sql = xPathHelper.getString(queryPrefix + "sql");
      String database = xPathHelper.getString(queryPrefix + "database/@idref");
      System.out.println("database: " + database);

      String databasePrefix = "//database[@id=\'" + database + "']/";
      if ("".equals(database))
      {
         // find the first database and use that
         databasePrefix = "//database/";
      }

      System.out.println("using databasePrefix: " + databasePrefix);

      String driver = xPathHelper.getString(databasePrefix + "driver");
      String url = xPathHelper.getString(databasePrefix + "url");
      String username = xPathHelper.getString(databasePrefix + "username");
      String password = xPathHelper.getString(databasePrefix + "password");

      DriverManagerDataSource dbConnInfo = new DriverManagerDataSource(url, username, password);
      dbConnInfo.setDriverClassName(driver);

      List<InputField> inputFields = getInputFieldsBySql(sql);

      Query query = new Query(queryName, dbConnInfo, sql, inputFields);

      return query;
   }

   private List<InputField> getInputFieldsBySql(String sqlIn)
   {
      log.debug("looking for input fields in: " + sqlIn);

      List<InputField> inputFields = new ArrayList<InputField>();

      StringTokenizer st = new StringTokenizer(sqlIn);
      while (st.hasMoreTokens())
      {
         String currToken = st.nextToken();
         if (currToken.startsWith(":"))
         {
            log.debug("found an input field: " + currToken);
            inputFields.add(new InputField(currToken.substring(1)));
         }
      }

      return inputFields;
   }

   @Override
   public List<String> getQueryNames()
   {
      List<String> queryNames = new ArrayList<String>();
      XPathHelper xPathHelper = new XPathHelper(file);

      String expression = "//query/@id";
      NodeList queryNodeList = xPathHelper.getNodeList(expression);

      for (int i = 0; i < queryNodeList.getLength(); i++)
      {
         org.w3c.dom.Node node = queryNodeList.item(i);
         queryNames.add(node.getNodeValue());
      }

      return queryNames;
   }

   public void setFile(String fileIn)
   {
      file = fileIn;
   }
}
