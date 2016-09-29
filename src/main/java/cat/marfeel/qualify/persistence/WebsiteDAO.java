package cat.marfeel.qualify.persistence;

import cat.marfeel.qualify.model.Website;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value = "marfeel")
public class WebsiteDAO {

    @PersistenceContext
    @Qualifier("entityManager")
    private EntityManager entityManager;

    @Transactional
    public void save(Website website) throws PersistenceException{

        //Force no repetitions
        if (findByUrl(website.getUrl()).size() > 0) {
            Website already = findByUrl(website.getUrl()).get(0);
            already.setQualify(website.getQualify());
            already.setError(website.getError());
            entityManager.merge(already);
        } else {
            website.setCreationtime(new Date());
            entityManager.persist(website);
        }
        entityManager.flush();

    }

    public List<Website> getAll() {
        String sQuery = "SELECT w FROM Website w ";
        List<Website> listaEventos = entityManager.createQuery(sQuery, Website.class)
                .getResultList();
        return listaEventos;
    }

    public List<Website> findByQualify(String value) {
        String sQuery = "SELECT w FROM Website w WHERE w.qualify = :value";
        List<Website> listaEventos = entityManager.createQuery(sQuery, Website.class)
                .setParameter("value", value)
                .getResultList();
        return listaEventos;
    }

    public List<Website> findByUrl(String value) {
        String sQuery = "SELECT w FROM Website w WHERE w.url = :value";
        List<Website> listaEventos = entityManager.createQuery(sQuery, Website.class)
                .setParameter("value", value)
                .getResultList();
        return listaEventos;
    }

    public void drop() {
        String sQuery = "DELETE FROM Website";
        entityManager.createQuery(sQuery);
    }

}
