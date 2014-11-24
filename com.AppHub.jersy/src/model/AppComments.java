package model;

public class AppComments {
	private String userName, appId, createTime, appComment, commentId;
	private boolean approvalComment;
	
	public AppComments(String userName, String appId, String createTime,
			String appComment, String commentId, boolean approvalComment) {
		this.userName = userName;
		this.appId = appId;
		this.createTime = createTime;
		this.appComment = appComment;
		this.commentId = commentId;
		this.approvalComment = approvalComment;
	}
	
	public AppComments(String userName, String appId,
			String appComment, String commentId, boolean approvalComment) {
		this.userName = userName;
		this.appId = appId;
		this.createTime = null;
		this.appComment = appComment;
		this.commentId = commentId;
		this.approvalComment = approvalComment;
	}
	
	public AppComments() {}
	
	@Override
	public String toString() {
		return "AppComments [userName=" + userName + ", appId=" + appId
				+ ", createTime=" + createTime + ", appComment=" + appComment
				+ ", commentId=" + commentId + ", approvalComment="
				+ approvalComment + "]";
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @param appId the appId to set
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * @param appComment the appComment to set
	 */
	public void setAppComment(String appComment) {
		this.appComment = appComment;
	}

	/**
	 * @param commentId the commentId to set
	 */
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	/**
	 * @param approvalComment the approvalComment to set
	 */
	public void setApprovalComment(boolean approvalComment) {
		this.approvalComment = approvalComment;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @return the appId
	 */
	public String getAppId() {
		return appId;
	}
	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * @return the appComment
	 */
	public String getAppComment() {
		return appComment;
	}
	/**
	 * @return the commentId
	 */
	public String getCommentId() {
		return commentId;
	}
	/**
	 * @return the approvalComment
	 */
	public boolean isApprovalComment() {
		return approvalComment;
	}
	
}
