package com.neidetcher.silobase;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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

   public static void printXML(Element element) throws TransformerException
   {
      Transformer transformer = TransformerFactory.newInstance().newTransformer();
      transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
      Source source = new DOMSource(element);
      Result output = new StreamResult(System.out);
      transformer.transform(source, output);
      System.out.println();
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
         //         System.out.println("el: " + el);
         System.out.println("content: " + el.getTextContent());

         //         System.out.println(">>" + elements.item(i));
         //         if (elements.item(i) instanceof Element)
         //         {
         //            printXML((Element) elements.item(i));
         //         }
         //         else
         //         {
         //            System.out.println(elements.item(i));
         //         }
      }
   }
}