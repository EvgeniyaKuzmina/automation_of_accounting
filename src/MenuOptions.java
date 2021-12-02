
// содержит варианты для выбора пункта меню
public enum MenuOptions {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    ZERO("0");

    private final String input;

    MenuOptions (String input) {
        this.input = input;
    }

    public String getNumber() {
        return input;
    }

}
