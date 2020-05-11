package com.zhiling.bank.util;

public class Constants {
	
	
	/*********************** page size ************************************************************/
	public static final Integer PAGESIZE = 8;
	/*********************** redis keys ************************************************/
	
	public static final String REDIS_KEY_MSGIDS = "msgids";
	public static final String REDIS_KEY_ACCOUNTS = "accounts";
	public static final String REDIS_KEY_LOGS = "logs";
	public static final String REDIS_KEY_RESENDIDS = "resendids";
	public static final String REDIS_KEY_FAILED = "failedids";
	
	public static final String MYREDISTEMPLATE = "myredistemplate";
	
	/*********************  schedule params *****************************************/
	
	public static final Integer RETRY_PERTIME = 500;
	public static final Integer SAVE_PERTIME = 1000;
	public static final Long SAVE_TRIGGER = 100L; 
	
	/***********************  queuename exchangename **************************************************/
	
	public static final String QUEUE_NAME = "accque";
	public static final String EXCHANGE_NAME = "accexchange";
	
	
	/*********************** acclog params name *********************************************/
	
	public static final Integer MAX_RETRYCOUNT = 3;
	public static final String MSG_STATUS_SENDING = "0";
	public static final String MSG_STATUS_SUCCESS = "1";
	public static final String MSG_STATUS_FAILED = "2";
	public static final String MSG_STATUS_NOTROUTE = "3";
	public static final Integer RETRY_INTERVAL = 2;
	
	
	/********************** rabbit queue params name *******************************/
	
	public static final String X_EXPIRES = "x-expires";
	public static final String X_MESSAGE_TTL= "x-message-ttl";
	public static final String X_MAX_LENGTH = "x-max-length";
	public static final String X_MAX_LENGTH_BYTES = "x-max-length-bytes";
	public static final String X_OVERFLOW = "x-overflow";
	public static final String X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";
	public static final String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";
	public static final String X_SINGLE_ACTIVE_CONSUMER = "x-single-active-consumer";
	public static final String X_MAX_PRIORITY = "x-max-priority";
	public static final String X_QUEUE_MODE = "x-queue-mode";
	public static final String X_QUEUE_MASTER_LOCATOR = "x-queue-master-locator";
	
	
	
	
	
}
