package com.talentport.login.dto;

public class RolDTO {
	
	private boolean moduleUser;
	private boolean moduleQuestion;
	private boolean moduleConfig;
	private boolean modulePromotions;
	private boolean moduleCampaing;
	
	public boolean isModuleUser() {
		return moduleUser;
	}
	public void setModuleUser(boolean moduleUser) {
		this.moduleUser = moduleUser;
	}
	public boolean isModuleQuestion() {
		return moduleQuestion;
	}
	public void setModuleQuestion(boolean moduleQuestion) {
		this.moduleQuestion = moduleQuestion;
	}
	public boolean isModuleConfig() {
		return moduleConfig;
	}
	public void setModuleConfig(boolean moduleConfig) {
		this.moduleConfig = moduleConfig;
	}
	public boolean isModulePromotions() {
		return modulePromotions;
	}
	public void setModulePromotions(boolean modulePromotions) {
		this.modulePromotions = modulePromotions;
	}
	public boolean isModuleCampaing() {
		return moduleCampaing;
	}
	public void setModuleCampaing(boolean moduleCampaing) {
		this.moduleCampaing = moduleCampaing;
	}
	
	
}
