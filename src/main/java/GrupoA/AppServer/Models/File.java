package GrupoA.AppServer.Models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class File extends INode {
    public byte[] Data; //This gets encoded as Base64

}
