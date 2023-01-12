package com.hibernate.introduction;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.introduction.model.Persona;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // Crear objeto que permita fabricar sesiones
        SessionFactory factory = new Configuration().configure("cfg.xml").addAnnotatedClass(Persona.class).buildSessionFactory();

        // Abrir sesiion
        Session session = factory.openSession();

        // Preparar la sesion para realizar transacciones (crear actualizar eliminar)
        session.beginTransaction();

        // Realizar transacciones...
        // Pueden generar errores  por malas consultas, metodos etc usamos try catch
        try {

            /*****CRUD******/
            // Crear  objeto persona
                /* Persona objPersona = new Persona("Andrea", "Hernandez", "andrea@gmail.com", new Date(), "http://fake-photo");
                // Preparar el objeto para guardarlo
                session.persist(objPersona);
                // Guardar en la base de datos
                session.getTransaction().commit(); */

            // READ
                //Obtener la persona por id
                    //Persona persona = session.find(Persona.class, 2); //me lee entityclass y primary Key 
                    //Persona persona = session.byId(Persona.class).load(1);
                    //System.out.println(persona);
                // Si queremos obtener a todas las personas podemos ejecutar queries
                    List<Persona> personas = session.createQuery("from Persona", Persona.class).list();
                // Iterar la lista que se esta obteniendo
                    for (int i = 0; i < personas.size();i++){
                        System.out.println(personas.get(i));
                    }


        } catch (Exception e) {
            e.printStackTrace();
        }

        // Cerrar sesión
        session.close();
    }
}
