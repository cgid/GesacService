-- MySQL Script generated by MySQL Workbench
-- 11/01/16 13:58:09
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema siscentralrel
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema siscentralrel
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `siscentralrel` DEFAULT CHARACTER SET latin1 ;
USE `siscentralrel` ;

-- -----------------------------------------------------
-- Table `siscentralrel`.`pid`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siscentralrel`.`pid` (
  `cod_pid` INT(11) NOT NULL,
  `cod_gesac` INT(11) NULL DEFAULT NULL,
  `cod_tc` INT(11) NULL DEFAULT NULL,
  `cod_cd` INT(11) NULL DEFAULT NULL,
  `cod_crc` INT(11) NULL DEFAULT NULL,
  `nome_estabelecimento` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`cod_pid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siscentralrel`.`perfil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siscentralrel`.`perfil` (
  `id_perfil` INT(11) NOT NULL AUTO_INCREMENT,
  `descricao_perfil` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_perfil`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siscentralrel`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siscentralrel`.`usuario` (
  `id_usuario` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `login` VARCHAR(100) NOT NULL,
  `senha` VARCHAR(100) NOT NULL,
  `Perfil_cod_perfil` INT(11) NOT NULL,
  `ativo` TINYINT(4) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  INDEX `fk_Usuario_Perfil_idx` (`Perfil_cod_perfil` ASC),
  CONSTRAINT `fk_Usuario_Perfil`
    FOREIGN KEY (`Perfil_cod_perfil`)
    REFERENCES `siscentralrel`.`perfil` (`id_perfil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siscentralrel`.`servico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siscentralrel`.`servico` (
  `id_servico` INT(11) NOT NULL AUTO_INCREMENT,
  `dt_criacao_servico` DATETIME NOT NULL,
  `descricao` VARCHAR(300) NOT NULL,
  `Dt_encerramento` DATETIME NULL DEFAULT NULL,
  `status` TINYINT(4) NOT NULL,
  `usuario_id_usuario` INT(11) NOT NULL,
  PRIMARY KEY (`id_servico`),
  INDEX `fk_servico_usuario1_idx` (`usuario_id_usuario` ASC),
  CONSTRAINT `fk_servico_usuario1`
    FOREIGN KEY (`usuario_id_usuario`)
    REFERENCES `siscentralrel`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siscentralrel`.`solicitacoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siscentralrel`.`solicitacoes` (
  `id_solicitacao` INT(11) NOT NULL AUTO_INCREMENT,
  `Qtde_tentativas` INT(11) NULL DEFAULT NULL,
  `Dt_ult_tentativa` DATETIME NOT NULL,
  `Dt_agenda` DATETIME NULL DEFAULT NULL,
  `em_chamado` TINYINT(1) NULL DEFAULT NULL,
  `contato_ok` TINYINT(1) NULL DEFAULT NULL,
  `PID_cod_pid` INT(11) NOT NULL,
  `servico_id_servico` INT(11) NOT NULL,
  PRIMARY KEY (`id_solicitacao`, `PID_cod_pid`, `servico_id_servico`),
  UNIQUE INDEX `PID_cod_pid_UNIQUE` (`PID_cod_pid` ASC, `servico_id_servico` ASC),
  INDEX `fk_Solicitacoes_PID1_idx` (`PID_cod_pid` ASC),
  INDEX `fk_solicitacoes_servico1_idx` (`servico_id_servico` ASC),
  CONSTRAINT `fk_Solicitacoes_PID1`
    FOREIGN KEY (`PID_cod_pid`)
    REFERENCES `siscentralrel`.`pid` (`cod_pid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_solicitacoes_servico1`
    FOREIGN KEY (`servico_id_servico`)
    REFERENCES `siscentralrel`.`servico` (`id_servico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siscentralrel`.`chamado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siscentralrel`.`chamado` (
  `id_chamado` INT(11) NOT NULL AUTO_INCREMENT,
  `dt_chamado_aberto` DATETIME NOT NULL,
  `dt_chamado_finalizado` DATETIME NOT NULL,
  `observacao` VARCHAR(500) NULL DEFAULT NULL,
  `Usuario_cod_usuario` INT(11) NOT NULL,
  `Solicitacoes_id_solicitacao` INT(11) NOT NULL,
  PRIMARY KEY (`id_chamado`),
  INDEX `fk_chamado_Usuario1_idx` (`Usuario_cod_usuario` ASC),
  INDEX `fk_chamado_Solicitacoes1_idx` (`Solicitacoes_id_solicitacao` ASC),
  CONSTRAINT `fk_chamado_Solicitacoes1`
    FOREIGN KEY (`Solicitacoes_id_solicitacao`)
    REFERENCES `siscentralrel`.`solicitacoes` (`id_solicitacao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_chamado_Usuario1`
    FOREIGN KEY (`Usuario_cod_usuario`)
    REFERENCES `siscentralrel`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;



-- -----------------------------------------------------
-- Table `siscentralrel`.`contato`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siscentralrel`.`contato` (
  `id_contato` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NULL DEFAULT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `PID_cod_pid` INT(11) NOT NULL,
  `Situacao`  TINYINT(1) NULL DEFAULT NULL,
  `usuario_id_usuario` INT(11) NOT NULL,
  PRIMARY KEY (`id_contato`, `PID_cod_pid`),
  UNIQUE INDEX `contato/telefone/ddd` (`id_contato` ASC, `nome` ASC, `PID_cod_pid` ASC),
  INDEX `fk_Contato_PID1_idx` (`PID_cod_pid` ASC),
  INDEX `fk_contato_usuario1_idx` (`usuario_id_usuario` ASC),
  CONSTRAINT `fk_Contato_PID1`
    FOREIGN KEY (`PID_cod_pid`)
    REFERENCES `siscentralrel`.`pid` (`cod_pid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contato_usuario1`
    FOREIGN KEY (`usuario_id_usuario`)
    REFERENCES `siscentralrel`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB

DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siscentralrel`.`Log_contato`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siscentralrel`.`Log_contato` (
  
  `id_log_contato` INT NOT NULL AUTO_INCREMENT,
`operacao` VARCHAR(45) NOT NULL,
  `contato_id_contato` INT(11) NOT NULL,
  INDEX `fk_Log_contato_contato_idx` (`contato_id_contato` ASC),
  PRIMARY KEY (`id_log_contato`),
  CONSTRAINT `fk_Log_contato_contato`
    FOREIGN KEY (`contato_id_contato`)
    REFERENCES `siscentralrel`.`contato` (`id_contato`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `siscentralrel` ;


-- -----------------------------------------------------
-- Table `siscentralrel`.`municipio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siscentralrel`.`municipio` (
  `cod_IBGE` INT(11) NOT NULL,
  `nome_municipio` VARCHAR(100) NULL DEFAULT NULL,
  `UF` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`cod_IBGE`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siscentralrel`.`endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siscentralrel`.`endereco` (
  `id_endereco` INT(11) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(200) NULL DEFAULT NULL,
  `numero` VARCHAR(100) NULL DEFAULT NULL,
  `bairro` VARCHAR(100) NULL DEFAULT NULL,
  `cep` VARCHAR(100) NULL DEFAULT NULL,
  `complemento` VARCHAR(100) NULL DEFAULT NULL,
  `Municipio_cod_IBGE` INT(11) NOT NULL,
  `PID_cod_pid` INT(11) NOT NULL,
  `valido` TINYINT(1) NULL DEFAULT NULL,
  `dt_atualizacao` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id_endereco`, `Municipio_cod_IBGE`, `PID_cod_pid`),
  INDEX `fk_Endereco_Municipio1_idx` (`Municipio_cod_IBGE` ASC),
  INDEX `fk_Endereco_PID1_idx` (`PID_cod_pid` ASC),
  CONSTRAINT `fk_Endereco_Municipio1`
    FOREIGN KEY (`Municipio_cod_IBGE`)
    REFERENCES `siscentralrel`.`municipio` (`cod_IBGE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Endereco_PID1`
    FOREIGN KEY (`PID_cod_pid`)
    REFERENCES `siscentralrel`.`pid` (`cod_pid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siscentralrel`.`endereco_novo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siscentralrel`.`endereco_novo` (
  `id_endereco` INT(11) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(200) NULL DEFAULT NULL,
  `numero` VARCHAR(100) NULL DEFAULT NULL,
  `bairro` VARCHAR(100) NULL DEFAULT NULL,
  `cep` VARCHAR(100) NULL DEFAULT NULL,
  `complemento` VARCHAR(100) NULL DEFAULT NULL,
  `Municipio_cod_IBGE` INT(11) NOT NULL,
  `PID_cod_pid` INT(11) NOT NULL,
  `valido` TINYINT(1) NULL DEFAULT NULL,
  `usuario_id_usuario` INT(11) NOT NULL,
  PRIMARY KEY (`id_endereco`, `Municipio_cod_IBGE`, `PID_cod_pid`),
  INDEX `fk_Endereco_novo_Municipio1_idx` (`Municipio_cod_IBGE` ASC),
  INDEX `fk_Endereco_novo_PID1_idx` (`PID_cod_pid` ASC),
  INDEX `fk_endereco_novo_usuario1_idx` (`usuario_id_usuario` ASC),
  CONSTRAINT `fk_Endereco_novo_Municipio1`
    FOREIGN KEY (`Municipio_cod_IBGE`)
    REFERENCES `siscentralrel`.`municipio` (`cod_IBGE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Endereco_novo_PID1`
    FOREIGN KEY (`PID_cod_pid`)
    REFERENCES `siscentralrel`.`pid` (`cod_pid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_endereco_novo_usuario1`
    FOREIGN KEY (`usuario_id_usuario`)
    REFERENCES `siscentralrel`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `siscentralrel`.`log_chamado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siscentralrel`.`log_chamado` (
  `id_log_chamado` INT(11) NOT NULL AUTO_INCREMENT,
  `operacao` VARCHAR(45) NULL DEFAULT NULL,
  `chamado_id_chamado` INT(11) NULL DEFAULT NULL,
  `duracao` TIME NOT NULL,
  PRIMARY KEY (`id_log_chamado`),
  INDEX `fk_Log_chamado_idx` (`chamado_id_chamado` ASC),
  CONSTRAINT `fk_Log_chamado`
    FOREIGN KEY (`chamado_id_chamado`)
    REFERENCES `siscentralrel`.`chamado` (`id_chamado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `siscentralrel`.`log_servico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siscentralrel`.`log_servico` (
  `id_log_servico` INT(11) NOT NULL AUTO_INCREMENT,
  `operacao` VARCHAR(45) NULL DEFAULT NULL,
  `servico_id_servico` INT(11) NOT NULL,
  PRIMARY KEY (`id_log_servico`),
  INDEX `fk_Log_servico_servico1_idx` (`servico_id_servico` ASC),
  CONSTRAINT `fk_Log_servico_servico1`
    FOREIGN KEY (`servico_id_servico`)
    REFERENCES `siscentralrel`.`servico` (`id_servico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `siscentralrel`.`perguntas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siscentralrel`.`perguntas` (
  `id_Perguntas` INT(11) NOT NULL AUTO_INCREMENT,
  `pergunta` VARCHAR(300) NULL DEFAULT NULL,
  `servico_id_servico` INT(11) NOT NULL,
  PRIMARY KEY (`id_Perguntas`),
  INDEX `fk_perguntas_servico1_idx` (`servico_id_servico` ASC),
  CONSTRAINT `fk_perguntas_servico1`
    FOREIGN KEY (`servico_id_servico`)
    REFERENCES `siscentralrel`.`servico` (`id_servico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siscentralrel`.`respostas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siscentralrel`.`respostas` (
  `id_Respostas` INT(11) NOT NULL AUTO_INCREMENT,
  `Resposta` VARCHAR(1000) NULL DEFAULT NULL,
  `chamado_cod_chamado` INT(11) NOT NULL,
  PRIMARY KEY (`id_Respostas`),
  INDEX `fk_Respostas_chamado1_idx` (`chamado_cod_chamado` ASC),
  CONSTRAINT `fk_Respostas_chamado1`
    FOREIGN KEY (`chamado_cod_chamado`)
    REFERENCES `siscentralrel`.`chamado` (`id_chamado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siscentralrel`.`telefone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siscentralrel`.`telefone` (
  `id_telefone` INT(11) NOT NULL AUTO_INCREMENT,
  `ddd` INT(11) NOT NULL,
  `telefone` INT(11) NOT NULL,
  `Contato_id_contato` INT(11) NOT NULL,
  `Situacao` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id_telefone`),
  INDEX `fk_Telefone_Contato1_idx` (`Contato_id_contato` ASC),
  CONSTRAINT `fk_Telefone_Contato1`
    FOREIGN KEY (`Contato_id_contato`)
    REFERENCES `siscentralrel`.`contato` (`id_contato`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `siscentralrel` ;

-- -----------------------------------------------------
-- procedure data_agendamento
-- -----------------------------------------------------

DELIMITER $$
USE `siscentralrel`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `data_agendamento`()
BEGIN

update siscentralrel.solicitacoes set em_chamado = 1

where dt_agenda >= date(current_datetime());

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure mudar_status
-- -----------------------------------------------------

DELIMITER $$
USE `siscentralrel`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `mudar_status`()
BEGIN


update siscentralrel.solicitacoes set em_chamado = 1

where time(dt_ult_tentativa) - hour(current_timestamp()) >= 1 and em_chamado = 2
;
END$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;