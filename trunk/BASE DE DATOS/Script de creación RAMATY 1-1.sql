
                    /* SCRIPT DE CREACIÓN RAMATY - Versión 1-1*/
                    

CREATE TABLE T_ASISTENCIAS_EMPLEADO (
       ID_ASISTENCIA_EMPLEADO numeric(4) IDENTITY,
       FEC_ASISTENCIA       datetime NOT NULL,
       HORA_INGRESO         varchar(20) NOT NULL,
       FEC_EGRESO           datetime NULL,
       OBSERVACIONES        varchar(200) NULL,
       ID_EMPLEADO          numeric(5) NOT NULL
)
go


ALTER TABLE T_ASISTENCIAS_EMPLEADO
       ADD PRIMARY KEY (ID_ASISTENCIA_EMPLEADO ASC)
go


CREATE TABLE T_BARRIOS (
       ID_BARRIO            numeric(3) IDENTITY,
       NOMBRE               varchar(50) NOT NULL,
       DESCRIPCION          varchar(200) NULL,
       ID_LOCALIDAD         numeric(3) NOT NULL
)
go


ALTER TABLE T_BARRIOS
       ADD PRIMARY KEY (ID_BARRIO ASC)
go


CREATE TABLE T_CARGOS (
       ID_CARGO             numeric(2) IDENTITY,
       NOMBRE               varchar(50) NOT NULL,
       DESCRIPCION          varchar(200) NULL,
       FEC_BAJA             datetime NULL,
       MOTIVO_BAJA          varchar(200) NULL,
       ID_EMPLEADO          numeric(5) NOT NULL
)
go


ALTER TABLE T_CARGOS
       ADD PRIMARY KEY (ID_CARGO ASC)
go


CREATE TABLE T_CHEQUES (
       ID_CHEQUE            numeric(5) IDENTITY,
       FEC_EMISION          datetime NOT NULL,
       FEC_VENCIMIENTO      datetime NOT NULL,
       MONTO                numeric(6,4) NOT NULL,
       NOMBRE_BANCO         varchar(50) NOT NULL,
       NRO_SUCURSAL         numeric(5) NOT NULL
)
go


ALTER TABLE T_CHEQUES
       ADD PRIMARY KEY (ID_CHEQUE ASC)
go


CREATE TABLE T_CLIENTES (
       ID_CLIENTE           numeric(5) IDENTITY,
       CORREO_ELECTRONICO   varchar(50) NULL,
       CUIL                 numeric(11) NOT NULL,
       RAZON_SOCIAL         varchar(50) NOT NULL,
       ID_DOMICILIO         numeric(3) NULL,
       ID_TCLIENTE          numeric(2) NOT NULL,
       ID_CLIENTE_WEB       numeric(5) NULL,
       ID_USUARIO           numeric(5) NULL,
       NOMBRE_RESPONSABLE   varchar(50) NOT NULL,
       APELLIDO_RESPONSABLE varchar(50) NOT NULL,
       TELEFONO_RESPONSABLE numeric(13) NULL
)
go


ALTER TABLE T_CLIENTES
       ADD PRIMARY KEY (ID_CLIENTE ASC)
go


CREATE TABLE T_CLIENTES_WEB (
       ID_CLIENTE_WEB       numeric(5) IDENTITY,
       USUARIO              varchar(20) NOT NULL,
       CONTRASENIA          varchar(20) NOT NULL
)
go


ALTER TABLE T_CLIENTES_WEB
       ADD PRIMARY KEY (ID_CLIENTE_WEB ASC)
go


CREATE TABLE T_COBROS (
       ID_COBRO             numeric(8) IDENTITY,
       ID_ENCARGADO         numeric(5) NOT NULL,
       FEC_COBRO            datetime NOT NULL,
       IMPORTE              numeric(6,4) NOT NULL,
       OBSERVACIONES        varchar(200) NULL,
       ID_FORMA_PAGO        numeric(2) NOT NULL,
       ID_CHEQUE            numeric(5) NULL,
       ID_FACTURA           numeric(8) NULL
)
go


ALTER TABLE T_COBROS
       ADD PRIMARY KEY (ID_COBRO ASC)
go


CREATE TABLE T_DETALLES_ETAPA (
       ID_DETALLE_ETAPA     numeric(5) IDENTITY,
       CANTIDAD_NECESARIA   numeric(6,2) NOT NULL,
       ID_MATERIAL          numeric(3) NOT NULL,
       ID_ETAPA_PRODUCCION_ESPECIFICA numeric(5) NOT NULL
)
go


