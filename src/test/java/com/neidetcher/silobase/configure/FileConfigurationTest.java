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
      assertEquals("All Projects", queryNames.get(0));
      assertEquals("All Iterations for a Project", queryNames.get(1));

      System.out.println("queryNames: " + queryNames);
   }

   public void testGetQuery_allProjects()
   {
      Query query = fileConfiguration.getQuery("All Projects");
      assertNotNull(query);
      //assertTrue(query.getSql().startsWith("select   id,"));
   }

   public void testGetQuery_usersByCustomer()
   {
      Query query = fileConfiguration.getQuery("All Iterations for a Project");
      assertNotNull(query);

      for (InputField currInputField : query.getInputFields())
      {
         System.out.println("currInputField: " + currInputField);
      }

   }
}
