package com.neidetcher.silobase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Represents a query that the customer can provide input to.
 * On the user side a form will be created, on the database
 * side a query will happen.
 */
public class Query
{

   private final DriverManagerDataSource dataSource;
   private final String name;
   private final String sql;
   private final List<InputField> inputFields;
   private List<Map<String, String>> result;

   public Query(String nameIn, DriverManagerDataSource dataSourceIn, String sqlIn, List<InputField> inputFieldsIn)
   {
      name = nameIn;
      dataSource = dataSourceIn;
      sql = sqlIn;
      inputFields = inputFieldsIn;
   }

   public String getName()
   {
      return name;
   }

   public DriverManagerDataSource getDataSource()
   {
      return dataSource;
   }

   public String getSql()
   {
      return sql;
   }

   public List<InputField> getInputFields()
   {
      return inputFields;
   }

   public Map<String, String> getInputFieldsAsMap()
   {
      return InputField.generateSqlParameterSource(inputFields);
   }

   public List<Map<String, String>> getResult()
   {
      return result;
   }

   public void setResults(List<Map<String, String>> result)
   {
      this.result = result;
   }

   public List<Map<String, String>> getResults()
   {
      if (result == null)
      {
         return new ArrayList<Map<String, String>>();
      }
      return result;
   }

   //   public Map<String, List<String>> getSeries()
   //   {
   //       Map<String, List<String>> series = new HashMap<String, List<String>>();
   //
   //       int seriesCount = 0;
   //       String currSeriesName = "";
   //       for(Map<String, String> row : getResults())
   //       {
   //           System.out.println("row: " + row);
   //           if(seriesCount == 0)
   //           {
   //              currSeriesName = row.
   //           }
   //
   //       }
   //
   //       return series;
   //   }

   @Override
   public String toString()
   {
      return ToStringBuilder.reflectionToString(this);
   }

}
