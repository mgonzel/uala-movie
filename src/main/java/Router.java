import controllers.UsersController;
import spark.servlet.SparkApplication;

import static spark.Spark.*;

public class Router implements SparkApplication {

    private UsersController usersController = new UsersController();

    @Override
    public void init() {
        port(8080);

        routes();
    }

    private void routes(){
        get("/", (req, res) -> "Hello, World!");

        path("/users", () -> {
            post("",(req, res) -> usersController.create(req,res));
            //post("/login", usersController.login);
            //get("/:id", usersController.get);

        });
    }
}
