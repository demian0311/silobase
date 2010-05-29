package com.neidetcher.silobase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import com.neidetcher.silobase.configure.FileConfiguration;
import com.neidetcher.silobase.data.DataImpl;

public class QueryTest extends TestCase
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

   public void test()
   {
      List<Map<String, String>> results = new ArrayList<Map<String, String>>();

      for (int ii = 0; ii < 20; ii++)
      {
         Map<String, String> newMap = new HashMap<String, String>();
         newMap.put("name", "fred" + ii);
         newMap.put("age", "" + ii);
         results.add(newMap);
      }

      System.out.println("results" + results);

      ///////////////// now use
      for (String key : results.get(0).keySet())
      {
         System.out.println("key: " + key);
         System.out.println("result: " + results.get(0).get(key));
      }
   }

   //   public void testGetSeries()
   //   {
   //        Query query = service.getQuery("Customer Bill Termination");
   //        Map<String, List<String>> series = query.getSeries();
   //
   //        System.out.println("Customer Bill Termination");
   //        System.out.println("series: " + series);
   //        for(String currKey : series.keySet())
   //        {
   //            System.out.println("currKey: " + currKey);
   //        }
   //   }
}
