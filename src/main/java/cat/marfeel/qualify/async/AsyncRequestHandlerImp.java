package cat.marfeel.qualify.async;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

@Service
class AsyncRequestHandlerImp implements AsyncRequestHandlerService {

    @Autowired
    private AsyncRestTemplate asyncRestTemplate;

    /*
     @Override
     public ListenableFuture<RepoListDto> search(String query) {
     ListenableFuture<ResponseEntity<GitHubItems>> gitHubItems = asyncRestTemplate.getForEntity(QUESTIONS_URL, GitHubItems.class, query);
     return new RepositoryListDtoAdapter(query, gitHubItems);
     }
     */
    @Override
    public ListenableFuture<String> handle(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
