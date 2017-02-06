package com.github.bean;

/*
 * Created by pmathur on 4/2/17.
 */

@SuppressWarnings({"unused"})
public class GitCommitter implements Comparable {

    public static final String NAME = "name";
    public static final String COMMITS = "commits";

    private String name;
    private Integer commits;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCommits() {
        return commits;
    }

    public void setCommits(Integer commits) {
        this.commits = commits;
    }


    public int compareTo(Object o) {
        GitCommitter gitCommitter = (GitCommitter) o;
        if (this.getCommits() == null) {
            return 1;
        } else if (gitCommitter.getCommits() == null) {
            return -1;
        } else {
            return gitCommitter.getCommits().compareTo(this.getCommits());
        }
    }
}
