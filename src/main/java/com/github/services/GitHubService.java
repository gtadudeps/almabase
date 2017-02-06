package com.github.services;

import com.github.bean.GitCommitBean;
import com.github.bean.GitCommitter;
import com.github.bean.GitRepoBean;
import com.github.bean.ResponseDTO;

import java.util.List;

/*
 * Created by pmathur on 2/2/17.
 */
public interface GitHubService {

    List<ResponseDTO> getTopRepoAndCommitters(String org);
    List<GitRepoBean> getTopRepo(String org);
    List<GitCommitter>  getTopCommittees(GitRepoBean gitRepoBean, int n);
}
