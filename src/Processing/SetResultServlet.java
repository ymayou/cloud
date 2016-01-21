package Processing;

import Model.PersonalData;
import com.google.appengine.api.datastore.KeyFactory;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by You on 21/01/2016.
 */
public class SetResultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cmd = request.getParameter("cmd");

        switch (cmd) {
            case "insert":
                Cookie[] cook = request.getCookies();
                Cookie cookie = null;
                for (int tmp = 0; tmp < cook.length; tmp++)
                {
                    if(cook[tmp].getName().equals("email_user"))
                        cookie = cook[tmp];
                }
                PersonalData perso = new PersonalData();
                perso.setId(cookie.getValue());
                perso.setTraining(KeyFactory.stringToKey(request.getParameter(PersonalData.TRAINING_LABEL)));
                Date now = new Date();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                perso.setDate(format.format(now));
                break;
            case "update":
                break;
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}