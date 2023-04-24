package repository;

import domain.JournalEntry;
import interfaces.IRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JournalEntryRepository implements IRepository<JournalEntry, Boolean> {
    private final SessionFactory sessionFactory;

    public JournalEntryRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Boolean save(JournalEntry entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);
        return true;
    }

    @Override
    public Boolean update(JournalEntry entity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(entity);
        return true;
    }

    @Override
    public Boolean delete(JournalEntry entity) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(entity);
        return true;
    }

    @Override
    public JournalEntry findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(JournalEntry.class, id);
    }

    @Override
    public List<JournalEntry> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from JournalEntry", JournalEntry.class).list();
    }

    public List<JournalEntry> findAllByUserName(String username) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from JournalEntry where JournalEntry.user.username = :username", JournalEntry.class)
                .setParameter("username", username)
                .list();
    }
}
