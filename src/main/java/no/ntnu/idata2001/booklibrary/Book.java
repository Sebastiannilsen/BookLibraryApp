package no.ntnu.idata2001.booklibrary;

/**
 * Write a description of class no.ntnu.idata2001.booklibrary.Book here.
 *
 * @Sebastian_Nilsen 
 * @version 0.1
 */
public class Book {
    private String title;
    private String author;
    private String publisher;
    private int yearPublished;
    private int pages;
    private String barcode;
    public boolean available;
    
    public Book(String bookTitle, String bookAuthor, String bookPublisher, int bookYearPublished, int bookPages,
    String bookBarcode){        
        title = bookTitle;
        author = bookAuthor;
        publisher = bookPublisher;
        yearPublished = bookYearPublished;
        pages = bookPages;
        barcode = bookBarcode; 
        available = true;
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getAuthor(){
        return author;
    }
    
    public int getPages(){
        return pages;
    }
    
    public String getPublisher(){
        return publisher;
    }
    
    public String getBarcode(){
        return barcode;
    }
    
    public int getYearPublished(){
        return yearPublished;
    }
    
    public boolean getStatus(){
        return available;
    }
    
    public void setBorrowed(){
        available = false;
    }
    
    public void setAvailable(){
        available = true;
    }
}


