package com.neidetcher.silobase.data;

import com.neidetcher.silobase.Query;

/**
 * This is the interface to the database.  We accept the query
 * and modify it with the results from the database.
 */
public interface Data
{

   /**
    * This is a side-effect method.  We modify the input field and 
    * add the Result object to it.
    */
   public void performQuery(Query queryIn);

}
