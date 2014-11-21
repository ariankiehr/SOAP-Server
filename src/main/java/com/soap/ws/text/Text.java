package com.soap.ws.text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.jws.WebService;

import org.mcavallo.opencloud.Cloud;


import org.languagetool.*;
import org.languagetool.rules.RuleMatch;

import  me.champeau.ld.UberLanguageDetector;

@WebService(endpointInterface = "com.soap.ws.text.IText")
public class Text implements IText { 
    
    
    @Override
	public List<Tag> detectLanguage(String text) {
        UberLanguageDetector detector = UberLanguageDetector.getInstance();
        String language = detector.detectLang(text);
        Tag tag = new Tag() ;
        tag.language = language ;
        tag.probability = 1 ;
        List<Tag> l = new ArrayList<Tag>() ;
        l.add( tag ) ;
        return l ;
	}
    


    /**
     * Para poder comparar los org.mcavallo.opencloud.Tag y asi ordenarlos por frecuencia de ocurrencia.
     * @author leandro
     *
     */
    public class TagComparator implements Comparator<org.mcavallo.opencloud.Tag>{
        public int compare(org.mcavallo.opencloud.Tag ob1, org.mcavallo.opencloud.Tag ob2) {
            return (int) (ob2.getScore() - ob1.getScore()) ;
        }
    }
    
    @Override
    public List<String> getMostUsedWords(String text) {
        List<String> result = new ArrayList<String>() ;
        Cloud cloud = new Cloud(); // create cloud
        cloud.addText( text ) ;
        List<org.mcavallo.opencloud.Tag> l = cloud.tags() ;
        Collections.sort( l, new TagComparator() );
        for (org.mcavallo.opencloud.Tag t : l) {
            if ( t.getScore() > 1) {
                result.add( t.getName()  ) ;                
            }
        }          
        
        result = new ArrayList<String>() ;

        return result;
        
    }
    
    @Override
    public String correctErrors(String text) {
        JLanguageTool langTool = new JLanguageTool(new org.languagetool.language.AmericanEnglish());
        String suggestions = "" ;
        try {
            langTool.activateDefaultPatternRules();
            List<RuleMatch> matches = langTool.check(text);
            for (RuleMatch match : matches) {               
                suggestions += "Potential error at line " +
                        match.getEndLine() + ", column " +
                        match.getColumn() + ": " + 
                        match.getMessage() + "\n" +
                        "Suggested correction: " +
                        match.getSuggestedReplacements() + "\n";                
            } 
            suggestions.replace( "<", "[" ).replace( ">", "]" ) ;
            
            return suggestions;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
       return null;
    }
    
    
	

}
