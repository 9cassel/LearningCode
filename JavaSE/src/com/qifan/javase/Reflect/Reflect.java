package com.qifan.javase.Reflect;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class Reflect {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        File file = new File("src/Student.xml");
        Document doc = db.parse(file);
        Element root = doc.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            getObject(item);
        }
    }

    public static Object getObject(Node item) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {

        String className = item.getNodeName();
        Class clazz = Class.forName("com.qifan.javase.Reflect." + className);
        Field[] declaredFields = clazz.getDeclaredFields();
        Object student = clazz.newInstance();
        NodeList childNodes = item.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (!(node instanceof Element)) {
                continue;
            }
//            String field = node.getNodeName();
//            System.out.println(field);
//            Field field1 = clazz.getField(field);


        }
        return new Object();

    }
}
