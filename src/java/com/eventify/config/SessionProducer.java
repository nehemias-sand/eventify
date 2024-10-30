package com.eventify.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author nehem
 */
@ApplicationScoped
public class SessionProducer {
    
    @Inject
    private SessionFactory sessionFactory;

    @Produces
    @RequestScoped
    public Session createSession() {
        return sessionFactory.openSession();
    }

    public void closeSession(@Disposes Session session) {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}