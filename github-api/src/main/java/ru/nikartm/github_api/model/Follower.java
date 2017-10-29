package ru.nikartm.github_api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Follower implements Parcelable{

	private long id;
	private String login;
	private String type;
	private String url;

	@SerializedName("gists_url")
	private String gistsUrl;

	@SerializedName("repos_url")
	private String reposUrl;

	@SerializedName("following_url")
	private String followingUrl;

	@SerializedName("starred_url")
	private String starredUrl;

	@SerializedName("followers_url")
	private String followersUrl;

	@SerializedName("subscriptions_url")
	private String subscriptionsUrl;

	@SerializedName("received_events_url")
	private String receivedEventsUrl;

	@SerializedName("avatar_url")
	private String avatarUrl;

	@SerializedName("events_url")
	private String eventsUrl;

	@SerializedName("html_url")
	private String htmlUrl;

	@SerializedName("site_admin")
	private boolean siteAdmin;

	@SerializedName("gravatar_id")
	private String gravatarId;

	@SerializedName("organizations_url")
	private String organizationsUrl;

	public Follower() {
	}

	public Follower(Parcel parcel) {
		id = parcel.readLong();
		login = parcel.readString();
		type = parcel.readString();
		url = parcel.readString();
		gistsUrl = parcel.readString();
		reposUrl = parcel.readString();
		followingUrl = parcel.readString();
		starredUrl = parcel.readString();
		followersUrl = parcel.readString();
		subscriptionsUrl = parcel.readString();
		receivedEventsUrl = parcel.readString();
		avatarUrl = parcel.readString();
		eventsUrl = parcel.readString();
		htmlUrl = parcel.readString();
		siteAdmin = parcel.readByte() != 0;
		gravatarId = parcel.readString();
		organizationsUrl = parcel.readString();
	}

	public long getId() {
		return id;
	}

	public Follower setId(long id) {
		this.id = id;
		return this;
	}

	public String getLogin() {
		return login;
	}

	public Follower setLogin(String login) {
		this.login = login;
		return this;
	}

	public String getType() {
		return type;
	}

	public Follower setType(String type) {
		this.type = type;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public Follower setUrl(String url) {
		this.url = url;
		return this;
	}

	public String getGistsUrl() {
		return gistsUrl;
	}

	public Follower setGistsUrl(String gistsUrl) {
		this.gistsUrl = gistsUrl;
		return this;
	}

	public String getReposUrl() {
		return reposUrl;
	}

	public Follower setReposUrl(String reposUrl) {
		this.reposUrl = reposUrl;
		return this;
	}

	public String getFollowingUrl() {
		return followingUrl;
	}

	public Follower setFollowingUrl(String followingUrl) {
		this.followingUrl = followingUrl;
		return this;
	}

	public String getStarredUrl() {
		return starredUrl;
	}

	public Follower setStarredUrl(String starredUrl) {
		this.starredUrl = starredUrl;
		return this;
	}

	public String getFollowersUrl() {
		return followersUrl;
	}

	public Follower setFollowersUrl(String followersUrl) {
		this.followersUrl = followersUrl;
		return this;
	}

	public String getSubscriptionsUrl() {
		return subscriptionsUrl;
	}

	public Follower setSubscriptionsUrl(String subscriptionsUrl) {
		this.subscriptionsUrl = subscriptionsUrl;
		return this;
	}

	public String getReceivedEventsUrl() {
		return receivedEventsUrl;
	}

	public Follower setReceivedEventsUrl(String receivedEventsUrl) {
		this.receivedEventsUrl = receivedEventsUrl;
		return this;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public Follower setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
		return this;
	}

	public String getEventsUrl() {
		return eventsUrl;
	}

	public Follower setEventsUrl(String eventsUrl) {
		this.eventsUrl = eventsUrl;
		return this;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public Follower setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
		return this;
	}

	public boolean isSiteAdmin() {
		return siteAdmin;
	}

	public Follower setSiteAdmin(boolean siteAdmin) {
		this.siteAdmin = siteAdmin;
		return this;
	}

	public String getGravatarId() {
		return gravatarId;
	}

	public Follower setGravatarId(String gravatarId) {
		this.gravatarId = gravatarId;
		return this;
	}

	public String getOrganizationsUrl() {
		return organizationsUrl;
	}

	public Follower setOrganizationsUrl(String organizationsUrl) {
		this.organizationsUrl = organizationsUrl;
		return this;
	}

	public static final Parcelable.Creator<Follower> CREATOR = new Parcelable.Creator<Follower>() {

		@Override
		public Follower createFromParcel(Parcel source) {
			return new Follower(source);
		}

		@Override
		public Follower[] newArray(int size) {
			return new Follower[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeString(login);
		dest.writeString(type);
		dest.writeString(url);
		dest.writeString(gistsUrl);
		dest.writeString(reposUrl);
		dest.writeString(followingUrl);
		dest.writeString(starredUrl);
		dest.writeString(followersUrl);
		dest.writeString(subscriptionsUrl);
		dest.writeString(receivedEventsUrl);
		dest.writeString(avatarUrl);
		dest.writeString(eventsUrl);
		dest.writeString(htmlUrl);
		dest.writeByte((byte) (siteAdmin ? 1 : 0));
		dest.writeString(gravatarId);
		dest.writeString(organizationsUrl);
	}
}