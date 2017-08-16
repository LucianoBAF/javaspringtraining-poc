package com.sap.javaspringtrainingpoc.daos.impls;

import com.sap.javaspringtrainingpoc.daos.VoteDao;
import com.sap.javaspringtrainingpoc.models.Restaurant;
import com.sap.javaspringtrainingpoc.models.User;
import com.sap.javaspringtrainingpoc.models.VoteHistory;
import com.sap.javaspringtrainingpoc.services.VoteService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by I863409 on 08/08/2017.
 */
public class DefaultVoteDao implements VoteDao{
    @Resource
    private SessionFactory sessionFactory;


    @Override
    public void addRestaurantToPoll(Restaurant restaurant) {
        Session session = sessionFactory.getCurrentSession();

        VoteHistory voteHistory = new VoteHistory();
        voteHistory.setDate(LocalDate.now());
        voteHistory.setRestaurant(restaurant);
        voteHistory.setUser(null);

        session.save(voteHistory);
    }

    @Override
    public void addUserVoteToRestaurant(User user, Restaurant restaurant) {
        Session session = sessionFactory.getCurrentSession();

        VoteHistory voteHistory = new VoteHistory();
        voteHistory.setDate(LocalDate.now());
        voteHistory.setRestaurant(restaurant);
        voteHistory.setUser(user);

        session.save(voteHistory);
    }

    @Override
    public void removeUserVoteToRestaurant(User user, Restaurant restaurant) {
        Session session = sessionFactory.getCurrentSession();
        int restaurantId = restaurant.getId();

        DetachedCriteria dc = DetachedCriteria
                .forClass(VoteHistory.class , "v")
                .add(Restrictions.eq("date",LocalDate.now()))
                        .createAlias("v.user", "vu")
                        .add(Restrictions.eq("vu.id",user.getId()))
                                .createAlias("v.restaurant", "vr")
                                .add(Restrictions.eq("vr.id",restaurantId));


        VoteHistory voteHistory = (VoteHistory) dc.getExecutableCriteria(session).uniqueResult();

        session.delete(voteHistory);

//        voteHistory.getUsers().remove(user);
//        session.update(voteHistory);
    }

    @Override
    public List<VoteHistory> getCompleteVoteHistory() {
        try(Session session = sessionFactory.openSession()){
            DetachedCriteria criteria = DetachedCriteria.forClass(VoteHistory.class);
            criteria.addOrder(Order.asc("date"));

            return criteria.getExecutableCriteria(session).list();
        }
    }

    @Override
    public List<VoteHistory> getTodayVoteHistory() {
        try(Session session = sessionFactory.openSession()){

            DetachedCriteria criteria = DetachedCriteria.forClass(VoteHistory.class);
            criteria.add(Restrictions.like("date",LocalDate.now()));

            return criteria.getExecutableCriteria(session).list();
            //return todayVoteHistory;
        }
    }

    @Override
    public List<VoteHistory> getRestaurantVoteHistory(int restaurantId) {
        try(Session session = sessionFactory.openSession()){
            DetachedCriteria criteria = DetachedCriteria.forClass(VoteHistory.class);
            criteria.addOrder(Order.asc("date"))
                    .createCriteria("restaurant")
                    .add(Restrictions.like("id",restaurantId));

            return criteria.getExecutableCriteria(session).list();
            //return voteHistory;
        }
    }

    @Override
    public List<VoteHistory> getUserVoteHistory(int userId) {
        try(Session session = sessionFactory.openSession()){
            DetachedCriteria criteria = DetachedCriteria.forClass(VoteHistory.class);
            //criteria.add(Restrictions.in("users",String.valueOf(userId)));
            criteria.createCriteria("user")
                    .add(Restrictions.like("id",String.valueOf(userId)));

            return criteria.getExecutableCriteria(session).list();
            //return voteHistory;
        }
    }

    @Override
    public List<User> getRestaurantVotersToday(int restaurantId) {
        try(Session session = sessionFactory.openSession()){
            DetachedCriteria criteria = DetachedCriteria.forClass(VoteHistory.class);
            criteria.createCriteria("restaurant")
                    .add(Restrictions.like("id",String.valueOf(restaurantId)));
            criteria.add(Restrictions.like("date",LocalDate.now()));

            List<VoteHistory> voteHistory = (List<VoteHistory>) criteria.getExecutableCriteria(session).list();

            List<User> userList = null;
            for(VoteHistory vote : voteHistory){
                userList.add(vote.getUser());
            }

            return userList;
        }
    }
}
