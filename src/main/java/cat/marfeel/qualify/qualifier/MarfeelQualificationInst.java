
package cat.marfeel.qualify.qualifier;

/**
 * This is the Marfeel implementation of QualifierFacade
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
