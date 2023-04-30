package repository;

import domain.User;
import interfaces.IRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

@Repository
public class UserRepository implements IRepository<User, Boolean> {
    private Logger logger = Logger.getLogger(getClass().getName());
    private final SessionFactory sessionFactory;

    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Boolean save(User entity) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(entity);
            logger.info("User saved successfully, User Details=" + entity);
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean update(User entity) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.update(entity);
            logger.info("User updated successfully, User Details=" + entity);
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean delete(User entity) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.delete(entity);
            logger.info("User deleted successfully, User Details=" + entity);
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return false;
        }
    }

    @Override
    public User findById(int id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            User user = session.get(User.class, id);
            logger.info("User loaded successfully, User details=" + user);
            return user;
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        try {
            Session session = sessionFactory.getCurrentSession();
            List<User> users = session.createQuery("from User", User.class).list();
            for (User user : users) {
                logger.info("User List::" + user);
            }
            return users;
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return null;
        }
    }

    public User findByUsername(String username) {
        try {
            Session session = sessionFactory.getCurrentSession();
            User user = session.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class).setParameter("username", username).uniqueResult();
            logger.info("User loaded successfully, User details=" + user);
            return user;
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return null;
        }
    }
}