ALTER TABLE T_DETALLES_ETAPA
       ADD PRIMARY KEY (ID_DETALLE_ETAPA ASC)
go


CREATE TABLE T_DETALLES_FACTURA (
       ID_DETALLE_FACTURA   numeric(8) IDENTITY,
       CANTIDAD             numeric(5) NOT NULL,
       ID_DETALLE_PEDIDO    numeric(8) NOT NULL,
       PRECIO               numeric(6,2) NOT NULL,
       ID_FACTURA           numeric(8) NOT NULL
)
go


ALTER TABLE T_DETALLES_FACTURA
       ADD PRIMARY KEY (ID_DETALLE_FACTURA ASC)
go


CREATE TABLE T_DETALLES_ORDEN_COMPRA (
       ID_DETALLE_ORDEN_COMPRA numeric(8) IDENTITY,
       CANTIDAD_PEDIDA      numeric(3) NOT NULL,
       CANTIDAD_RECIBIDA    numeric(3) NOT NULL,
       ID_ORDEN_COMPRA      numeric(8) NOT NULL,
       ID_MATERIAL          numeric(3) NULL
)
go


ALTER TABLE T_DETALLES_ORDEN_COMPRA
       ADD PRIMARY KEY (ID_DETALLE_ORDEN_COMPRA ASC)
go


CREATE TABLE T_DETALLES_PEDIDO (
       ID_DETALLE_PEDIDO    numeric(8) IDENTITY,
       CANTIDAD             numeric(5) NOT NULL,
       PRECIO               numeric(6,2) NOT NULL,
       ID_PEDIDO            numeric(8) NOT NULL,
       ID_PRODUCTO          numeric(5) NOT NULL
)
go


ALTER TABLE T_DETALLES_PEDIDO
       ADD PRIMARY KEY (ID_DETALLE_PEDIDO ASC)
go


CREATE TABLE T_DETALLES_PLAN (
       ID_DETALLE_PLAN      numeric(8) IDENTITY,
       CANTIDAD             numeric(5) NOT NULL,
       FEC_HORA_PREVISTA_FIN datetime NOT NULL,
       FEC_HORA_PREVISTA_INICIO datetime NOT NULL,
       FEC_HORA_REAL_INICIO datetime NOT NULL,
       FEC_HORA_REAL_FIN    datetime NOT NULL,
       ID_ETAPA_PRODUCCION_ESPECIFICA numeric(5) NOT NULL,
       ID_MAQUINA_HERRAMIENTA_PARTICULAR numeric(3) NOT NULL,
       ID_OPERARIO          numeric(5) NOT NULL,
       ID_PLAN_PRODUCCION   numeric(8) NOT NULL
)
go


ALTER TABLE T_DETALLES_PLAN
       ADD PRIMARY KEY (ID_DETALLE_PLAN ASC)
go


CREATE TABLE T_DETALLES_PRODUCTO (
       ID_DETALLE_PRODUCTO  numeric(5) IDENTITY,
       LONGITUD             numeric(3) NOT NULL,
       ID_PRODUCTO          numeric(5) NOT NULL,
       ID_MATERIAL          numeric(3) NOT NULL
)
go


ALTER TABLE T_DETALLES_PRODUCTO
       ADD PRIMARY KEY (ID_DETALLE_PRODUCTO ASC)
go


CREATE TABLE T_DIAS_HORA_LABORABLE (
       ID_DIA_HORA_LABORABLE numeric(4) IDENTITY,
       DIA                  varchar(20) NOT NULL,
       HORA_INICIO          varchar(20) NOT NULL,
       HORA_FIN             varchar(20) NULL,
       ID_EMPLEADO          numeric(5) NOT NULL
)
go


ALTER TABLE T_DIAS_HORA_LABORABLE
       ADD PRIMARY KEY (ID_DIA_HORA_LABORABLE ASC)
go


CREATE TABLE T_DOMICILIOS (
       ID_DOMICILIO         numeric(3) IDENTITY,
       CALLE                varchar(20) NOT NULL,
       DEPTO                numeric(3) NULL,
       NUMERO               numeric(5) NOT NULL,
       PISO                 numeric(2) NULL,
       ID_PAIS              numeric(3) NOT NULL,
       ID_LOCALIDAD         numeric(3) NULL,
       ID_PROVINCIA         numeric(5) NULL,
       ID_BARRIO            numeric(3) NULL
)
go


ALTER TABLE T_DOMICILIOS
       ADD PRIMARY KEY (ID_DOMICILIO ASC)
go


