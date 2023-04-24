package repository;

import DTOs.JournalEntryTagDto;
import domain.JournalEntryTag;
import interfaces.IRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JournalEntryTagsRepository implements IRepository<JournalEntryTag, Boolean> {

    private final SessionFactory sessionFactory;

    public JournalEntryTagsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Boolean save(JournalEntryTag entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);
        return true;
    }

    @Override
    public Boolean update(JournalEntryTag entity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(entity);
        return true;
    }

    @Override
    public Boolean delete(JournalEntryTag entity) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(entity);
        return true;
    }

    @Override
    public JournalEntryTag findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT j FROM JournalEntryTag j WHERE j.id = :id", JournalEntryTag.class).setParameter("id", id).uniqueResult();
    }

    @Override
    public List<JournalEntryTag> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT j FROM JournalEntryTag j", JournalEntryTag.class).list();
    }

    public List<JournalEntryTagDto> findAllTags() {
        Session session = sessionFactory.getCurrentSession();
        List<JournalEntryTagDto> result = session.createQuery(
                "SELECT NEW DTOs.JournalEntryTagDto(j.id, j.journalEntry.id, j.tag.id) FROM JournalEntryTag j",
                JournalEntryTagDto.class
        ).getResultList();
        return result;
    }



    public List<JournalEntryTagDto> findByJournalEntryId(int id) {
        Session session = sessionFactory.getCurrentSession();
        List<JournalEntryTagDto> result = session.createQuery(
                        "SELECT new DTOs.JournalEntryTagDto(j.id, j.journalEntry.id, j.tag.id) FROM JournalEntryTag j WHERE j.journalEntry.id = :id",
                        JournalEntryTagDto.class
                )
                .setParameter("id", id)
                .list();
        return result;
    }


}
