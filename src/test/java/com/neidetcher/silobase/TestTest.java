package com.neidetcher.silobase;

import java.util.Map;

import junit.framework.TestCase;

import com.neidetcher.silobase.configure.Configuration;
import com.neidetcher.silobase.configure.MockConfiguration;
import com.neidetcher.silobase.data.Data;
import com.neidetcher.silobase.data.DataImpl;

public class TestTest extends TestCase
{

   public void test()
   {
      Configuration config = new MockConfiguration();
      Query query = config.getQuery("Get All Customers");

      Data data = new DataImpl();
      data.performQuery(query);

      for (Map<String, String> currRow : query.getResult())
      {
         System.out.println("map: " + currRow);
      }

      assertTrue(true);
   }

   public void test2()
   {
      Configuration config = new MockConfiguration();
      Query query = config.getQuery("Get Users for Customer");

      Data data = new DataImpl();
      data.performQuery(query);

      for (Map<String, String> currRow : query.getResult())
      {
         System.out.println("map: " + currRow);
      }

      assertTrue(true);
   }
}
