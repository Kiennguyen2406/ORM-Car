package com.example.ormcar.service;

import com.example.ormcar.model.Car;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

@Service
public class CarService implements ICarService {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Car> findAll() {
        String queryStr = "SELECT c FROM Car AS c";
        TypedQuery<Car> query = entityManager.createQuery(queryStr, Car.class);
        return query.getResultList();
    }

    @Override
    public void save(Car car) {
        Transaction transaction = null;
        Car origin;
        if (car.getId() == 0) {
            origin = new Car();
        } else {
            origin = findById(car.getId());
        }
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            origin.setName(car.getName());
            origin.setDescription(car.getDescription());
            origin.setCode(car.getCode());
            origin.setProducer(car.getProducer());
            origin.setPrice(car.getPrice());
            origin.setImg(car.getImg());
            session.saveOrUpdate(origin);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }

        }


    }

    @Override
    public Car findById(int id) {
        String queryStr = "SELECT c FROM Car AS c WHERE c.id = :id";
        TypedQuery<Car> query = entityManager.createQuery(queryStr, Car.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void remote(int id) {
        Car car = findById(id);
        if (car != null) {
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.remove(car);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }
    @Override
    public List<Car> SearchByName(String name) {
        String queryStr = "SELECT c FROM Car AS c WHERE c.name = :name";
        TypedQuery<Car> query = entityManager.createQuery(queryStr, Car.class);
        query.setParameter("name", name);
        return query.getResultList();
    }
}
