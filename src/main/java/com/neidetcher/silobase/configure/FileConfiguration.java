package com.neidetcher.silobase.configure;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.neidetcher.silobase.Query;

public class FileConfiguration implements Configuration
{

   protected Log log = LogFactory.getLog(FileConfiguration.class);

   private String file;
   private Map<String, Query> queries;

   @Override
   public Query getQuery(String name)
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public List<String> getQueryNames()
   {
      // TODO Auto-generated method stub
      return null;
   }

   Map<String, Query> getQueries()
   {
      if (queries == null)
      {

         log.debug("reading " + file);
         // do a we bit of file parsing
      }
      return queries;
   }

   public void setFile(String fileIn)
   {
      file = fileIn;
   }
}
