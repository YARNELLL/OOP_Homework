package Homework5.part1;

import java.util.*;

public class Antivirus {
    public static void main(String[] args){
        File_class root_folder = new File_class("根文件夹", "folder");
        File_class image_folder = new File_class("图片文件夹", "folder");
        File_class image = new File_class("照片","jpg");
        File_class screenshot = new File_class("截图","gif");
        File_class video_folder = new File_class("视频文件夹", "folder");
        File_class screen_recording = new File_class("录屏","mp4");
        File_class audio_folder = new File_class("音频文件夹","folder");
        File_class recording = new File_class("录音","mp3");

        root_folder.add(image_folder);
        root_folder.add(video_folder);

        image_folder.add(image);
        image_folder.add(screenshot);

        video_folder.add(screen_recording);
        video_folder.add(audio_folder);

        audio_folder.add(recording);

        System.out.println("对整个文件系统扫描病毒");
        root_folder.detect_virus();
        System.out.println("对视频文件夹里面所有文件扫描病毒");
        video_folder.detect_virus();
    }
}

class File_class{
    private String name;
    private String file_type;
    private int deep;
    private List<File_class> file_list;

    public File_class(String name, String file_type) {
        this.name = name;
        this.file_type = file_type;
        this.deep = 0;
        this.file_list = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeep() {
        return deep;
    }

    public void setDeep(int deep) {
        this.deep = deep;
    }

    public void add(File_class f){
        file_list.add(f);
        f.setDeep((this.deep + 1));
    }

    public void remove(File_class f){
        file_list.remove(f);
    }

    public String toString(){
        return ("File :[ FileName : " + this.name + ", FileType : " + this.file_type +
                ", FileDepth : " + this.deep + "]");
    }
    public void detect_virus(){
        System.out.println("正在杀毒" + this + ".");
        for(File_class file : file_list){
            file.detect_virus();
        }
    }
}
