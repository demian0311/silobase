package com.neidetcher.silobase.configure;

import java.util.List;

import com.neidetcher.silobase.Query;

public interface Configuration
{

   public List<String> getQueryNames();

   public Query getQuery(String name);

}
