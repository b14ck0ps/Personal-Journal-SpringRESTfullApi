package repository;

import domain.User;
import interfaces.IRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository implements IRepository<User, Boolean> {

    private final SessionFactory sessionFactory;

    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Boolean save(User entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);
        return true;
    }

    @Override
    public Boolean update(User entity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(entity);
        return true;
    }

    @Override
    public Boolean delete(User entity) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(entity);
        return true;
    }

    @Override
    public User findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User", User.class).list();
    }

    public User findByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User where username = :username", User.class).setParameter("username", username).uniqueResult();
    }
}

