package repository;

import domain.JournalEntryComment;
import interfaces.IRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

@Repository
public class JournalCommentRepository implements IRepository<JournalEntryComment, Boolean> {
    private Logger logger = Logger.getLogger(JournalCommentRepository.class.getName());
    private final SessionFactory sessionFactory;

    public JournalCommentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Boolean save(JournalEntryComment entity) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(entity);
            logger.info("JournalEntryComment saved: " + entity);
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean update(JournalEntryComment entity) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.update(entity);
            logger.info("JournalEntryComment updated: " + entity);
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean delete(JournalEntryComment entity) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.delete(entity);
            logger.info("JournalEntryComment deleted: " + entity);
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return false;
        }
    }

    @Override
    public JournalEntryComment findById(int id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery("SELECT jc FROM JournalEntryComment jc JOIN FETCH jc.user JOIN FETCH  jc.journalEntry WHERE jc.id = :id", JournalEntryComment.class).setParameter("id", id).uniqueResult();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return null;
        }
    }

    @Override
    public List<JournalEntryComment> findAll() {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery("SELECT jc FROM JournalEntryComment jc JOIN FETCH jc.user JOIN FETCH  jc.journalEntry", JournalEntryComment.class).list();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return null;
        }
    }

    public List<JournalEntryComment> findAllByJournalEntryId(int journalEntryId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery("SELECT jc FROM JournalEntryComment jc JOIN FETCH jc.user JOIN FETCH  jc.journalEntry WHERE jc.journalEntry.id = :journalEntryId", JournalEntryComment.class).setParameter("journalEntryId", journalEntryId).list();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return null;
        }
    }
}

