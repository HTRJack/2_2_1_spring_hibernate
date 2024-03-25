package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @Transactional
    public User getUserByCar(String model, int series) {
        String queryString = "from User where car.model = :model and car.series = :series";
        Query<User> queryObject = sessionFactory.getCurrentSession().createQuery(queryString);
        queryObject.setParameter("model", model);
        queryObject.setParameter("series", series);
        List<User> list = queryObject.list();
        return list.get(0);
    }
}
