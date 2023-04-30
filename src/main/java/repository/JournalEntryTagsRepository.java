package repository;

import DTOs.JournalEntryTagDto;
import domain.JournalEntryTag;
import interfaces.IRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

@Repository
public class JournalEntryTagsRepository implements IRepository<JournalEntryTag, Boolean> {

    private Logger logger = Logger.getLogger(JournalEntryTagsRepository.class.getName());

    private final SessionFactory sessionFactory;

    public JournalEntryTagsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Boolean save(JournalEntryTag entity) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(entity);
            logger.info("JournalEntryTag saved: " + entity);
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean update(JournalEntryTag entity) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.update(entity);
            logger.info("JournalEntryTag updated: " + entity);
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean delete(JournalEntryTag entity) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.delete(entity);
            logger.info("JournalEntryTag deleted: " + entity);
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return false;
        }
    }

    @Override
    public JournalEntryTag findById(int id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery("SELECT j FROM JournalEntryTag j WHERE j.id = :id", JournalEntryTag.class).setParameter("id", id).uniqueResult();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return null;
        }
    }

    @Override
    public List<JournalEntryTag> findAll() {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery("SELECT j FROM JournalEntryTag j", JournalEntryTag.class).list();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return null;
        }
    }

    public List<JournalEntryTagDto> findAllTags() {
        try {
            Session session = sessionFactory.getCurrentSession();
            List<JournalEntryTagDto> result = session.createQuery(
                    "SELECT NEW DTOs.JournalEntryTagDto(j.id, j.journalEntry.id, j.tag.id) FROM JournalEntryTag j",
                    JournalEntryTagDto.class
            ).getResultList();
            return result;
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return null;
        }
    }


    public List<JournalEntryTagDto> findByJournalEntryId(int id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            List<JournalEntryTagDto> result = session.createQuery(
                            "SELECT new DTOs.JournalEntryTagDto(j.id, j.journalEntry.id, j.tag.id) FROM JournalEntryTag j WHERE j.journalEntry.id = :id",
                            JournalEntryTagDto.class
                    )
                    .setParameter("id", id)
                    .list();
            return result;
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return null;
        }
    }
}