CREATE TABLE T_EGRESOS (
       ID_EGRESO            numeric(8) NOT NULL,
       FEC_HORA_EGRESO      datetime NOT NULL,
       CANTIDAD             numeric(5) NOT NULL,
       ID_MATERIAL          numeric(3) NOT NULL
)
go


ALTER TABLE T_EGRESOS
       ADD PRIMARY KEY (ID_EGRESO ASC)
go


CREATE TABLE T_EMAQUINA (
       ID_EMAQUINA          numeric(2) IDENTITY,
       NOMBRE               nvarchar(50) NOT NULL,
       DESCRIPCION          varchar(200) NULL
)
go


ALTER TABLE T_EMAQUINA
       ADD PRIMARY KEY (ID_EMAQUINA ASC)
go


CREATE TABLE T_EMPLEADOS (
       ID_EMPLEADO          numeric(5) IDENTITY,
       NOMBRE               varchar(100) NOT NULL,
       APELLIDO             varchar(100) NOT NULL,
       CELULAR              numeric(13) NULL,
       CORREO_ELECTRONICO   varchar(50) NULL,
       FEC_NACIMIENTO       datetime NOT NULL,
       NUMERO_DOCUMENTO     numeric(8) NOT NULL,
       TELEFONO             numeric(11) NULL,
       ID_TDOCUMENTO        numeric(2) NOT NULL,
       ID_SEXO              numeric(2) NOT NULL,
       ID_DOMICILIO         numeric(3) NOT NULL,
       LEGAJO               numeric(5) NULL,
       OBSERVACIONES        varchar(200) NULL,
       ID_ASISTENCIA_EMPLEADO numeric(5) NULL,
       ID_DIAS_HORAS_LABORABLES numeric(3) NULL,
       ID_USUARIO           numeric(5) NOT NULL
)
go


ALTER TABLE T_EMPLEADOS
       ADD PRIMARY KEY (ID_EMPLEADO ASC)
go


CREATE TABLE T_EORDEN_COMPRA (
       ID_EORDEN_COMPRA     numeric(2) IDENTITY,
       NOMBRE               varchar(50) NOT NULL,
       DESCRIPCION          varchar(200) NULL
)
go


ALTER TABLE T_EORDEN_COMPRA
       ADD PRIMARY KEY (ID_EORDEN_COMPRA ASC)
go


CREATE TABLE T_EPEDIDO (
       ID_EPEDIDO           numeric(2) IDENTITY,
       NOMBRE               varchar(50) NOT NULL,
       DESCRIPCION          varchar(200) NULL
)
go


ALTER TABLE T_EPEDIDO
       ADD PRIMARY KEY (ID_EPEDIDO ASC)
go


CREATE TABLE T_ERECLAMO (
       ID_ERECLAMO          numeric(2) IDENTITY,
       NOMBRE               varchar(50) NOT NULL,
       DESCRIPCION          varchar(200) NULL
)
go


ALTER TABLE T_ERECLAMO
       ADD PRIMARY KEY (ID_ERECLAMO ASC)
go


CREATE TABLE T_ETAPAS_PRODUCCION (
       ID_ETAPA_PRODUCCION  numeric(3) IDENTITY,
       NOMBRE               varchar(50) NOT NULL,
       DESCRIPCION          varchar(200) NULL,
       FEC_BAJA             datetime NULL,
       MOTIVO_BAJA          varchar(200) NULL
)
go


ALTER TABLE T_ETAPAS_PRODUCCION
       ADD PRIMARY KEY (ID_ETAPA_PRODUCCION ASC)
go


CREATE TABLE T_ETAPAS_PRODUCCION_ESPECIFICA (
       ID_ETAPA_PRODUCCION_ESPECIFICA numeric(5) IDENTITY,
       DESCRIPCION_ESPECIFICA varchar(200) NOT NULL,
       HORAS_HOMBRE         numeric(6,2) NOT NULL,
       NUMERO_ORDEN         numeric(2) NOT NULL,
       ID_PRODUCTO          numeric(5) NOT NULL,
       ID_CARGO             numeric(2) NOT NULL,
       ID_ETAPA_PRODUCCION  numeric(3) NOT NULL
)
go


ALTER TABLE T_ETAPAS_PRODUCCION_ESPECIFICA
       ADD PRIMARY KEY (ID_ETAPA_PRODUCCION_ESPECIFICA ASC)
go


CREATE TABLE T_FACTURAS (
       ID_FACTURA           numeric(8) IDENTITY,
       DESCUENTO            numeric(6,4) NULL,
       FEC_FACTURA          datetime NOT NULL,
       NUMERO               numeric(8) NOT NULL,
       RECARGO              numeric(6,4) NULL,
       ID_EMPLEADO          numeric(5) NOT NULL
)
go


