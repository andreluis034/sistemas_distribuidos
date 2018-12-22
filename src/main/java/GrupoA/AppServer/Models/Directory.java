package GrupoA.AppServer.Models;

import java.util.List;

public class Directory extends INode {
    public List<File> Files;
    public List<Directory> Directories;
}
