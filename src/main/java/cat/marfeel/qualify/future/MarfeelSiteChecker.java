package cat.marfeel.qualify.future;

import cat.marfeel.qualify.model.Website;
import cat.marfeel.qualify.persistence.WebsiteDAO;
import cat.marfeel.qualify.qualifier.QualifierFacade;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.PersistenceException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Carlos Velez
 */
public class MarfeelSiteChecker implements Callable<Website> {

    private Website website = null;
    private final WebsiteDAO websiteDAO;
    private final QualifierFacade qualificatorImplementation;

    private static final String HTTP = "http://";
    private static final String HTTPS = "https://";

    private static final Logger LOG = Logger.getLogger(MarfeelSiteChecker.class.getName());

    public MarfeelSiteChecker(Website website, WebsiteDAO websiteDAO, QualifierFacade qualificatorImplementation) {
        this.website = website;
        this.websiteDAO = websiteDAO;
        this.qualificatorImplementation = qualificatorImplementation;
    }

    //This method represents a single thread lifecycle:
    //1.attack the URL via HTTP, 2.qualify the content and 3.persists
    @Override
    public Website call()  {
        Document site = accesSite();
        if (site != null) {
            website.emptyError();
            if (qualificatorImplementation.check(site)) {
                website.setQualify(Website.Qualification.YES.name());
            } else {
                website.setQualify(Website.Qualification.NO.name());
            }
        }
        try {
            websiteDAO.save(website);
        } catch (PersistenceException ex) {
            LOG.log(Level.SEVERE, "ERROR persisting object: " + website.getUrl() + " : " + ex.getClass().getName(), "");
        }

        return website;
    }

    private Document accesSite() {
        Document site = doGet(HTTP);
        if (site == null) {
            site = doGet(HTTPS);
        }
        return site;
    }

    private Document doGet(String http) {
        try {
            return Jsoup.connect(http + website.getUrl()).get();

        } catch (IOException ex) {
            website.setError(ex.getClass().getName());
            website.setQualify("NO");
            LOG.log(Level.SEVERE, "ERROR accesing site: " + website.getUrl() + " : " + ex.getClass().getName(), "");
        }
        return null;
    }
}
