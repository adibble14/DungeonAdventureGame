/**
 * This class creates a random dungeon. The rooms and paths are generated
 * using DFS. Must be given a width and a length.
 */



public class GenerateDungeon {

    public static void main(String[] args) {
        int size = 15;
        Room[][] dung = new Room[size][size];
        int x = Tools.random.nextInt(0, size -1);
        int y = Tools.random.nextInt(0, size -1);
        Room entrance = new Room(x,y);
        entrance.setEntrance();
        dung [x][y] = entrance;
        System.out.println("Entrance Coords: " + x + " " + y);
        x = Tools.random.nextInt(0, 5);
        y = Tools.random.nextInt(0, 5);
        Room exit = new Room(x,y);
        exit.setExit();
        dung[x][y] = exit;
        System.out.println("Exit Coords: " + x + " " + y);
        Tools.dfs(dung, entrance);
        print(dung);

    }

    static void print(Room [][] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length; j++) {
                if(arr[i][j] == null)
                    System.out.print("* ");
                else {
                    arr[i][j].unhide();
                    System.out.print(arr[i][j] + " ");
                }

            }
            System.out.println();
        }
    }

}


