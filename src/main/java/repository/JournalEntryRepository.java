package repository;

import domain.JournalEntry;
import interfaces.IRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

@Repository
public class JournalEntryRepository implements IRepository<JournalEntry, Boolean> {
    private Logger logger = Logger.getLogger(JournalEntryRepository.class.getName());
    private final SessionFactory sessionFactory;

    public JournalEntryRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Boolean save(JournalEntry entity) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(entity);
            logger.info("JournalEntry saved: " + entity);
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean update(JournalEntry entity) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.update(entity);
            logger.info("JournalEntry updated: " + entity);
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean delete(JournalEntry entity) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.delete(entity);
            logger.info("JournalEntry deleted: " + entity);
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return false;
        }
    }

    @Override
    public JournalEntry findById(int id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery("SELECT j FROM JournalEntry j JOIN FETCH j.user WHERE j.id = :id", JournalEntry.class).setParameter("id", id).uniqueResult();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return null;
        }
    }

    @Override
    public List<JournalEntry> findAll() {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery("FROM JournalEntry j JOIN FETCH j.user", JournalEntry.class).list();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return null;
        }
    }

    public List<JournalEntry> findAllByUserName(String username) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery("SELECT je FROM JournalEntry je JOIN FETCH je.user u WHERE u.username = :username", JournalEntry.class).setParameter("username", username).getResultList();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return null;
        }
    }

}
