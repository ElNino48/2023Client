package XmlDao;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ReadXML {
    public static String ReadXmlFile(String name)
    {
        try {
            File file = new File("src\\main\\resources\\texts.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("string");
            for (int temp = 0; temp < nodeList.getLength(); temp++)
            {
                System.out.println(11);
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {System.out.println(22);

                    Element element = (Element) node;
                    if (element.getAttribute("name").equals(name))
                    {
                        System.out.println(33);
                        return element.getTextContent().trim();
                    }
                }
            } System.out.println(44);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(55);
            return null;
        }
        return null;
    }
}