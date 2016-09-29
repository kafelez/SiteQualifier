/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.marfeel.qualify.async;

import javax.servlet.http.HttpServletRequest;
import org.springframework.util.concurrent.ListenableFuture;

public interface AsyncRequestHandlerService {

    ListenableFuture<String> handle(HttpServletRequest request);
}
