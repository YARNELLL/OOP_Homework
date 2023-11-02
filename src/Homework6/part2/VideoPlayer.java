package Homework6.part2;

public class VideoPlayer {
    public static void main(String[] args){
        Video_player videoPlayer = new Video_player();
        Complete_model completeModel = new Complete_model(videoPlayer);
        completeModel.create_player();
        System.out.println("----------------");
        Streamlining_mode streamliningMode = new Streamlining_mode(videoPlayer);
        streamliningMode.create_player();
        System.out.println("----------------");
        Memory_mode memoryMode = new Memory_mode(videoPlayer);
        memoryMode.create_player();
    }
}

interface Play_interface{
    void create_menu();
    void create_playlist();
    void create_main_window();
    void create_control_bar();
    void create_favorites_list();
}

class Video_player implements Play_interface{

    @Override
    public void create_menu() {
        System.out.println("显示菜单。");
    }

    @Override
    public void create_playlist() {
        System.out.println("显示播放列表。");
    }

    @Override
    public void create_main_window() {
        System.out.println("显示主窗口。");
    }

    @Override
    public void create_control_bar() {
        System.out.println("显示控制条。");
    }

    @Override
    public void create_favorites_list() {
        System.out.println("显示收藏列表。");
    }
}

class Complete_model{
    private final Play_interface play_interface;

    public Complete_model(Play_interface play_interface) {
        this.play_interface = play_interface;
    }
    public void create_player(){
        System.out.println("完整模式：");
        play_interface.create_menu();
        play_interface.create_playlist();
        play_interface.create_main_window();
        play_interface.create_control_bar();
    }
}

class Streamlining_mode{
    private final Play_interface play_interface;

    public Streamlining_mode(Play_interface play_interface) {
        this.play_interface = play_interface;
    }
    public void create_player(){
        System.out.println("精简模式：");
        play_interface.create_menu();
        play_interface.create_control_bar();
    }
}

class Memory_mode{
    private final Play_interface play_interface;

    public Memory_mode(Play_interface play_interface) {
        this.play_interface = play_interface;
    }

    public void create_player(){
        System.out.println("记忆模式：");
        play_interface.create_menu();
        play_interface.create_control_bar();
        play_interface.create_favorites_list();
    }
}
