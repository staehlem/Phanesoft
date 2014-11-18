package model;
import java.util.ArrayList;
import java.util.UUID;

public class App {
	
	private String appID, appName, appUrl, appDeveloper, description,
			appPlatform;
	private double appRating, appCost, appVersion;
	private boolean appAvailable, appApproved;
	private ArrayList<AppComments> appComments = new ArrayList<AppComments>();

	public ArrayList<AppComments> getAppComments() {
		return appComments;
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
			double appVersion, ArrayList<AppComments> appComments) {
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
			Boolean appAvailable, double appCost, double appVersion, ArrayList<AppComments> appComments) {

		this(appID, appName, appLocation, appDeveloper, description,
				appPlatform, appAvailable, 0.0, appCost, appVersion, appComments);

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
	public App(String appName, String appLocation,
			String appDeveloper, String description, String appPlatform,
			Boolean appAvailable, double appRating, double appCost, double appVersion, ArrayList<AppComments> appComments) {

		this(UUID.randomUUID()+"", appName, appLocation, appDeveloper, description,
				appPlatform, appAvailable, appRating, appCost, appVersion, appComments);

	}

	@Override
	public String toString() {
		return "App [appID=" + appID + ", appName=" + appName + ", appUrl="
				+ appUrl + ", appDeveloper=" + appDeveloper + ", description="
				+ description + ", appPlatform=" + appPlatform + ", appRating="
				+ appRating + ", appCost=" + appCost + ", appVersion="
				+ appVersion + ", appAvailable=" + appAvailable
				+ ", appApproved=" + appApproved + ", appComments="
				+ appComments + "]";
	}

	public App(){}
	
	

}
