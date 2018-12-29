package GrupoA.FuseSupport;

import GrupoA.AppServer.Models.*;
import GrupoA.StorageController.gRPCService.FileSystem.RedundancyProto;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

public class RestClient {

    private String restBaseUri = "";
    private Client client;
    public RestClient(String address) {
        ClientConfig config = new ClientConfig(NodeAttributes.class);
        config.register(JacksonJsonProvider.class);
        client = ClientBuilder.newClient(config);
        this.restBaseUri = "http://" + address + "/";
    }

    public NodeAttributes getAttribute(String path) {
        try {
            NodeAttributes attr  = client.target(restBaseUri).path("attribute").path(path)
                    .request(MediaType.APPLICATION_JSON)
                    .get(NodeAttributes.class);
            return attr;

        } catch (NotFoundException ex) {
            return null;
        }
    }

    public NodeAttributes getAttribute(long iNode) {
        try {
            System.out.println(restBaseUri + "inode/" + iNode + "/attr");
            NodeAttributes attr  = client.target(restBaseUri).path("inode").path(Long.toString(iNode)).path("attr")
            .request(MediaType.APPLICATION_JSON)
            .get(NodeAttributes.class);
            return attr;

        } catch (NotFoundException ex) {
            return null;
        }
    }

    public boolean updateAttribute(String path, long uidValue, long gidValue) {

        AttributeUpdateRequest aur = new AttributeUpdateRequest();
        aur.Value = uidValue;
        aur.Type = AttributeUpdateRequest.UpdateType.CHUID;
        if (client.target(restBaseUri).path("attribute").path(path)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(aur, MediaType.APPLICATION_JSON), Boolean.class)) {

            aur = new AttributeUpdateRequest();
            aur.Value = gidValue;
            aur.Type = AttributeUpdateRequest.UpdateType.CHGID;

            return client.target(restBaseUri).path("attribute").path(path)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(aur, MediaType.APPLICATION_JSON), Boolean.class);

        } else {
            return false;
        }
    }

    public boolean updateAttribute(String path, AttributeUpdateRequest.UpdateType type, long value) {

        AttributeUpdateRequest aur = new AttributeUpdateRequest();
        aur.Value = value;
        aur.Type = type;
        return client.target(restBaseUri).path("attribute").path(path)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(aur, MediaType.APPLICATION_JSON), Boolean.class);
    }

    public boolean mkDir(String path, long mode, long uid, long gid, long permissions) {
        CreateRequest cdr = new CreateRequest();

        cdr.type = CreateRequest.CreateRequestType.DIR;
        cdr.Path = path;
        cdr.mode = mode;
        cdr.uid = uid;
        cdr.gid = gid;
        cdr.creationTime = System.currentTimeMillis();
        cdr.permission = permissions;
        try {
            return client.target(restBaseUri).path("directory")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(cdr, MediaType.APPLICATION_JSON), Boolean.class);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public DirectoryContents readDir(String path) {
        return client.target(restBaseUri).path("directory").path(path)
                .request(MediaType.APPLICATION_JSON)
                .get(DirectoryContents.class);
    }

    public Integer removeDir(String path) {
        try{
            return client.target(restBaseUri).path("directory").path(path)
                    .request(MediaType.APPLICATION_JSON)
                    .delete(Integer.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return -2;
    }

    public Boolean createFile(String path, long mode, long uid, long gid, long permissions){ //TODO set creation time
        CreateRequest cfr = new CreateRequest();

        cfr.type = CreateRequest.CreateRequestType.FILE;
        cfr.Path = path;
        cfr.mode = mode;
        cfr.uid = uid;
        cfr.gid = gid;
        cfr.permission = permissions;
        cfr.redundancyProto = RedundancyProto.Replication;
        cfr.creationTime = System.currentTimeMillis();
        return client.target(restBaseUri).path("file")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(cfr, MediaType.APPLICATION_JSON), Boolean.class);
    }

    public int write(String path, WriteRequest writeRequest) {
        return client.target(restBaseUri).path("file")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(writeRequest, MediaType.APPLICATION_JSON), Integer.class);
    }
}
