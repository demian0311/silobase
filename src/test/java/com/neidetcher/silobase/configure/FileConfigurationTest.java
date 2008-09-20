package com.neidetcher.silobase.configure;

import java.util.List;

import junit.framework.TestCase;

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
      System.out.println("queryNames: " + queryNames);
   }
}
