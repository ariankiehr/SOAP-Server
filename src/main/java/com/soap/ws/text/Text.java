package com.soap.ws.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.jws.WebService;

import org.mcavallo.opencloud.Cloud;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import com.cybozu.labs.langdetect.Language;

@WebService(endpointInterface = "com.soap.ws.text.IText")
public class Text implements IText { 
	
    private static boolean firstTime = true ;
    private static String profileDirectory = "/home/leandro/Tesis/workspace/SOA/src/main/resources/langprofiles" ; //TODO quitar harcodeo 
	/**
	 * Carga los perfiles para poder operar con detectLangs.
	 * @param profileDirectory
	 * @throws LangDetectException
	 */
    private synchronized static void init() throws LangDetectException {
        if ( firstTime ) {
            DetectorFactory.loadProfile(profileDirectory);
        } 
        firstTime = false;
    }
    
    /**
     * Dado una texto, detecta los lenguajes en los que posiblemente esté escrito. 
     * En funcion a los perfiles de lenguajes cargados.
     * 
     * @param text
     * @return Una lista de los lenguajes con la probabilidad de que el texto pertenesca a este.
     * @throws LangDetectException
     */
    private ArrayList<Language> detectLangs(String text) throws LangDetectException {
        Detector detector = DetectorFactory.create();
        detector.append(text);
        return detector.getProbabilities();
    }

    
    @Override
	public List<Tag> detectLanguage(String text) {
        try {
            init() ;
        } catch (LangDetectException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
	    List<Tag> result = new ArrayList<Tag>() ;
	    List<Language> langs;
        try {
            langs = detectLangs( text );
            for (Language l : langs) {
                Tag t = new Tag() ;
                t.language = l.lang ;
                t.probability = (float) l.prob ;
                result.add( t ) ;
            }

        } catch (LangDetectException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return result;

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
            result.add( t.getName()  ) ;
        }          
        return result;
        
    }

    @Override
    public String correctErrors(String text) {
        // TODO Auto-generated method stub
        return null;
    }
    
    
	

}
