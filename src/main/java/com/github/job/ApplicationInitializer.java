package com.github.job;

import com.github.bean.GitCommitter;
import com.github.bean.ResponseDTO;
import com.github.services.GitHubService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/*
 * Created by pmathur on 6/2/17.
 */
public class ApplicationInitializer {

    // Organisation to fetch the data for example
    public static final String ORGANISATION = "google";

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring*.xml");
        GitHubService bean = context.getBean(GitHubService.class);

        List<ResponseDTO> responseDTOs = bean.getTopRepoAndCommitters(ORGANISATION);
        for (ResponseDTO responseDTO : responseDTOs) {
            System.out.println(" --- Repo Name -- ");
            System.out.println(responseDTO.getGitRepoBeen().getName());
            System.out.println("---- Committers ----");
            for (GitCommitter dto : responseDTO.getGitCommitters()) {
                System.out.print(dto.getName());
                System.out.println("  commit count: " + dto.getCommits());
            }
        }
    }
}
