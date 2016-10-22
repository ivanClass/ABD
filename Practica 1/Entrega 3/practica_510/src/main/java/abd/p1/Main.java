package abd.p1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import abd.p1.bd.Dao;
import abd.p1.controller.Controlador;
import abd.p1.model.Contesta;
import abd.p1.model.Generos;
import abd.p1.model.Opcion;
import abd.p1.model.Pregunta;
import abd.p1.model.Usuario;
import abd.p1.view.MainFrame;
import abd.p1.view.Sesion;

/**************************************
 * DUDAS
 * 2.- Clase contesta, opcion (one to one o many to one) ?¿?¿?¿
 * 3.- Ejemplo: Tenemos las entidades cuenta bancaria y persona. Una persona tiene una lista de cuentas bancarias sobre este atributo pondriamos un 'onetomany' en el lado de la cuenta bancaria
 * 		esta pertenece a una sola persona. En primer lugar, podriamos pensar que como una cuenta solo pertenece a una persona, pondríamos el atributo onetone pero como una persona puede tener varias
 * 		cuentas bancarias hay que poner 'manyToOne'. Si esto lo trasladamos a MTipoPregunta y pregunta, un mensaje pertenece a una sola pregunta, por lo que podriamos poner one to may. Pero como se puede tener
 * varios mensajes de la misma pregunta --> se puede poner 'many to one'.
 * 4.- El delete cascade de la clase usuarios de amigos nos borra todo.
 * 
 * 
 ***************************************/
/**
 * Ésta es la clase que arranca la aplicación. La ejecución del método main()
 * no reconstruirá la base de datos. La base de datos se supone ya construida
 * por el método CreateDB.main()
 *
 */
public class Main {
    
