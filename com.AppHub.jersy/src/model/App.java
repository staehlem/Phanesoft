package model;
import java.util.ArrayList;
import java.util.UUID;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class App {
	
	private String appID, appName, appUrl, appDeveloper, description,
			appPlatform, appLocalImage;
	private double appRating, appCost, appVersion;
	private boolean appAvailable, appApproved;
	private ArrayList<AppComments> appComments = new ArrayList<AppComments>();

	public ArrayList<AppComments> getAppComments() {
		return appComments;
	}

	/**
	 * @param appID the appID to set
	 */
	public void setAppID(String appID) {
		this.appID = appID;
	}

	/**
	 * @param appName the appName to set
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * @param appUrl the appUrl to set
	 */
	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	/**
	 * @param appDeveloper the appDeveloper to set
	 */
	public void setAppDeveloper(String appDeveloper) {
		this.appDeveloper = appDeveloper;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param appPlatform the appPlatform to set
	 */
	public void setAppPlatform(String appPlatform) {
		this.appPlatform = appPlatform;
	}

	/**
	 * @param appCost the appCost to set
	 */
	public void setAppCost(double appCost) {
		this.appCost = appCost;
	}

	/**
	 * @param appVersion the appVersion to set
	 */
	public void setAppVersion(double appVersion) {
		this.appVersion = appVersion;
	}

	/**
	 * @param appAvailable the appAvailable to set
	 */
	public void setAppAvailable(boolean appAvailable) {
		this.appAvailable = appAvailable;
	}

	/**
	 * @param appApproved the appApproved to set
	 */
	public void setAppApproved(boolean appApproved) {
		this.appApproved = appApproved;
	}

	/**
	 * @param appComments the appComments to set
	 */
	public void setAppComments(ArrayList<AppComments> appComments) {
		this.appComments = appComments;
	}

	public String getAppID() {
		return appID;
	}

	public String getAppName() {
		return appName;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public String getAppDeveloper() {
		return appDeveloper;
	}

	public String getDescription() {
		return description;
	}

	public String getAppPlatform() {
		return appPlatform;
	}

	public Boolean isAppAvailable() {
		return appAvailable;
	}
	
	public Boolean isAppApproved() {
		return appApproved;
	}

	public double getAppRating() {
		return appRating;
	}

	public double getAppCost() {
		return appCost;
	}

	public double getAppVersion() {
		return appVersion;
	}

	/**
	 * This is a constructor that takes all parameters for an app that has been
	 * previously made and has all the necessary criteria
	 * 
	 * @param appID
	 *            number corresponding to the app
	 * @param appName
	 *            name of app
	 * @param appLocation
	 *            ???
	 * @param appDeveloper
	 *            name of developer(s)
	 * @param description
	 *            what the app does
	 * @param appPlatform
	 *            what platforms the app runs on
	 * @param appAvailable
	 *            when the app will be or was available
	 * @param appRating
	 *            numerical rating of 0.0-5.0 inclusive
	 * @param appCost
	 *            price of app
	 * @param appVersion
	 *            version of app
	 */
	public App(String appID, String appName, String appLocation,
			String appDeveloper, String description, String appPlatform,
			Boolean appAvailable, double appRating, double appCost,
			double appVersion, ArrayList<AppComments> appComments, String appLocalImage) {
		super();
		this.appID = appID;
		this.appName = appName;
		this.appUrl = appLocation;
		this.appDeveloper = appDeveloper;
		this.description = description;
		this.appPlatform = appPlatform;
		this.appAvailable = appAvailable;
		setAppRating(appRating);
		this.appCost = appCost;
		this.appVersion = appVersion;
		this.appComments = appComments;
		this.appLocalImage = appLocalImage;
	}

	/**
	 * this ensures that appRating is a logical and formatted value
	 * 
	 * @param appRating
	 *            the provided appRating
	 */
	private void setAppRating(double appRating) {
		if (appRating > 10) {
			this.appRating = 0;
		} else if (appRating > 5 && appRating <= 10) {
			this.appRating = appRating / 2;
		} else if (appRating < 0.0) {
			this.appRating = 0.0;
		} else {
			this.appRating = appRating;
		}

	}

	/**
	 * Same as ctor that takes all parameters but is for a new app that has not
	 * yet recieved a rating
	 * 
	 * @param appID
	 *            number corresponding to the app
	 * @param appName
	 *            name of app
	 * @param appLocation
	 *            ???
	 * @param appDeveloper
	 *            name of developer(s)
	 * @param description
	 *            what the app does
	 * @param appPlatform
	 *            what platforms the app runs on
	 * @param appAvailable
	 *            when the app will be or was available
	 * @param appCost
	 *            price of app
	 * @param appVersion
	 *            version of app
	 */
	public App(String appID, String appName, String appLocation,
			String appDeveloper, String description, String appPlatform,
			Boolean appAvailable, double appCost, double appVersion, ArrayList<AppComments> appComments, 
			String appLocalImage) {

		this(appID, appName, appLocation, appDeveloper, description,
				appPlatform, appAvailable, 0.0, appCost, appVersion, appComments, appLocalImage);

	}
	
	/**
	 * Same as ctor that takes all parameters but is for a new app that has not
	 * been added to the database, and has no unique ID already assigned
	 * 
	 * @param appID
	 *            number corresponding to the app
	 * @param appName
	 *            name of app
	 * @param appLocation
	 *            ???
	 * @param appDeveloper
	 *            name of developer(s)
	 * @param description
	 *            what the app does
	 * @param appPlatform
	 *            what platforms the app runs on
	 * @param appAvailable
	 *            when the app will be or was available
	 * @param appRating
	 *            numerical rating of 0.0-5.0 inclusive
	 * @param appCost
	 *            price of app
	 * @param appVersion
	 *            version of app
	 */
	@JsonCreator
	public App(@JsonProperty("appName") String appName, @JsonProperty("appLocation") String appLocation,
			@JsonProperty("appDeveloper") String appDeveloper, @JsonProperty("description") String description, 
			@JsonProperty("appPlatform") String appPlatform,
			@JsonProperty("appAvailable") Boolean appAvailable, @JsonProperty("appRating") double appRating, 
			@JsonProperty("appCost") double appCost, @JsonProperty("appVersion") double appVersion,
			@JsonProperty("appComments") ArrayList<AppComments> appComments, 
			@JsonProperty("appLocalImage") String appLocalImage) {

		this(UUID.randomUUID()+"", appName, appLocation, appDeveloper, description,
				appPlatform, appAvailable, appRating, appCost, appVersion, appComments, appLocalImage);

	}

	/**
	 * @return the appLocalImage
	 */
	public String getAppLocalImage() {
		return appLocalImage;
	}

	/**
	 * @param appLocalImage the appLocalImage to set
	 */
	public void setAppLocalImage(String appLocalImage) {
		this.appLocalImage = appLocalImage;
	}

	@Override
	public String toString() {
		return "App [appID=" + appID + ", appName=" + appName + ", appUrl="
				+ appUrl + ", appDeveloper=" + appDeveloper + ", description="
				+ description + ", appPlatform=" + appPlatform
				+ ", appLocalImage=" + appLocalImage + ", appRating="
				+ appRating + ", appCost=" + appCost + ", appVersion="
				+ appVersion + ", appAvailable=" + appAvailable
				+ ", appApproved=" + appApproved + ", appComments="
				+ appComments + "]";
	}

	public App(){}
	
	

}
