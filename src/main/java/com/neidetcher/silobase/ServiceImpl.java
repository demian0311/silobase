package com.neidetcher.silobase;

import java.util.List;

import com.neidetcher.silobase.configure.Configuration;
import com.neidetcher.silobase.data.Data;

public class ServiceImpl implements Service
{

   Configuration configuration;
   Data data;

   public void setConfiguration(Configuration configuration)
   {
      this.configuration = configuration;
   }

   public void setData(Data data)
   {
      this.data = data;
   }

   @Override
   public Query getQuery(String name)
   {
      return configuration.getQuery(name);
   }

   @Override
   public List<String> getQueryNames()
   {
      return configuration.getQueryNames();
   }

   @Override
   public void performQuery(Query queryIn)
   {
      data.performQuery(queryIn);
   }
}
