package com.neidetcher.silobase;

import java.util.List;

import com.neidetcher.silobase.configure.Configuration;
import com.neidetcher.silobase.configure.FileConfiguration;
import com.neidetcher.silobase.data.Data;
import com.neidetcher.silobase.data.DataImpl;

public class ServiceImpl implements Service
{

   Configuration configuration;
   Data data;

   public static Service createServiceImpl(String filename)
   {
      FileConfiguration fileConfiguration = new FileConfiguration();
      fileConfiguration.setFile(filename);

      DataImpl data = new DataImpl();

      ServiceImpl service = new ServiceImpl();
      service.setConfiguration(fileConfiguration);
      service.setData(data);

      return service;
   }

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
