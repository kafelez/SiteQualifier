package cat.marfeel.qualify.rest;

import cat.marfeel.qualify.future.MarfeelSiteChecker;
import cat.marfeel.qualify.model.Website;
import cat.marfeel.qualify.persistence.WebsiteDAO;
import cat.marfeel.qualify.qualifier.MarfeelQualificationInst;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Carlos Velez
 */
@RestController
public class RestEntryPoint {

    @Autowired
    private WebsiteDAO websiteDAO;

    @Autowired
    MarfeelQualificationInst marfeelQualificationInst;

    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(RestEntryPoint.class.getName());

    @RequestMapping(value = "/post_urls", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Void> handlePost(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        String jsonString = IOUtils.toString(request.getInputStream());

        List<Website> websiteList = new ObjectMapper().readValue(jsonString, new TypeReference<List<Website>>() {
        });
        LOG.log(Level.INFO, "New POST: {0} items received", websiteList.size());
        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(websiteList.size());

        for (Website website : websiteList) {

            MarfeelSiteChecker checker = new MarfeelSiteChecker(website, websiteDAO, marfeelQualificationInst.defineMe());
            threadPool.submit(checker);
        }

        threadPool.shutdown();

        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.ACCEPTED);
    }

}
