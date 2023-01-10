package com.epam.esm.model.dao.impl;

import com.epam.esm.model.dao.GiftCertificateDao;
import com.epam.esm.model.entity.GiftCertificate;
import com.epam.esm.model.entity.Tag;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        log.info("get giftCertificate " + giftCertificate.getName() + " by id from table -> " + id);
        return giftCertificate;
    }

    @Override
    public List<GiftCertificate> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<GiftCertificate> giftCertificates = session.createQuery("from gift_certificate").list();
        log.info("got list of giftCertificate from table " + giftCertificates);
        return giftCertificates;
    }

    @Override
    public void insert(GiftCertificate item) {
        Session session = sessionFactory.getCurrentSession();
        session.save(item);
        log.info("successfully created giftCertificate in table " + item.getName());
    }

    @Override
    public void remove(GiftCertificate item) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(item);
        log.info("successfully deleted giftCertificate from table -> " + item.getName());
    }

    @Override
    public void update(GiftCertificate item) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(item);
        log.info("successfully updated giftCertificate's information in table -> " + item.getName());
    }

    @Override
    public Map<List<GiftCertificate>, List<Tag>> getCertificatesWithTagsByPartOfDescription(String description) {
        Session session = sessionFactory.getCurrentSession();
        Query firstQuery = session.createQuery("from tag t join t.certificates c where c.description like ?");
        firstQuery.setString(0,"%"+description+"%");
        List<Tag> tags = firstQuery.list();
        log.info("get " + tags + " by certificate's description -> " + description);

        Query secondQuery = session.createQuery("from gift_certificate where description like ?");
        secondQuery.setString(0,"%"+description+"%");
        List<GiftCertificate> giftCertificates = secondQuery.list();
        log.info("get " + giftCertificates+  " by certificate's description -> " + description);

        Map<List<GiftCertificate>, List<Tag>> result = new HashMap<>();
        result.put(giftCertificates,tags);
        log.info("make map " + result +  " with gift certificates and tags");
        return result;
    }

    @Override
    public Map<List<GiftCertificate>, List<Tag>> getCertificatesWithTagsSortByCreateDateASC() {
        Session session = sessionFactory.getCurrentSession();
        List<Tag> tags =  session.
                createQuery("from tag t join t.certificates c order by c.create_date asc").list();
        log.info("get tags order by certificate's create date - > " + tags);

        List<GiftCertificate> certificates = session.
                createQuery("from gift_certificate c order by c.create_date asc").list();
        log.info("get certificates order by certificate's create date -> " + certificates);
        Map<List<GiftCertificate>, List<Tag>> result = new HashMap<>();
        result.put(certificates,tags);
        log.info("make map " + result + "with gift certificates and tags");
        return result;
    }
}
