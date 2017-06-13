
-- --------------------------------------------------
-- Entity Designer DDL Script for SQL Server 2005, 2008, 2012 and Azure
-- --------------------------------------------------
-- Date Created: 05/30/2017 17:59:10
-- Generated from EDMX file: C:\Users\Javi\Desktop\ApiWebService\ApiWebService\Entity\BikerBoxModels.edmx
-- --------------------------------------------------

SET QUOTED_IDENTIFIER OFF;
GO
USE [BikerBoxDB];
GO
IF SCHEMA_ID(N'dbo') IS NULL EXECUTE(N'CREATE SCHEMA [dbo]');
GO

-- --------------------------------------------------
-- Dropping existing FOREIGN KEY constraints
-- --------------------------------------------------

IF OBJECT_ID(N'[dbo].[FK_FKDireccionesTaller]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[Direcciones] DROP CONSTRAINT [FK_FKDireccionesTaller];
GO
IF OBJECT_ID(N'[dbo].[FK_FKMotoAnuncios]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[Anuncios] DROP CONSTRAINT [FK_FKMotoAnuncios];
GO
IF OBJECT_ID(N'[dbo].[FK_FKReparacionMoto]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[Reparaciones] DROP CONSTRAINT [FK_FKReparacionMoto];
GO
IF OBJECT_ID(N'[dbo].[FK_FKReparacionTaller]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[Reparaciones] DROP CONSTRAINT [FK_FKReparacionTaller];
GO
IF OBJECT_ID(N'[dbo].[FK_FKReparacionValoracion]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[Reparaciones] DROP CONSTRAINT [FK_FKReparacionValoracion];
GO
IF OBJECT_ID(N'[dbo].[FK_FKUsuarioAnuncios]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[Anuncios] DROP CONSTRAINT [FK_FKUsuarioAnuncios];
GO
IF OBJECT_ID(N'[dbo].[FK_FKValoracionesTaller]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[Valoraciones] DROP CONSTRAINT [FK_FKValoracionesTaller];
GO
IF OBJECT_ID(N'[dbo].[FK_FKValoracionesUsuarios]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[Valoraciones] DROP CONSTRAINT [FK_FKValoracionesUsuarios];
GO

-- --------------------------------------------------
-- Dropping existing tables
-- --------------------------------------------------

IF OBJECT_ID(N'[dbo].[Anuncios]', 'U') IS NOT NULL
    DROP TABLE [dbo].[Anuncios];
GO
IF OBJECT_ID(N'[dbo].[Direcciones]', 'U') IS NOT NULL
    DROP TABLE [dbo].[Direcciones];
GO
IF OBJECT_ID(N'[dbo].[Motos]', 'U') IS NOT NULL
    DROP TABLE [dbo].[Motos];
GO
IF OBJECT_ID(N'[dbo].[Reparaciones]', 'U') IS NOT NULL
    DROP TABLE [dbo].[Reparaciones];
GO
IF OBJECT_ID(N'[dbo].[Talleres]', 'U') IS NOT NULL
    DROP TABLE [dbo].[Talleres];
GO
IF OBJECT_ID(N'[dbo].[Usuarios]', 'U') IS NOT NULL
    DROP TABLE [dbo].[Usuarios];
GO
IF OBJECT_ID(N'[dbo].[Valoraciones]', 'U') IS NOT NULL
    DROP TABLE [dbo].[Valoraciones];
GO
IF OBJECT_ID(N'[BikerBoxDBModelStoreContainer].[BB_LoginTaller]', 'U') IS NOT NULL
    DROP TABLE [BikerBoxDBModelStoreContainer].[BB_LoginTaller];
GO
IF OBJECT_ID(N'[BikerBoxDBModelStoreContainer].[BB_LoginUsuario]', 'U') IS NOT NULL
    DROP TABLE [BikerBoxDBModelStoreContainer].[BB_LoginUsuario];
GO

-- --------------------------------------------------
-- Creating all tables
-- --------------------------------------------------

-- Creating table 'Anuncios'
CREATE TABLE [dbo].[Anuncios] (
    [id] int IDENTITY(1,1) NOT NULL,
    [comunidadAutonoma] varchar(20)  NULL,
    [provincia] varchar(20)  NULL,
    [localidad] varchar(30)  NULL,
    [descripcion] varchar(200)  NULL,
    [idMoto] int  NULL,
    [idUsuario] int  NULL
);
GO

-- Creating table 'Direcciones'
CREATE TABLE [dbo].[Direcciones] (
    [id] int IDENTITY(1,1) NOT NULL,
    [calle] varchar(30)  NULL,
    [numero] tinyint  NULL,
    [letra] char(1)  NULL,
    [idTaller] int  NULL
);
GO

-- Creating table 'Motos'
CREATE TABLE [dbo].[Motos] (
    [id] int IDENTITY(1,1) NOT NULL,
    [matricula] char(7)  NULL,
    [marca] varchar(15)  NULL,
    [modelo] varchar(20)  NULL,
    [estilo] varchar(10)  NULL,
    [cilindrada] int  NULL,
    [año] int  NULL,
    [km] int  NULL,
    [color] varchar(10)  NULL
);
GO

-- Creating table 'Reparaciones'
CREATE TABLE [dbo].[Reparaciones] (
    [id] int IDENTITY(1,1) NOT NULL,
    [idReparacion] int  NULL,
    [diagnostico] varchar(30)  NULL,
    [explicacion] nvarchar(300)  NULL,
    [estado] varchar(20)  NULL,
    [prefio] float  NULL,
    [idMoto] int  NULL,
    [idTaller] int  NULL,
    [idValoracion] int  NULL
);
GO

-- Creating table 'Talleres'
CREATE TABLE [dbo].[Talleres] (
    [id] int IDENTITY(1,1) NOT NULL,
    [correo] varchar(20)  NULL,
    [contrasena] varchar(32)  NULL,
    [nombre] varchar(30)  NULL,
    [nEmpleados] tinyint  NULL,
    [descripcion] nvarchar(300)  NULL,
    [imagen] varchar(300)  NULL
);
GO

-- Creating table 'Usuarios'
CREATE TABLE [dbo].[Usuarios] (
    [id] int IDENTITY(1,1) NOT NULL,
    [correo] varchar(20)  NULL,
    [contrasena] varchar(32)  NULL,
    [nombre] varchar(20)  NULL,
    [apellidos] varchar(30)  NULL,
    [dni] char(9)  NULL,
    [fnacimiento] datetime  NULL,
    [localidad] varchar(30)  NULL
);
GO

-- Creating table 'Valoraciones'
CREATE TABLE [dbo].[Valoraciones] (
    [id] int IDENTITY(1,1) NOT NULL,
    [idTaller] int  NULL,
    [idUsuario] int  NULL,
    [comentario] varchar(100)  NULL,
    [nota] tinyint  NULL,
    [fecha] datetime  NULL
);
GO

-- Creating table 'BB_LoginTaller'
CREATE TABLE [dbo].[BB_LoginTaller] (
    [id] int IDENTITY(1,1) NOT NULL,
    [correo] varchar(20)  NULL,
    [contrasena] varchar(32)  NULL
);
GO

-- Creating table 'BB_LoginUsuario'
CREATE TABLE [dbo].[BB_LoginUsuario] (
    [id] int IDENTITY(1,1) NOT NULL,
    [correo] varchar(20)  NULL,
    [contrasena] varchar(32)  NULL
);
GO

-- --------------------------------------------------
-- Creating all PRIMARY KEY constraints
-- --------------------------------------------------

-- Creating primary key on [id] in table 'Anuncios'
ALTER TABLE [dbo].[Anuncios]
ADD CONSTRAINT [PK_Anuncios]
    PRIMARY KEY CLUSTERED ([id] ASC);
GO

-- Creating primary key on [id] in table 'Direcciones'
ALTER TABLE [dbo].[Direcciones]
ADD CONSTRAINT [PK_Direcciones]
    PRIMARY KEY CLUSTERED ([id] ASC);
GO

-- Creating primary key on [id] in table 'Motos'
ALTER TABLE [dbo].[Motos]
ADD CONSTRAINT [PK_Motos]
    PRIMARY KEY CLUSTERED ([id] ASC);
GO

-- Creating primary key on [id] in table 'Reparaciones'
ALTER TABLE [dbo].[Reparaciones]
ADD CONSTRAINT [PK_Reparaciones]
    PRIMARY KEY CLUSTERED ([id] ASC);
GO

-- Creating primary key on [id] in table 'Talleres'
ALTER TABLE [dbo].[Talleres]
ADD CONSTRAINT [PK_Talleres]
    PRIMARY KEY CLUSTERED ([id] ASC);
GO

-- Creating primary key on [id] in table 'Usuarios'
ALTER TABLE [dbo].[Usuarios]
ADD CONSTRAINT [PK_Usuarios]
    PRIMARY KEY CLUSTERED ([id] ASC);
GO

-- Creating primary key on [id] in table 'Valoraciones'
ALTER TABLE [dbo].[Valoraciones]
ADD CONSTRAINT [PK_Valoraciones]
    PRIMARY KEY CLUSTERED ([id] ASC);
GO

-- Creating primary key on [id] in table 'BB_LoginTaller'
ALTER TABLE [dbo].[BB_LoginTaller]
ADD CONSTRAINT [PK_BB_LoginTaller]
    PRIMARY KEY CLUSTERED ([id] ASC);
GO

-- Creating primary key on [id] in table 'BB_LoginUsuario'
ALTER TABLE [dbo].[BB_LoginUsuario]
ADD CONSTRAINT [PK_BB_LoginUsuario]
    PRIMARY KEY CLUSTERED ([id] ASC);
GO

-- --------------------------------------------------
-- Creating all FOREIGN KEY constraints
-- --------------------------------------------------

-- Creating foreign key on [idMoto] in table 'Anuncios'
ALTER TABLE [dbo].[Anuncios]
ADD CONSTRAINT [FK_FKMotoAnuncios]
    FOREIGN KEY ([idMoto])
    REFERENCES [dbo].[Motos]
        ([id])
    ON DELETE NO ACTION ON UPDATE NO ACTION;
GO

-- Creating non-clustered index for FOREIGN KEY 'FK_FKMotoAnuncios'
CREATE INDEX [IX_FK_FKMotoAnuncios]
ON [dbo].[Anuncios]
    ([idMoto]);
GO

-- Creating foreign key on [idUsuario] in table 'Anuncios'
ALTER TABLE [dbo].[Anuncios]
ADD CONSTRAINT [FK_FKUsuarioAnuncios]
    FOREIGN KEY ([idUsuario])
    REFERENCES [dbo].[Usuarios]
        ([id])
    ON DELETE NO ACTION ON UPDATE NO ACTION;
GO

-- Creating non-clustered index for FOREIGN KEY 'FK_FKUsuarioAnuncios'
CREATE INDEX [IX_FK_FKUsuarioAnuncios]
ON [dbo].[Anuncios]
    ([idUsuario]);
GO

-- Creating foreign key on [idTaller] in table 'Direcciones'
ALTER TABLE [dbo].[Direcciones]
ADD CONSTRAINT [FK_FKDireccionesTaller]
    FOREIGN KEY ([idTaller])
    REFERENCES [dbo].[Talleres]
        ([id])
    ON DELETE NO ACTION ON UPDATE NO ACTION;
GO

-- Creating non-clustered index for FOREIGN KEY 'FK_FKDireccionesTaller'
CREATE INDEX [IX_FK_FKDireccionesTaller]
ON [dbo].[Direcciones]
    ([idTaller]);
GO

-- Creating foreign key on [idMoto] in table 'Reparaciones'
ALTER TABLE [dbo].[Reparaciones]
ADD CONSTRAINT [FK_FKReparacionMoto]
    FOREIGN KEY ([idMoto])
    REFERENCES [dbo].[Motos]
        ([id])
    ON DELETE NO ACTION ON UPDATE NO ACTION;
GO

-- Creating non-clustered index for FOREIGN KEY 'FK_FKReparacionMoto'
CREATE INDEX [IX_FK_FKReparacionMoto]
ON [dbo].[Reparaciones]
    ([idMoto]);
GO

-- Creating foreign key on [idTaller] in table 'Reparaciones'
ALTER TABLE [dbo].[Reparaciones]
ADD CONSTRAINT [FK_FKReparacionTaller]
    FOREIGN KEY ([idTaller])
    REFERENCES [dbo].[Talleres]
        ([id])
    ON DELETE NO ACTION ON UPDATE NO ACTION;
GO

-- Creating non-clustered index for FOREIGN KEY 'FK_FKReparacionTaller'
CREATE INDEX [IX_FK_FKReparacionTaller]
ON [dbo].[Reparaciones]
    ([idTaller]);
GO

-- Creating foreign key on [idValoracion] in table 'Reparaciones'
ALTER TABLE [dbo].[Reparaciones]
ADD CONSTRAINT [FK_FKReparacionValoracion]
    FOREIGN KEY ([idValoracion])
    REFERENCES [dbo].[Valoraciones]
        ([id])
    ON DELETE NO ACTION ON UPDATE NO ACTION;
GO

-- Creating non-clustered index for FOREIGN KEY 'FK_FKReparacionValoracion'
CREATE INDEX [IX_FK_FKReparacionValoracion]
ON [dbo].[Reparaciones]
    ([idValoracion]);
GO

-- Creating foreign key on [idTaller] in table 'Valoraciones'
ALTER TABLE [dbo].[Valoraciones]
ADD CONSTRAINT [FK_FKValoracionesTaller]
    FOREIGN KEY ([idTaller])
    REFERENCES [dbo].[Talleres]
        ([id])
    ON DELETE NO ACTION ON UPDATE NO ACTION;
GO

-- Creating non-clustered index for FOREIGN KEY 'FK_FKValoracionesTaller'
CREATE INDEX [IX_FK_FKValoracionesTaller]
ON [dbo].[Valoraciones]
    ([idTaller]);
GO

-- Creating foreign key on [idUsuario] in table 'Valoraciones'
ALTER TABLE [dbo].[Valoraciones]
ADD CONSTRAINT [FK_FKValoracionesUsuarios]
    FOREIGN KEY ([idUsuario])
    REFERENCES [dbo].[Usuarios]
        ([id])
    ON DELETE NO ACTION ON UPDATE NO ACTION;
GO

-- Creating non-clustered index for FOREIGN KEY 'FK_FKValoracionesUsuarios'
CREATE INDEX [IX_FK_FKValoracionesUsuarios]
ON [dbo].[Valoraciones]
    ([idUsuario]);
GO

-- --------------------------------------------------
-- Script has ended
-- --------------------------------------------------