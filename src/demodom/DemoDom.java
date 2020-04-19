
package demodom;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * ЗАДАНИЕ: Написать класс DemoDOM,
 * который на основе DOM разбирает файл sapmle.xml
 */
public class DemoDom {

    static String fileName = "sample.xml";

    public static void main(String[] args) {
    
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DemoDom.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
       
        Document document = null;
        try {
            document = documentBuilder.parse(fileName);
        } catch (SAXException | IOException ex) {
            Logger.getLogger(DemoDom.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        System.out.println("Список персонала:");

        
// получаем корневой узел
        Node root = document.getDocumentElement();

        NodeList staff = root.getChildNodes();
        // цикл по юзерам
        for (int i = 0; i < staff.getLength(); i++) {
            Node user = staff.item(i);
 
            NamedNodeMap attributes = null;
            if (user.hasAttributes()) {
                attributes = user.getAttributes();
                int n = attributes.getLength();
                StringBuilder sb = new StringBuilder("User attributes:");
                for (int k = 0; k < n; k++) {
                    Node attr = attributes.item(k);
                    String name = attr.getNodeName();
                    String value = attr.getNodeValue();
                    sb.append(name + "= " + value + "; ");
                }
                System.out.println(sb);
            }
            // =============================================
            if (user.getNodeType() != Node.TEXT_NODE) {
                NodeList staffProps = user.getChildNodes();
                for (int j = 0; j < staffProps.getLength(); j++) {
                    Node staffProp = staffProps.item(j);
                    if (staffProp.getNodeType() != Node.TEXT_NODE) {
                        System.out.print(staffProp.getNodeName() + ": ");
                        Node child = staffProp.getChildNodes().item(0);
                        if (child != null ) {
                        System.out.println(child.getTextContent());
                        }
                    }
                    NamedNodeMap attrs = null;
                    if (staffProp.hasAttributes()) {
                        attrs = staffProp.getAttributes();
                        int n = attrs.getLength();
                        StringBuilder sb = new StringBuilder("User attributes:");
                        for (int k = 0; k < n; k++) {
                            Node attr = attrs.item(k);
                            String name = attr.getNodeName();
                            String value = attr.getNodeValue();
                            sb.append(name + "= " + value + "; ");
                        }
                        System.out.println(sb);
                    }
                } // цикл по элементам внутри user
                System.out.println("-------------------------");
            }

        } // цикл по юзерам
    }

}
