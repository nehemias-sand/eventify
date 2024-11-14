package com.eventify.dao.implementations;

import com.eventify.dao.PagoDao;
import com.eventify.entity.Pago;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nehem
 */
@ApplicationScoped
public class PagoDaoImpl implements PagoDao {

    @Inject
    private Session session;

    @Override
    public void save(Pago pago) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(pago);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw e;
        }
    }
}
