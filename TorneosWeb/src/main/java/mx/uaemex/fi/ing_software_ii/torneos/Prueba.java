/*
 * @José Juan García Romero
 * @Luis Angel Rocha Ronquillo
 * @Jesús Alberto Sanchez Mendieta
 * @Isaac Misael Vazquez Albor
 * @Francisco Gamaliel Alvaro Portillo
 * 
 * */
package mx.uaemex.fi.ing_software_ii.torneos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import mx.uaemex.fi.ing_software_ii.torneos.dao.derby.JugadorDaoDerby;
import mx.uaemex.fi.ing_software_ii.torneos.dao.derby.JugadorDaoDerbyImp;
import mx.uaemex.fi.ing_software_ii.torneos.dao.dto.Jugador;

public class Prueba {

    public static void main(String[] args) {
        Prueba prueba = new Prueba();
        prueba.execute();
    }

 // ...

 // ...

    public void execute() {
        Connection con = null;

        // Configuración de los datos de conexión
        String jdbcUrl = "jdbc:derby://localhost:1527/venationes";

        JugadorDaoDerby dao;
        JugadorDaoDerby dao2;
        JugadorDaoDerbyImp realDao;
        Jugador jugadorDB = new Jugador();

        String auxNC = null;

        String nombre = "Luis";
        String primerApellido = "Rocha";
        String segundoApellido = "Ronquillo";
        String fechaNacimiento = "2023-12-09";
        int numCuenta = 2021004;
        String correo = "luis@gmail.com";
        String usuario = "luisillopillo";
        String contrasena = "lalalala";

        try {
            // Cargar el controlador JDBC (driver)
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            // Obtener la conexión
            con = DriverManager.getConnection(jdbcUrl);

            realDao = new JugadorDaoDerbyImp();
            realDao.setCon(con);
            dao = realDao;
            dao2 = realDao;

            Jugador jugador = new Jugador();
            jugador.setNumCuenta(numCuenta);

            // Verificar si el jugador ya está registrado
            dao.get(jugador);
            auxNC = jugador.getUsuario();

            if (auxNC != null) {
                System.out.println("JUGADOR YA REGISTRADO");
            } else {
                // El jugador no está registrado, entonces lo registramos
                jugadorDB.setNombre(nombre);
                jugadorDB.setPrimerApellido(primerApellido);
                jugadorDB.setSegundoApellido(segundoApellido);
                jugadorDB.setFechaNacimiento(fechaNacimiento);
                jugadorDB.setNumCuenta(numCuenta);
                jugadorDB.setCorreo(correo);
                jugadorDB.setUsuario(usuario);
                jugadorDB.setContrasena(contrasena);

                // Verificar que la conexión esté abierta antes de ejecutar la inserción
                
                    dao2.create(jugadorDB);
                    System.out.println("JUGADOR SE REGISTRO");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
    }

}
