package com.neidetcher.silobase.configure;

import junit.framework.TestCase;

public class FileConfigurationTest extends TestCase
{

   FileConfiguration fileConfiguration;

   @Override
   protected void setUp() throws Exception
   {
      fileConfiguration = new FileConfiguration();
   }

   public void testGetQueries()
   {
      fileConfiguration.setFile("silobase.sql");
   }
}
