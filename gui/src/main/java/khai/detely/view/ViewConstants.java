package khai.detely.view;

public class ViewConstants {

    private static final String VIEWS = "/views/";

    public enum ViewPageName {
        CREATE_DIALOG(VIEWS + "createProject.fxml"),
        FILL_CELL(VIEWS + "fillCell.fxml"),
        CHART(VIEWS + "chart.fxml"),
        START_PAGE(VIEWS + "startPage.fxml"),
        MAIN(VIEWS + "main.fxml"),
        ;
        String name;

        ViewPageName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
