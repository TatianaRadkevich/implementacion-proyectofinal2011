

                                   /*SCRIPT DE ACTUALIZACI�N*/


                  /* CAMBIOS SOLICITADOS A PARTIR DEL SCRIPT DE CREACI�N 1-0 */


/*Cambios solicitados por Rodrigo - 10/08/2011*/

EXEC sp_rename 'T_USUARIOS.CONTRASE�A', 'CONTRASENIA';
GO
EXEC sp_rename 'T_CLIENTES_WEB.CONTRASE�A', 'CONTRASENIA';
GO

/*Cambios solicitados por Ivan 13/08/2011*/
ALTER TABLE T_CLIENTES ALTER COLUMN ID_CLIENTE_WEB NUMERIC(5) NULL;
GO
ALTER TABLE T_CLIENTES ALTER COLUMN ID_USUARIO NUMERIC(5) NULL;
GO
ALTER TABLE T_CLIENTES ALTER COLUMN TELEFONO_RESPONSABLE NUMERIC(13) NULL;
GO
ALTER TABLE T_CARGOS DROP CONSTRAINT FK__T_CARGOS__ID_EMP__2B0A656D; /*B�scar en la tabla el nombre de la constraint. De acuerdo a la configuracion del motor va a ser diferente a la mia.*/
GO
ALTER TABLE T_CARGOS DROP COLUMN ID_EMPLEADO;
GO

CREATE TABLE T_EMPLEADOS_X_CARGO (
       ID_EMPLEADO_X_CARGO  numeric(5) IDENTITY,
       ID_CARGO             numeric(2) NOT NULL,
       ID_EMPLEADO          numeric(5) NOT NULL)
GO

ALTER TABLE T_EMPLEADOS_X_CARGO
       ADD PRIMARY KEY (ID_EMPLEADO_X_CARGO ASC)

GO

