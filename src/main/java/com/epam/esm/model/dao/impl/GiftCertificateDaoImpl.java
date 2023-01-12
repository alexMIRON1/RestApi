package com.epam.esm.model.dao.impl;

import com.epam.esm.model.dao.GiftCertificateDao;
import com.epam.esm.model.entity.GiftCertificate;
import com.epam.esm.model.entity.Tag;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
        GiftCertificate giftCertificate = session.get(GiftCertificate.class,id);
        log.info("get giftCertificate " + giftCertificate.getName() + " by id from table -> " + id);
        return giftCertificate;
    }

    @Override
    public List<GiftCertificate> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<GiftCertificate> giftCertificates = session.createQuery(" from gift_certificate").list();
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
        session.createQuery("delete from gift_certificate g where g.id= :id ")
                .setParameter("id",item.getId()).executeUpdate();
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
        Query<Tag> firstQuery = session.createQuery("select t from tag t join t.certificates c where c.description like :description");
        firstQuery.setParameter("description","%"+description+"%");
        List<Tag> tags = firstQuery.list();
        log.info("get " + tags + " by certificate's description -> " + description);

        Query<GiftCertificate> secondQuery = session.createQuery("select g from gift_certificate g  where description like :description");
        secondQuery.setParameter("description","%"+description+"%");
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
                createQuery("select t from tag t join t.certificates c order by c.createDate asc").list();
        log.info("get tags order by certificate's create date - > " + tags);

        List<GiftCertificate> certificates = session.
                createQuery("select c from gift_certificate c order by c.createDate asc").list();
        log.info("get certificates order by certificate's create date -> " + certificates);
        Map<List<GiftCertificate>, List<Tag>> result = new HashMap<>();
        result.put(certificates,tags);
        log.info("make map " + result + "with gift certificates and tags");
        return result;
    }
}
