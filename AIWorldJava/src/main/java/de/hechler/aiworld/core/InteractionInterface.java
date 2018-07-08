package de.hechler.aiworld.core;

import java.util.Set;

public interface InteractionInterface {

	public final static String E_FORMAT_QUESTION = "E_FORMAT_QUESTION";
	public final static String E_UNKNOWN_TOPIC = "E_UNKNOWN_TOPIC";
	public final static String E_CLOSED = "E_CLOSED";
	public final static String S_OK = "S_OK";
	
	Set<String> queryTopics();

	default boolean understandsTopic(String topic) {
		return queryTopics().contains(topic);
	}
	
	String ask(String topic, String question);
	
	String endConversation();
	
}
