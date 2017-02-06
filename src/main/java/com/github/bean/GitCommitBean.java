package com.github.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.codehaus.jackson.type.TypeReference;

import java.lang.reflect.Type;
import java.util.List;


@SuppressWarnings({"unused"})
public class GitCommitBean implements  Comparable{

    public static final Type GIT_COMMIT_BEAN_LIST = new TypeReference<List<GitCommitBean>>() {
    }.getType();

    @SerializedName("author")
    @Expose
    private Author author;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("weeks")
    @Expose
    private List<Week> weeks = null;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Week> getWeeks() {
        return weeks;
    }

    public void setWeeks(List<Week> weeks) {
        this.weeks = weeks;
    }

    @Override
    public int compareTo(Object o) {
        GitCommitBean gitCommitter = (GitCommitBean) o;
        if (this.getTotal() == null) {
            return 1;
        } else if (gitCommitter.getTotal() == null) {
            return -1;
        } else {
            return gitCommitter.getTotal().compareTo(this.getTotal());
        }
    }


    public static class Author {

        @SerializedName("login")
        @Expose
        private String login;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("avatar_url")
        @Expose
        private String avatarUrl;
        @SerializedName("gravatar_id")
        @Expose
        private String gravatarId;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("html_url")
        @Expose
        private String htmlUrl;
        @SerializedName("followers_url")
        @Expose
        private String followersUrl;
        @SerializedName("following_url")
        @Expose
        private String followingUrl;
        @SerializedName("gists_url")
        @Expose
        private String gistsUrl;
        @SerializedName("starred_url")
        @Expose
        private String starredUrl;
        @SerializedName("subscriptions_url")
        @Expose
        private String subscriptionsUrl;
        @SerializedName("organizations_url")
        @Expose
        private String organizationsUrl;
        @SerializedName("repos_url")
        @Expose
        private String reposUrl;
        @SerializedName("events_url")
        @Expose
        private String eventsUrl;
        @SerializedName("received_events_url")
        @Expose
        private String receivedEventsUrl;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("site_admin")
        @Expose
        private Boolean siteAdmin;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getGravatarId() {
            return gravatarId;
        }

        public void setGravatarId(String gravatarId) {
            this.gravatarId = gravatarId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHtmlUrl() {
            return htmlUrl;
        }

        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }

        public String getFollowersUrl() {
            return followersUrl;
        }

        public void setFollowersUrl(String followersUrl) {
            this.followersUrl = followersUrl;
        }

        public String getFollowingUrl() {
            return followingUrl;
        }

        public void setFollowingUrl(String followingUrl) {
            this.followingUrl = followingUrl;
        }

        public String getGistsUrl() {
            return gistsUrl;
        }

        public void setGistsUrl(String gistsUrl) {
            this.gistsUrl = gistsUrl;
        }

        public String getStarredUrl() {
            return starredUrl;
        }

        public void setStarredUrl(String starredUrl) {
            this.starredUrl = starredUrl;
        }

        public String getSubscriptionsUrl() {
            return subscriptionsUrl;
        }

        public void setSubscriptionsUrl(String subscriptionsUrl) {
            this.subscriptionsUrl = subscriptionsUrl;
        }

        public String getOrganizationsUrl() {
            return organizationsUrl;
        }

        public void setOrganizationsUrl(String organizationsUrl) {
            this.organizationsUrl = organizationsUrl;
        }

        public String getReposUrl() {
            return reposUrl;
        }

        public void setReposUrl(String reposUrl) {
            this.reposUrl = reposUrl;
        }

        public String getEventsUrl() {
            return eventsUrl;
        }

        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }

        public String getReceivedEventsUrl() {
            return receivedEventsUrl;
        }

        public void setReceivedEventsUrl(String receivedEventsUrl) {
            this.receivedEventsUrl = receivedEventsUrl;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Boolean getSiteAdmin() {
            return siteAdmin;
        }

        public void setSiteAdmin(Boolean siteAdmin) {
            this.siteAdmin = siteAdmin;
        }

    }


    public static class Week {

        @SerializedName("w")
        @Expose
        private String w;
        @SerializedName("a")
        @Expose
        private Integer a;
        @SerializedName("d")
        @Expose
        private Integer d;
        @SerializedName("c")
        @Expose
        private Integer c;

        public String getW() {
            return w;
        }

        public void setW(String w) {
            this.w = w;
        }

        public Integer getA() {
            return a;
        }

        public void setA(Integer a) {
            this.a = a;
        }

        public Integer getD() {
            return d;
        }

        public void setD(Integer d) {
            this.d = d;
        }

        public Integer getC() {
            return c;
        }

        public void setC(Integer c) {
            this.c = c;
        }


    }
}