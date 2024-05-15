package com.example.task.dao;

import com.example.task.controller.ResourceController;
import com.example.task.entity.Resource;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;


    @Service
    @Repository
    @Transactional
    public class ResourceDao {
        private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ResourceController.class);
        @PersistenceContext
        EntityManager entityManager;


        public List<Resource> getAll(){Query query = entityManager.createQuery("SELECT r FROM Resource r");
            return query.getResultList();
        }

    @Async
    public void saveResToDB(Resource resource){
        try {
            logger.info("Zapisuję do bazy danych.Wątek: " + Thread.currentThread().getName());
            entityManager.persist(resource);
            logger.info("Zapisano do bazy. Wątek: " + Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
