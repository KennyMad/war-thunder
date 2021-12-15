package com.example.warThunder.repository.impl;

import com.example.warThunder.model.User;
import com.example.warThunder.repository.UserDao;
import com.example.warThunder.repository.comparator.NameComparator;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Comparator;


@Repository
@Slf4j
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public User getUserByUsername(String username) {
        log.info("Поиск пользователя по имени " + username);
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(getEntityClass());
        Root<User> root = query.from(getEntityClass());
        query.select(root).where(builder.equal(root.get("name"), username));
        return getEntityManager().createQuery(query).getSingleResult();
    }

    @Override
    public User getUserByNamePass(User user) {
        log.info("Поиск пользователя по имени и паролю с именем " + user.getName());
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(getEntityClass());
        Root<User> root = query.from(getEntityClass());
        Predicate usernamePredicate = builder.equal(root.get("name"), user.getName());
        Predicate passPredicate = builder.equal(root.get("password"), user.getPassword());
        query.select(root).where(builder.and(usernamePredicate, passPredicate));
        return getEntityManager().createQuery(query).getSingleResult();
    }

    @Override
    @Transactional
    public boolean isUsernameExist(String username) {
        log.info("Проверка на существование пользователя с именем: " + username);
        String queryString = "select count(id) from User where name = :name";
        return (Long)getEntityManager()
                .createQuery(queryString)
                .setParameter("name", username)
                .getSingleResult() > 0;
    }

    @Override
    public List<User> getSortByName() {
        log.info("Сортировка пользователей по имени");
        return getEntityManager().createQuery("SELECT user FROM User user ORDER BY user.name").getResultList();
    }

    @Override
    public List<User> getUsersWithTurnNumber(int turnNumber) {
        String queryString = "SELECT DISTINCT user FROM Movement mov, User user WHERE mov.turnNumber = :x and mov.userId = user.id";
        return getEntityManager().createQuery(queryString).setParameter("x", turnNumber).getResultList();
    }
}