ALTER TABLE T_FACTURAS
       ADD PRIMARY KEY (ID_FACTURA ASC)
go


CREATE TABLE T_FALTANTES (
       ID_FALTANTE          numeric(4) IDENTITY,
       CANTIDAD             numeric(5,2) NOT NULL,
       FEC_GENERACION       datetime NOT NULL,
       FEC_NECESIDAD        datetime NOT NULL,
       ID_DETALLE_ORDEN_COMPRA numeric(8) NULL,
       ID_MATERIAL          numeric(3) NOT NULL,
       ID_DETALLE_PLAN      numeric(8) NULL
)
go


ALTER TABLE T_FALTANTES
       ADD PRIMARY KEY (ID_FALTANTE ASC)
go


CREATE TABLE T_FORMAS_PAGO (
       ID_FORMA_PAGO        numeric(2) IDENTITY,
       NOMBRE               varchar(50) NOT NULL,
       DESCRIPCION          varchar(200) NULL
)
go


ALTER TABLE T_FORMAS_PAGO
       ADD PRIMARY KEY (ID_FORMA_PAGO ASC)
go


CREATE TABLE T_LOCALIDADES (
       ID_LOCALIDAD         numeric(3) IDENTITY,
       NOMBRE               varchar(50) NOT NULL,
       DESCRIPCION          varchar(200) NULL,
       ID_PROVINCIA         numeric(5) NOT NULL
)
go


ALTER TABLE T_LOCALIDADES
       ADD PRIMARY KEY (ID_LOCALIDAD ASC)
go


CREATE TABLE T_MAQUINAS_HERRAMIENTA_PARTICULAR (
       ID_MAQUINA_HERRAMIENTA_PARTICULAR numeric(3) IDENTITY,
       CAPACIDAD_PRODUCTIVA numeric(5) NOT NULL,
       CARACTERISTICAS      varchar(200) NOT NULL,
       MODELO               varchar(50) NOT NULL,
       NOMBRE               varchar(50) NOT NULL,
       OBSERVACIONES        varchar(200) NULL,
       ID_TMAQUINA_HERRAMIENTA numeric(2) NOT NULL,
       ID_EMAQUINA          numeric(2) NOT NULL,
       CODIGO               varchar(2) NOT NULL
)
go


ALTER TABLE T_MAQUINAS_HERRAMIENTA_PARTICULAR
       ADD PRIMARY KEY (ID_MAQUINA_HERRAMIENTA_PARTICULAR ASC)
go


CREATE TABLE T_MATERIALES (
       ID_MATERIAL          numeric(3) IDENTITY,
       NOMBRE               varchar(50) NOT NULL,
       DESCRIPCION          varchar(200) NULL,
       DIAMETRO             numeric(6) NULL,
       ES_MATERIA_PRIMA     bit NOT NULL,
       LOGITUD              numeric(3) NULL,
       FEC_BAJA             datetime NULL,
       MOTIVO_BAJA          varchar(200) NULL,
       STOCK_ACTUAL         numeric(3) NOT NULL,
       STOCK_MINIMO         numeric(3) NOT NULL,
       STOCK_RESERVADO      numeric(3) NOT NULL,
       CODIGO               varchar(2) NOT NULL
)
go


ALTER TABLE T_MATERIALES
       ADD PRIMARY KEY (ID_MATERIAL ASC)
go


CREATE TABLE T_MATERIALES_X_PROVEEDOR (
       ID_MATERIAL_X_PROVEEDOR numeric(3) IDENTITY,
       ID_PROVEEDOR         numeric(3) NULL,
       ID_MATERIAL          numeric(3) NULL
)
go


ALTER TABLE T_MATERIALES_X_PROVEEDOR
       ADD PRIMARY KEY (ID_MATERIAL_X_PROVEEDOR ASC)
go


CREATE TABLE T_ORDENES_COMPRA (
       ID_ORDEN_COMPRA      numeric(8) IDENTITY,
       CODIGO               varchar(2) NOT NULL,
       FEC_GENERACION       datetime NOT NULL,
       FEC_RECEPCION        datetime NOT NULL,
       ID_PROVEEDOR         numeric(3) NOT NULL,
       ID_EORDEN_COMPRA     numeric(2) NOT NULL
)
go


ALTER TABLE T_ORDENES_COMPRA
       ADD PRIMARY KEY (ID_ORDEN_COMPRA ASC)
go


