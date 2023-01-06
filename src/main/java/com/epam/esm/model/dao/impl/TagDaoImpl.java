package com.epam.esm.model.dao.impl;

import com.epam.esm.model.dao.TagDao;
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
public class TagDaoImpl implements TagDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public Tag getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Tag tag = (Tag) session.get(Tag.class,id);
        log.info("get tag from table by id");
        return tag;
    }

    @Override
    public List<Tag> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Tag> tags = session.createQuery("from tag").list();
        log.info("get all tags from table");
        return tags;
    }

    @Override
    public void insert(Tag item) {
        Session session = sessionFactory.getCurrentSession();
        session.save(item);
        log.info("tag was saved");
    }

    @Override
    public void removeById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(id);
        log.info("delete tag from table by id");
    }

    @Override
    public Map<List<GiftCertificate>, List<Tag>> getCertificatesWithTags(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query firstQuery = session.createQuery("from gift_certificate g join g.tags t  where t.name = :name");
        firstQuery.setParameter("name", name);
        List<GiftCertificate> giftCertificates = firstQuery.list();
        log.info("get certificates from table by tags name ");

        Query secondQuery = session.createQuery("from tag where name =:name");
        secondQuery.setParameter("name",name);
        List<Tag> tags = secondQuery.list();
        log.info("get tags from table by name");

        Map<List<GiftCertificate>,List<Tag>> result = new HashMap<>();
        result.put(giftCertificates,tags);
        log.info("make map with gift certificates and tags");
        return result;
    }
}
