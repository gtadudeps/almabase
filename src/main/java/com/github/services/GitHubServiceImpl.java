package com.github.services;


import com.github.bean.GitCommitBean;
import com.github.bean.GitCommitter;
import com.github.bean.GitRepoBean;
import com.github.bean.ResponseDTO;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;

/*
 * Created by pmathur on 4/2/17.
 */
@Service
public class GitHubServiceImpl implements GitHubService {

    private static final String REPO_URL = "https://api.github.com/orgs/";
    private static final String COMMIT_URL = "https://api.github.com/repos/";

    public static final String APPLICATION_JSON = "application/json";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CLIENT_ID = "02b9dc6475db0ddfba6b";
    public static final String CLIENT_SECRET = "2523d693282519886a4a7b98e4e15fd694cabf28";


    public static final Gson GSON_INSTANCE = new GsonBuilder().serializeSpecialFloatingPointValues().create();
    private static final long SLEEP_TIME = 300000L;
    private static final int NUM_COMMITTIES = 3;

    public static void main(String[] args) throws InterruptedException {

      //  GitHubServiceImpl.getTopRepo("google");
      //  List<GitCommitter> topCommittees = GitHubServiceImpl.getTopCommittees(null, 3);
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring*.xml");
        GitHubService bean = context.getBean(GitHubService.class);

     //   bean.getTopCommittees(null,3);
//        // wikipediaService.startFetch();
        bean.getTopRepoAndCommitters("google");
        // GitHubServiceImpl.bomb("abcdeeeeddcbfgf");
    }


    @Override
    public List<ResponseDTO> getTopRepoAndCommitters(String org) {

        List<ResponseDTO> rv = Lists.newArrayList();
        List<GitRepoBean> topRepo = getTopRepo(org);
        for (GitRepoBean gitRepoBean : topRepo) {
            ResponseDTO responseDTO = new ResponseDTO();
            System.out.println("--- Fetching Top Committees For Organisation: " + gitRepoBean.getName() + "--- ");
            List<GitCommitter> topCommittees = getTopCommittees(gitRepoBean, NUM_COMMITTIES);
            responseDTO.setGitRepoBeen(gitRepoBean);
            responseDTO.setGitCommitters(topCommittees);
            rv.add(responseDTO);
        }

        return rv;

    }

    @Override
    public  List<GitRepoBean> getTopRepo(String org) {

        String responseData;
        boolean paginate = false;
        HttpURLConnection connection;
        List<GitRepoBean> repos = com.google.common.collect.Lists.newArrayList();
        int page = 1;
        int resposeCode = 0;
        boolean retry =false;

        do {
            try {

                retry =false;
                paginate =false;
                System.out.println("---- Finding Top Repos for Org ---- "+ org + "--- Page Number --- " + page);
                String jobUrl = REPO_URL + org + "/repos?page=" +
                        page+"&per_page=100&client_id="+CLIENT_ID+"&client_secret="+CLIENT_SECRET;
                URL url = new URL(jobUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty(CONTENT_TYPE, APPLICATION_JSON);
                resposeCode = connection.getResponseCode();
                InputStream responseStream = connection.getInputStream();
                String charset = "UTF-8";
                responseData = IOUtils.toString(responseStream, charset);
                List<GitRepoBean> gitRepoBeanList = GSON_INSTANCE.fromJson(responseData, GitRepoBean.GIT_BEAN_LIST);
                repos.addAll(gitRepoBeanList);
                if (CollectionUtils.isNotEmpty(gitRepoBeanList)) {
                    paginate = true;
                }
                page++;
            } catch (Exception e) {
                if (resposeCode == 403) {
                    try {
                        Thread.sleep(SLEEP_TIME);
                        retry = true;
                    } catch (InterruptedException e1) {
                        // Log in Logger
                    }
                }
            }

        } while (paginate || retry);


        Collections.sort(repos);

        if(repos.size() >5){
            repos = repos.subList(0,5);
        }

        return repos;
    }

    @Override
    public  List<GitCommitter> getTopCommittees(GitRepoBean gitRepoBean, int n)  {

        List<GitCommitBean> commits = Lists.newArrayList();
        HttpURLConnection connection;
        int resposeCode = 0;
        boolean retry = false;
        do {
            try {

                retry = false;
                String jobUrl = COMMIT_URL + gitRepoBean.getOwner().getLogin() + "/"+gitRepoBean.getName()
                        +"/stats/contributors?client_id="+CLIENT_ID+"&client_secret="+CLIENT_SECRET;
                URL url = new URL(jobUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty(CONTENT_TYPE, APPLICATION_JSON);
                resposeCode = connection.getResponseCode();
                InputStream responseStream = connection.getInputStream();
                String charset = "UTF-8";
                String responseData = IOUtils.toString(responseStream, charset);
                List<GitCommitBean> commitList = GSON_INSTANCE.fromJson(responseData, GitCommitBean
                        .GIT_COMMIT_BEAN_LIST);
                commits.addAll(commitList);


            }catch (Exception ex){
                if (resposeCode == 403) {
                    try {
                        Thread.sleep(SLEEP_TIME);
                        retry = true;
                    } catch (InterruptedException e) {
                        // Log in logger
                    }
                }
            }
        } while (retry);

        Collections.sort(commits);

        if(commits.size() > n){
            commits = commits.subList(0,n);
        }

        List<GitCommitter> gitCommitters = Lists.newArrayList();
        for (GitCommitBean commit : commits) {
            GitCommitter gitCommitter = new GitCommitter();
            gitCommitter.setName(commit.getAuthor().getLogin());
            gitCommitter.setCommits(commit.getTotal());
            gitCommitters.add(gitCommitter);
        }

        return gitCommitters;
    }

}
