package com.neidetcher.silobase.configure;

import java.util.List;

import junit.framework.TestCase;

import com.neidetcher.silobase.InputField;
import com.neidetcher.silobase.Query;

public class FileConfigurationTest extends TestCase
{

   FileConfiguration fileConfiguration;

   @Override
   protected void setUp() throws Exception
   {
      fileConfiguration = new FileConfiguration();
      fileConfiguration.setFile("src/test/resources/silobase.xml");
   }

   public void testGetQueryNames()
   {
      List<String> queryNames = fileConfiguration.getQueryNames();
      assertEquals(2, queryNames.size());
      assertEquals("Users by Customer", queryNames.get(0));
      assertEquals("All Customers", queryNames.get(1));

      System.out.println("queryNames: " + queryNames);
   }

   public void testGetQuery_allCustomers()
   {
      Query query = fileConfiguration.getQuery("All Customers");
      assertNotNull(query);
      assertEquals("select * from customer", query.getSql());
   }

   public void testGetQuery_usersByCustomer()
   {
      Query query = fileConfiguration.getQuery("Users by Customer");
      assertNotNull(query);

      for (InputField currInputField : query.getInputFields())
      {
         System.out.println("currInputField: " + currInputField);
      }

   }
}
