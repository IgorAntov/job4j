package ru.job4j.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class StoreXML {

    private File file;

    public StoreXML(File target) {
        this.file = target;
    }

    @XmlRootElement
    public static class Entries {
        private List<Entry> entry;

        public Entries() {
        }

        public Entries(List<Entry> values) {
            this.entry = values;
        }

        public List<Entry> getValues() {
            return entry;
        }

        @XmlElement(name = "entry")
        public void setValues(List<Entry> values) {
            this.entry = values;
        }
    }

    @XmlRootElement
    public static class Entry {
        private int field;

        public Entry() {
        }

        public Entry(int value) {
            this.field = value;
        }

        public int getValue() {
            return field;
        }
        @XmlElement(name = "field")
        public void setValue(int value) {
            this.field = value;
        }

    }

    /**
     * Method does marshalling from DB to XML file
     * @param list
     * @throws JAXBException
     */
    public void save(List<Entry> list) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(new Entries(list), file);
    }
}

