import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.Set;

/**
 * <p>Класс парсер XML файлов</p>
 *
 * @author Вотинцев Евгений
 * @version 1.0
 */
public class ParserXML {


    public String readXML(String filename) throws FileNotFoundException,NullPointerException {
        FileReader reader = new FileReader(filename);
        Scanner scanner = new Scanner(reader);
        String filestring = "";
        while (scanner.hasNextLine()) {
            filestring += scanner.nextLine() + "\n";
        }
        try {
            reader.close();
        } catch (IOException e) {
            System.err.println("Ошибка");
        }
        return filestring;
    }

    /**
     * <p>Парсит XML строку</p>
     *
     * @param filestring Строка XML
     * @throws IOException
     * @throws  SAXException При парсинге пустого файла
     *
     */
    public void parseXML(String filestring) throws IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;

        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("Ошибка хml файла ");
        }
        Document document = null;
        Animal animal = new Pyatachok();
        document = builder.parse(new ByteArrayInputStream(filestring.getBytes(StandardCharsets.UTF_8)));
        try {
            NodeList animalslist = document.getElementsByTagName("animal");
            for (int i = 0; i < animalslist.getLength(); i++) {
                if (animalslist.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element animalelement = (Element) animalslist.item(i);

                    Element classelement = (Element) animalelement.getElementsByTagName("class").item(0);
                    Element iqElement = (Element) classelement.getElementsByTagName("iq").item(0);
                    switch (classelement.getAttribute("name")) {
                        case "Pyatachok": {
                            animal = new Pyatachok();
                            ((Pyatachok) animal).setIq(Double.valueOf(iqElement.getTextContent()));
                        }
                        break;
                        case "Sova": {
                            animal = new Sova();
                            ((Sova) animal).setIq(Double.valueOf(iqElement.getTextContent()));
                        }
                        break;
                        case "WinniePooh": {
                            animal = new WinniePooh();
                            ((WinniePooh) animal).setIq(Double.valueOf(iqElement.getTextContent()));
                        }
                    }

                    NodeList childNodes = animalelement.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            Element childElement = (Element) childNodes.item(j);

                            switch (childElement.getNodeName()) {
                                case "name": {
                                    animal.setName(childElement.getTextContent());
                                }
                                break;
                                case "height": {
                                    animal.setHeight(Double.valueOf(childElement.getTextContent()));
                                }
                                break;
                                case "weight": {
                                    animal.setWeight(Double.valueOf(childElement.getTextContent()));
                                }
                                break;
                                case "width": {
                                    animal.setWidth(Double.valueOf(childElement.getTextContent()));
                                }
                                break;
                                case "color": {
                                    animal.setColor(childElement.getTextContent());
                                }
                            }

                        }
                    }
                }
                CollectionManager.add(animal);
            }

        } catch (NullPointerException e) {
            System.err.println("Неверный формат xml");
        }
    }

    /**
     * <p>Парсит коллекцию в xml </p>
     *
     * @param animals Коллекция животных
     * @return Объект типа document, содержащий коллекцию животных
     * @throws ParserConfigurationException
     */
    public Document parseCollection(Set<Animal> animals) throws ParserConfigurationException {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element world = document.createElement("world");
        document.appendChild(world);

        for (Animal current : animals) {
            Element animal = document.createElement("animal");
            Element name = document.createElement("name");
            name.appendChild(document.createTextNode(current.getName()));
            animal.appendChild(name);

            Element height = document.createElement("height");
            height.appendChild(document.createTextNode(String.valueOf(current.getHeight())));
            animal.appendChild(height);

            Element weight = document.createElement("weight");
            weight.appendChild(document.createTextNode(String.valueOf(current.getWeight())));
            animal.appendChild(weight);

            Element width = document.createElement("width");
            width.appendChild(document.createTextNode(String.valueOf(current.getWidth())));
            animal.appendChild(width);

            Element color = document.createElement("color");
            color.appendChild(document.createTextNode(String.valueOf(current.getColor())));
            animal.appendChild(color);

            Element class_el = document.createElement("class");
            String class_name = String.valueOf(current.getClass()).replace("class ", "");
            class_el.setAttribute("name", class_name);

            Element iq = document.createElement("iq");
            switch (class_name) {
                case ("Pyatachok"): {
                    iq.appendChild(document.createTextNode(String.valueOf(((Pyatachok) current).getIq())));
                }
                break;
                case ("WinniePooh"): {
                    iq.appendChild(document.createTextNode(String.valueOf(((WinniePooh) current).getIq())));
                }
                break;
                case ("Sova"): {
                    iq.appendChild(document.createTextNode(String.valueOf(((Sova) current).getIq())));
                }
            }

            class_el.appendChild(iq);

            animal.appendChild(class_el);

            world.appendChild(animal);
        }
        return document;
    }
}
