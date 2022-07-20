
package conn;

public class folder {
    
    private String id;
    private byte[] file;


    public folder() {
        super();
    }

    public folder(byte[] file,String id) {
        super();
        this.file = file;

    }

//    public String getId() {
//        return id;
//
//    }
//
//    public void setId(String id) {
//        this.id = id;
//
//    }

    public byte[] getFile() {
        return file;

    }

    public void setFile(byte[] file) {
        this.file = file;

    }

}
