

/*SCRIPT DE ACTUALIZACIÓN*/

/*Cambios solicitados por Rodrigo - 10/08/2011*/

EXEC sp_rename 'T_USUARIOS.CONTRASEÑA', 'CONTRASENIA';
GO
EXEC sp_rename 'T_CLIENTES_WEB.CONTRASEÑA', 'CONTRASENIA';