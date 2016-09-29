/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.marfeel.qualify.qualifier;

/**
 *
 * @author cvelez
 */
public class MarfeelQualificationInst extends QualifierFacade {

    private static final String CONTENT_NOTICIAS = "noticias";
    private static final String CONTENT_NEWS = "news";
    private static final String TAG = "title";

    @Override
    public MarfeelQualificationInst defineMe() {
       
        this.addOrQualifier(new Qualifier(TAG, CONTENT_NEWS));
        this.addOrQualifier(new Qualifier(TAG, CONTENT_NOTICIAS));
        return this;

    }
}
