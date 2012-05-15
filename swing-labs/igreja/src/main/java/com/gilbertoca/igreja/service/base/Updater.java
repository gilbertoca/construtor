package com.gilbertoca.igreja.service.base;

import java.util.Vector;

public class Updater extends BaseService {

    public static final int VERSION = 100;

    public static void exe() {
        (new Updater()).execute();
    }

    public void execute() {
        String hql = "SELECT numero FROM version;";
        Integer version = 0;
        try {
            version = (Integer) ((Vector) getEntityManager().createNativeQuery(hql).getSingleResult()).get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (version == null || version < 1) {
            //script1();
        }
        
        /*
        if (version == null || version < 2) {
            script2();
        }

        if (version == null || version < 3) {
            script3();
        }

        if (version == null || version < 4) {
            script4();
        }

        if (version == null || version < 5) {
            script5();
        }
        */
        
    }
    
    /**********************  Actualizacion V 1.5   *********************/
    private void script5() {
        String hql = "";
        getEntityManager().getTransaction().begin();

        //Actualizo la version
        hql = "UPDATE gimnasio.version SET numero=5;";
        getEntityManager().createNativeQuery(hql).executeUpdate();

        getEntityManager().getTransaction().commit();
    }

    /**********************  Actualizacion V 1.4   *********************

    //Detalle
    1)Agregar a la tabla caja los campos monto_inicial, monto_final de tipo FLOAT; cerrada de tipo BOOLEAN y fecha_creacion de tipo DATETIME.
    2)Agregar las funciones de las nuevas funcionalidades y agregarlas a traves del sistema al usuario admin.
    3)Agregar el campo fecha en la tabla pago_de_sueldos con el tipo DATETIME
    4)Convertir el campo fecha de la tabla movimientos_caja de DATE a DATETIME
    5)Agregar la columna de borrado para le pago sueldo
    6)Agregar las cosas para los movimientos de Especial

     * //SQL
    1)
    ALTER TABLE `gimnasio`.`caja` ADD COLUMN `monto_inicial` FLOAT AFTER `BORRADO`;
    ALTER TABLE `gimnasio`.`caja` ADD COLUMN `monto_final` FLOAT AFTER `monto_inicial`;
    ALTER TABLE `gimnasio`.`caja` ADD COLUMN `cerrada` BOOLEAN DEFAULT 0 AFTER `monto_final`;
    ALTER TABLE `gimnasio`.`caja` ADD COLUMN `fecha_creacion` DATETIME AFTER `cerrada`;
    2)
    insert into gimnasio.accion values (10, 0, 'CALC_SUE', 'Calcular el sueldo de un profesor');
    insert into gimnasio.accion values (11, 0, 'REGP_SUE', 'Registrar el pago de sueldo de un profesor');
    3)
    ALTER TABLE `gimnasio`.`pago_de_sueldos` ADD COLUMN `fecha` DATETIME NOT NULL AFTER `hs_trabajadas`;
    4)
    ALTER TABLE `gimnasio`.`movimientos_caja` MODIFY COLUMN `FECHA` DATETIME DEFAULT NULL;
    5)
    ALTER TABLE `gimnasio`.`pago_de_sueldos` ADD COLUMN `borrado` BOOLEAN NOT NULL DEFAULT 0 AFTER `fecha`;
    6)
    ALTER TABLE `clases_especiales` CHANGE `monto_por_hora_pago_profesor` `monto_para_profesor` BIGINT( 20 ) NOT NULL;
    ALTER TABLE `clases_especiales` CHANGE `monto_por_hora_pago_profesor` `monto_para_profesor` BIGINT( 20 ) NOT NULL;
    CREATE TABLE `clases_especiales_profesores` (
    `id_clase` BIGINT( 20 ) NOT NULL ,
    `id_profesor` BIGINT( 20 ) NOT NULL
    ) TYPE = MYISAM ;
    ALTER TABLE `movimientos_caja` ADD `ALUMNO_id_alumno` BIGINT( 20 ) ;
    ALTER TABLE `movimientos_caja` ADD `CLASEESPECIAL_id_clase` BIGINT( 20 ) ;
     */

    private void script4() {
        String hql = "";
        getEntityManager().getTransaction().begin();
        
        //Actualizo la version
        hql = "UPDATE gimnasio.version SET numero=4;";
        getEntityManager().createNativeQuery(hql).executeUpdate();

        //1)
        hql = "ALTER TABLE `gimnasio`.`caja` ADD COLUMN `monto_inicial` FLOAT AFTER `BORRADO`;";
        getEntityManager().createNativeQuery(hql).executeUpdate();
        hql = "ALTER TABLE `gimnasio`.`caja` ADD COLUMN `monto_final` FLOAT AFTER `monto_inicial`;";
        getEntityManager().createNativeQuery(hql).executeUpdate();
        hql = "ALTER TABLE `gimnasio`.`caja` ADD COLUMN `cerrada` BOOLEAN DEFAULT 0 AFTER `monto_final`;";
        getEntityManager().createNativeQuery(hql).executeUpdate();
        hql = "ALTER TABLE `gimnasio`.`caja` ADD COLUMN `fecha_creacion` DATETIME AFTER `cerrada`;";
        getEntityManager().createNativeQuery(hql).executeUpdate();
        
        //2)
        hql = "insert into gimnasio.accion values (10, 0, 'CALC_SUE', 'Calcular el sueldo de un profesor');";
        getEntityManager().createNativeQuery(hql).executeUpdate();
        hql = "insert into gimnasio.accion values (11, 0, 'REGP_SUE', 'Registrar el pago de sueldo de un profesor');";
        getEntityManager().createNativeQuery(hql).executeUpdate();
        hql = "insert into gimnasio.accion values (29, 0, 'REPO_SUE', 'Generar reporte del pago de sueldo de un profesor');";
        getEntityManager().createNativeQuery(hql).executeUpdate();
        hql = "insert into gimnasio.accion values (30, 0, 'INIC_CAJ', 'Iniciar una caja diaria');";
        getEntityManager().createNativeQuery(hql).executeUpdate();
        hql = "insert into gimnasio.accion values (31, 0, 'GMOV_CAJ', 'Guardar un ingreso/egreso de caja');";
        getEntityManager().createNativeQuery(hql).executeUpdate();
        hql = "insert into gimnasio.accion values (32, 0, 'EMOV_CAJ', 'Eliminar un ingreso/egreso de caja');";
        getEntityManager().createNativeQuery(hql).executeUpdate();
        hql = "insert into gimnasio.accion values (33, 0, 'RESG_BKP', 'Realizar copias de seguridad (resguardar)');";
        getEntityManager().createNativeQuery(hql).executeUpdate();
        hql = "insert into gimnasio.accion values (34, 0, 'ELIM_SUE', 'Eliminar el pago de un sueldo de un profesor');";
        getEntityManager().createNativeQuery(hql).executeUpdate();
        hql = "insert into gimnasio.accion values (35, 0, 'VERR_CLA', 'Permite visualisar los datos de una clase');";
        getEntityManager().createNativeQuery(hql).executeUpdate();

        //3)
        hql = "ALTER TABLE `gimnasio`.`pago_de_sueldos` ADD COLUMN `fecha` DATETIME NOT NULL AFTER `hs_trabajadas`;";
        getEntityManager().createNativeQuery(hql).executeUpdate();
        
        //4)
        hql = "ALTER TABLE `gimnasio`.`movimientos_caja` MODIFY COLUMN `FECHA` DATETIME DEFAULT NULL;";
        getEntityManager().createNativeQuery(hql).executeUpdate();

        //4)
        hql = "ALTER TABLE `gimnasio`.`movimientos_caja` MODIFY COLUMN `FECHA` DATETIME DEFAULT NULL;";
        getEntityManager().createNativeQuery(hql).executeUpdate();

        //5
        hql = "ALTER TABLE `gimnasio`.`pago_de_sueldos` ADD COLUMN `borrado` BOOLEAN NOT NULL DEFAULT 0 AFTER `fecha`;";
        getEntityManager().createNativeQuery(hql).executeUpdate();

        //6
        hql = "ALTER TABLE `gimnasio`.`clases_especiales` CHANGE `monto_por_hora_pago_profesor` `monto_para_profesor` BIGINT( 20 ) NOT NULL;";
        getEntityManager().createNativeQuery(hql).executeUpdate();
        hql = "CREATE TABLE `gimnasio`.`clases_especiales_profesores` ("+
            "`Especial_id_clase` BIGINT( 20 ) NOT NULL ,"+
            "`profesoresACargo_id_profesor` BIGINT( 20 ) NOT NULL"+
            ") ENGINE = MYISAM ;";
        getEntityManager().createNativeQuery(hql).executeUpdate();
        hql = "ALTER TABLE `gimnasio`.`movimientos_caja` ADD `ALUMNO_id_alumno` BIGINT( 20 ) ;";
        getEntityManager().createNativeQuery(hql).executeUpdate();
        hql = "ALTER TABLE `gimnasio`.`movimientos_caja` ADD `CLASEESPECIAL_id_clase` BIGINT( 20 ) ;";
        getEntityManager().createNativeQuery(hql).executeUpdate();

        getEntityManager().getTransaction().commit();
    }

    /**********************  Actualizacion V 1.3   ***************

    --Actualizar la tabla de profesor_clase que en la fecha_asignacion y la fecha_desasignacion sean DateTime
    --Actualizar la tabla clase_especial agregarle una columna llamada monto_por_hora_pago_profesor de tipo FLOAT
    --Actualizar la tabla dias_no_habiles agregarle la columna borrado
    --Actualizar la tabla cuotas agregarle la columna borrado

    //Version
    UPDATE gimnasio.version SET numero=3;

    /*************************************************************/
    private void script3() {
       /*
        String hql = "";
        getEntityManager().getTransaction().begin();
        
        hql = "UPDATE gimnasio.version SET numero=3;";
        getEntityManager().createNativeQuery(hql).executeUpdate();
        
        getEntityManager().getTransaction().commit();
        * */
    }

     private void script2() {
        /*String hql = "";
        getEntityManager().getTransaction().begin();
        hql = "UPDATE version SET numero = 102;";

        
        getEntityManager().createNativeQuery(hql).executeUpdate();
        hql = "CREATE CACHED TABLE CC_ENTRADAS(ID_CC INTEGER NOT NULL,ID_MOVIMIENTO INTEGER NOT NULL,PRIMARY KEY(ID_CC,ID_MOVIMIENTO))";
        getEntityManager().createNativeQuery(hql).executeUpdate();
        hql = "CREATE CACHED TABLE CC_SALIDAS(ID_CC INTEGER NOT NULL,ID_MOVIMIENTO INTEGER NOT NULL,PRIMARY KEY(ID_CC,ID_MOVIMIENTO))";
        getEntityManager().createNativeQuery(hql).executeUpdate();
        hql = "CREATE CACHED TABLE CUENTASCORRIENTES(ID INTEGER NOT NULL PRIMARY KEY,BORRADO TINYINT,FECHA DATE,SALDO REAL,CLIENTE_ID NUMERIC(19),CONSTRAINT CLIENTEID_UK UNIQUE(CLIENTE_ID))";
        getEntityManager().createNativeQuery(hql).executeUpdate();
        hql = "CREATE CACHED TABLE MOVIMIENTOSCUENTA(ID INTEGER NOT NULL PRIMARY KEY,DETALLE VARCHAR(255),FECHA DATE,MONTO REAL)";
        getEntityManager().createNativeQuery(hql).executeUpdate();
        hql = "CREATE CACHED TABLE GENERADOR_CC(KEY_CC VARCHAR(50) NOT NULL PRIMARY KEY,ID_CC NUMERIC(38))";
        getEntityManager().createNativeQuery(hql).executeUpdate();
        hql = "CREATE CACHED TABLE GENERADOR_MOVIMIENTOCC(KEY_MOVIMIENTOCC VARCHAR(50) NOT NULL PRIMARY KEY,ID_MOVIMIENTOCC NUMERIC(38))";
        getEntityManager().createNativeQuery(hql).executeUpdate();
        hql = "INSERT INTO GENERADOR_CC VALUES('pk_cc',1);";
        getEntityManager().createNativeQuery(hql).executeUpdate();
        hql = "INSERT INTO GENERADOR_MOVIMIENTOCC VALUES('pk_movimientocc',1);";
        getEntityManager().createNativeQuery(hql).executeUpdate();

        getEntityManager().getTransaction().commit();
         * */
    }

    private void script0() {
        String hql = "";
        getEntityManager().getTransaction().begin();

        getEntityManager().createNativeQuery(hql).executeUpdate();

        getEntityManager().getTransaction().commit();
    }

    private void script1() {
        String hql = "";
        
        getEntityManager().getTransaction().begin();

        hql = "UPDATE version SET numero = 1;";
        getEntityManager().createNativeQuery(hql).executeUpdate();

        /*Localidad*/
        hql = "ALTER TABLE localidad ADD COLUMN id_region bigint; " +
                "ALTER TABLE localidad ALTER COLUMN id_region SET STORAGE PLAIN;";
        getEntityManager().createNativeQuery(hql).executeUpdate();

        /*Informacion personal*/
        hql = "ALTER TABLE informacion_personal DROP COLUMN dni;" +
                " ALTER TABLE informacion_personal ADD COLUMN edad character varying(20);" +
                " ALTER TABLE informacion_personal ADD COLUMN altura character varying(20);" +
                "ALTER TABLE informacion_personal ADD COLUMN peso character varying(20);" +
                " ALTER TABLE informacion_personal ADD COLUMN patologia character varying(255);" +
                "ALTER TABLE informacion_personal ADD COLUMN id_region bigint;" +
                "ALTER TABLE informacion_personal ALTER COLUMN id_region SET STORAGE PLAIN;" +
                "ALTER TABLE informacion_personal ADD COLUMN parentezco character varying;" ;
        getEntityManager().createNativeQuery(hql).executeUpdate();

        hql = "ALTER TABLE informacion_personal" +
                " ADD CONSTRAINT fk_informacion_personal_id_localidad FOREIGN KEY (id_localidad)" +
                "     REFERENCES localidad (id) MATCH SIMPLE" +
                "     ON UPDATE NO ACTION ON DELETE NO ACTION;";
        getEntityManager().createNativeQuery(hql).executeUpdate();

        hql = "ALTER TABLE observacion ALTER detalle TYPE text;";
        getEntityManager().createNativeQuery(hql).executeUpdate();

        getEntityManager().getTransaction().commit();
    }

    private void script100() {
        /*
        String hql = "";
        getEntityManager().getTransaction().begin();
        hql = "CREATE CACHED TABLE version (numero integer);";
        getEntityManager().createNativeQuery(hql).executeUpdate();
        hql = "INSERT INTO version VALUES(100);";
        getEntityManager().createNativeQuery(hql).executeUpdate();
        getEntityManager().getTransaction().commit();
         * */

    }

    

}
