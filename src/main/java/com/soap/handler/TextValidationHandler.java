package com.soap.handler;

import java.io.ByteArrayInputStream;  
import java.io.IOException;  
import java.io.StringWriter;  
import java.net.MalformedURLException;  
import java.net.URL;  
  

import javax.xml.XMLConstants;  
import javax.xml.parsers.DocumentBuilder;  
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.ParserConfigurationException;  
import javax.xml.transform.Source;  
import javax.xml.transform.Transformer;  
import javax.xml.transform.TransformerConfigurationException;  
import javax.xml.transform.TransformerException;  
import javax.xml.transform.TransformerFactory;  
import javax.xml.transform.TransformerFactoryConfigurationError;  
import javax.xml.transform.dom.DOMSource;  
import javax.xml.transform.stream.StreamResult;  
import javax.xml.validation.Schema;  
import javax.xml.validation.SchemaFactory;  
import javax.xml.ws.LogicalMessage;  
import javax.xml.ws.WebServiceException;
import javax.xml.ws.handler.LogicalHandler;  
import javax.xml.ws.handler.LogicalMessageContext;  
import javax.xml.ws.handler.MessageContext;  
  

import org.w3c.dom.Document;  
import org.xml.sax.SAXException;  
  
public class TextValidationHandler implements LogicalHandler<LogicalMessageContext> {  
    private String schemaUrl = "http://localhost:9999/ws/Text?xsd=1";
  
    @Override  
    public boolean handleMessage(LogicalMessageContext context) {  
    	
        Boolean isOutBound = (Boolean) context  
                .get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);  
  
        if (isOutBound) {  
            return true;  
        }  
  
        LogicalMessage lm = context.getMessage();  
        Source payload = lm.getPayload();  
        StreamResult res = new StreamResult(new StringWriter());  
        String message = "";  
  
        try {  
            Transformer trans;  
            trans = TransformerFactory.newInstance().newTransformer();  
            trans.transform(payload, res);  
            message = res.getWriter().toString();  
            // Validate  
            validate(message, schemaUrl);  
        } catch (TransformerConfigurationException e) {  
            // When Source payload Transformer object could not be obtained.  
            throw new WebServiceException(e);  
        } catch (TransformerFactoryConfigurationError e) {  
            // When Source payload Transformer object could not be obtained.  
            throw new WebServiceException(e);  
        } catch (TransformerException e) {  
            // When Source payload could not be transformed to String.  
            throw new WebServiceException(e);  
        } catch (MalformedURLException e) {  
            // When URL to schema is invalid.  
            throw new WebServiceException(e);  
        } catch (ParserConfigurationException e) {  
            // When parser needed for validation could not be obtained.  
            throw new WebServiceException(e);  
        } catch (IOException e) {  
            // When something is wrong with IO.  
            throw new WebServiceException(e);  
        } catch (SAXException e) {  
            // When XSD-schema validation fails.  
            throw new WebServiceException(e);  
        }  
  
        return true;  
    }  
  
    private void validate(String xml, String schemaUrl)  
            throws ParserConfigurationException, IOException,  
            MalformedURLException, SAXException {  
  
        DocumentBuilder parser = null;  
        Document document = null;  
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
        dbf.setNamespaceAware(true);  
        parser = dbf.newDocumentBuilder();  
  
        byte bytes[] = xml.getBytes();  
        document = parser.parse(new ByteArrayInputStream(bytes));  
        SchemaFactory factory = SchemaFactory  
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);  
  
        Schema schema = null;  
        schema = factory.newSchema(new URL(schemaUrl));  
        javax.xml.validation.Validator validator = schema.newValidator();  
        validator.validate(new DOMSource(document));  
    }  
  
    @Override  
    public boolean handleFault(LogicalMessageContext context) {  
        return true;  
    }  
  
    @Override  
    public void close(MessageContext context) {  
        ;  
    }  
  
}