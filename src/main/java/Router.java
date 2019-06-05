import controllers.RecomendationController;
import controllers.UsersController;
import spark.servlet.SparkApplication;

import static spark.Spark.*;

public class Router implements SparkApplication {

    private UsersController usersController = new UsersController();
    private RecomendationController recomendationController = new RecomendationController();

    @Override
    public void init() {
        port(8080);

        routes();
    }

    private void routes(){
        get("/", (req, res) -> "Hello, World!");

        path("/users", () -> {
            post("",(req, res) -> usersController.create(req,res));
            post("/login", (req,res) -> usersController.login(req,res));
            //get("/:id", usersController.get);
        });

        get("/recomendations", (req, res) -> recomendationController.find(req,res));
    }
}