ALTER TABLE T_EMPLEADOS_X_CARGO
       ADD FOREIGN KEY (ID_EMPLEADO)
                             REFERENCES T_EMPLEADOS  (ID_EMPLEADO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
GO

ALTER TABLE T_EMPLEADOS_X_CARGO
       ADD FOREIGN KEY (ID_CARGO)
                             REFERENCES T_CARGOS  (ID_CARGO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
GO              
ALTER TABLE T_PRODUCTOS DROP COLUMN CODIGO;
GO
ALTER TABLE T_TPRODUCTO ADD CODIGO VARCHAR(4) NOT NULL;
GO
ALTER TABLE T_EMPLEADOS DROP COLUMN LEGAJO;
GO

/*Cambios solicitados por Gabriela - 15/08/2011*/
ALTER TABLE T_PROVEEDORES ADD FEC_BAJA DATETIME;
GO
ALTER TABLE T_PROVEEDORES ADD MOTIVO_BAJA VARCHAR(100);
GO

CREATE TABLE T_EGRESOS (
       ID_EGRESO            numeric(8) NOT NULL,
       FEC_HORA_EGRESO      datetime NOT NULL,
       CANTIDAD             numeric(5) NOT NULL,
       ID_MATERIAL          numeric(3) NOT NULL
)
GO

ALTER TABLE T_EGRESOS
       ADD PRIMARY KEY (ID_EGRESO ASC)
       
GO

ALTER TABLE T_EGRESOS
       ADD FOREIGN KEY (ID_MATERIAL)
                             REFERENCES T_MATERIALES  (ID_MATERIAL)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
GO
       

/*Cambios solicitados por Gabriela 18/08/2011*/

ALTER TABLE T_TPRODUCTO ADD FEC_BAJA DATETIME
GO
ALTER TABLE T_TPRODUCTO ADD MOTIVO_BAJA VARCHAR(100)
GO
ALTER TABLE T_PRODUCTOS ADD FEC_BAJA DATETIME
GO
ALTER TABLE T_PRODUCTOS ADD MOTIVO_BAJA VARCHAR(100)
GO

/*Cambios solicitados por Ivan 18/08/2011*/

ALTER TABLE T_TMAQUINA_HERRAMIENTA ADD FEC_BAJA DATETIME
GO
ALTER TABLE T_TMAQUINA_HERRAMIENTA ADD MOTIVO_BAJA VARCHAR(100)
GO

ALTER TABLE T_MAQUINAS_HERRAMIENTA_PARTICULAR ADD FEC_BAJA DATETIME
GO
ALTER TABLE T_MAQUINAS_HERRAMIENTA_PARTICULAR ADD MOTIVO_BAJA VARCHAR(100)
GO

ALTER TABLE T_PEDIDOS ADD FEC_BAJA DATETIME
GO
ALTER TABLE T_PEDIDOS ADD MOTIVO_BAJA VARCHAR(100)
GO

ALTER TABLE T_CLIENTES ADD FEC_BAJA DATETIME
GO
ALTER TABLE T_CLIENTES ADD MOTIVO_BAJA VARCHAR(100)
GO
ALTER TABLE T_PRODUCTOS ADD CONSTRAINT R_UNIQ_PROD_NOM UNIQUE (NOMBRE)
GO
ALTER TABLE T_CLIENTES ADD CONSTRAINT R_UNIQ_CLI_CUIL UNIQUE (CUIL)
GO
ALTER TABLE T_TPRODUCTO ADD CONSTRAINT R_UNIQ_TPROD_NOM UNIQUE (NOMBRE)
GO
ALTER TABLE T_TDOCUMENTO ADD CONSTRAINT R_UNIQ_TDOC_NOM UNIQUE (NOMBRE)
GO
ALTER TABLE T_CARGOS ADD CONSTRAINT R_UNIQ_CARG_NOM UNIQUE (NOMBRE)
GO
ALTER TABLE T_TPRODUCTO ADD CONSTRAINT R_UNIQ_TPROD_COD UNIQUE (CODIGO)
GO

/*Cambios solicitados por Gabriela - 20/08/2011*/

CREATE TABLE T_PRODUCTOS_TERMINADO (
       ID_PRODUCTO_TERMINADO numeric(8) IDENTITY,
       FEC_HORA_ALMACENAMIENTO datetime NOT NULL,
       ID_DETALLE_PEDIDO    numeric(8) NOT NULL,
       ID_EPRODUCTO_TERMINADO numeric(2) NOT NULL,
       CANTIDAD             numeric(5) NOT NULL
)
GO
ALTER TABLE T_PRODUCTOS_TERMINADO
       ADD PRIMARY KEY (ID_PRODUCTO_TERMINADO ASC)

GO


ALTER TABLE T_PRODUCTOS_TERMINADO
       ADD FOREIGN KEY (ID_DETALLE_PEDIDO)
                             REFERENCES T_DETALLES_PEDIDO  (
              ID_DETALLE_PEDIDO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION       
                             
GO
CREATE TABLE T_EPRODUCTO_TERMINADO (
       ID_EPRODUCTO_TERMINADO numeric(2) IDENTITY,
       DESCRIPCION          varchar(200) NULL,
       NOMBRE               varchar(50) NOT NULL
)
GO

ALTER TABLE T_EPRODUCTO_TERMINADO
       ADD PRIMARY KEY (ID_EPRODUCTO_TERMINADO ASC)
GO
ALTER TABLE T_PRODUCTOS_TERMINADO
       ADD FOREIGN KEY (ID_EPRODUCTO_TERMINADO)
                             REFERENCES T_EPRODUCTO_TERMINADO  (
              ID_EPRODUCTO_TERMINADO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
GO

CREATE TABLE T_EDETALLE_PEDIDO (
       ID_EDETALLE_PEDIDO numeric(2) IDENTITY,
       NOMBRE               varchar(50) NOT NULL,
       DESCRIPCION          varchar(200) NULL
)
GO
ALTER TABLE T_EDETALLE_PEDIDO
       ADD PRIMARY KEY (ID_EDETALLE_PEDIDO ASC)
GO
ALTER TABLE T_DETALLES_PEDIDO ADD ID_EDETALLE_PEDIDO numeric(2) not null
GO
ALTER TABLE T_DETALLES_PEDIDO
       ADD FOREIGN KEY (ID_EDETALLE_PEDIDO)
                             REFERENCES T_EDETALLE_PEDIDO  (
              ID_EDETALLE_PEDIDO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
GO

CREATE TABLE T_EGRESOS (
       ID_EGRESO            numeric(8) NOT NULL,
       FEC_HORA_EGRESO      datetime NOT NULL,
       ID_RESPONSABLE_DEPOSITO numeric(5) NOT NULL
)
GO
ALTER TABLE T_EGRESOS
       ADD PRIMARY KEY (ID_EGRESO ASC)
GO
CREATE TABLE T_DETALLES_EGRESO (
       ID_DETALLE_EGRESO    numeric(8) IDENTITY,
       CANTIDAD             numeric(5) NOT NULL,
       ID_EGRESO            numeric(8) NOT NULL,
       ID_DETALLE_PLAN      numeric(8) NOT NULL
)
GO
ALTER TABLE T_DETALLES_EGRESO
       ADD PRIMARY KEY (ID_DETALLE_EGRESO ASC)
GO

ALTER TABLE T_DETALLES_EGRESO
       ADD FOREIGN KEY (ID_DETALLE_PLAN)
                             REFERENCES T_DETALLES_PLAN  (
              ID_DETALLE_PLAN)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
GO


ALTER TABLE T_DETALLES_EGRESO
       ADD FOREIGN KEY (ID_EGRESO)
                             REFERENCES T_EGRESOS  (ID_EGRESO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
GO

ALTER TABLE T_EGRESOS
       ADD FOREIGN KEY (ID_RESPONSABLE_DEPOSITO)
                             REFERENCES T_EMPLEADOS  (ID_EMPLEADO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
GO                                                                               

ALTER TABLE T_DETALLES_EGRESO ADD ID_MATERIAL numeric (3) not null

GO
ALTER TABLE T_DETALLES_EGRESO
       ADD FOREIGN KEY (ID_MATERIAL)
                             REFERENCES T_MATERIALES  (ID_MATERIAL)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
GO
               
/*Cambios solicitados por Gabriela - 22/08/2011*/                             

EXEC sp_rename 'T_PRODUCTOS_TERMINADO', 'T_ALMACENAMIENTOS_PRODUCTO_TERMINADO';
GO
EXEC sp_rename 'T_ALMACENAMIENTOS_PRODUCTO_TERMINADO.ID_PRODUCTO_TERMINADO', 'ID_ALMACENAMIENTO_PRODUCTO_TERMINADO';
GO
EXEC sp_rename 'T_EPRODUCTO_TERMINADO', 'T_EALMACENAMIENTO_PRODUCTO_TERMINADO';
GO
EXEC sp_rename 'T_EALMACENAMIENTO_PRODUCTO_TERMINADO.ID_EPRODUCTO_TERMINADO', 'ID_EALMACENAMIENTO_PRODUCTO_TERMINADO';
GO

/*Cambios solicitados por Gabriela - 24/08/2011*/

ALTER TABLE T_DETALLES_ORDEN_COMPRA ADD ID_EDETALLE_ORDEN_COMPRA NUMERIC(2) NOT NULL
GO


CREATE TABLE T_EDETALLE_ORDEN_COMPRA (
       ID_EDETALLE_ORDEN_COMPRA numeric(2) IDENTITY,
       NOMBRE               varchar(50) NOT NULL,
       DESCRIPCION          varchar(200) NULL
)
GO
ALTER TABLE T_EDETALLE_ORDEN_COMPRA
       ADD PRIMARY KEY (ID_EDETALLE_ORDEN_COMPRA ASC)
GO       
ALTER TABLE T_DETALLES_ORDEN_COMPRA
       ADD FOREIGN KEY (ID_EDETALLE_ORDEN_COMPRA)
                             REFERENCES T_EDETALLE_ORDEN_COMPRA  (
              ID_EDETALLE_ORDEN_COMPRA)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
GO                

                    /* CAMBIOS SOLICITADOS A PARTIR DEL SCRIPT DE CREACI�N 1-1 */

/*Cambios observados por Mairen - 27/08/2011*/             

EXEC sp_rename 'T_DETALLES_PLAN.CANTIDAD', 'CANTIDAD_PLANIFICADA';
GO
EXEC sp_rename 'T_DETALLES_PLAN.ID_OPERARIO', 'ID_EMPLEADO';
GO
ALTER TABLE T_DETALLES_PLAN ADD CANTIDAD_PRODUCIDA NUMERIC(5);
GO
ALTER TABLE T_DETALLES_PLAN ADD OBSERVACIONES varchar(200);
GO
ALTER TABLE T_DETALLES_PLAN ADD ID_EDETALLE_PLAN NUMERIC(2) NOT NULL;
GO

CREATE TABLE T_EDETALLE_PLAN (
       ID_EDETALLE_PLAN     numeric(2) IDENTITY,
       NOMBRE               varchar(50) NOT NULL,
       DESCRIPCION          varchar(200) NULL
)
GO


ALTER TABLE T_EDETALLE_PLAN
       ADD PRIMARY KEY (ID_EDETALLE_PLAN ASC)
GO
ALTER  TABLE T_DETALLES_PLAN ADD FOREIGN KEY (ID_EDETALLE_PLAN) REFERENCES T_EDETALLE_PLAN (ID_EDETALLE_PLAN);
GO

CREATE TABLE T_EORDEN_TRABAJO (
       ID_EORDEN_TRABAJO    numeric(2) IDENTITY,
       NOMBRE               varchar(50) NOT NULL,
       DESCRIPCION          varchar(20) NULL
)
GO

ALTER TABLE T_EORDEN_TRABAJO
       ADD PRIMARY KEY (ID_EORDEN_TRABAJO ASC)
GO       

CREATE TABLE T_ORDENES_TRABAJO (
       ID_ORDEN_TRABAJO     numeric(10) IDENTITY,
       FEC_EMISION          datetime NOT NULL,
       OBSERVACIONES        varchar(20) NULL,
       ID_DETALLE_PLAN      numeric(8) NOT NULL,
       ID_EMPLEADO          numeric(5) NOT NULL,
       ID_EORDEN_TRABAJO    numeric(2) NOT NULL
)
GO

ALTER TABLE T_ORDENES_TRABAJO
       ADD PRIMARY KEY (ID_ORDEN_TRABAJO ASC)
GO

ALTER TABLE T_ORDENES_TRABAJO
       ADD FOREIGN KEY (ID_EMPLEADO)
                             REFERENCES T_EMPLEADOS (ID_EMPLEADO);
                                                      
GO
ALTER TABLE T_ORDENES_TRABAJO
       ADD FOREIGN KEY (ID_DETALLE_PLAN)
                             REFERENCES T_DETALLES_PLAN  (
              ID_DETALLE_PLAN)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
GO                                                                 

ALTER TABLE T_ORDENES_TRABAJO
       ADD FOREIGN KEY (ID_EORDEN_TRABAJO)
                             REFERENCES T_EORDEN_TRABAJO  (
              ID_EORDEN_TRABAJO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
GO


CREATE TABLE T_EPLAN_PRODUCCION (
       ID_EPLAN_PRODUCCION  numeric(2) IDENTITY,
       NOMBRE               varchar(50) NOT NULL,
       DESCRIPCION          varchar(200) NULL
)
GO
ALTER TABLE T_EPLAN_PRODUCCION
       ADD PRIMARY KEY (ID_EPLAN_PRODUCCION ASC)

GO
ALTER TABLE T_PLANES_PRODUCCION ADD ID_EPLAN_PRODUCCION NUMERIC(2) NOT NULL

GO
ALTER TABLE T_PLANES_PRODUCCION
       ADD FOREIGN KEY (ID_EPLAN_PRODUCCION)
                             REFERENCES T_EPLAN_PRODUCCION  (
              ID_EPLAN_PRODUCCION)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
GO                             

/*Cambios solicitados por Rodrigo - 27/08/2011*/

ALTER TABLE T_PEDIDOS DROP COLUMN ENTREGA_MATERIAL;
GO
ALTER TABLE T_TMAQUINA_HERRAMIENTA ADD DESCRIPCION VARCHAR(200);
GO
ALTER TABLE T_PEDIDOS ALTER COLUMN FEC_HORA_REAL_ENTREGA DATETIME NULL;
GO

/*Cambios por comparacion del modelo relacional con el Diagrama de Clases - 27/08/2011*/

ALTER TABLE T_CHEQUES ADD ID_CLIENTE NUMERIC(5) NOT NULL;
GO

CREATE TABLE T_MAQ_HERR_PART_X_DET_PLAN (
       ID_MAQ_HERR_PART_X_DET_PLAN numeric(10) IDENTITY,
       ID_DETALLE_PLAN      numeric(8) NOT NULL,
       ID_MAQUINA_HERRAMIENTA_PARTICULAR numeric(3) NOT NULL
)
GO
ALTER TABLE T_MAQ_HERR_PART_X_DET_PLAN
       ADD PRIMARY KEY (ID_MAQ_HERR_PART_X_DET_PLAN ASC)
GO

ALTER TABLE T_MAQ_HERR_PART_X_DET_PLAN
       ADD FOREIGN KEY (ID_MAQUINA_HERRAMIENTA_PARTICULAR)
                             REFERENCES T_MAQUINAS_HERRAMIENTA_PARTICULAR  (
              ID_MAQUINA_HERRAMIENTA_PARTICULAR)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
GO


ALTER TABLE T_MAQ_HERR_PART_X_DET_PLAN
       ADD FOREIGN KEY (ID_DETALLE_PLAN)
                             REFERENCES T_DETALLES_PLAN  (
              ID_DETALLE_PLAN)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
GO       

EXEC sp_rename 'T_ASISTENCIAS_EMPLEADO .FEC_EGRESO', 'HORA_EGRESO'; 

GO

/*Cambios indicados en el diagrama de clases -08/09/2011*/

ALTER TABLE T_PROVEEDORES ADD RAZON_SOCIAL VARCHAR(50) NOT NULL;

GO

ALTER TABLE T_DETALLES_ETAPA ADD CANTIDAD_REPETICIONES NUMERIC (6,2) NOT NULL;

GO

ALTER TABLE T_DETALLES_ETAPA ADD HORAS_MAQUINA NUMERIC (6,2) NOT NULL;

GO


CREATE TABLE T_UNIDADES_MEDIDA (
       ID_UNIDAD_MEDIDA     numeric(5) IDENTITY,
       NOMBRE               varchar(50) NOT NULL,
       DESCRIPCION          varchar(200) NULL
)
GO

ALTER TABLE T_UNIDADES_MEDIDA
       ADD PRIMARY KEY (ID_UNIDAD_MEDIDA ASC)


GO

ALTER TABLE T_PRODUCTOS ADD ID_UNIDAD_MEDIDA NUMERIC(5) NOT NULL;

GO


ALTER TABLE T_PRODUCTOS
       ADD FOREIGN KEY (ID_UNIDAD_MEDIDA)
                             REFERENCES T_UNIDADES_MEDIDA (ID_UNIDAD_MEDIDA);

GO

ALTER TABLE T_MATERIALES ADD ID_UNIDAD_MEDIDA NUMERIC(5) NOT NULL;

GO


ALTER TABLE T_MATERIALES
       ADD FOREIGN KEY (ID_UNIDAD_MEDIDA)
                             REFERENCES T_UNIDADES_MEDIDA (ID_UNIDAD_MEDIDA);

GO

EXEC SP_RENAME 'T_CLIENTES.CUIL','CUIT';

GO

ALTER TABLE T_CLIENTES ALTER COLUMN CUIT VARCHAR(20);

GO

/* Cambios solicitados - 17/09/2011*/

ALTER TABLE T_PEDIDOS ADD ID_EMPLEADO NUMERIC(5) NOT NULL;
GO

ALTER TABLE T_PEDIDOS
       ADD FOREIGN KEY (ID_EMPLEADO)
                             REFERENCES T_EMPLEADOS (ID_EMPLEADO);
                             
GO

ALTER TABLE T_DOMICILIOS ALTER COLUMN DEPTO VARCHAR (3);
GO
ALTER TABLE T_ETAPAS_PRODUCCION_ESPECIFICA ADD DURACION NUMERIC(5);
GO
