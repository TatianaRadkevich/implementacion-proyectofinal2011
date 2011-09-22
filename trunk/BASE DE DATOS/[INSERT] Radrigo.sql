----------------------------------Tipo Pedido------------------------------------------
INSERT INTO dbo.T_TPEDIDO (NOMBRE, DESCRIPCION) VALUES ('MP del cliente', NULL);
INSERT INTO dbo.T_TPEDIDO (NOMBRE, DESCRIPCION) VALUES ('MP propia', NULL);

----------------------------------Estado Pedido--------------------------------
INSERT INTO dbo.T_EPEDIDO (NOMBRE, DESCRIPCION) VALUES ('No Autorizado', NULL);
INSERT INTO dbo.T_EPEDIDO (NOMBRE, DESCRIPCION) VALUES ('Planificado', NULL);
INSERT INTO dbo.T_EPEDIDO (NOMBRE, DESCRIPCION) VALUES ('Cancelado', NULL);
INSERT INTO dbo.T_EPEDIDO (NOMBRE, DESCRIPCION) VALUES ('Almacenado', NULL);
INSERT INTO dbo.T_EPEDIDO (NOMBRE, DESCRIPCION) VALUES ('Retirado', NULL);
INSERT INTO dbo.T_EPEDIDO (NOMBRE, DESCRIPCION) VALUES ('Terminado', NULL);
INSERT INTO dbo.T_EPEDIDO (NOMBRE, DESCRIPCION) VALUES ('Produccion', NULL);
INSERT INTO dbo.T_EPEDIDO (NOMBRE, DESCRIPCION) VALUES ('Autorizado-Pendiente', NULL);
INSERT INTO dbo.T_EPEDIDO (NOMBRE, DESCRIPCION) VALUES ('Detenido', NULL);
----------------------------Estados Detalle Pedido----------------------------------
INSERT INTO dbo.T_EDETALLE_PEDIDO (NOMBRE, DESCRIPCION) VALUES ('Pendiente', NULL);
INSERT INTO dbo.T_EDETALLE_PEDIDO (NOMBRE, DESCRIPCION) VALUES ('Producción', NULL);
INSERT INTO dbo.T_EDETALLE_PEDIDO (NOMBRE, DESCRIPCION) VALUES ('Terminado', NULL);

----------------------------Estados Maquina/Herramienta---------------------------------
INSERT INTO dbo.T_EMAQUINA (NOMBRE, DESCRIPCION) VALUES ('Disponible', NULL);
INSERT INTO dbo.T_EMAQUINA (NOMBRE, DESCRIPCION) VALUES ('No Disponible', NULL);
INSERT INTO dbo.T_EMAQUINA (NOMBRE, DESCRIPCION) VALUES ('Dada de Baja', NULL);

----------------------------Estado Orden de Compra---------------------------------
INSERT INTO dbo.T_EORDEN_COMPRA (NOMBRE, DESCRIPCION) VALUES ('Pendiente', '');
INSERT INTO dbo.T_EORDEN_COMPRA (NOMBRE, DESCRIPCION) VALUES ('Cancelada', '');
INSERT INTO dbo.T_EORDEN_COMPRA (NOMBRE, DESCRIPCION) VALUES ('Concretada Parcial', '');
INSERT INTO dbo.T_EORDEN_COMPRA (NOMBRE, DESCRIPCION) VALUES ('Concretada Total', '');
INSERT INTO dbo.T_EORDEN_COMPRA (NOMBRE, DESCRIPCION) VALUES ('Enviada', '');
----------------------------Estado Detalle Orden de Compra---------------------------------
INSERT INTO dbo.T_EDETALLE_ORDEN_COMPRA (NOMBRE, DESCRIPCION) VALUES ('Pendiente', '');
INSERT INTO dbo.T_EDETALLE_ORDEN_COMPRA (NOMBRE, DESCRIPCION) VALUES ('Cancelada', '');
INSERT INTO dbo.T_EDETALLE_ORDEN_COMPRA (NOMBRE, DESCRIPCION) VALUES ('Concretada Parcial', '');
INSERT INTO dbo.T_EDETALLE_ORDEN_COMPRA (NOMBRE, DESCRIPCION) VALUES ('Concretada Total', '');
