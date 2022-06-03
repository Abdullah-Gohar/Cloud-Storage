import java.io.File;

public class CreateDir {
    private String path;
    public CreateDir(String p){
        this.path = p;
    }

    public void create(){
        new File(path).mkdir();
    }
    
}