CREATE TABLE T_PAISES (
       ID_PAIS              numeric(3) IDENTITY,
       NOMBRE               varchar(50) NOT NULL,
       DESCRIPCION          varchar(200) NULL
)
go


ALTER TABLE T_PAISES
       ADD PRIMARY KEY (ID_PAIS ASC)
go


CREATE TABLE T_PEDIDOS (
       ID_PEDIDO            numeric(8) IDENTITY,
       ENTREGA_MATERIAL     bit NULL,
       FEC_HORA_ESTIMADA_ENTREGA datetime NOT NULL,
       FEC_HORA_GENERACION  datetime NOT NULL,
       FEC_HORA_REAL_ENTREGA datetime NOT NULL,
       FEC_SOLICITADA       datetime NOT NULL,
       PRIORIDAD            numeric(2) NOT NULL,
       ID_EPEDIDO           numeric(2) NOT NULL,
       ID_TPEDIDO           numeric(2) NOT NULL,
       ID_CLIENTE           numeric(5) NOT NULL
)
go


ALTER TABLE T_PEDIDOS
       ADD PRIMARY KEY (ID_PEDIDO ASC)
go


CREATE TABLE T_PLANES_PRODUCCION (
       ID_PLAN_PRODUCCION   numeric(8) IDENTITY,
       ID_ENCARGADO         numeric(5) NOT NULL,
       FEC_GENERACION       datetime NOT NULL,
       FEC_HORA_PREVISTA_FIN datetime NOT NULL,
       FEC_HORA_PREVISTA_INICIO datetime NOT NULL,
       FEC_HORA_REAL_FIN    datetime NOT NULL,
       FEC_HORA_REAL_INICIO datetime NOT NULL,
       OBSERVACIONES        varchar(200) NULL,
       ID_PEDIDO            numeric(8) NOT NULL,
       FEC_ULTIMA_MODIFICACION datetime NULL
)
go


ALTER TABLE T_PLANES_PRODUCCION
       ADD PRIMARY KEY (ID_PLAN_PRODUCCION ASC)
go


CREATE TABLE T_PRODUCTOS (
       ID_PRODUCTO          numeric(5) IDENTITY,
       DESCRIPCION          varchar(200) NULL,
       NOMBRE               varchar(50) NOT NULL,
       PRECIO_UNITARIO      numeric(4,2) NOT NULL,
       ID_TPRODUCTO         numeric(2) NOT NULL,
       CODIGO               varchar(2) NOT NULL
)
go


ALTER TABLE T_PRODUCTOS
       ADD PRIMARY KEY (ID_PRODUCTO ASC)
go


CREATE TABLE T_PROVEEDORES (
       ID_PROVEEDOR         numeric(3) IDENTITY,
       NOMBRE               varchar(100) NOT NULL,
       PAGINA_WEB           varchar(50) NULL,
       TELEFONO             varchar(20) NULL,
       CORREO_ELECTRONICO   varchar(50) NULL,
       ID_DOMICILIO         numeric(3) NOT NULL,
       APELLIDO             varchar(100) NOT NULL,
       FEC_BAJA             datetime NULL,
       MOTIVO_BAJA          varchar(100) NULL
)
go


ALTER TABLE T_PROVEEDORES
       ADD PRIMARY KEY (ID_PROVEEDOR ASC)
go


CREATE TABLE T_PROVINCIAS (
       ID_PROVINCIA         numeric(5) IDENTITY,
       NOMBRE               varchar(50) NOT NULL,
       DESCRIPCION          varchar(200) NULL,
       ID_PAIS              numeric(3) NOT NULL
)
go


ALTER TABLE T_PROVINCIAS
       ADD PRIMARY KEY (ID_PROVINCIA ASC)
go


CREATE TABLE T_RECLAMOS (
       ID_RECLAMO           numeric(3) IDENTITY,
       DESCRIPCION          varchar(200) NOT NULL,
       FEC_RECLAMO          datetime NOT NULL,
       ID_ORDEN_COMPRA      numeric(8) NULL,
       ID_ERECLAMO          numeric(2) NOT NULL,
       MOTIVO               varchar(200) NOT NULL
)
go


ALTER TABLE T_RECLAMOS
       ADD PRIMARY KEY (ID_RECLAMO ASC)
go


CREATE TABLE T_SEXOS (
       ID_SEXO              numeric(2) IDENTITY,
       NOMBRE               varchar(50) NOT NULL,
       DESCRIPCION          varchar(200) NULL
)
go


ALTER TABLE T_SEXOS
       ADD PRIMARY KEY (ID_SEXO ASC)
