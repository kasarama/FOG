package PresentationLayer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author Magdalena
 */
public class OrderCarport extends Command{
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("payMSG", "nothing happened- it's not ready jet!");
        return "customerPage";
    }
}
