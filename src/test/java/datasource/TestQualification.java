package datasource;

import cat.marfeel.qualify.model.Website;
import cat.marfeel.qualify.qualifier.MarfeelQualificationInst;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.assertj.core.api.Assertions.assertThat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class TestQualification {

    public static final String YES = Website.Qualification.YES.name();

    private final String DOC_NO = "<html><head><title>carrasclas web page</title></head><body>hello</body></html>";
    private final String DOC_YES = "<html><head><title>news and more</title></head><body>hello</body></html>";
    private final String DOC_NO_TAG = "<html><head></head><body>hello</body></html>";

    @Autowired
    MarfeelQualificationInst marfeelQualificationInst;

    @Test
    public void testQualify() {
        Document docYes = createDummyDoc(DOC_YES);
        assertThat(marfeelQualificationInst.defineMe().check(docYes)).isTrue();
    }

    @Test
    public void testNoQualify() {
        Document docNo = createDummyDoc(DOC_NO);
        assertThat(marfeelQualificationInst.defineMe().check(docNo)).isFalse();
    }

    @Test
    public void testNoQualifyNoTag() {
        Document docNo = createDummyDoc(DOC_NO_TAG);
        assertThat(marfeelQualificationInst.defineMe().check(docNo)).isFalse();
    }

    private Document createDummyDoc(String content) {
        return Jsoup.parse(content);
    }

}