go


CREATE TABLE T_TCLIENTE (
       ID_TCLIENTE          numeric(2) IDENTITY,
       NOMBRE               varchar(50) NOT NULL,
       DESCRIPCION          varchar(200) NULL
)
go


ALTER TABLE T_TCLIENTE
       ADD PRIMARY KEY (ID_TCLIENTE ASC)
go


CREATE TABLE T_TDOCUMENTO (
       ID_TDOCUMENTO        numeric(2) IDENTITY,
       NOMBRE               varchar(50) NOT NULL,
       DESCRIPCION          varchar(200) NULL
)
go


ALTER TABLE T_TDOCUMENTO
       ADD PRIMARY KEY (ID_TDOCUMENTO ASC)
go


CREATE TABLE T_TMAQUINA_HERRAMIENTA (
       ID_TMAQUINA_HERRAMIENTA numeric(2) IDENTITY,
       NOMBRE               varchar(50) NOT NULL,
       ES_HERRAMIENTA       bit NOT NULL,
       ID_ETAPA_PRODUCCION_ESPECIFICA numeric(5) NOT NULL
)
go


ALTER TABLE T_TMAQUINA_HERRAMIENTA
       ADD PRIMARY KEY (ID_TMAQUINA_HERRAMIENTA ASC)
go


CREATE TABLE T_TPEDIDO (
       ID_TPEDIDO           numeric(2) IDENTITY,
       NOMBRE               varchar(50) NOT NULL,
       DESCRIPCION          varchar(200) NULL
)
go


ALTER TABLE T_TPEDIDO
       ADD PRIMARY KEY (ID_TPEDIDO ASC)
go


CREATE TABLE T_TPRODUCTO (
       ID_TPRODUCTO         numeric(2) IDENTITY,
       NOMBRE               varchar(50) NOT NULL,
       DESCRIPCION          varchar(200) NULL
)
go


ALTER TABLE T_TPRODUCTO
       ADD PRIMARY KEY (ID_TPRODUCTO ASC)
go


CREATE TABLE T_USUARIOS (
       ID_USUARIO           numeric(5) IDENTITY,
       NOMBRE_USUARIO       varchar(50) NOT NULL,
       CONTRASENIA          varchar(50) NOT NULL
)
go


ALTER TABLE T_USUARIOS
       ADD PRIMARY KEY (ID_USUARIO ASC)
go


