package best_layer;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public interface Objectifier {

    static String setDefaultPathToFile(String filename){
        Path def = Paths.get("rent_data_.txt");
        Path file = Paths.get(filename);
        String correctPath;
        File fool = file.toFile();

        if(!fool.exists()){
          correctPath = def.toAbsolutePath().toString();
        }else{
            correctPath = fool.getAbsolutePath();
        }

        return correctPath;

    }
    void ImportData(String filePath);

    Object objectify(List<Object> list);




}
