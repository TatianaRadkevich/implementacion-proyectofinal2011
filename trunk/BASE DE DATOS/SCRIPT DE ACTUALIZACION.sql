

/*SCRIPT DE ACTUALIZACI�N*/

/*Cambios solicitados por Rodrigo - 10/08/2011*/

EXEC sp_rename 'T_USUARIOS.CONTRASE�A', 'CONTRASENIA';
GO
EXEC sp_rename 'T_CLIENTES_WEB.CONTRASE�A', 'CONTRASENIA';