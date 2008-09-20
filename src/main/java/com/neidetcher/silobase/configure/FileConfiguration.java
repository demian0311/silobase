package com.neidetcher.silobase.configure;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.neidetcher.silobase.Query;

public class FileConfiguration implements Configuration
{

   protected Log log = LogFactory.getLog(FileConfiguration.class);

   private String file;

   //private Map<String, Query> queries;

   @Override
   public Query getQuery(String name)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public List<String> getQueryName()
   {
      List<String> queryNames = new ArrayList<String>();

      return queryNames;
   }

   @Override
   public List<String> getQueryNames()
   {
      List<String> queryNames = new ArrayList<String>();

      XPath xpath = XPathFactory.newInstance().newXPath();
      String expression = "/silobase/queries/*/name";
      InputSource inputSource = new InputSource(file);
      try
      {
         NodeList nodes = (NodeList) xpath.evaluate(expression, inputSource, XPathConstants.NODESET);

         log.debug("nodes: " + ToStringBuilder.reflectionToString(nodes));
         for (int ii = 0; ii < nodes.getLength(); ii++)
         {
            Node node = nodes.item(ii);
            log.debug("node: " + node);

            queryNames.add(node.getNodeValue());
         }
      }
      catch (XPathExpressionException e)
      {
         e.printStackTrace();
      }

      return queryNames;
   }

   public void setFile(String fileIn)
   {
      file = fileIn;
   }
}
