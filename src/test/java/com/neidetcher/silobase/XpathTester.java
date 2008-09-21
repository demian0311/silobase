package com.neidetcher.silobase;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import junit.framework.TestCase;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XpathTester extends TestCase
{

   public void test1() throws Exception
   {
      String expression = "//query/@id";
      String filename = "src/test/resources/silobase.xml";
      XPath xpath = XPathFactory.newInstance().newXPath();
      InputSource inputSource = new InputSource(filename);
      NodeList attrImpl = (NodeList) xpath.evaluate(expression, inputSource, XPathConstants.NODESET);
      for (int i = 0; i < attrImpl.getLength(); i++)
      {
         org.w3c.dom.Node node = attrImpl.item(i);
         System.out.println("name: " + node.getNodeName());
         System.out.println("value: " + node.getNodeValue());

         //      {
         //         System.out.println("name: " + attrImpl.getName());
         //         System.out.println("value: " + attrImpl.getValue());
         //         Element el = (Element) elements.item(i);
         //         System.out.println("content: " + el.getTextContent());
      }
   }

   public void testExpression() throws XPathExpressionException, TransformerException
   {
      String expression = "/silobase/queries/*/name";
      String filename = "src/test/resources/silobase.xml";
      XPath xpath = XPathFactory.newInstance().newXPath();
      InputSource inputSource = new InputSource(filename);
      NodeList elements = (NodeList) xpath.evaluate(expression, inputSource, XPathConstants.NODESET);
      for (int i = 0; i < elements.getLength(); i++)
      {
         Element el = (Element) elements.item(i);
         System.out.println("content: " + el.getTextContent());
      }
   }

   private NodeList runXpe(String expression)
   {
      NodeList elements = null;

      try
      {
         String filename = "src/test/resources/silobase.xml";
         XPath xpath = XPathFactory.newInstance().newXPath();
         InputSource inputSource = new InputSource(filename);
         elements = (NodeList) xpath.evaluate(expression, inputSource, XPathConstants.NODESET);
      }
      catch (XPathExpressionException e)
      {
         e.printStackTrace();
      }

      return elements;
   }

   private String xpeAsString(String expression)
   {
      String stringOut = null;

      try
      {
         String filename = "src/test/resources/silobase.xml";
         XPath xpath = XPathFactory.newInstance().newXPath();
         InputSource inputSource = new InputSource(filename);
         stringOut = (String) xpath.evaluate(expression, inputSource, XPathConstants.STRING);
      }
      catch (XPathExpressionException e)
      {
         e.printStackTrace();
      }

      return stringOut;
   }

   public void testExpression2() throws XPathExpressionException, TransformerException
   {
      NodeList elements = runXpe("/silobase/queries/query");

      int numberOfQueries = elements.getLength();
      assertEquals(2, numberOfQueries);
      String queryName = "All Customers";

      for (int currQueryIndex = 1; currQueryIndex <= numberOfQueries; currQueryIndex++)
      {
         String foundQueryName = xpeAsString("silobase/queries/query[" + currQueryIndex + "]/name");

         //         String filename = "src/test/resources/silobase.xml";
         //         XPath xpath = XPathFactory.newInstance().newXPath();
         //         InputSource inputSource = new InputSource(filename);
         //         String foundQueryName = xpath.evaluate(queryNameExpression, inputSource);
         //         System.out.println("queryName[" + currQueryIndex + "] : " + queryName);

         if (foundQueryName.equals(queryName))
         {
            String db = xpeAsString("silobase/queries/query[" + currQueryIndex + "]/database");
            String sql = xpeAsString("silobase/queries/query[" + currQueryIndex + "]/sql");

            System.out.println("db: " + db);
            System.out.println("sql: " + sql);

         }
      }
   }
}
