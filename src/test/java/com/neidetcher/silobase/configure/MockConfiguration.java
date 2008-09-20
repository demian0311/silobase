package com.neidetcher.silobase.configure;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.neidetcher.silobase.InputField;
import com.neidetcher.silobase.Query;

public class MockConfiguration implements Configuration
{

   @Override
   public Query getQuery(String name)
   {
      String url = "jdbc:mysql://localhost:3306/dash";
      String user = "dash";
      String pass = "dash";
      String driver = "com.mysql.jdbc.Driver";
      DriverManagerDataSource dbConnInfo = new DriverManagerDataSource(url, user, pass);
      dbConnInfo.setDriverClassName(driver);

      if ("Get All Customers".equals(name))
      {
         String sql = "select * from customer";
         Query query = new Query("Get All Customers", dbConnInfo, sql, null);
         return query;
      }
      else if ("Get Users for Customer".equals(name))
      {
         String sql =

         " select   user.username,\n" +

         "          user.first_name,\n" +

         "          user.last_name,\n" +

         "          user.title\n" +

         " from     user,\n" +

         "          customer\n" +

         " where    user.customer_id = customer.id\n" +

         " and      customer.name = :customer_name ";

         List<InputField> inputFields = new ArrayList<InputField>();
         InputField inputField = new InputField("customer_name", "Voip Planet");
         inputFields.add(inputField);

         Query query = new Query("Get All Customers", dbConnInfo, sql, inputFields);
         return query;
      }
      else
      {
         throw new IllegalArgumentException("we dont' have query: " + name);
      }
   }

   @Override
   public List<String> getQueryNames()
   {
      List<String> queryNames = new ArrayList<String>();
      queryNames.add("Get All Customers");
      queryNames.add("Get Users for Customer");

      return queryNames;
   }

}
