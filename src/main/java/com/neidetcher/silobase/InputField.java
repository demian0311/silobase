package com.neidetcher.silobase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * represents a field that would come from the user.
 */
public class InputField
{

   public final String name;
   public String value;

   public static Map<String, String> generateSqlParameterSource(List<InputField> inputFields)
   {
      Map<String, String> map = new HashMap<String, String>();

      if (inputFields == null)
      {
         return map;
      }

      for (InputField inputField : inputFields)
      {
         map.put(inputField.getName(), inputField.getValue());
      }
      return map;
   }

   public InputField(String nameIn)
   {
      name = nameIn;
   }

   public InputField(String nameIn, String valueIn)//, Type typeIn)
   {
      name = nameIn;
      value = valueIn;
      //      type = typeIn;
   }

   public String getName()
   {
      return name;
   }

   public String getPrettyName()
   {
      return name.replace('_', ' ');
   }

   public String getValue()
   {
      if (value == null)
      {
         return "";
      }
      return value;
   }

   public void setValue(String valueIn)
   {
      value = valueIn;
   }

   @Override
   public String toString()
   {
      return ToStringBuilder.reflectionToString(this);
   }

}
