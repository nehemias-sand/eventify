package com.eventify.config;

import com.eventify.entity.Butaca;
import com.eventify.entity.CategoriaButaca;
import com.eventify.entity.CategoriaEvento;
import com.eventify.entity.EstadoEvento;
import com.eventify.entity.EstadoPago;
import com.eventify.entity.Evento;
import com.eventify.entity.Genero;
import com.eventify.entity.Instalacion;
import com.eventify.entity.Organizador;
import com.eventify.entity.Pago;
import com.eventify.entity.Cliente;
import com.eventify.entity.Reserva;
import com.eventify.entity.ReservaButaca;
import com.eventify.entity.Rol;
import com.eventify.entity.Usuario;
import java.util.Properties;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author nehem
 */
@ApplicationScoped
public class HibernateConfig {

    @Produces
    @ApplicationScoped
    public SessionFactory getSessionFactory() {
        try {
            Configuration configuration = new Configuration();

            // Configuración de Hibernate
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            settings.put(Environment.URL, "jdbc:mysql://localhost:3306/eventify?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=America/El_Salvador");
            settings.put(Environment.USER, "root");
            settings.put(Environment.PASS, "sa");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            settings.put(Environment.HBM2DDL_AUTO, "update");

            configuration.setProperties(settings);

            // Clases de entidad
            configuration.addAnnotatedClass(Usuario.class);
            configuration.addAnnotatedClass(Rol.class);
            configuration.addAnnotatedClass(Genero.class);
            configuration.addAnnotatedClass(Cliente.class);
            configuration.addAnnotatedClass(Reserva.class);
            configuration.addAnnotatedClass(Evento.class);
            configuration.addAnnotatedClass(CategoriaEvento.class);
            configuration.addAnnotatedClass(EstadoEvento.class);
            configuration.addAnnotatedClass(Instalacion.class);
            configuration.addAnnotatedClass(Organizador.class);
            configuration.addAnnotatedClass(Butaca.class);
            configuration.addAnnotatedClass(CategoriaButaca.class);
            configuration.addAnnotatedClass(ReservaButaca.class);
            configuration.addAnnotatedClass(Pago.class);
            configuration.addAnnotatedClass(EstadoPago.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            return configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException e) {
            throw new RuntimeException("Error al crear SessionFactory: " + e.getMessage());
        }
    }

}
