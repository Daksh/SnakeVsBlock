/**
 * Singleton Design Pattern
 */
public class LeadersBoard {

    private static LeadersBoard board = new LeadersBoard();

    private LeadersBoard(){

    }

    public static LeadersBoard getInstance(){
        if(board == null)
            board = new LeadersBoard();
        return board;
    }

    public static void loadData() {
        LeadCtrl.data.add(new User("Arsh", 1243));
        LeadCtrl.data.add(new User("Arsh", 1220));
        LeadCtrl.data.add(new User("Arsh", 1203));
        LeadCtrl.data.add(new User("Arsh", 1197));
        LeadCtrl.data.add(new User("Arsh", 1170));
        LeadCtrl.data.add(new User("Arsh", 1146));
        LeadCtrl.data.add(new User("Daksh", 1123));
        LeadCtrl.data.add(new User("Arsh", 1102));
        LeadCtrl.data.add(new User("Daksh", 1101));
        LeadCtrl.data.add(new User("Arsh", 1094));
        LeadCtrl.data.add(new User("Arsh", 1090));
        LeadCtrl.data.add(new User("Arsh", 1090));
        LeadCtrl.data.add(new User("Arsh", 1056));
        LeadCtrl.data.add(new User("Arsh", 1033));
        LeadCtrl.data.add(new User("Daksh", 1011));
        LeadCtrl.data.add(new User("Arsh", 1002));
        LeadCtrl.data.add(new User("Arsh", 1001));
        LeadCtrl.data.add(new User("Arsh", 994));
        LeadCtrl.data.add(new User("Daksh", 983));
        LeadCtrl.data.add(new User("Arsh", 945));
    }

}
