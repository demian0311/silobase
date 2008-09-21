package com.neidetcher.silobase.util;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XPathHelper
{

   protected Log log = LogFactory.getLog(XPathHelper.class);

   private InputSource inputSource = null;
   private XPath xpath = null;

   public XPathHelper(String filename)
   {
      xpath = XPathFactory.newInstance().newXPath();
      inputSource = new InputSource(filename);
   }

   public String getString(String expression)
   {
      String result = (String) evaluate(expression, XPathConstants.STRING);
      log.debug(expression + ": " + result);
      return result;
   }

   public NodeList getNodeList(String expression)
   {
      NodeList nodeList = (NodeList) evaluate(expression, XPathConstants.NODESET);

      return nodeList;
   }

   private Object evaluate(String expression, QName qName)
   {
      try
      {
         return xpath.evaluate(expression, inputSource, qName);
      }
      catch (XPathExpressionException e)
      {
         e.printStackTrace();
         throw new RuntimeException("error doing xpath: " + e.getMessage());
      }
   }

}
