package xml;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ConvertSAX  extends DefaultHandler {
    private String thisElement = "";
    private int count;

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        thisElement = qName;
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        thisElement = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (thisElement.equals("field")) {
            add(new Integer(new String(ch, start, length)));
        }
    }

    private void add(int value) {
        count += value;
    }

    private int getCount() {
        return count;
    }

    void convertSax(File file) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
            parser.parse(file, this);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        System.out.println(this.getCount());
    }
}
