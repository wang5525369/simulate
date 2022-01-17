package com.itrus.contract.tools.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.util.List;

public class XmlUtils {
    public static Document getDocument(byte [] fileBytes) throws DocumentException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileBytes);
        SAXReader reader = new SAXReader();
        Document document = reader.read(byteArrayInputStream);
        return document;
    }

    public static List<Node> getNodeList(Document document,String nodePath){
        List<Node> nodeList = document.selectNodes(nodePath);
        return nodeList;
    }

    public static String getNodeText(Node node){
        return node.getText();
    }
}
