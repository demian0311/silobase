package com.neidetcher.silobase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * represents a field that would come from the user.
 */
public class InputField
{

   // ordinal
   public final String name;
   public String value;

   //   public final Type type;

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

   //   /**
   //    * valid types for an input field.
   //    */
   //   public enum Type
   //   {
   //      STRING, NUMBER
   //   }

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

   public String getValue()
   {
      return value;
   }

   //   public Type getType()
   //   {
   //      return type;
   //   }

}
