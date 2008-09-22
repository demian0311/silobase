package com.neidetcher.silobase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

public class QueryTest extends TestCase
{

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
}
