/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.marfeel.qualify.qualifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

abstract public class QualifierFacade
{
    
    private final  Collection<Qualifier> evaluatorORList;
    private final  Collection<Qualifier> evaluatorANDList;
    
    abstract public QualifierFacade defineMe();
    
     QualifierFacade(){
        evaluatorORList = new ArrayList<>();
        evaluatorANDList = new ArrayList<>();
    }
    
    private boolean checkAND(Document doc ) { 
        for (Qualifier qualifier : evaluatorANDList){
            List<Element> elementsTag = doc.getElementsByTag(qualifier.getElementText());
            if (elementsTag.isEmpty()){
                return false;
            }
            for (Element elem : elementsTag){
                 if (!elem.text().toLowerCase().contains(qualifier.getValue()))
                return false;
            }
        }
        return true;
    }
    
     private boolean checkOR(Document doc ) { 
        for (Qualifier qualifier : evaluatorORList){
            List<Element> elementsTag = doc.getElementsByTag(qualifier.getElementText());
            if (elementsTag.isEmpty()){
                return false;
            }
            for (Element elem : elementsTag){
                 if (elem.text().toLowerCase().contains(qualifier.getValue()))
                return true;
            }
        }
        return false;
    }
    
    final public boolean check(Document doc){
        return checkAND(doc) && checkOR(doc);
    }
    
    final public void addOrQualifier(Qualifier q){
        evaluatorORList.add(q);
    }
    
    final public void addAndQualifier(Qualifier q){
        evaluatorANDList.add(q);
    }
}
