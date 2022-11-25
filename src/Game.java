public class Game {
    public static void main(String[] args) {
        ConwayModel model = new ConwayModel(10);
        ConwayView view = new ConwayView(model);
        ConwayController controller = new ConwayController(model, view);
    }
}
