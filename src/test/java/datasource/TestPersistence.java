package datasource;

import cat.marfeel.qualify.model.Website;
import cat.marfeel.qualify.persistence.WebsiteDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class TestPersistence {

    public static final String YES = Website.Qualification.YES.name();

    @Autowired
    private WebsiteDAO websiteDAO;

    private String SITE = "edition.cnn.com";

    private Website dummy() {
        return new Website(SITE);
    }

    @Test
    @Rollback(false)
    public void testSave() {
        websiteDAO.save(dummy());
        assertThat(websiteDAO.getAll().isEmpty()).isFalse();
    }

    @Test
    @Rollback(false)
    public void testFind() {
        websiteDAO.save(dummy());
        assertThat(websiteDAO.findByUrl(SITE).size()).isGreaterThan(0);
    }

}
