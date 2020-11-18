package com.jasperstrijp.wolftracker.api;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MySqlPackContext implements PackContext {

    private SessionFactory sessionFactory;

    public MySqlPackContext() {
        this("hibernate.cfg.xml");
    }

    public MySqlPackContext(String hibernateConfigFilePath){
        try {
            this.sessionFactory = new Configuration().configure(hibernateConfigFilePath).buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    @Override
    public long savePack(Pack pack) {
        Session session = sessionFactory.getCurrentSession();

        try (session){
            session.beginTransaction();
            long id = (long) session.save(pack);
            session.getTransaction().commit();

            return id;
        }
    }

    @Override
    public boolean removeWolfFromPack(long packId, long wolfId) {
        Session session = sessionFactory.getCurrentSession();

        try (session){
            session.beginTransaction();
            Pack pack = session.get(Pack.class, packId);
            pack.getMembers().removeIf(wolf -> wolf.id == wolfId);

            session.getTransaction().commit();
            return true;
        } catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean addWolfToPack(long packId, long wolfId) {
        Session session = sessionFactory.getCurrentSession();

        try (session){
            session.beginTransaction();
            Pack pack = session.get(Pack.class, packId);
            Wolf wolf = session.get(Wolf.class, wolfId);

            pack.getMembers().add(wolf);
            session.getTransaction().commit();
            return true;
        } catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Pack> getAllPacks() {
        Session session = sessionFactory.getCurrentSession();

        try (session){
            session.beginTransaction();
            return session.createQuery("from Pack ", Pack.class).list();
        }
    }

    @Override
    public Pack getPackById(long packId) {
        Session session = sessionFactory.getCurrentSession();

        try (session){
            session.beginTransaction();
            return session.find(Pack.class, packId);
        }
    }
}
