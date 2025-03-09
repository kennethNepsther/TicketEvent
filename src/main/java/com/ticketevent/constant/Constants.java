package com.ticketevent.constant;

public class Constants {

    public static final String ERROR_SENDING_VERIFICATION_EMAIL = "Erro ao enviar verificação de email";
    public static final String NEW_USER_ACCOUNT_VERIFICATION = "Verificação de nova conta";
    public static final String PASSWORD_RESET_REQUEST = "Pedido de reposição da palavra-passe";
    public static final String USER_NOT_FOUND_MESSAGE = "Utilizador não encontrado";

    public static final String EVENT_NOT_FOUND_MESSAGE = "EVENTO NÃO ENCONTRADO";
    public static final String ERROR_ON_CREATE_EVENT = "ERRO AO CRIAR EVENTO";
    public static final String EVENT_SAVE_SUCCESSFULLY_MESSAGE = "EVENTO SALVO COM SUCESSO";
    public static final String EVENT_UPDATE_SUCCESSFULLY_MESSAGE = "EVENTO ACTUALIZADO COM SUCESSO";
    public static final String EVENT_DELETE_SUCCESSFULLY_MESSAGE = "EVENTO APAGADO COM SUCESSO";
    public static final String EVENT_INVALID_MESSAGE = "EVENTO INVÁLIDO";
    public static final String EVENT_APPROVED_MESSAGE = "APROVADO";
    public static final String EVENT_ALREADY_EXISTS_MESSAGE = "JÁ EXISTE UM EVENTO COM ESTE NOME";


    public static final String USER_SAVE_SUCCESSFULLY_MESSAGE = "UTILIZADOR SALVO  COM SUCESSO";
    public static final String USER_ALREADY_EXISTS_WITH_EMAIL = "JÁ EXISTE UM UTILIZADOR COM ESTE E-MAIL";
    public static final String USER_ALREADY_EXISTS_WITH_PHONE = "JÁ EXISTE UM UTLIZADOR COM ESTE TELEFONE";
    public static final String USER_NOT_VERIFIED_MESSAGE = "UTILIZADOR INACTIVO, VERIFIQUE SE O E-MAIL";
    public static final String WRONG_CREDENTIALS_MESSAGE = "CREDENCIAIS INVÁLIDA";
    public static final String INVALID_TOKEN_MESSAGE = "Token inválido";

    public static final String ERROR_ON_CREATE_USER = "Error ao gravar utilizador";
    public static final String ACCOUNT_ALREADY_VERIFIED = "Esta conta já foi verificada, por favor, inicie sessão..";

    public static final String ACCOUNT_SUCCESSFULLY_VERIFIED_MESSAGE = "E-mail verificado com sucesso. Agora pode aceder à sua conta";
    public static final String TOKEN_ALREADY_EXPIRED = "Token já expirado";

    public static final String NEW_VERIFICATION_TOKEN_MESSAGE = "Novo token de verificação enviado para o seu e-mail";
    public static final String USER_ALREADY_EXIST_MESSAGE=  "O EMAIL OU TELEFONE JÁ EXISTE";



    // URLS
    public static final String[] UN_SECURED_URLs = {"/login","/user/save", "/user/verifyEmail/account",
            "/user/resendVerificationToken", "/event/find/**", "/event/all"};

   public static final String[] ADMIN_WHITELIST = {"/admin/**", "/event/save"};

    public static final String[] USER_WHITELIST = {"/user/all", "/event/save"};







}
