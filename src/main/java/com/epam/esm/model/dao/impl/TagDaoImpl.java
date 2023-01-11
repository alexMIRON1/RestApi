package com.epam.esm.model.dao.impl;

import com.epam.esm.model.dao.TagDao;
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
public class TagDaoImpl implements TagDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public Tag getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Tag tag = session.get(Tag.class,id);
        log.info("get tag " + tag.getName() + " from table by id " + id );
        return tag;
    }

    @Override
    public List<Tag> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Tag> tags = session.createQuery("from tag").list();
        log.info("get all tags " + tags + " from table");
        return tags;
    }

    @Override
    public void insert(Tag item) {
        Session session = sessionFactory.getCurrentSession();
        session.save(item);
        log.info("tag " + item + " was saved");
    }

    @Override
    public void remove(Tag item) {
        Session session = this.sessionFactory.getCurrentSession();
        session.createQuery("delete from tag t where t.id= :id ").setParameter("id",item.getId())
                .executeUpdate();
        log.info("delete tag " + item + " from table");
    }

    @Override
    public Map<List<GiftCertificate>, List<Tag>> getCertificatesWithTags(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query<GiftCertificate> firstQuery = session.createQuery("select g from gift_certificate g join g.tags t  where t.name = :name");
        firstQuery.setParameter("name", name);
        List<GiftCertificate> giftCertificates = firstQuery.list();
        log.info("get certificates " + giftCertificates + " from table by tags name " + name);

        Query<Tag> secondQuery = session.createQuery("from tag where name =:name");
        secondQuery.setParameter("name",name);
        List<Tag> tags = secondQuery.list();
        log.info("get tags " + tags + " from table by name" + name);

        Map<List<GiftCertificate>,List<Tag>> result = new HashMap<>();
        result.put(giftCertificates,tags);
        log.info("make map " + result + " with gift certificates and tags");
        return result;
    }
}
