package ru.job4j.servlets.http.servlet;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.servlets.http.servlet.models.User;
import ru.job4j.servlets.http.validate.Validate;
import ru.job4j.servlets.http.validate.ValidateService;
import ru.job4j.servlets.http.validate.ValidateStub;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserCreateServletTest {

    @Test
    public void whenAddUserThenStoreIt() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn("Igor");
        new UserCreateServlet().doPost(req, resp);
        Assert.assertThat(validate.findAll().iterator().next().getName(), is("Igor"));
    }

    @Test
    public void whenUpdateUserThenUpdateItInStore() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        validate.add(new User("name", "", "", ""));
        when(req.getParameter("id")).thenReturn("1");
        when(req.getParameter("name")).thenReturn("Igor");
        new UserUpdateServlet().doPost(req, resp);
        Assert.assertThat(validate.findAll().iterator().next().getName(), is("Igor"));
    }

    @Test
    public void whenDeleteOneUserOfTwoThenStoreOneUserOnly() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        validate.add(new User("name1", "", "", ""));
        validate.add(new User("name2", "", "", ""));
        when(req.getParameter("id")).thenReturn("1");
        new UserServlet().doPost(req, resp);
        Assert.assertThat(validate.findAll().iterator().next().getName(), is("name2"));
    }
}