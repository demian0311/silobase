package com.neidetcher.silobase.data;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.neidetcher.silobase.Query;

public class DataImpl implements Data
{

   protected Log log = LogFactory.getLog(DataImpl.class);

   @SuppressWarnings("unchecked")
   @Override
   public void performQuery(Query queryIn)
   {
      String sql = queryIn.getSql();

      NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(queryIn.getDataSource());
      List<Map<String, String>> result = template.queryForList(sql, queryIn.getInputFieldsAsMap());

      queryIn.setResult(result);
   }
}
