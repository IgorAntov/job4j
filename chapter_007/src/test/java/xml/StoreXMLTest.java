package xml;

import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.File;

/** Test
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class StoreXMLTest {

    @Test
    public void testXML() {
        StoreSQL storeSQL = new StoreSQL(new Config());
        storeSQL.generate(100);
        File file = new File("testxml.xml");
        StoreXML storeXML = new StoreXML(file);
        try {
            storeXML.save(storeSQL.doEntryList());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void xsqtTest() {
        ConvertXSQT convertXSQT = new ConvertXSQT();
        convertXSQT.convert(new File("testxml.xml"), new File("testxsqt.xml"), new File("testxsl.xsl"));
    }

    @Test
    public void saxTest() {
        ConvertSAX convertSAX = new ConvertSAX();
        convertSAX.convertSax(new File("testxml.xml"));
    }
}
