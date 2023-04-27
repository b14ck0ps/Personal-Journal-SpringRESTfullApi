package repository;

import domain.Tag;
import interfaces.IRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagRepository implements IRepository<Tag, Boolean> {
    private final SessionFactory sessionFactory;

    public TagRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Boolean save(Tag entity) {
        return null;
    }

    @Override
    public Boolean update(Tag entity) {
        return null;
    }

    @Override
    public Boolean delete(Tag entity) {
        return null;
    }

    @Override
    public Tag findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Tag.class, id);
    }

    @Override
    public List<Tag> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Tag", Tag.class).list();
    }
}
