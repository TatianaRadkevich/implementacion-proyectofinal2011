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
----------------------------------Tipo Pedido------------------------------------------
INSERT INTO dbo.T_TPEDIDO (NOMBRE, DESCRIPCION) VALUES ('MP del cliente', NULL);
INSERT INTO dbo.T_TPEDIDO (NOMBRE, DESCRIPCION) VALUES ('MP propia', NULL);
----------------------------Estados Detalle Pedido----------------------------------
INSERT INTO dbo.T_EDETALLE_PEDIDO (NOMBRE, DESCRIPCION) VALUES ('Pendiente', NULL);
INSERT INTO dbo.T_EDETALLE_PEDIDO (NOMBRE, DESCRIPCION) VALUES ('Producción', NULL);
INSERT INTO dbo.T_EDETALLE_PEDIDO (NOMBRE, DESCRIPCION) VALUES ('Terminado', NULL);