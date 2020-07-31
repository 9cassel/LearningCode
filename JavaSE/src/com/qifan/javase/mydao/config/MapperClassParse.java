package com.qifan.javase.mydao.config;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.List;

public class MapperClassParse {

    public static void parseXml(String xmlPath) {
        InputStream is = MapperClassParse.class.getResourceAsStream(xmlPath);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(is);
            Element root = document.getDocumentElement();
            NodeList childNodes = root.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item = childNodes.item(i);
                if (!(item instanceof Element)) {
                    continue;
                }
                String type = item.getNodeName();
                NamedNodeMap attributes = item.getAttributes();
                String id = attributes.getNamedItem(MapperClassElementName.ID_ATTRIBUTE).getNodeValue();
                String resultType = attributes.getNamedItem(MapperClassElementName.RESULT_TYPE_ATTRIBUTE).getNodeValue();
                String sqlStatement = item.getTextContent().trim();
                MapperClass mapper = new MapperClass();
                mapper.setId(id);
                mapper.setType(type);
                mapper.setResultType(Class.forName(resultType));
                MapperClassSqlParse.parseParameter(sqlStatement,mapper);
                MapperSet.add(mapper);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