    private static SessionFactory buildSessionFactory() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            return new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
            return null;
        } 
    }
   
    
    public static void main(String[] args) {
    	SessionFactory sf = null;
    	sf = buildSessionFactory();
    	Dao dao = new Dao(sf);
    	Controlador contr =  new Controlador(dao);
    	
    	/////////////////////////////////////////////////////////////////////////////////////
    	/*Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
    	
    	
    	
    	Usuario u1 = new Usuario();
    	u1.setCorreo("dghjghjd");
    	u1.setGenero(Generos.HOMBRE);
    	u1.setFechaNacimiento(null);
    	u1.setDescripcion("d2");
    	u1.setImagenPerfil(null);
    	u1.setPsw("1222");
    	u1.setInteres(Generos.HOMBRE);
    	u1.setLatitud(1234);
    	u1.setLongitud(123);
    	u1.setNombre("f");
    	Usuario u2 = new Usuario();
    	u2.setCorreo("aghjs");
    	u2.setGenero(Generos.HOMBRE);
    	u2.setFechaNacimiento(null);
    	u2.setDescripcion("d2");
    	u2.setPsw("1222");
    	u2.setInteres(Generos.HOMBRE);
    	u2.setLatitud(45);
    	u2.setLongitud(123);
    	u2.setNombre("f");
    	Usuario u3 = new Usuario();
    	u3.setCorreo("dghjgf");
    	u3.setGenero(Generos.HOMBRE);
    	u3.setFechaNacimiento(null);
    	u3.setDescripcion("d2");
    	u3.setImagenPerfil(null);
    	u3.setPsw("1222");
    	u3.setInteres(Generos.HOMBRE);
    	u3.setLatitud(345);
    	u3.setLongitud(123);
    	u3.setNombre("f");
    	Usuario u4 = new Usuario();
    	u4.setCorreo("dsghjgjjjjf");
    	u4.setGenero(Generos.HOMBRE);
    	u4.setFechaNacimiento(null);
    	u4.setDescripcion("d2");
    	u4.setImagenPerfil(null);
    	u4.setPsw("1222");
    	u4.setInteres(Generos.HOMBRE);
    	u4.setLatitud(345);
    	u4.setLongitud(123);
    	u4.setNombre("f");
    	Usuario u5 = new Usuario();
    	u5.setCorreo("dsdggggd");
    	u5.setGenero(Generos.HOMBRE);
    	u5.setFechaNacimiento(null);
    	u5.setDescripcion("d2");
    	u5.setImagenPerfil(null);
    	u5.setPsw("1222");
    	u5.setInteres(Generos.HOMBRE);
    	u5.setLatitud(4235);
    	u5.setLongitud(123);
    	u5.setNombre("f");
  
    	
    	Opcion o11 = new Opcion();
    	Opcion o12 = new Opcion();
    	Opcion o13 = new Opcion();
    	Opcion o14 = new Opcion();
    	
    	Pregunta p1 = new Pregunta();
    	p1.setEnunciado("¿Te gustan las vacas?");
    	o11 = new Opcion();
    	o11.setNumeroOrden(1);
    	o11.setTexto("1");
    	o11.setPreguntaMadre(p1);
    	
    	o12 = new Opcion();
    	o12.setNumeroOrden(2);
    	o12.setTexto("2");
    	o12.setPreguntaMadre(p1);
    	
    	o13 = new Opcion();
    	o13.setNumeroOrden(3);
    	o13.setTexto("3");
    	o13.setPreguntaMadre(p1);
    	
    	p1.addOpcion(o11);
    	p1.addOpcion(o12);
    	p1.addOpcion(o13);
    	p1.addOpcion(o14);
    	
    	Contesta c1 = new Contesta();
    	c1.setOpcion(o12);
    	c1.setRelevancia(1);
    	c1.setUsuario(u1);
      	Contesta c2 = new Contesta();
    	c2.setOpcion(o12);
    	c2.setRelevancia(6);
    	c2.setUsuario(u2);
    	Contesta c3 = new Contesta();
    	c3.setOpcion(o11);
    	c3.setRelevancia(2);
    	c3.setUsuario(u3);
      	Contesta c4 = new Contesta();
    	c4.setOpcion(o12);
    	c4.setRelevancia(8);
    	c4.setUsuario(u4);
    	Contesta c5 = new Contesta();
    	c5.setOpcion(o14);
    	c5.setRelevancia(7);
    	c5.setUsuario(u5);
    	
    	Opcion o21 = new Opcion();
    	Opcion o22 = new Opcion();
    	
    	
    	Pregunta p2 = new Pregunta();
    	p2.setEnunciado("¿Te gusta la playa?");
    	o21 = new Opcion();
    	o21.setNumeroOrden(1);
    	o21.setTexto("1");
    	o21.setPreguntaMadre(p2);
    	
    	o22 = new Opcion();
    	o22.setNumeroOrden(2);
    	o22.setTexto("2");
    	o22.setPreguntaMadre(p2);
    	
    	p2.addOpcion(o21);
    	p2.addOpcion(o22);
    	
    	Contesta c21 = new Contesta();
    	c21.setOpcion(o22);
    	c21.setRelevancia(1);
    	c21.setUsuario(u1);
      	Contesta c22 = new Contesta();
    	c22.setOpcion(o21);
    	c22.setRelevancia(3);
    	c22.setUsuario(u2);
    	Contesta c23 = new Contesta();
    	c23.setOpcion(o21);
    	c23.setRelevancia(5);
    	c23.setUsuario(u3);
      	Contesta c24 = new Contesta();
    	c24.setOpcion(o22);
    	c24.setRelevancia(8);
    	c24.setUsuario(u4);
    	Contesta c25 = new Contesta();
    	c25.setOpcion(o22);
    	c25.setRelevancia(2);
    	c25.setUsuario(u5);
    	
    	Opcion o31 = new Opcion();
    	Opcion o32 = new Opcion();
    	Opcion o33 = new Opcion();
    	Opcion o34 = new Opcion();
    
    	
    	Pregunta p3 = new Pregunta();
    	p3.setEnunciado("¿Te gusta ser malo?");
    	o31 = new Opcion();
    	o31.setNumeroOrden(1);
    	o31.setTexto("1");
    	o31.setPreguntaMadre(p3);
    	
    	o32 = new Opcion();
    	o32.setNumeroOrden(2);
    	o32.setTexto("2");
    	o32.setPreguntaMadre(p3);
    	
    	o33 = new Opcion();
    	o33.setNumeroOrden(3);
    	o33.setTexto("3");
    	o33.setPreguntaMadre(p3);
    	
    	o34 = new Opcion();
    	o34.setNumeroOrden(4);
    	o34.setTexto("4");
    	o34.setPreguntaMadre(p3);
    	
    	p3.addOpcion(o31);
    	p3.addOpcion(o32);
    	p3.addOpcion(o33);
    	p3.addOpcion(o34);
    	
    	Contesta c31 = new Contesta();
    	c31.setOpcion(o31);
    	c31.setRelevancia(1);
    	c31.setUsuario(u1);
      	Contesta c32 = new Contesta();
    	c32.setOpcion(o34);
    	c32.setRelevancia(3);
    	c32.setUsuario(u2);
    	Contesta c33 = new Contesta();
    	c33.setOpcion(o33);
    	c33.setRelevancia(5);
    	c33.setUsuario(u3);
      	Contesta c34 = new Contesta();
    	c34.setOpcion(o33);
    	c34.setRelevancia(8);
    	c34.setUsuario(u4);
    	Contesta c35 = new Contesta();
    	c35.setOpcion(o34);
    	c35.setRelevancia(2);
    	c35.setUsuario(u5);
    	
    	Opcion o41 = new Opcion();
    	Opcion o42 = new Opcion();
    	Opcion o43 = new Opcion();
    	Opcion o44 = new Opcion();
    	
    	Pregunta p4 = new Pregunta();
    	p4.setEnunciado("¿Te gusta Harry Potter?");
    	o41 = new Opcion();
    	o41.setNumeroOrden(1);
    	o41.setTexto("1");
    	o41.setPreguntaMadre(p4);
    	
    	o42 = new Opcion();
    	o42.setNumeroOrden(2);
    	o42.setTexto("2");
    	o42.setPreguntaMadre(p4);
    	
    	o43 = new Opcion();
    	o43.setNumeroOrden(3);
    	o43.setTexto("3");
    	o43.setPreguntaMadre(p4);
    	
    	o44 = new Opcion();
    	o44.setNumeroOrden(4);
    	o44.setTexto("4");
    	o44.setPreguntaMadre(p4);
    	
    	p4.addOpcion(o41);
    	p4.addOpcion(o42);
    	p4.addOpcion(o43);
    	p4.addOpcion(o44);
    	
    	Contesta c41 = new Contesta();
    	c41.setOpcion(o41);
    	c41.setRelevancia(2);
    	c41.setUsuario(u1);
      	Contesta c42 = new Contesta();
    	c42.setOpcion(o44);
    	c42.setRelevancia(9);
    	c42.setUsuario(u2);
    	Contesta c43 = new Contesta();
    	c43.setOpcion(o44);
    	c43.setRelevancia(4);
    	c43.setUsuario(u3);
      	Contesta c44 = new Contesta();
    	c44.setOpcion(o42);
    	c44.setRelevancia(1);
    	c44.setUsuario(u4);
    	Contesta c45 = new Contesta();
    	c45.setOpcion(o41);
    	c45.setRelevancia(7);
    	c45.setUsuario(u5);
    	
    	Opcion o51 = new Opcion();
    	Opcion o52 = new Opcion();
    	
    	Pregunta p5 = new Pregunta();
    	p5.setEnunciado("¿Te gustan los hechizos?");
    	o51 = new Opcion();
    	o51.setNumeroOrden(1);
    	o51.setTexto("1");
    	o51.setPreguntaMadre(p5);
    	
    	o52 = new Opcion();
    	o52.setNumeroOrden(2);
    	o52.setTexto("2");
    	o52.setPreguntaMadre(p5);
    	
    	p5.addOpcion(o51);
    	p5.addOpcion(o52);
    	
    	Contesta c51 = new Contesta();
    	c51.setOpcion(o52);
    	c51.setRelevancia(1);
    	c51.setUsuario(u1);
      	Contesta c52 = new Contesta();
    	c52.setOpcion(o51);
    	c52.setRelevancia(3);
    	c52.setUsuario(u2);
    	Contesta c53 = new Contesta();
    	c53.setOpcion(o51);
    	c53.setRelevancia(5);
    	c53.setUsuario(u3);
      	Contesta c54 = new Contesta();
    	c54.setOpcion(o52);
    	c54.setRelevancia(8);
    	c54.setUsuario(u4);
    	Contesta c55 = new Contesta();
    	c55.setOpcion(o52);
    	c55.setRelevancia(2);
    	c55.setUsuario(u5);
    	
		
    	s.save(u1);
    	s.save(u2);
    	s.save(u3);
    	s.save(u4);
    	s.save(u5);
    	s.save(p1);
    	s.save(p2);
    	s.save(p3);
    	s.save(p4);
    	s.save(p5);
    	

    	s.save(o11);
    	s.save(o12);
    	s.save(o13);
    	s.save(o14);
    	s.save(o21);
    	s.save(o22);
    	s.save(o31);
    	s.save(o32);
    	s.save(o33);
    	s.save(o34);
    	s.save(o41);
    	s.save(o42);
    	s.save(o43);
    	s.save(o44);
    	s.save(o51);
    	s.save(o52);
    	
    	s.save(c1);
    	s.save(c2);
    	s.save(c3);
    	s.save(c4);
    	s.save(c5);
    	s.save(c21);
    	s.save(c22);
    	s.save(c23);
    	s.save(c24);
    	s.save(c25);
    	s.save(c31);
    	s.save(c32);
    	s.save(c33);
    	s.save(c34);
    	s.save(c35);
    	s.save(c41);
    	s.save(c42);
    	s.save(c43);
    	s.save(c44);
    	s.save(c45);
    	s.save(c51);
    	s.save(c52);
    	s.save(c53);
    	s.save(c54);
    	s.save(c55);
    	
 

    	tx.commit();*/
    	
  
    	
    	////////////////////////////////////////////////////////////////////////////////////
    	
    	//Sesion iniSesVentana = new Sesion(contr);
    	MainFrame ventana = new MainFrame(contr);
    	ventana.setVisible(true);
    	DateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:");
    	
    	System.out.println(new Date());
    	/*
        SessionFactory sf = null;
        
        try {
            sf = buildSessionFactory();
            Session s = sf.openSession();
            Transaction tx = null;
            try {
                tx = s.beginTransaction();
                //do some work
                //Aficion af = new Aficion("hola");
                
                
                Usuario u7 = (Usuario) s.get(Usuario.class, "ivanag01@ucm.es");
                Aficion af =new Aficion(u7, "gsdgsdg");
            	EditarUsuario eu = new EditarUsuario(new Controlador(u7));
            	u7.setNombre("Ivaaaaaaaaaaaan");
            	
            	//u7.setListaAficiones(u7.getListaAficiones());
            	u7.getListaAficiones().add(af);
            	u7.setDescripcion("BLALBLALBALB");
            	eu.setVisible(true);
            	
                s.save(u7);
                
                tx.commit();
            }
            catch (Exception e) {
                if (tx!=null) tx.rollback();
                throw e;
            }
            finally {
                s.close();
            }
            // Mostrar ventana de login y comprobar validez del usuario y contraseña.
            // Si son validos, mostrar ventana principal.
            


        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (sf != null) sf.close();
        }
    	//EditarUsuario eu = new EditarUsuario(new Controlador(new Usuario()));
    	//eu.setVisible(true);
    	 * 
    	 */
    }

}
