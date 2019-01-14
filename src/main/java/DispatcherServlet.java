import annotations.Controller;
import annotations.RequestMapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/*", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

    private Map<String, Method> uriMappings = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("Getting request for " + req.getRequestURI());
        // TODO (instancier le controller)
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // on enregistre notre controller au démarrage de la servlet
        this.registerController(HelloController.class);
    }

    protected void registerController(Class controllerClass) {
        System.out.println("Analysing class " + controllerClass.getName());
        if (controllerClass.getDeclaredAnnotation(Controller.class) != null) {
            Arrays.stream(controllerClass.getMethods()).forEach(method -> {
                if (method.getDeclaredAnnotation(RequestMapping.class) != null && method.getReturnType() != void.class) {
                    registerMethod(method);
                }
            });
        } else {
            throw new IllegalArgumentException();
        }
    }

    protected void registerMethod(Method method) {
        System.out.println("Registering method " + method.getName());
        var path = method.getDeclaredAnnotation(RequestMapping.class).uri();
        this.uriMappings.put(path, method);
    }

    protected Map<String, Method> getMappings() {
        return this.uriMappings;
    }

    protected Method getMappingForUri(String uri) {
        return this.uriMappings.get(uri);
    }
}