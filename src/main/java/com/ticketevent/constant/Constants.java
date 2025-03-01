package com.ticketevent.constant;

public class Constants {

    public static final String ERROR_SENDING_VERIFICATION_EMAIL = "Erro ao enviar verificação de email";
    public static final String NEW_USER_ACCOUNT_VERIFICATION = "Verificação de nova conta";
    public static final String PASSWORD_RESET_REQUEST = "Pedido de reposição da palavra-passe";

    public static final String USER_NOT_FOUND_MESSAGE = "Utilizador não encontrado";
    public static final String USER_NOT_VERIFIED_MESSAGE = "Utilizador não verificado";
    public static final String WRONG_CREDENTIALS_MESSAGE = "Credenciais inválidas";
    public static final String WRONG_PASSWORD_MESSAGE = "Palavra-passe errada";
    public static final String INVALID_TOKEN_MESSAGE = "Token inválido";

    public static final String ERROR_ON_CREATE_USER = "Error ao gravar utilizador";
    public static final String ACCOUNT_ALREADY_VERIFIED = "Esta conta já foi verificada, por favor, inicie sessão..";

    public static final String ACCOUNT_SUCCESSFULLY_VERIFIED_MESSAGE = "E-mail verificado com sucesso. Agora pode aceder à sua conta";
    public static final String TOKEN_ALREADY_EXPIRED = "Token já expirado";
    public static final String NEW_VERIFICATION_TOKEN_MESSAGE = "Novo token de verificação enviado para o seu e-mail";
    public static final String USER_ALREADY_EXIST_MESSAGE=  "Erro ao salvar utilizador, o email ou o número de telefone já existe";


    public static final String[] UN_SECURED_URLs = {"/login", "/user/all", "/events/**"};
    public static final String[] ADMIN_WHITELIST = {"/admin/**", "/user/**"};



}
