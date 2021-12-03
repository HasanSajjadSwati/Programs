
package net.hasansajjadswati;


public class Greeting {
    
    private int id = 0;
    private String content;

    public Greeting() {
    }
   
    public Greeting(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
}
