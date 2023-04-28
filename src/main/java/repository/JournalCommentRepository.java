package repository;

import domain.JournalEntryComment;
import interfaces.IRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JournalCommentRepository implements IRepository<JournalEntryComment, Boolean> {
    private final SessionFactory sessionFactory;

    public JournalCommentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Boolean save(JournalEntryComment entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);
        return true;
    }

    @Override
    public Boolean update(JournalEntryComment entity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(entity);
        return true;
    }

    @Override
    public Boolean delete(JournalEntryComment entity) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(entity);
        return true;
    }

    @Override
    public JournalEntryComment findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT jc FROM JournalEntryComment jc  WHERE jc.id = :id", JournalEntryComment.class).setParameter("id", id).uniqueResult();
    }

    @Override
    public List<JournalEntryComment> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM JournalEntryComment jc JOIN FETCH jc.user JOIN FETCH  jc.journalEntry", JournalEntryComment.class).list();
    }

    public List<JournalEntryComment> findAllByJournalEntryId(int journalEntryId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT jc FROM JournalEntryComment jc JOIN FETCH jc.journalEntry JOIN FETCH jc.user WHERE jc.journalEntry.id = :journalEntryId", JournalEntryComment.class).setParameter("journalEntryId", journalEntryId).getResultList();
    }
}

