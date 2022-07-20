
package conn;


public class status {
     private String number,comment;
     
   public status() {
        super();
    }

    public status(String number, String comment) {
        super();
        this.number = number;
        this.comment = comment;
       
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
}
