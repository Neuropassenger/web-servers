package dbService.dao;

import dbService.datasets.UserDataSet;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Created by turge on 007 07.12.16.
 */
public class UsersDAO {
    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UserDataSet getById(long id) {
        return (UserDataSet) session.load(UserDataSet.class, id);
    }

    public UserDataSet getByName(String name) {
        Criteria criteria = session.createCriteria(UserDataSet.class);
        return (UserDataSet) criteria
                .add(Restrictions.eq("login", name))
                .uniqueResult();
    }

    public void insertUser(String name, String password) {
        Transaction trx = session.beginTransaction();
        session.save(new UserDataSet(name, password));
        trx.commit();
    }
}
