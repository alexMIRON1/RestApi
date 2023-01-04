package com.epam.esm.model.dao.impl;

import com.epam.esm.model.dao.GiftCertificateDao;
import com.epam.esm.model.entity.GiftCertificate;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional
@Slf4j
public class GiftCertificateDaoImpl implements GiftCertificateDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public GiftCertificate getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        GiftCertificate giftCertificate = (GiftCertificate) session.get(GiftCertificate.class,id);
        log.info("got giftCertificate by id from table");
        return giftCertificate;
    }

    @Override
    public List<GiftCertificate> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<GiftCertificate> giftCertificates = session.createQuery("from gift_certificate").list();
        log.info("got  list of giftCertificate from table");
        return giftCertificates;
    }

    @Override
    public void insert(GiftCertificate item) {
        Session session = sessionFactory.getCurrentSession();
        session.save(item);
        log.info("successfully created giftCertificate in table");
    }

    @Override
    public void removeById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(id);
        log.info("successfully deleted giftCertificate from table");
    }

    @Override
    public void update(GiftCertificate item) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(item);
        log.info("successfully updated giftCertificate's information in table");
    }
}
