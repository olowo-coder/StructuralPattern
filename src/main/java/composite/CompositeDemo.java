package composite;

public class CompositeDemo {
    public static void main(String[] args) {

        Menu mainMenu = new Menu("Main", "/main");

        MenuItem safetyMenuItem = new MenuItem("Safety", "/safety");

        mainMenu.add(safetyMenuItem);

        Menu claimsSubMenu = new Menu("Claims", "/claims");

        mainMenu.add(claimsSubMenu);

        MenuItem personalClaimsMenu = new MenuItem("Personal", "/personal");

        claimsSubMenu.add(personalClaimsMenu);


        System.out.println(mainMenu.toString());



    }
}
