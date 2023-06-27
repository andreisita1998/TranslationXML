package com.example.Translation;

import org.springframework.stereotype.Service;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@Service
public class TranslationService {
    private static final String XML_FILE_PATH = "classpath:words.xml";

    public String translate(String word, String sourceLanguage, String targetLanguage) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(XML_FILE_PATH);

            NodeList nodeList = document.getElementsByTagName("word");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element wordElement = (Element) nodeList.item(i);
                String sourceTranslation = getTranslation(wordElement, sourceLanguage);
                String targetTranslation = getTranslation(wordElement, targetLanguage);
                if (sourceTranslation != null && targetTranslation != null) {
                    if (sourceTranslation.equals(word)) {
                        return targetTranslation;
                    }
                }
            }

            return "Translation not found";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred";
        }
    }

    private String getTranslation(Element wordElement, String language) {
        NodeList translationNodes = wordElement.getElementsByTagName(language);
        if (translationNodes.getLength() > 0) {
            Element translationElement = (Element) translationNodes.item(0);
            return translationElement.getTextContent();
        }
        return null;
    }
}