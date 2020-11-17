package com.jasperstrijp.wolftracker.api;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class MySqlWolfContext implements WolfContext {

    private SessionFactory sessionFactory;

    public MySqlWolfContext() {
        this("hibernate.cfg.xml");
    }

    public MySqlWolfContext(String hibernateConfigFilePath){
        try {
            this.sessionFactory = new Configuration().configure(hibernateConfigFilePath).buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    @Override
    public long saveWolf(Wolf wolf) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        try (session){
            long id = (long) session.save(wolf);
            session.getTransaction().commit();

            return id;
        }
    }

    @Override
    public Wolf getWolf(long wolfId) {
        Session session = sessionFactory.getCurrentSession();

        try (session) {
            session.beginTransaction();
            return session.get(Wolf.class, wolfId);
        }
    }

    @Override
    public List<Wolf> getAllWolfs() {
        Session session = sessionFactory.getCurrentSession();

        try (session) {
            session.beginTransaction();
            return session.createQuery("from Wolf ", Wolf.class).list();
        }
    }

    @Override
    public boolean removeWolf(long wolfId) {
        Session session = sessionFactory.getCurrentSession();

        try (session){
            session.beginTransaction();
            Query query = session.createQuery("delete from Wolf where id = :id").setParameter("id", wolfId);
            query.executeUpdate();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateWolf(long wolfId, Wolf updatedWolf) {
        Session session = sessionFactory.getCurrentSession();

        try (session){
            session.beginTransaction();
            Wolf toUpdate = session.get(Wolf.class, wolfId);

            session.update(updateValues(toUpdate, updatedWolf));
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateWolfLocation(long wolfId, String location) {
        Session session = sessionFactory.getCurrentSession();

        try (session){
            Wolf toUpdate = getWolf(wolfId);
            toUpdate.location = location;

            session.beginTransaction();
            session.update(toUpdate);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private Wolf updateValues(Wolf original, Wolf updated){
        original.firstName = updated.firstName;
        original.lastName = updated.lastName;
        original.middleName = updated.lastName;
        original.birthdate = updated.birthdate;
        original.gender = updated.gender;
        original.location = updated.location;

        return original;
    }
}
