package com.github.bean;

import java.util.List;

/*
 * Created by pmathur on 6/2/17.
 */
public class ResponseDTO {

    private GitRepoBean gitRepoBeen ;
    private List<GitCommitter> gitCommitters;

    public GitRepoBean getGitRepoBeen() {
        return gitRepoBeen;
    }

    public void setGitRepoBeen(GitRepoBean gitRepoBeen) {
        this.gitRepoBeen = gitRepoBeen;
    }

    public List<GitCommitter> getGitCommitters() {
        return gitCommitters;
    }

    public void setGitCommitters(List<GitCommitter> gitCommitters) {
        this.gitCommitters = gitCommitters;
    }
}
