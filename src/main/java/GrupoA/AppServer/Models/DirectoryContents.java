package GrupoA.AppServer.Models;

import GrupoA.StorageController.gRPCService.FileSystem.DirContents;
import GrupoA.StorageController.gRPCService.FileSystem.FileType;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;

@XmlRootElement
public class DirectoryContents {
    @XmlRootElement
    public static class DirectoryContent {
        public FileType FileType;
        public long INode;
        public String Name;
        public DirectoryContent(FileType filetype, long INode, String Name) {
            this.FileType = filetype;
            this.INode = INode;
            this.Name = Name;
        }
        public DirectoryContent() {

        }
    }

    public long ParentINode;
    public long INode;
    public LinkedList<DirectoryContent> Contents = new LinkedList<>();
    public boolean Valid = false;

    public void AddContent(DirectoryContent cont) {
        this.Contents.add(cont);
    }

    public static DirectoryContents fromDirContents(DirContents dirConts) {
        DirectoryContents contents = new DirectoryContents();
        if (!dirConts.getSuccess())
            return contents;

        contents.Valid = true;
        contents.ParentINode = dirConts.getParentINode();
        contents.INode = dirConts.getINode();
        for(DirContents.Content content : dirConts.getContentsList()){
            contents.AddContent(new DirectoryContent(content.getContentFileType(),
                    content.getINode(),
                    content.getContentName()));
        }

        return contents;
    }
}