ALTER TABLE T_ASISTENCIAS_EMPLEADO
       ADD FOREIGN KEY (ID_EMPLEADO)
                             REFERENCES T_EMPLEADOS  (ID_EMPLEADO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_BARRIOS
       ADD FOREIGN KEY (ID_LOCALIDAD)
                             REFERENCES T_LOCALIDADES  (ID_LOCALIDAD)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_CARGOS
       ADD FOREIGN KEY (ID_EMPLEADO)
                             REFERENCES T_EMPLEADOS  (ID_EMPLEADO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_CLIENTES
       ADD FOREIGN KEY (ID_CLIENTE_WEB)
                             REFERENCES T_CLIENTES_WEB  (
              ID_CLIENTE_WEB)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_CLIENTES
       ADD FOREIGN KEY (ID_USUARIO)
                             REFERENCES T_USUARIOS  (ID_USUARIO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_CLIENTES
       ADD FOREIGN KEY (ID_DOMICILIO)
                             REFERENCES T_DOMICILIOS  (ID_DOMICILIO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_CLIENTES
       ADD FOREIGN KEY (ID_TCLIENTE)
                             REFERENCES T_TCLIENTE  (ID_TCLIENTE)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_COBROS
       ADD FOREIGN KEY (ID_FACTURA)
                             REFERENCES T_FACTURAS  (ID_FACTURA)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_COBROS
       ADD FOREIGN KEY (ID_FORMA_PAGO)
                             REFERENCES T_FORMAS_PAGO  (ID_FORMA_PAGO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_COBROS
       ADD FOREIGN KEY (ID_CHEQUE)
                             REFERENCES T_CHEQUES  (ID_CHEQUE)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_COBROS
       ADD FOREIGN KEY (ID_ENCARGADO)
                             REFERENCES T_EMPLEADOS  (ID_EMPLEADO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_DETALLES_ETAPA
       ADD FOREIGN KEY (ID_ETAPA_PRODUCCION_ESPECIFICA)
                             REFERENCES T_ETAPAS_PRODUCCION_ESPECIFICA  (
              ID_ETAPA_PRODUCCION_ESPECIFICA)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_DETALLES_ETAPA
       ADD FOREIGN KEY (ID_MATERIAL)
                             REFERENCES T_MATERIALES  (ID_MATERIAL)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_DETALLES_FACTURA
       ADD FOREIGN KEY (ID_DETALLE_PEDIDO)
                             REFERENCES T_DETALLES_PEDIDO  (
              ID_DETALLE_PEDIDO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_DETALLES_FACTURA
       ADD FOREIGN KEY (ID_FACTURA)
                             REFERENCES T_FACTURAS  (ID_FACTURA)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_DETALLES_ORDEN_COMPRA
       ADD FOREIGN KEY (ID_ORDEN_COMPRA)
                             REFERENCES T_ORDENES_COMPRA  (
              ID_ORDEN_COMPRA)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_DETALLES_ORDEN_COMPRA
       ADD FOREIGN KEY (ID_MATERIAL)
                             REFERENCES T_MATERIALES  (ID_MATERIAL)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_DETALLES_PEDIDO
       ADD FOREIGN KEY (ID_PRODUCTO)
                             REFERENCES T_PRODUCTOS  (ID_PRODUCTO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_DETALLES_PEDIDO
       ADD FOREIGN KEY (ID_PEDIDO)
                             REFERENCES T_PEDIDOS  (ID_PEDIDO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_DETALLES_PLAN
       ADD FOREIGN KEY (ID_OPERARIO)
                             REFERENCES T_EMPLEADOS  (ID_EMPLEADO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_DETALLES_PLAN
       ADD FOREIGN KEY (ID_MAQUINA_HERRAMIENTA_PARTICULAR)
                             REFERENCES T_MAQUINAS_HERRAMIENTA_PARTICULAR  (
              ID_MAQUINA_HERRAMIENTA_PARTICULAR)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_DETALLES_PLAN
       ADD FOREIGN KEY (ID_PLAN_PRODUCCION)
                             REFERENCES T_PLANES_PRODUCCION  (
              ID_PLAN_PRODUCCION)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_DETALLES_PLAN
       ADD FOREIGN KEY (ID_ETAPA_PRODUCCION_ESPECIFICA)
                             REFERENCES T_ETAPAS_PRODUCCION_ESPECIFICA  (
              ID_ETAPA_PRODUCCION_ESPECIFICA)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_DETALLES_PRODUCTO
       ADD FOREIGN KEY (ID_MATERIAL)
                             REFERENCES T_MATERIALES  (ID_MATERIAL)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_DETALLES_PRODUCTO
       ADD FOREIGN KEY (ID_PRODUCTO)
                             REFERENCES T_PRODUCTOS  (ID_PRODUCTO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_DIAS_HORA_LABORABLE
       ADD FOREIGN KEY (ID_EMPLEADO)
                             REFERENCES T_EMPLEADOS  (ID_EMPLEADO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_DOMICILIOS
       ADD FOREIGN KEY (ID_LOCALIDAD)
                             REFERENCES T_LOCALIDADES  (ID_LOCALIDAD)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_DOMICILIOS
       ADD FOREIGN KEY (ID_BARRIO)
                             REFERENCES T_BARRIOS  (ID_BARRIO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_DOMICILIOS
       ADD FOREIGN KEY (ID_PROVINCIA)
                             REFERENCES T_PROVINCIAS  (ID_PROVINCIA)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_DOMICILIOS
       ADD FOREIGN KEY (ID_PAIS)
                             REFERENCES T_PAISES  (ID_PAIS)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_EGRESOS
       ADD FOREIGN KEY (ID_MATERIAL)
                             REFERENCES T_MATERIALES  (ID_MATERIAL)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_EMPLEADOS
       ADD FOREIGN KEY (ID_SEXO)
                             REFERENCES T_SEXOS  (ID_SEXO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_EMPLEADOS
       ADD FOREIGN KEY (ID_TDOCUMENTO)
                             REFERENCES T_TDOCUMENTO  (ID_TDOCUMENTO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_EMPLEADOS
       ADD FOREIGN KEY (ID_DOMICILIO)
                             REFERENCES T_DOMICILIOS  (ID_DOMICILIO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_EMPLEADOS
       ADD FOREIGN KEY (ID_USUARIO)
                             REFERENCES T_USUARIOS  (ID_USUARIO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_ETAPAS_PRODUCCION_ESPECIFICA
       ADD FOREIGN KEY (ID_CARGO)
                             REFERENCES T_CARGOS  (ID_CARGO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_ETAPAS_PRODUCCION_ESPECIFICA
       ADD FOREIGN KEY (ID_PRODUCTO)
                             REFERENCES T_PRODUCTOS  (ID_PRODUCTO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_ETAPAS_PRODUCCION_ESPECIFICA
       ADD FOREIGN KEY (ID_ETAPA_PRODUCCION)
                             REFERENCES T_ETAPAS_PRODUCCION  (
              ID_ETAPA_PRODUCCION)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_FACTURAS
       ADD FOREIGN KEY (ID_EMPLEADO)
                             REFERENCES T_EMPLEADOS  (ID_EMPLEADO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_FALTANTES
       ADD FOREIGN KEY (ID_DETALLE_ORDEN_COMPRA)
                             REFERENCES T_DETALLES_ORDEN_COMPRA  (
              ID_DETALLE_ORDEN_COMPRA)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_FALTANTES
       ADD FOREIGN KEY (ID_DETALLE_PLAN)
                             REFERENCES T_DETALLES_PLAN  (
              ID_DETALLE_PLAN)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_FALTANTES
       ADD FOREIGN KEY (ID_MATERIAL)
                             REFERENCES T_MATERIALES  (ID_MATERIAL)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_LOCALIDADES
       ADD FOREIGN KEY (ID_PROVINCIA)
                             REFERENCES T_PROVINCIAS  (ID_PROVINCIA)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_MAQUINAS_HERRAMIENTA_PARTICULAR
       ADD FOREIGN KEY (ID_TMAQUINA_HERRAMIENTA)
                             REFERENCES T_TMAQUINA_HERRAMIENTA  (
              ID_TMAQUINA_HERRAMIENTA)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_MAQUINAS_HERRAMIENTA_PARTICULAR
       ADD FOREIGN KEY (ID_EMAQUINA)
                             REFERENCES T_EMAQUINA  (ID_EMAQUINA)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_MATERIALES_X_PROVEEDOR
       ADD FOREIGN KEY (ID_PROVEEDOR)
                             REFERENCES T_PROVEEDORES  (ID_PROVEEDOR)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_MATERIALES_X_PROVEEDOR
       ADD FOREIGN KEY (ID_MATERIAL)
                             REFERENCES T_MATERIALES  (ID_MATERIAL)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_ORDENES_COMPRA
       ADD FOREIGN KEY (ID_PROVEEDOR)
                             REFERENCES T_PROVEEDORES  (ID_PROVEEDOR)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_ORDENES_COMPRA
       ADD FOREIGN KEY (ID_EORDEN_COMPRA)
                             REFERENCES T_EORDEN_COMPRA  (
              ID_EORDEN_COMPRA)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_PEDIDOS
       ADD FOREIGN KEY (ID_TPEDIDO)
                             REFERENCES T_TPEDIDO  (ID_TPEDIDO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_PEDIDOS
       ADD FOREIGN KEY (ID_EPEDIDO)
                             REFERENCES T_EPEDIDO  (ID_EPEDIDO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_PEDIDOS
       ADD FOREIGN KEY (ID_CLIENTE)
                             REFERENCES T_CLIENTES  (ID_CLIENTE)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_PLANES_PRODUCCION
       ADD FOREIGN KEY (ID_PEDIDO)
                             REFERENCES T_PEDIDOS  (ID_PEDIDO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_PLANES_PRODUCCION
       ADD FOREIGN KEY (ID_ENCARGADO)
                             REFERENCES T_EMPLEADOS  (ID_EMPLEADO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_PRODUCTOS
       ADD FOREIGN KEY (ID_TPRODUCTO)
                             REFERENCES T_TPRODUCTO  (ID_TPRODUCTO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_PROVEEDORES
       ADD FOREIGN KEY (ID_DOMICILIO)
                             REFERENCES T_DOMICILIOS  (ID_DOMICILIO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_PROVINCIAS
       ADD FOREIGN KEY (ID_PAIS)
                             REFERENCES T_PAISES  (ID_PAIS)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_RECLAMOS
       ADD FOREIGN KEY (ID_ORDEN_COMPRA)
                             REFERENCES T_ORDENES_COMPRA  (
              ID_ORDEN_COMPRA)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_RECLAMOS
       ADD FOREIGN KEY (ID_ERECLAMO)
                             REFERENCES T_ERECLAMO  (ID_ERECLAMO)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE T_TMAQUINA_HERRAMIENTA
       ADD FOREIGN KEY (ID_ETAPA_PRODUCCION_ESPECIFICA)
                             REFERENCES T_ETAPAS_PRODUCCION_ESPECIFICA  (
              ID_ETAPA_PRODUCCION_ESPECIFICA)
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go



                    