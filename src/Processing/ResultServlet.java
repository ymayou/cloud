package Processing;

import Dao.PersonalDataDao;
import Dao.TrainingDao;
import Model.PersonalData;
import Model.Training;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by You on 29/01/2016.
 */
public class ResultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cook = request.getCookies();
        Cookie cookie = null;
        if (cook != null)
        {
            for (int tmp = 0; tmp < cook.length; tmp++)
            {
                if(cook[tmp].getName().equals("email_user"))
                    cookie = cook[tmp];
            }
        }
        if (cookie == null)
        {
            request.setAttribute("error", "<div class='alert alert-danger' role='alert'><span class='glyphicon glyphicon-exclamation-sign' aria-hidden='true'></span><span class='sr-only'>Error:</span>You must be logged to access to your results</div>");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/results.jsp");
            rd.forward(request, response);
        }
        else
        {
            PersonalDataDao dao = new PersonalDataDao();
            TrainingDao tdao = new TrainingDao();
            String result = "";
            List<PersonalData> list  = dao.selectById(cookie.getValue());
            for (PersonalData p : list)
            {
                Training train = tdao.select(p.getTrainingKey());
                result += "<tr><td>" + p.getDate() + "</td><td>" + train.getTitle() + "</td><td>" + train.getDuration() + "</td><td>" + p.getTime() + "</td><td>" + (p.isStatus() ? "Yes" : "No") + "</td></tr>";
            }
            request.setAttribute("result", result);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/results.jsp");
            rd.forward(request, response);
        }
    }
}
