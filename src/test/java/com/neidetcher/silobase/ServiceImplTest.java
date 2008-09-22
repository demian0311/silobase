package com.neidetcher.silobase;

import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import com.neidetcher.silobase.configure.FileConfiguration;
import com.neidetcher.silobase.data.DataImpl;

public class ServiceImplTest extends TestCase
{

   String TEST_FILE = "src/test/resources/silobase.xml";
   ServiceImpl service;

   @Override
   public void setUp()
   {
      FileConfiguration fileConfiguration = new FileConfiguration();
      fileConfiguration.setFile(TEST_FILE);

      DataImpl data = new DataImpl();

      service = new ServiceImpl();
      service.setConfiguration(fileConfiguration);
      service.setData(data);

   }

   public void testHappyPath()
   {
      // find out what queries are available
      List<String> queryNames = service.getQueryNames();
      System.out.println("*** available queries ***");
      for (String currQuery : queryNames)
      {
         System.out.println("\t" + currQuery);
      }

      // actually run a query
      Query query = service.getQuery("All Customers");
      service.performQuery(query);
      for (Map<String, String> resultRow : query.getResult())
      {
         System.out.println(">>" + resultRow);
      }
   }

   public void test_setInputField()
   {
      // get the query
      Query query = service.getQuery("Users by Customer");

      // now add a value for an input field
      for (InputField currInputField : query.getInputFields())
      {
         if ("customer_name".equals(currInputField.getName()))
         {
            currInputField.setValue("VoIP Planet");
            assertEquals("customer name", currInputField.getPretyName());
         }
      }

      boolean hasFredstone = false;

      // do the query
      service.performQuery(query);
      for (Map<String, String> resultRow : query.getResult())
      {
         if (resultRow.get("username").equals("fredstone"))
         {
            hasFredstone = true;
         }
         System.out.println(">>" + resultRow);
      }

      assertTrue(hasFredstone);
   }
}
