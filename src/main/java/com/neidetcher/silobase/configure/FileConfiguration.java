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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.neidetcher.silobase.Query;

public class FileConfiguration implements Configuration
{

   protected Log log = LogFactory.getLog(FileConfiguration.class);

   private String file;

   @Override
   public Query getQuery(String queryName)
   {
      if (queryName == null || "".equals(queryName))
      {
         throw new IllegalArgumentException("query name can't be null or blank");
      }

      XPath xpath = XPathFactory.newInstance().newXPath();
      String expression = "/silobase/queries/*";
      InputSource inputSource = new InputSource(file);
      try
      {
         /*
         <query>
            <database>staging</database>
            <name>All Customers</name>
            <sql>select * from customer</sql>
         </query>
          */
         NodeList queryNodes = (NodeList) xpath.evaluate(expression, inputSource, XPathConstants.NODESET);

         //log.debug("nodes: " + ToStringBuilder.reflectionToString(nodes));

         loopThruQueries: for (int ii = 0; ii < queryNodes.getLength(); ii++)
         {
            // look for the right one
            Element queryNode = (Element) queryNodes.item(ii);
            log.debug("ii>> " + queryNode.getTagName() + ":" + queryNode.getTextContent());

            NodeList queryAttributeNodes = queryNode.getChildNodes();

            String database = null;
            String sql = null;

            for (int jj = 0; jj < queryAttributeNodes.getLength(); jj++)
            {
               database = null;
               sql = null;

               Node queryAttributeNode = queryAttributeNodes.item(jj);
               String attributeName = queryAttributeNode.getNodeName();
               String attributeValue = queryAttributeNode.getTextContent();

               log.debug("jj>>" + queryAttributeNode.getNodeName() + ":" + queryAttributeNode.getTextContent());

               if ("name".equals(attributeName) && !queryName.equals(attributeValue))
               {
                  log.debug("not a match: " + attributeValue);
                  break loopThruQueries;
               }

               if ("sql".equals(attributeName))
               {
                  sql = attributeValue;
               }

               if ("database".equals(attributeName))
               {
                  database = attributeValue;
               }
            }

            log.debug("actual name: " + queryName);
            log.debug("actual sql: " + sql);
            log.debug("actual database: " + database);

            //            NodeList queryChildren = node.getChildNodes();
            //            boolean correctQueryName = false;
            //            for (int jj = 0; jj < queryChildren.getLength(); jj++)
            //            {
            //               Element currQueryChild = (Element) nodes.item(jj);
            //               log.debug(currQueryChild.getTagName() + ":" + currQueryChild.getTextContent());
            //
            //               if ("name".equals(currQueryChild.getTagName()))
            //               {
            //                  queryName = currQueryChild.getTextContent();
            //                  if (name.equals(queryName))
            //                  {
            //                     correctQueryName = true;
            //                  }
            //               }
            //               else if ("database".equals(currQueryChild.getTagName()))
            //               {
            //                  dataSource = currQueryChild.getTextContent();
            //               }
            //               else if ("sql".equals(currQueryChild.getTagName()))
            //               {
            //                  sql = currQueryChild.getTextContent();
            //               }
            //               else
            //               {
            //                  log.warn("found a tag under query we don't know about: " + currQueryChild.getTagName());
            //               }
            //
            //               if (correctQueryName)
            //               {
            //                  log.debug("queryName  : " + queryName);
            //                  log.debug("dataSource : " + dataSource);
            //                  log.debug("sql        : " + sql);
            //               }
            //            }
         }
      }
      catch (XPathExpressionException e)
      {
         e.printStackTrace();
      }
      return null;
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
            Element node = (Element) nodes.item(ii);
            log.debug("node: " + node.getTextContent());

            queryNames.add(node.getTextContent());
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
